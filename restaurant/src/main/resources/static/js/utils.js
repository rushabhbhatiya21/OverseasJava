// copyTableContent.js
function getTableContent() {
    let tableContent = '';

    // Iterate through each row in the table
    $('#mainTable tr').each(function () {
        // Iterate through each cell in the row
        $(this).find('td:not(:last-child)').each(function () {
            // Add the cell content to the tableContent string
            tableContent += $(this).text() + '\t';
        });

        // Add a newline character after each row
        tableContent += '\n';
    });

    return tableContent;
}

// function getHTMLString() {
//     let tableContent = '';
//     let headerContent = '';
//
//     // Extract headers from the first row (assuming they are in the first row)
//     $('#wholeTable thead th').each(function () {
//         headerContent += '<th>' + $(this).text() + '</th>';
//     });
//
//     // Iterate through each row in the table (excluding the header row)
//     $('#mainTable tr').each(function () {
//         let rowContent = '';
//
//         // Iterate through each cell in the row
//         $(this).find('td:not(:last-child)').each(function () {
//             // Add the cell content to the rowContent string
//             rowContent += '<td>' + $(this).text() + '</td>';
//         });
//
//         // Add a new row with the collected cell content
//         tableContent += '<tr>' + rowContent + '</tr>';
//     });
//
//     // Construct the HTML string with headers and tableContent
//     let htmlString = '<!DOCTYPE html>\n' +
//         '        <html lang="en">\n' +
//         '        <head>\n' +
//         '            <meta charset="UTF-8">\n' +
//         '            <meta name="viewport" content="width=device-width, initial-scale=1.0">\n' +
//         '            <title>Table Data</title>\n' +
//         '        </head>\n' +
//         '        <body><table class="table table-bordered table-striped"><thead class="thead-dark"><tr>' + headerContent + '</tr></thead><tbody>' + tableContent + '</tbody></table>' +
//         '        </body>' +
//         '        </html>';
//
//     return htmlString;
// }


function copyTableContent() {
    const tableContent = getTableContent();

    console.log(tableContent);

    // Use the Clipboard API to copy text to the clipboard
    navigator.clipboard.writeText(tableContent)
        .then(function () {
            // Show a success message (you can customize this part)
            toastr.success("Table content copied to clipboard");
        })
        .catch(function (err) {
            console.error('Unable to copy to clipboard', err);
            // Show an error message (you can customize this part)
            toastr.error("Error copying table content");
        });
}

function downloadCSV() {
    const csvContent = getTableContent();

    // Create a Blob containing the CSV content
    const blob = new Blob([csvContent], {type: 'text/csv;charset=utf-8;'});

    // Create a temporary anchor element
    const link = document.createElement('a');

    // Set the href attribute with the Blob URL
    link.href = window.URL.createObjectURL(blob);

    // Set the download attribute with the desired file name
    link.download = 'table_data.csv';

    // Append the link to the document
    document.body.appendChild(link);

    // Trigger a click on the link to start the download
    link.click();

    // Remove the temporary link from the document
    document.body.removeChild(link);
}

function downloadExcel() {
    // Get the HTML content of the table
    const tableElement = document.getElementById('wholeTable');

    // Clone the table element to avoid modifying the original HTML
    const clonedTable = tableElement.cloneNode(true);

    // Remove the last row (which contains buttons) from the cloned table
    clonedTable.querySelector('thead th:last-child').remove();

    // Remove the last td from every tr in the tbody
    const tbodyRows = clonedTable.querySelectorAll('tbody tr');
    tbodyRows.forEach(row => {
        const lastTd = row.querySelector('td:last-child');
        if (lastTd) {
            row.removeChild(lastTd);
        }
    });

    // Create a new HTML table element
    const excelTable = document.createElement('table');
    excelTable.innerHTML = clonedTable.innerHTML;

    // console.log(excelTable);

    // Create a Blob from the Excel data
    const blob = new Blob([excelTable.outerHTML], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8' });

    // Create a download link and trigger a click to download the Excel file
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = 'table_data.xlsx';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}


function printTable() {
    // Open a new window with a blank HTML page containing only the table content
    const printWindow = window.open('', '_blank');

// Write the table content to the new window
    printWindow.document.write('<html lang="en"><head><title>Print Table</title>');
    printWindow.document.write('<style>');
    printWindow.document.write('body { font-family: Arial, sans-serif; margin: 20px; }');
    printWindow.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }');
    printWindow.document.write('th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }');
    printWindow.document.write('th { background-color: #f2f2f2; }');
    printWindow.document.write('</style>');
    printWindow.document.write('</head><body>');

    printWindow.document.write('<table>');

    // Iterate through each row in the table
    $('#mainTable tr').each(function () {
        printWindow.document.write('<tr>');
        // Iterate through each cell in the row
        $(this).find('td:not(:last-child)').each(function () {
            // Add the cell content to the tableContent string
            printWindow.document.write('<td>' + $(this).text() + '</td>');
        });
        printWindow.document.write('</tr>');
    });

    printWindow.document.write('</table>');
    printWindow.document.write('</body></html>');
    printWindow.document.close();

    // Print the new window
    printWindow.print();
}

// Assuming you have jsPDF already loaded

function downloadPDF() {
    // Create a new jsPDF instance
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF('p', 'pt', 'a4');
    let y = 20;

    // Get the HTML content of the table
    const tableElement = document.getElementById('wholeTable');

    // Clone the table element to avoid modifying the original HTML
    const clonedTable = tableElement.cloneNode(true);

    // Remove the last row (which contains buttons) from the cloned table
    clonedTable.querySelector('thead th:last-child').remove();

    // Add title to the PDF
    doc.setLineWidth(2);
    doc.text(200, y = y + 30, "Data Table");

    // Generate the PDF using autoTable
    doc.autoTable({
        html: clonedTable,
        startY: 100,
        theme: 'grid',
        columnStyles: {
            0: {
                // border: '1px solid black',
                halign: 'right',
                tableWidth: 100,
            },
            1: {
                tableWidth: 100,
            },
            2: {
                tableWidth: 100,
            },
            3: {
                tableWidth: 100,
            }
        },
    });

    // Save the PDF
    doc.save('table_data');
}
