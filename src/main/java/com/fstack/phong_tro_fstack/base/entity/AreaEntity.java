package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "area")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaEntity extends BaseEntity{
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "longitude", length = 50)
    private String longitude;

    @Column(name = "latitude", length = 50)
    private String latitude;

    @Column(name = "ward", length = 255, nullable = false)
    private String ward;

    @Column(name = "district", length = 255, nullable = false)
    private String district;

    @Column(name = "city", length = 255, nullable = false)
    private String city;

    @Column(name="exact_address", length = 255)
    private String exactAddress;

    @OneToOne(mappedBy = "areaEntity")
    private PostEntity postEntity;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RateEntity> rateEntities;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RoomEntity> roomEntities;
}
