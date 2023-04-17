package com.fstack.phong_tro_fstack.admin.post.service;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fstack.phong_tro_fstack.admin.post.respostory.PostResponstoryAdmin;
import com.fstack.phong_tro_fstack.admin.users.service.UserRepos;

@Service
public class PostService {


  @Autowired
  PostResponstoryAdmin postResponstory;

  @Autowired
  UserRepos userRepos;

  public String updatePost(int status, Long id, Long idUser) {

    float bankAccount = userRepos.findBankAccountById(idUser);
    if (bankAccount > 0) {
      float bank = bankAccount - 2000;
      Optional<UserEntity> userEntity = userRepos.findById(idUser);
			userEntity.get().setBalance(bank);
      userRepos.save(userEntity.get());

			Optional<PostEntity> postEntity = postResponstory.findById(id);
			postEntity.get().setStatus(status);
			postResponstory.save(postEntity.get());

//      Object updateBank = userRepos.updateBankAccont(bank, idUser);
//      Object object = postResponstory.updatePost(status, id);

      return "Thành công";
    } else {
      return "Tài khoản không đủ tiền";
    }

//		return null;

  }
}
