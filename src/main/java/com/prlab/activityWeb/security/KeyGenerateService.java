package com.prlab.activityWeb.security;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class KeyGenerateService {

    public String getRandomKey(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodeSalt = Base64.getEncoder().encodeToString(salt);

        return encodeSalt;
    }
}
