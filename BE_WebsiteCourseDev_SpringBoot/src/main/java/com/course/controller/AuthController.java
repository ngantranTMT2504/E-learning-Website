package com.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.request.AuthRequest;
import com.course.dto.request.AuthRequestDTO;
import com.course.dto.request.RegisterTeacherRequestDTO;
import com.course.dto.response.AuthResponseDTO;
import com.course.model.Account;
import com.course.model.Student;
import com.course.repository.AccountRepository;
import com.course.repository.StudentRepository;
import com.course.service.JwtService;
import com.course.service.TeacherRegisterService;
import com.course.service.iface.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final StudentRepository studentRepository;
	private final AccountService accountService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final TeacherRegisterService registerService;
	private final AccountRepository accountRepository;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				boolean active = accountService.checkActive(authRequest.getEmail());
				
				if (active) {
					String token = jwtService.generateToken(authRequest.getEmail());
					return ResponseEntity.ok(token);
				} else {
					return ResponseEntity.status(403).body("Tài khoản bị khóa");
				}
			} else {
				throw new UsernameNotFoundException("Tài khoản không hợp lệ!");
			}
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(403).body("Sai email hoặc mật khẩu.");
		}
	}

	@PostMapping("/student/register")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody AuthRequestDTO accountDto) {
		try {
			AuthResponseDTO createdAccount = accountService.create(accountDto);
			
			Account savedAccount = accountRepository.findByEmail(accountDto.getEmail()).orElseThrow();
			
			Student student = new Student();
            student.setAccount(savedAccount);
            
            studentRepository.save(student);
            
//			emailService.sendWelcomeEmail(accountDto.getEmail());
			
			return ResponseEntity.ok(createdAccount);
		} catch (Exception e) {
		    e.printStackTrace(); 
		    return ResponseEntity.status(403).body("Đã xảy ra lỗi khi tạo tài khoản.");
		}
	}
	
	@PostMapping("/teacher/register")
    public ResponseEntity<?> registerTeacher(@RequestBody AuthRequestDTO accountDto) {
		try {
			AuthResponseDTO createdAccount = accountService.create(accountDto);
			
			return ResponseEntity.ok(createdAccount);
		} catch (Exception e) {
			return ResponseEntity.status(403).body("Đã xảy ra lỗi khi tạo tài khoản.");
		}
    }
	 
   
	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}
	
	@GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body( accountService.findAll());
    }

}
