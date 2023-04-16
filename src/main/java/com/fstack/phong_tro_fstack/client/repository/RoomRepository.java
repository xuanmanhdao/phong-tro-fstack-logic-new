package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
  @Query(value = "select r from RoomEntity  r inner join AreaEntity a on a.id=r.areaEntity.id where r.areaEntity.id=:idArea")
  List<RoomEntity> getRoombyAreaId(@Param("idArea") long idArea);
}
