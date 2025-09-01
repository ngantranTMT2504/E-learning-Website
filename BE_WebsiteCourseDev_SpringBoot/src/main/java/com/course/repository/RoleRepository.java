package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
