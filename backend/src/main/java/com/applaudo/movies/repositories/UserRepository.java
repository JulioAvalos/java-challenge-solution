package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);

}
