package com.example.onlineshop.model;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;
    @Column
    private String productName;

    @Column
    private Long quantity;
    @Column
    private float amount;

}