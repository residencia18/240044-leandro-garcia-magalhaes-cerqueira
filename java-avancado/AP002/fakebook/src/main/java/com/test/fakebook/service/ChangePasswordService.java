package com.test.fakebook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.test.fakebook.dto.EmailRequest;
import com.test.fakebook.dto.NewPasswordRequest;
import com.test.fakebook.entity.PasswordResetToken;
import com.test.fakebook.entity.User;
import com.test.fakebook.validation.ValidPassword;


@Service
public class ChangePasswordService {

    // Autowired dependencies
    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationService authenticationService;

    // Method to find user by email and send password recovery email
    public Boolean findUserAndSendEmail(EmailRequest email){

        // Find user by email
        Optional<User> userOptional = userService.findByEmail(email.email());

        if (userOptional.isPresent()) {
            // If user exists, generate token and send recovery email
            User user = userOptional.get();
            String token = tokenService.generateToken();
            tokenService.saveToken(token, user);
            String recoveryLink = "http://yoursite.com/api/auth/password-recovery?token=" + token;
            String emailBody = "Hello,\n\nYou have requested password recovery. Please click on the following link to register a new password:\n" + recoveryLink;
            emailService.sendEmail(email.email(), "Password recovery", emailBody);
            return true;
        } else {
            // If user not found, return false
            return false;
        }
    }

    // Method to change user password using token
    public Boolean changePassword(String token, @ValidPassword NewPasswordRequest newPassword){

        // Find token by token value
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
        
        if (passwordResetToken != null) {
            // If token exists
            if (!tokenService.isTokenExpired(passwordResetToken)) {
                // If token is not expired, update user's password
                Optional<User> updateUser = userService.findById(passwordResetToken.getUser().getId());
                User user = updateUser.get();
                authenticationService.changePassword(newPassword);
                user.setPassword(passwordEncoder.encode(newPassword.password()));
                userService.saveUser(user);
                // Invalidate token
                tokenService.invalidateToken(token);
                return true;
            } else {
                // If token is expired, return false
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expired token.");
                return false;
            }
        } else {
            // If token not found, return false
            return false;
        }
    }
}
