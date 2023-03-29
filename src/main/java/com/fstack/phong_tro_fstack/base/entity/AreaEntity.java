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

    @OneToOne
    @JoinColumn(name = "id_address", nullable = false)
    private AddressEntity addressEntity;

    @OneToOne(mappedBy = "areaEntity")
    private PostEntity postEntity;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RateEntity> rateEntities;

    @OneToMany(mappedBy = "areaEntity")
    private Set<RoomEntity> roomEntities;
}
