package com.fstack.phong_tro_fstack.leo.landlord.dao.impl;

import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.client.repository.AreaRepository;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AreaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AreaDaoImpl implements AreaDao {

  @Autowired
  private AreaRepository areaRepository;


  @Override
  public void saveArea(AreaEntity areaEntity) {
    areaRepository.save(areaEntity);
  }

  @Override
  public void updateArea(AreaEntity areaEntity) {
    areaRepository.save(areaEntity);

  }

  @Override
  public AreaEntity getAreaById(long id) {
    return areaRepository.findById(id).get();

  }

  @Override
  public Long saveAreaAndGetId(AreaEntity areaEntity) {
    areaRepository.saveAndFlush(areaEntity);
    return areaEntity.getId();
  }

  @Override
  public List<AreaEntity> getAllArea() {
    return areaRepository.findAll();
  }

  @Override
  public AreaEntity getAreaByPostId(long idPost) {
    return areaRepository.getAreaByPostId(idPost);
  }

}
