package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("Select p from PostEntity p where p.numberDate <> 0 order by p.createdAt desc")
    List<PostEntity> findAllNumberDateOtherZeroOrderByCreatedAt();
}
