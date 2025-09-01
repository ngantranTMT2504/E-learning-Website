package com.course.service;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.dto.request.RegisterTeacherRequestDTO;
import com.course.dto.request.TeacherCertificateRequestDTO;
import com.course.model.Account;
import com.course.model.Role;
import com.course.model.Teacher;
import com.course.model.TeacherBank;
import com.course.model.TeacherCertificate;
import com.course.repository.AccountRepository;
import com.course.repository.TeacherBankRepository;
import com.course.repository.TeacherCertificateRepository;
import com.course.repository.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherRegisterService {
	private final AccountRepository accountRepo;
    private final TeacherRepository teacherRepo;
    private final TeacherBankRepository bankRepo;
    private final TeacherCertificateRepository certRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerTeacher(RegisterTeacherRequestDTO dto) {
        // 1. Tạo account
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        account.setFullname(dto.getFullname());
        account.setRegistrationDate(new Date());
        account.setStatus(true);
        account.setRole(new Role(2, "TEACHER", null));
        accountRepo.save(account);

        // 2. Tạo teacher
        Teacher teacher = new Teacher();
        teacher.setAccount(account);
        teacher.setDegree(dto.getDegree());
        teacher.setEducationLevel(dto.getEducationLevel());
        teacher.setPhone(dto.getPhone());
        teacher.setStatus(true);
        

        teacherRepo.save(teacher);

        // 3. Tạo bank info
        TeacherBank bank = new TeacherBank();
        bank.setAccountHolder(dto.getAccountHolder());
        bank.setAccountNumber(dto.getAccountNumber());
        bank.setBankName(dto.getBankName());
        bank.setBankCode(dto.getBankCode());
        bank.setDefault(true);        
        bank.setCreateDate(new Date());
        bank.setUploadedDate(new Date());
        bank.setTeacher(teacher);
        bankRepo.save(bank);

        // 4. Tạo certificates
        for (TeacherCertificateRequestDTO certDto : dto.getCertificates()) {
            TeacherCertificate cert = new TeacherCertificate();
            cert.setCertificateName(certDto.getCertificateName());
            cert.setCertificateImage(certDto.getCertificateImage());
            cert.setUploadedDate(new Date());
            cert.setTeacher(teacher);
            certRepo.save(cert);
        }
    }

}
