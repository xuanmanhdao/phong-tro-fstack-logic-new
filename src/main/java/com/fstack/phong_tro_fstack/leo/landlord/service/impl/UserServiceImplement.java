package com.fstack.phong_tro_fstack.leo.landlord.service.impl;


import com.fstack.phong_tro_fstack.leo.landlord.dao.UserDao;
import com.fstack.phong_tro_fstack.base.dto.UserDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;


@Service
@Transactional
public class UserServiceImplement implements UserService {


    private final UserDao userDao;

    UserServiceImplement(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        if (userDTO != null) {
            UserEntity user = new UserEntity();
            user.setCreatedAt(Date.valueOf(java.time.LocalDate.now()));
            user.setEmail(userDTO.getEmail());
            user.setFullName(userDTO.getFullName());
            user.setIdCard(userDTO.getIdCard());
            user.setPassword(userDTO.getPassword());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            userDao.addUser(user);
        }
        return userDTO;
    }

    @Override
    public UserDTO getUser(long id) {

        UserEntity user = userDao.getUser(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setIdCard(user.getIdCard());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, long id) {
        if(userDTO!=null && id!=0){
            UserEntity user = userDao.getUser(id);
        if(user!=null){
            userDTO.setId(user.getId());
            user.setCreatedAt(Date.valueOf(java.time.LocalDate.now()));
            user.setEmail(userDTO.getEmail());
            user.setFullName(userDTO.getFullName());
            user.setIdCard(userDTO.getIdCard());
            user.setPassword(userDTO.getPassword());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            userDao.addUser(user);
        }
        }
        return userDTO;
    }


}
