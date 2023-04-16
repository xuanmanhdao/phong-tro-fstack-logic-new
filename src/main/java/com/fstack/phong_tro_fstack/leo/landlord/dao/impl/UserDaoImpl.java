package com.fstack.phong_tro_fstack.leo.landlord.dao.impl;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.client.repository.UserRepository;
import com.fstack.phong_tro_fstack.leo.landlord.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUser(long id) {
        return userRepository.findById(id).get();
    }


}
