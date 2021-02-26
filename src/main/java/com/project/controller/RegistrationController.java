package com.project.controller;


import com.project.UserDataValidator;
import com.project.model.Role;
import com.project.model.Status;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping
    public String getRegistrationPage(){
        return "/registration";
    }

    @PostMapping
    public String register(User user, Model model){
        Optional<User> DbUser = userRepository.findByEmail(user.getEmail());
        String failedRegistrationReason;
        if(DbUser.isEmpty()){
            if(UserDataValidator.isValidEmail(user.getEmail())) {
                if(UserDataValidator.isValidPassword(user.getPassword())) {
                    user.setRole(Role.USER);
                    user.setStatus(Status.ACTIVE);
                    userRepository.save(user);
                    return "/login";
                }else
                    failedRegistrationReason = "Incorrect password";
            }else
                failedRegistrationReason = "Incorrect email";
        }else
            failedRegistrationReason = "User with this email already exists";

        model.addAttribute("failedRegistrationReason" ,failedRegistrationReason);
        return "/registration";
    }
}
