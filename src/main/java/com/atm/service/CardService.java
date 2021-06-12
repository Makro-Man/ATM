package com.atm.service;

import com.atm.dao.CardRepository;
import com.atm.domain.Card;
import com.atm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void save(Card card){
        card.setPinCode(bCryptPasswordEncoder.encode(card.getPinCode()));
        cardRepository.save(card);
    }

    public void saveChange(Card card) {
        cardRepository.save(card);
    }

    public Card findCardByNumber(String number) {

        return cardRepository.findCardByNumber(number).get();
    }


    public List<Card> getAllCard(){
        return cardRepository.findAll();
    }


}
