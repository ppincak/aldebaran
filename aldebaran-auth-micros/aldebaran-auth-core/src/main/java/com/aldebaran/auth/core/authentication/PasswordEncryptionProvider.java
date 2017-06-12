package com.aldebaran.auth.core.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


//NOTE(peter.pincak) do not move this alass to another module,
// as it may cause circular dependency issues
@Component
public class PasswordEncryptionProvider extends BCryptPasswordEncoder {

}
