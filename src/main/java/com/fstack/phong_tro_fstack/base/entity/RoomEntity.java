package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity extends BaseEntity{
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "image", columnDefinition = "text", nullable = false)
    private String image;

    @Column(name = "video", columnDefinition = "text", nullable = false)
    private String video;

    @Column(name = "electric_service", nullable = false)
    private Float electricService;

    @Column(name = "water_service", nullable = false)
    private Float waterService;

    @Column(name = "rent_price", nullable = false)
    private Float rentPrice;

    @Column(name = "acreage", nullable = false)
    private Float acreage;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private AreaEntity areaEntity;
}
