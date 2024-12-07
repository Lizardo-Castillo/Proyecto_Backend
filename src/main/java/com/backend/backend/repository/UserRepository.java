package com.backend.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
