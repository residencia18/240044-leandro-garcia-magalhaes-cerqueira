package com.test.fakebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.fakebook.dto.EmailRequest;
import com.test.fakebook.service.ChangePasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/password-recovery") // Defines base URL for the controller
@RequiredArgsConstructor // Automatically injects dependencies through constructor
public class PasswordRecoveryController {

    // Injecting ChangePasswordService bean
    @Autowired
    private final ChangePasswordService changePasswordService;

    // Endpoint to request password recovery
    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordRecovery(@RequestBody EmailRequest email) {
        // Check if user is found and email for password recovery is sent successfully
        if (changePasswordService.findUserAndSendEmail(email)) {
            // Return success response with a message indicating that a recovery email has been sent
            return ResponseEntity.ok("A recovery email has been sent to " + email.email());
        } else {
            // Return not found status with a message indicating that no user is found for the provided email
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for the email provided.");
        }
    }

}

