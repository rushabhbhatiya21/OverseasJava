package com.example.overseas.repository;

import com.example.overseas.model.AppointmentDao;
import com.example.overseas.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentDao, Integer> {
    List<AppointmentDao> findAppointmentDaoByStudentId(UserDao student);

    List<AppointmentDao> findAppointmentDaoByConsultantId(UserDao consultant);

    Optional<AppointmentDao> findAppointmentDaoById(Integer appointmentId);
}
