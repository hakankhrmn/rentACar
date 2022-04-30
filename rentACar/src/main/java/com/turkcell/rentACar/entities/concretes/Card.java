package com.turkcell.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private int cardId;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "mounth")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;
}
