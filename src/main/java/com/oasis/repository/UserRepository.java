package com.oasis.repository;

import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
