package com.fstack.phong_tro_fstack.base.converter;

public interface Mapper<E, D> {
  E toEntity(D dto);
  D toDTO(E entity);
}