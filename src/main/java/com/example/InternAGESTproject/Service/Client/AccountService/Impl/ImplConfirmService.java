package com.example.InternAGESTproject.Service.Client.AccountService.Impl;

import com.example.InternAGESTproject.DTO.Client.Request.ConfirmRequest;
import com.example.InternAGESTproject.Entity.RoleEnum;
import com.example.InternAGESTproject.Entity.Roles;
import com.example.InternAGESTproject.Entity.User;
import com.example.InternAGESTproject.Repository.RolesRepository;
import com.example.InternAGESTproject.Repository.UserRepository;
import com.example.InternAGESTproject.Service.Client.AccountService.Interface.ConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

/**
 * Xac nhan tk
 */
@RequiredArgsConstructor
public class ImplConfirmService implements ConfirmService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    @Override
    public ResponseEntity<?> confirm(ConfirmRequest request) {
        Optional<User> userCheck = userRepository.findByEmail(request.getSignUpResponse().getEmail());
        if(userCheck.isPresent()){
            if(request.getCode().equals(request.getSignUpResponse().getCode())){
                Optional<Roles> decentralization = rolesRepository.findByName(RoleEnum.USER.toString());
                if (decentralization.isEmpty()) {
                    return new ResponseEntity<>("Khong ton tai ", HttpStatus.NOT_FOUND);
                }
                userCheck.get().setConfirmed(true);
                userRepository.save(userCheck.get());
                return new ResponseEntity<>("Tao tk thanh cong ", HttpStatus.OK);
            }
            return new ResponseEntity<>("Sai code", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Ban chua tao tk", HttpStatus.NOT_FOUND);
    }
}
