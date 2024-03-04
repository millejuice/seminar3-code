package com.example.ThirdSeminar.user.repository;

import com.example.ThirdSeminar.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
}
