package com.example.InternAGESTproject.Service.Client.AccountService.Interface;

import com.example.InternAGESTproject.DTO.Client.Request.ConfirmRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ConfirmService {
    public ResponseEntity<?> confirm(ConfirmRequest request);
}
