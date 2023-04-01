package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "province")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceEntity{
    @Id
    @Column(name = "id", columnDefinition = "Char(6)")
    private char id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "type", length = 45, nullable = false)
    private String type;

    @OneToMany(mappedBy = "provinceEntity")
    private Set<DistrictEntity> districtEntities;

    @OneToMany(mappedBy = "provinceEntity")
    private Set<AreaEntity> areaEntities;
}
