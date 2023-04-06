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

    @Query(value = "SELECT p.id, p.thumbnail, p.title, p.content, p.created_at, " +
            "p.updated_at, p.number_date, p.phone_zalo, p.phone_number, p.status, " +
            "u.id as 'user_id', u.full_name, " +
            "a.id as 'area_id', a.exact_address, a.latitude, a.longitude, a.name, " +
            "r.id as 'room_id', r.name, r.description, r.acreage, r.rent_price, r.electric_service, r.water_service, r.image, r.video, " +
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
            "where p.number_date <> 0 ", nativeQuery = true)
    List<Object[]> getAllPostEnable();
}
