package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity extends BaseEntity{
    @Column(name = "ward", length = 255)
    private String ward;

    @Column(name = "district", length = 255)
    private String district;

    @Column(name = "city", length = 255)
    private String city;

    @OneToOne(mappedBy = "addressEntity")
    private AreaEntity areaEntity;
}
