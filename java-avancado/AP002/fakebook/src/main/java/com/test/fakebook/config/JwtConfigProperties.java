package com.test.fakebook.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

// Configuration class for JWT properties
@ConfigurationProperties(prefix = "jwt")
@EnableConfigurationProperties(JwtConfigProperties.class)
@Component
@Getter
@Setter
public class JwtConfigProperties {
    // RSA public key used for JWT encryption
    private RSAPublicKey publicKey;

    // RSA private key used for JWT decryption
    private RSAPrivateKey privateKey;
}
