package com.test.fakebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.fakebook.dto.NewPasswordRequest;
import com.test.fakebook.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/password-recovery") 
@RequiredArgsConstructor // Automatically injects dependencies through constructor
public class PasswordResetController {

    // Injecting ChangePasswordService bean
    @Autowired
    private final ChangePasswordService changePasswordService;

    // Endpoint to reset password
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody NewPasswordRequest newPassword) {
        // Attempt to change password with provided token and new password
        if (changePasswordService.changePassword(token, newPassword)) { 
            // Return success response with message indicating successful password reset
            return ResponseEntity.status(HttpStatus.OK).body("Password updated with success!");
        } else {
            // Return bad request status with message indicating invalid token
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
    }
}
