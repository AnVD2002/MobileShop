package com.example.InternAGESTproject.Service.Client.AccountService.Interface;

import com.example.InternAGESTproject.DTO.Client.Request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SignUpService {

    public ResponseEntity<?> SignUp(SignUpRequest request);
}
