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

    @Column(name="exact_address", length = 255)
    private String exactAddress;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private ProvinceEntity provinceEntity;

    @ManyToOne
    @JoinColumn(name = "id_district")
    private DistrictEntity districtEntity;

    @ManyToOne
    @JoinColumn(name = "id_ward")
    private WardEntity wardEntity;

    @OneToOne(mappedBy = "areaEntity")
    private PostEntity postEntity;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RateEntity> rateEntities;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RoomEntity> roomEntities;
}
