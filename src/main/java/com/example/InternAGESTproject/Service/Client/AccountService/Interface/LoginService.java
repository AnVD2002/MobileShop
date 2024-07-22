package com.example.InternAGESTproject.Service.Client.AccountService.Interface;

import com.example.InternAGESTproject.DTO.Client.Request.AccountRequest.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public ResponseEntity<?> login(LoginRequest request);
}
