package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
