package com.atm.dao;

import com.atm.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CardRepository extends JpaRepository<Card,Integer> {

    @Query("select c from Card c where c.number=?1")
    Optional<Card> findCardByNumber(String number);
}
