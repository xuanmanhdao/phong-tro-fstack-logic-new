package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findOneById(Long id);

  @Query(value =
      "Select u.id as id, u.email as email, u.full_name as full_name, u.password as password "
          + "from user u inner join user_role ur on u.id = ur.id_user "
          + "where u.email = :email "
          + "and u.password = :password "
          + "LIMIT 1", nativeQuery = true)
  List<Object[]> findByEmailAndPassword(@Param("email") String email,
      @Param("password") String password);

  UserEntity findByEmail(String email);
}
