package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "ward")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WardEntity{
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "type", length = 45, nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private DistrictEntity districtEntity;

    @OneToMany(mappedBy = "wardEntity")
    private Set<AreaEntity> areaEntities;

    public WardEntity(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
