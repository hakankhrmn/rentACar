package com.turkcell.rentACar.dataAccess.abstracts;

import com.turkcell.rentACar.entities.concretes.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentDao extends JpaRepository<CarRent,Integer> {
	List<CarRent> getAllByCar_CarId(Integer id);
	CarRent findById(int id);
}
