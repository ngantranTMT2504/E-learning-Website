package com.course.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.course.dto.request.RoleRequestDTO;
import com.course.dto.response.RoleResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.RoleMapper;
import com.course.model.Role;
import com.course.repository.RoleRepository;
import com.course.service.iface.RoleService;

public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleResponseDTO addRole(RoleRequestDTO roleReq) {
		Role role = RoleMapper.toEntity(roleReq);
        Role saved = roleRepository.save(role);
        RoleResponseDTO roleResponseDTO = RoleMapper.toResponse(saved);
        return roleResponseDTO;
	}

	@Override
	public RoleResponseDTO getRoleById(Integer id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy role"));
		RoleResponseDTO roleResponseDTO = RoleMapper.toResponse(role);
		return roleResponseDTO;
	}

	@Override
	public RoleResponseDTO getRoleByName(String name) {
		return null;
	}

	@Override
	public void deleteRole(Integer id) {
		getRoleById(id);
		roleRepository.deleteById(id);
		
	}

	@Override
	public RoleResponseDTO updateRole(RoleRequestDTO roleRequestDTO, Integer roleId) {
		getRoleById(roleId);
		Role updatedRole = RoleMapper.toEntity(roleRequestDTO);
		updatedRole.setId(roleId);
		Role update = roleRepository.save(updatedRole);
		RoleResponseDTO roleResponseDTO = RoleMapper.toResponse(update);
		return roleResponseDTO;
	}

	@Override
	public List<RoleResponseDTO> getAllRole() {
		 List<RoleResponseDTO> responseList = roleRepository.findAll().stream()
	                .map(RoleMapper::toResponse)
	                .sorted(Comparator.comparing(RoleResponseDTO::getId).reversed())
	                .collect(Collectors.toList());
	        return responseList;
	}

	

}
