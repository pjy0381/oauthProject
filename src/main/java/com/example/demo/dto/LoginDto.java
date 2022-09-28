package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    private String password;

}
