package com.fstack.phong_tro_fstack.admin.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fstack.phong_tro_fstack.admin.post.service.PostService;

@RestController
@RequestMapping("post")
public class PostControllerAdmin {
	
	@Autowired
	PostService postService;
	
	@PostMapping("update-post")
	public ResponseEntity<?> updatePosst(@RequestBody JsonNode json){
		
		Long id = json.get("idPost").asLong();
		int status = json.get("status").asInt();
		Long idUser =json.get("idUser").asLong();

		String messent = postService.updatePost(status, id, idUser);
		return ResponseEntity.ok(messent);
	}

}
