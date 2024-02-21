package com.example.task_3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "compressionThreshold", value = "1024") // Set the threshold for compression in bytes
        }
)
public class CompressionFilter implements Filter {

    private int compressionThreshold;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String compressionThresholdParam = filterConfig.getInitParameter("compressionThreshold");
        compressionThreshold = (compressionThresholdParam != null) ? Integer.parseInt(compressionThresholdParam) : 1024;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        String acceptEncoding = req.getHeader("Accept-Encoding");
        if (acceptEncoding != null && acceptEncoding.contains("gzip")) {

            httpResponse.setHeader("Content-Encoding", "gzip");


            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(response.getOutputStream());

            chain.doFilter(request, new WrappedResponse(httpResponse, gzipOutputStream, compressionThreshold));


            gzipOutputStream.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }

    private static class WrappedResponse extends HttpServletResponseWrapper {

        private GZIPOutputStream gzipOutputStream;
        private int compressionThreshold;

        WrappedResponse(HttpServletResponse response, GZIPOutputStream gzipOutputStream, int compressionThreshold) {
            super(response);
            this.gzipOutputStream = gzipOutputStream;
            this.compressionThreshold = compressionThreshold;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new GzipServletOutputStream(gzipOutputStream, compressionThreshold, getResponse().getOutputStream());
        }
    }

    private static class GzipServletOutputStream extends ServletOutputStream {

        private GZIPOutputStream gzipOutputStream;
        private int compressionThreshold;
        private ServletOutputStream originalOutputStream;

        GzipServletOutputStream(GZIPOutputStream gzipOutputStream, int compressionThreshold, ServletOutputStream originalOutputStream) {
            this.gzipOutputStream = gzipOutputStream;
            this.compressionThreshold = compressionThreshold;
            this.originalOutputStream = originalOutputStream;
        }

        @Override
        public void write(int b) throws IOException {
            if (gzipOutputStream != null) {
                gzipOutputStream.write(b);
            } else {
                originalOutputStream.write(b);
            }
        }

        @Override
        public void write(byte[] b) throws IOException {
            if (gzipOutputStream != null) {
                writeBytes(b, 0, b.length);
            } else {
                originalOutputStream.write(b);
            }
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            if (gzipOutputStream != null) {
                writeBytes(b, off, len);
            } else {
                originalOutputStream.write(b, off, len);
            }
        }

        private void writeBytes(byte[] b, int off, int len) throws IOException {
            if (len >= compressionThreshold) {

                gzipOutputStream.write(b, off, len);
            } else {

                originalOutputStream.write(b, off, len);
                gzipOutputStream = null;
            }
        }


        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}
