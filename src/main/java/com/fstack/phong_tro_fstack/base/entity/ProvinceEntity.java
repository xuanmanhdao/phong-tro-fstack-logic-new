package com.fstack.phong_tro_fstack.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "province")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProvinceEntity{
//    @Id
//    @Column(name = "id", columnDefinition = "Char(6)")
//    private Character id;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "type", length = 45, nullable = false)
    private String type;

    @OneToMany(mappedBy = "provinceEntity")
    @JsonIgnore
    private Set<DistrictEntity> districtEntities;

    @OneToMany(mappedBy = "provinceEntity")
    @JsonIgnore
    private Set<AreaEntity> areaEntities;

}
