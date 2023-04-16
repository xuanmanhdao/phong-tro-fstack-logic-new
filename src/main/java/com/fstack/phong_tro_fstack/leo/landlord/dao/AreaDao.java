package com.fstack.phong_tro_fstack.leo.landlord.dao;

import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import java.util.List;

public interface AreaDao {

  void saveArea(AreaEntity areaEntity);

  void updateArea(AreaEntity areaEntity);

  AreaEntity getAreaById(long id);

  Long saveAreaAndGetId(AreaEntity areaEntity);

  List<AreaEntity> getAllArea();

  AreaEntity getAreaByPostId(long idPost);
}
