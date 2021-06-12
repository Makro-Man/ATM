package com.atm.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String number;
    @Column
    private String pinCode;
    @Column
    private Long balance;
    @OneToOne
    private User user;

    public Card() {

        this.balance = 0L;
    }
}
