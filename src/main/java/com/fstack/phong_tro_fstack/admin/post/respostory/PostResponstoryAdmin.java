package com.fstack.phong_tro_fstack.admin.post.respostory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;

public interface PostResponstoryAdmin extends CrudRepository<PostEntity, Long>{
	
	@Query(value = "UPDATE post SET STATUS = ? WHERE id = ?" , nativeQuery = true)
	Object updatePost(int status, Long id);

}
