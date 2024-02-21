package com.example.overseas.controller;

import com.example.overseas.model.AppointmentDao;
import com.example.overseas.model.UserDao;
import com.example.overseas.repository.AppointmentRepository;
import com.example.overseas.repository.UserRepository;
import com.example.overseas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    private final UserService service;

    @Autowired
    public StudentController(UserRepository userRepository, AppointmentRepository appointmentRepository, UserService service) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.service = service;
    }

    @GetMapping("/loadStudentPage")
    public String loadStudent() {
        return "studentIndex";
    }

    @GetMapping("/loadStudentData")
    public String loadStudentData(Model model) {
        List<UserDao> users = userRepository.findAll();
        List<UserDao> availableConsultants = new ArrayList<>();

        Optional<UserDao> loggedInUser = userRepository.findUserDaoByUsername(service.getLoggedInUserDetails().getUsername());

        for (UserDao user : users) {
            if (user.getRole().equals(UserDao.Role.CONSULTANT)) {
                availableConsultants.add(user);
                System.out.println("Consultant: " + user.getUsername());
            }
        }

        if (loggedInUser.isPresent()) {
            List<AppointmentDao> appointments = appointmentRepository.findAppointmentDaoByStudentId(loggedInUser.get());
            if (!appointments.isEmpty()) {
//                System.out.println("appointments not empty");
                model.addAttribute("appointments", appointments);
            }
//            else {
//                for (AppointmentDao appointment : appointments) {
//                    System.out.println("cid: " + appointment.getConsultantId().getId() + "\n" + "side: " + appointment.getStudentId().getId());
//                }
//            }

            model.addAttribute("users", availableConsultants);
        }

        return "studentDashboard";
    }
    @PostMapping("/bookAppointment")
    public String createAppointment(
            @RequestParam String consultantId,
            @RequestParam String description,
            @RequestParam String startTime,
            @RequestParam String endTime,
            Model model
            ) {
        int studentId = userRepository.findUserDaoByUsername(service.getLoggedInUserDetails().getUsername()).get().getId();
        AppointmentDao newAppointment = new AppointmentDao();
        UserDao student = new UserDao();
        student.setId(studentId);

        UserDao consultant = new UserDao();
        consultant.setId(Integer.parseInt(consultantId));

        newAppointment.setStatus(AppointmentDao.Status.PENDING);
        newAppointment.setConsultantId(consultant);
        newAppointment.setStudentId(student);
        newAppointment.setDescription(description);
        newAppointment.setStartTime(startTime);
        newAppointment.setEndTime(endTime);
        newAppointment.setCreatedOn(new Date());

//        System.out.println("sid: " + newAppointment.getStudentId());
//        System.out.println("cid: " + newAppointment.getConsultantId());

        appointmentRepository.save(newAppointment);

        return "redirect:/student/loadStudentData";
    }
}
