package com.example.InternAGESTproject.Service.Client.AccountService.Impl;

import com.example.InternAGESTproject.Config.JWTConfig.JwtProvider;
import com.example.InternAGESTproject.DTO.Client.Request.LoginRequest;
import com.example.InternAGESTproject.DTO.Client.Response.LoginResponse;
import com.example.InternAGESTproject.Entity.CustomUserDetails;
import com.example.InternAGESTproject.Entity.RoleEnum;
import com.example.InternAGESTproject.Repository.UserRepository;
import com.example.InternAGESTproject.Service.Client.AccountService.Interface.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ImplLoginService implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    /**
     * Login
     */
    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            if (userDetails.getUser().isConfirmed()) {
                String refreshToken = jwtProvider.generateRefreshToken(userDetails);
                userDetails.getUser().setRefreshToken(refreshToken);
                userRepository.save(userDetails.getUser());
                String accessToken = jwtProvider.generateToken(userDetails);
                return new ResponseEntity<>(LoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .role(userDetails.getAuthorities().toString())
                        .message("Đăng nhập thành công")
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(LoginResponse.builder().message("Tài khoản chưa được xác nhận").build(),HttpStatus.UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(LoginResponse.builder().message("Sai tên tài khoản hoặc mật khẩu").build(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(LoginResponse.builder().message("Lỗi server: " + e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
