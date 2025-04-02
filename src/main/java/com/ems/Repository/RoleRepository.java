package com.ems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.UserRoles;

public interface RoleRepository extends JpaRepository<UserRoles,Integer>{
          
	/* UserRoles findByName(String roleName); */
}
