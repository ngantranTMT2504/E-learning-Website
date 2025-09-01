package com.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.dto.request.RoleRequestDTO;
import com.course.dto.response.RoleResponseDTO;
import com.course.model.Role;

public class RoleMapper {
	public static Role toEntity(RoleRequestDTO dto) {
		Role role = new Role();
		role.setRoleName(dto.getName());
		return role;
	}
	
	public static RoleResponseDTO toResponse(Role role) {
		RoleResponseDTO roleResponse = new RoleResponseDTO();
		roleResponse.setId(role.getId());
		roleResponse.getRoleName();
		return roleResponse;
	}
	
	public static List<RoleResponseDTO> toResponseDTOList(List<Role> roles) {
		return roles.stream().map(RoleMapper::toResponse).collect(Collectors.toList());
	}

}
