package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="car-damages")
public class CarDamage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_damage_id")
    private int carDamageId;

    @Column(name="car_damage_information")
    private String carDamageInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
