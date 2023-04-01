package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "ward")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardEntity{
    @Id
    @Column(name = "id", columnDefinition = "Char(6)")
    private char id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "type", length = 45, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_district")
    private DistrictEntity districtEntity;

    @OneToMany(mappedBy = "wardEntity")
    private Set<AreaEntity> areaEntities;
}
