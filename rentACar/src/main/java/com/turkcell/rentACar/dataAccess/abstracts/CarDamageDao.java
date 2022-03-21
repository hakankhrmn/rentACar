package com.turkcell.rentACar.dataAccess.abstracts;

import com.turkcell.rentACar.entities.concretes.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {
    boolean existsByCarDamageId(int id);
    List<CarDamage> findByCar_CarId(int id);
}
