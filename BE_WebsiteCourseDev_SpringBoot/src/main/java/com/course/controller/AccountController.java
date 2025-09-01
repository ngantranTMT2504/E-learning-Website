package com.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.request.ChangePasswordRequestDTO;
import com.course.dto.request.UpdateProfileRequestDTO;
import com.course.dto.response.AccountResponseDTO;
import com.course.service.iface.AccountService;
import com.course.service.iface.StudentService;
import com.course.service.iface.TeacherService;
import com.course.service.iface.UpdateProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
	private final TeacherService teacherService;
    private final StudentService studentService;
    private final AccountService accountService;
    private final UpdateProfileService	updateProfileService;
    
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudentInfo());
    }
    
    @PutMapping("/disable/{id}")
    public ResponseEntity<AccountResponseDTO> disableAccount(@PathVariable("id") Integer accountId) {
        return ResponseEntity.ok(accountService.disableAccount(accountId));
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable("id") Integer id,
			@Valid @RequestBody UpdateProfileRequestDTO dto) {
		return ResponseEntity.ok(updateProfileService.update(id, dto));
	}
    
    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable Integer id,
            @Valid @RequestBody ChangePasswordRequestDTO dto) {
        accountService.changePassword(id, dto);
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }
}
