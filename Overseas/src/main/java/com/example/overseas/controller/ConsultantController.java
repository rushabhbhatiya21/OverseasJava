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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserService service;

    @Autowired
    public ConsultantController(UserRepository userRepository, AppointmentRepository appointmentRepository, UserService service) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.service = service;
    }

    @GetMapping("/loadConsultantPage")
    public String loadConsultantPage() {
        return "consultantIndex";
    }

    @GetMapping("/loadConsultantData")
    public String loadConsultantData(Model model) {

        Optional<UserDao> loggedInUser = userRepository.findUserDaoByUsername(service.getLoggedInUserDetails().getUsername());

        if (loggedInUser.isPresent()) {
            List<AppointmentDao> appointments = appointmentRepository.findAppointmentDaoByConsultantId(loggedInUser.get());
            if (!appointments.isEmpty()) {
                System.out.println("appointments not empty");
                model.addAttribute("appointments", appointments);
            }
        }
        return "consultantDashboard";
    }

    @PostMapping("/updateAppointment")
    public String updateAppointment(
            @RequestParam String appointmentId,
            @RequestParam String status
    ) {
//        AppointmentDao appointment = new AppointmentDao();
        Optional<AppointmentDao> appointment = appointmentRepository.findAppointmentDaoById(Integer.parseInt(appointmentId));

        if (appointment.isPresent()) {
            appointment.get().setStatus(AppointmentDao.Status.valueOf(status));
            appointment.get().setModifiedOn(new Date());
            System.out.println("Update Status: " + appointment.get().getStatus());
            appointmentRepository.save(appointment.get());
        }
        else {
            System.out.println("Appointment not found!");
        }

        return "redirect:/consultant/loadConsultantData";
    }

//    public UserDetails getLoggedInUserDetails(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
//            System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
//            System.out.println("inside if");
//            return (UserDetails) authentication.getPrincipal();
//        }
//        else {
//            System.out.println("inside else");
//            System.out.println(authentication.getPrincipal());
//
//        }
//        return null;
//    }
}
