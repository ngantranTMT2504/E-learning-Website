package com.course.service.iface;

import java.util.List;

import com.course.dto.request.RoleRequestDTO;
import com.course.dto.response.RoleResponseDTO;

public interface RoleService {
	RoleResponseDTO addRole(RoleRequestDTO role);
	RoleResponseDTO getRoleById(Integer id);
	RoleResponseDTO getRoleByName(String name);
	void deleteRole(Integer id);
	RoleResponseDTO updateRole(RoleRequestDTO role, Integer roleId);
	List<RoleResponseDTO> getAllRole();
}
