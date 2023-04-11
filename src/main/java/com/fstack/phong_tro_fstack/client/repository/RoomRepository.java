package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
