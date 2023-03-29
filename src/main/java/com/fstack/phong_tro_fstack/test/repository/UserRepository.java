package com.fstack.phong_tro_fstack.test.repository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneById(Long id);
}
