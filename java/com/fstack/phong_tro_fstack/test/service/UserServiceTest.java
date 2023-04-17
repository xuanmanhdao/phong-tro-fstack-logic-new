package com.fstack.phong_tro_fstack.test.service;

import com.fstack.phong_tro_fstack.base.entity.RoleEntity;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.test.dto.UserDTO;
import com.fstack.phong_tro_fstack.test.repository.UserRepositoryTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceTest {
    @Autowired
    private UserRepositoryTest userRepositoryTest;

    @PersistenceContext
    private EntityManager entityManager;

//    public List<UserEntity> findAllUsers(){
//        return userRepositoryTest.findAllUsers();
//    }

    public List<UserEntity> findAllUsers() {
//        TypedQuery<Object[]> query = entityManager.createQuery(
//                "SELECT u, r, ur " +
//                        "FROM UserEntity u " +
//                        "LEFT JOIN u.userRoleEntities ur " +
//                        "left JOIN ur.roleEntity r", Object[].class);

        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT u " +
                        "FROM UserEntity u " +
                        "JOIN FETCH u.userRoleEntities ur " +
                        "JOIN FETCH ur.roleEntity r", Object[].class);

        List<Object[]> results = query.getResultList();

        List<UserEntity> users = new ArrayList<>();
        for (Object[] result : results) {
            UserEntity user = (UserEntity) result[0];
            RoleEntity role = (RoleEntity) result[1];
            Set<UserRoleEntity> userRoleEntities = user.getUserRoleEntities();
            userRoleEntities.forEach(userRole -> {
                if (userRole.getRoleEntity().getId().equals(role.getId())) {
                    userRole.setRoleEntity(role);
                    user.getUserRoleEntities().add(userRole);
                }
            });
            users.add(user);
        }

        return users;
    }
}
