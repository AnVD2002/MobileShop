package com.example.InternAGESTproject.Service.Client.AccountService.Impl;

import com.example.InternAGESTproject.DTO.Client.Request.AccountRequest.SignUpRequest;
import com.example.InternAGESTproject.DTO.Client.Response.SignUpResponse;
import com.example.InternAGESTproject.Entity.RoleEnum;
import com.example.InternAGESTproject.Entity.Roles;
import com.example.InternAGESTproject.Entity.User;
import com.example.InternAGESTproject.Repository.RolesRepository;
import com.example.InternAGESTproject.Repository.UserRepository;
import com.example.InternAGESTproject.Service.Client.AccountService.Interface.SignUpService;
import com.example.InternAGESTproject.Service.Until.OTP;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
@RequiredArgsConstructor
public class ImplSignUpService implements SignUpService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    /**
     * Tao tk
     */
    @Override
    public ResponseEntity<?> SignUp(SignUpRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty() || request.getUsername().isEmpty()){
            return new ResponseEntity<>("Không được bỏ trống ", HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<User> userCheck = userRepository.findByEmail(request.getEmail());
        Optional<Roles> decentralization = rolesRepository.findByName(RoleEnum.USER.toString());
        if(decentralization.isEmpty()){
            return new ResponseEntity<>(SignUpResponse.builder().message("not exist").build(), HttpStatus.NOT_FOUND);
        }
        OTP otp = new OTP();
        String newOTP = otp.GenerateOTP();

        if(userCheck.isPresent()){
            if(userCheck.get().isConfirmed()){
                return new ResponseEntity<>(SignUpResponse.builder().message("Đã tồn tại tài khoản").build(),HttpStatus.NOT_FOUND);
            }
            sendConfirmationEmail(newOTP, request.getEmail());
            return new ResponseEntity<>(SignUpResponse.builder()
                    .username(userCheck.get().getUserName())
                    .email(request.getEmail())
                    .code(newOTP)
                    .message("Còn 1 bước nữa để thành công")
                    .build(), HttpStatus.OK);
        }
        ResponseEntity<String> message= sendConfirmationEmail(newOTP, request.getEmail());
        if(!message.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(SignUpResponse.builder().message(message.getBody()).build(),HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setRole(decentralization.get());
        user.setConfirmed(false);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserName(request.getUsername());
        userRepository.save(user);

        return new ResponseEntity<>(SignUpResponse.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .code(newOTP)
                .message("Còn 1 bước nữa để thành công")
                .build(), HttpStatus.OK);
    }

    /**
     * Gui code toi email
     */
    public ResponseEntity<String> sendConfirmationEmail(String confirmationCode, String recipientEmail) {
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            return new ResponseEntity<>("Địa chỉ email không được để trống.", HttpStatus.BAD_REQUEST);
        }
        if (!isValidEmail(recipientEmail)) {
            return new ResponseEntity<>("Địa chỉ email không hợp lệ.", HttpStatus.BAD_REQUEST);
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Xác nhận đăng ký tài khoản");
        mailMessage.setText("Mã xác nhận của bạn là: " + confirmationCode);

        try {
            javaMailSender.send(mailMessage);
            return new ResponseEntity<>("Email xác nhận đã được gửi thành công.", HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>("Lỗi khi gửi email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * check email
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
