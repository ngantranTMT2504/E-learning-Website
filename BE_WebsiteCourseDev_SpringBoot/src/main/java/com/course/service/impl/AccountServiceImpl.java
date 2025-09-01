package com.course.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.dto.request.AccountRequestDTO;
import com.course.dto.request.AuthRequestDTO;
import com.course.dto.request.ChangePasswordRequestDTO;
import com.course.dto.response.AccountResponseDTO;
import com.course.dto.response.AuthResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.AccountMapper;
import com.course.mapper.RegisterMapper;
import com.course.model.Account;
import com.course.model.AccountDetails;
import com.course.repository.AccountRepository;
import com.course.service.iface.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public AuthResponseDTO create(AuthRequestDTO dto) {
		Account account = RegisterMapper.toEntity(dto);
		account.setPassword(encoder.encode(dto.getPassword()));
		Account saved = accountRepository.save(account);
		return RegisterMapper.toResponse(saved);
	}

	@Override
	public List<AccountResponseDTO> findAll() {
		List<Account> accounts = accountRepository.findAll();
		return AccountMapper.toResponseDTOList(accounts);
	}
	
	@Override
    public AccountResponseDTO disableAccount(Integer accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(!account.isStatus());
        accountRepository.save(account);
        return AccountMapper.toResponse(account);
    }

	@Override
	public AccountResponseDTO update(Integer id, AccountRequestDTO dto) {
		AccountResponseDTO accountFindById = findById(id); 
		Account account = AccountMapper.toEntity(dto);
		account.setId(id);
		account.setPassword(accountFindById.getPassword());
		Account updated = accountRepository.save(account);
		return AccountMapper.toResponse(updated);
	}

	@Override
	public AccountResponseDTO findById(Integer id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với ID: " + id));
		return AccountMapper.toResponse(account);
	}

	@Override
	public AccountResponseDTO deleteById(Integer id) {
		AccountResponseDTO acc = findById(id);
		accountRepository.deleteById(id);
		return acc;
	}

	@Override
	public AccountResponseDTO findByEmail(String email) {
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với email: " + email));
		return AccountMapper.toResponse(account);
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<Account> userDetail = accountRepository.findByEmail(email);

		return userDetail.map(AccountDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	}

	@Override
	public boolean checkActive(String email) {
		Account user = accountRepository.findByEmail(email).get();
		return user.isStatus();
	}
	
	@Override
    public void changePassword(Integer accountId, ChangePasswordRequestDTO dto) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));

        if (!encoder.matches(dto.getCurrentPassword(), account.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp");
        }

        account.setPassword(encoder.encode(dto.getNewPassword()));
        accountRepository.save(account);
    }

}
