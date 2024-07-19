package com.example.InternAGESTproject.DTO.Client.Request;

import com.example.InternAGESTproject.DTO.Client.Response.SignUpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmRequest {
    private SignUpResponse signUpResponse;
    private String code;
}
