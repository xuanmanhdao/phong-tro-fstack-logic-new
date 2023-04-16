package com.fstack.phong_tro_fstack.admin.post.service;

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
	
	public String updatePost(int status, Long id) {
		
		float bankAccount = userRepos.findBankAccountById(id);
		if(bankAccount > 0) {
			float bank = bankAccount - 2000;
			Object updateBank = userRepos.updateBankAccont(bank, id);
			
			Object object =  postResponstory.updatePost(status, id);
			
			return "Thành công";
		}else {
			return "Tài khoản không đủ tiền";
		}
		
		
//		return null;
		
	}
}
