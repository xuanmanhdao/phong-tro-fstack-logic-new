package com.fstack.phong_tro_fstack.leo.landlord.dao;


import com.fstack.phong_tro_fstack.base.entity.UserEntity;

public interface UserDao {

    void addUser(UserEntity user);
    void updateUser(UserEntity user);
    UserEntity getUser(long id);

}
