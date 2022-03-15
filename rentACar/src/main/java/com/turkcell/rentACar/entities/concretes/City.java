package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private int cityId;

    @Column(name="city_name")
    private String cityName;

    @OneToMany(mappedBy = "rentCity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarRent> carRentRentCities;

    @OneToMany(mappedBy = "returnCity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarRent> carRentReturnCities;
}
