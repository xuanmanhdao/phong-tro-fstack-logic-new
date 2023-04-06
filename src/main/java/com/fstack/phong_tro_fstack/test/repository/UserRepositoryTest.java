package com.fstack.phong_tro_fstack.test.repository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.test.dto.RoleDTO;
import com.fstack.phong_tro_fstack.test.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepositoryTest extends JpaRepository<UserEntity, Long> {
//    @Query("SELECT new com.fstack.phong_tro_fstack.test.dto.UserDTO(ue.id, ue.email, ue.fullName, new com.fstack.phong_tro_fstack.test.dto.RoleDTO(re.id, re.name)) " +
//            "FROM UserEntity ue INNER JOIN UserRoleEntity ure ON ue.id = ure.userRoleKey.idUser " +
//            "INNER JOIN RoleEntity re on re.id = ure.userRoleKey.idRole")
//    List<UserDTO> findAllUsers();

//    @Query("SELECT u, r " +
//            "FROM UserEntity u " +
//            "inner JOIN u.userRoleEntities ur " +
//            "inner JOIN ur.roleEntity r")
//    List<UserEntity> findAllUsers();
}
