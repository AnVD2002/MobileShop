package com.example.InternAGESTproject.DTO.Client.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponse {
    private String username;

    private String email;

    private boolean status;

    private String code;

    private String message;
}
