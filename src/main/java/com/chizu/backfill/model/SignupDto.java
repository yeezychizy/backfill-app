package com.chizu.backfill.model;

/**
 * @author : chizubeokwuosa
 * @Description :
 * @created : chizubeokwuosa, Sunday
 **/
import lombok.Data;

@Data
public class SignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
}


