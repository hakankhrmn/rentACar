package com.turkcell.rentACar.dataAccess.abstracts;

import com.turkcell.rentACar.entities.concretes.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {
    Card findById(int id);
    List<Card> findByCustomer_CustomerId(int id);
}
