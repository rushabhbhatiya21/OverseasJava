package com.example.restaurant.repository;

import com.example.restaurant.model.ComplaintDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<ComplaintDao, Long> {
    boolean existsComplaintDaoByComplaintSubject(String complaintSubject);
}
