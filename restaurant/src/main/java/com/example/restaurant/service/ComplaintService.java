package com.example.restaurant.service;

import com.example.restaurant.model.ComplaintDao;
import com.example.restaurant.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Page<ComplaintDao> findAllComplaintPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return complaintRepository.findAll(pageable);
    }

    public boolean existsComplaintBySubject(String subject) {
        return complaintRepository.existsComplaintDaoByComplaintSubject(subject);
    }

    public void saveComplaint(ComplaintDao complaint) {
        complaintRepository.save(complaint);
    }

    public Optional<ComplaintDao> findComplaintById(Long complaintId) {
        return complaintRepository.findById(complaintId);
    }
}
