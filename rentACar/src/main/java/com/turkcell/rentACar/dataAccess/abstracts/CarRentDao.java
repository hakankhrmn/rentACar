package com.turkcell.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentACar.entities.concretes.CarRent;

@Repository
public interface CarRentDao extends JpaRepository<CarRent,Integer> {
	List<CarRent> getAllByCar_CarId(Integer id);
}
