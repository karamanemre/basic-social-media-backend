package com.emrekaraman.springsocial.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginActivity {
    private String username;
    private String password;
}
