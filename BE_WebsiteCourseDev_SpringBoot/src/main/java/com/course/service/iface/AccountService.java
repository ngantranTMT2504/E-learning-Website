package com.course.service.iface;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.course.dto.request.AccountRequestDTO;
import com.course.dto.request.AuthRequestDTO;
import com.course.dto.request.ChangePasswordRequestDTO;
import com.course.dto.response.AccountResponseDTO;
import com.course.dto.response.AuthResponseDTO;

public interface AccountService {
	public AuthResponseDTO create(AuthRequestDTO account);

    public List<AccountResponseDTO> findAll();

    public AccountResponseDTO update(Integer id, AccountRequestDTO account);

    public AccountResponseDTO findById(Integer id);

    public AccountResponseDTO deleteById(Integer id);
    
    public AccountResponseDTO findByEmail(String email);

    public UserDetails loadUserByUsername(String email);
    
    public boolean checkActive(String email);
    
    AccountResponseDTO disableAccount(Integer accountId);
    
    void changePassword(Integer accountId, ChangePasswordRequestDTO dto);
}
