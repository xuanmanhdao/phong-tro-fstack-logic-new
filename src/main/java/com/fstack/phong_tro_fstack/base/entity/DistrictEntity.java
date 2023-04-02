package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "district")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DistrictEntity{
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "type", length = 45, nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_province")
    private ProvinceEntity provinceEntity;

    @OneToMany(mappedBy = "districtEntity")
    private Set<WardEntity> wardEntities;

    @OneToMany(mappedBy = "districtEntity")
    private Set<AreaEntity> areaEntities;

    public DistrictEntity(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
