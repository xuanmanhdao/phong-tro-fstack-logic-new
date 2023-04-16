package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

  @Query("Select p from PostEntity p where p.numberDate <> 0 order by p.createdAt desc")
  List<PostEntity> findAllNumberDateOtherZeroOrderByCreatedAt();

  @Query(value = "SELECT p.id, p.thumbnail, p.title, p.content, p.created_at, " +
      "p.updated_at, p.number_date, p.phone_zalo, p.phone_number, p.status, " +
      "u.id as 'user_id', u.full_name, " +
      "a.id as 'area_id', a.exact_address, a.latitude, a.longitude, a.name, " +
      "r.id as 'room_id', r.name, r.description, r.acreage, r.rent_price, r.electric_service, r.water_service, r.image, r.video, "
      +
      "prov.id as 'province_id', prov.name, " +
      "d.id as 'district_id', d.name, " +
      "w.id as 'ward_id', w.name " +
      "FROM post p " +
      "inner join user u on p.id_user = u.id " +
      "inner join area a on p.id_area = a.id " +
      "inner join room r on a.id = r.id_area " +
      "inner join district d on a.id_district=d.id " +
      "inner join province prov on a.id_province=prov.id " +
      "inner join ward w on a.id_ward=w.id " +
      "where p.number_date <> 0 " +
      "AND (:idProvince IS NULL OR a.id_province = :idProvince) " +
      "AND (:idDistrict IS NULL OR a.id_district = :idDistrict) " +
      "AND (:idWard IS NULL OR a.id_ward = :idWard) " +
      "AND (:rentPrice IS NULL OR r.rent_price between :minPrice and :maxPrice) " +
      "AND (:acreage IS NULL OR r.acreage between :minAcreage and :maxAcreage) " +
      "AND (:exactAddress IS NULL OR a.exact_address LIKE %:exactAddress%)", nativeQuery = true)
  List<Object[]> getAllPostEnable(
      @Param("idProvince") Optional<String> idProvince,
      @Param("idDistrict") Optional<String> idDistrict,
      @Param("idWard") Optional<String> idWard,
      @Param("rentPrice") Optional<String> rentPrice,
      @Param("minPrice") Optional<Float> minPrice,
      @Param("maxPrice") Optional<Float> maxPrice,
      @Param("acreage") Optional<String> acreage,
      @Param("minAcreage") Optional<Float> minAcreage,
      @Param("maxAcreage") Optional<Float> maxAcreage,
      @Param("exactAddress") Optional<String> exactAddress
  );

  @Query(value = "SELECT p.id, p.thumbnail, p.title, p.content, p.created_at, " +
      "p.updated_at, p.number_date, p.phone_zalo, p.phone_number, p.status, " +
      "u.id as 'user_id', u.full_name, " +
      "a.id as 'area_id', a.exact_address, a.latitude, a.longitude, a.name, " +
      "r.id as 'room_id', r.name, r.description, r.acreage, r.rent_price, r.electric_service, r.water_service, r.image, r.video, "
      +
      "prov.id as 'province_id', prov.name, " +
      "d.id as 'district_id', d.name, " +
      "w.id as 'ward_id', w.name " +
      "FROM post p " +
      "inner join user u on p.id_user = u.id " +
      "inner join area a on p.id_area = a.id " +
      "inner join room r on a.id = r.id_area " +
      "inner join district d on a.id_district=d.id " +
      "inner join province prov on a.id_province=prov.id " +
      "inner join ward w on a.id_ward=w.id " +
      "where p.number_date <> 0 " +
      "AND p.id = :id", nativeQuery = true)
  List<Object[]> getDetailPost(
      @Param("id") Long id
  );

  @Query(
      "SELECT p.content, p.status, p.title, p.thumbnail,p.id, CAST(AVG(r.ratingStarts) AS FLOAT) " +
          "FROM PostEntity p " +
          "INNER JOIN p.areaEntity a " +
          "LEFT JOIN a.rateEntities r " +
          "WHERE p.userEntity.id = :idUser " +
          "GROUP BY p.id, p.content, p.status, p.title, p.thumbnail")
  List<Object[]> getPostandRatebyUser(@Param("idUser") long idUser);
}
