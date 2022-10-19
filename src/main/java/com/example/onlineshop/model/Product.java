package com.example.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;
    @Column
    private Long count;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;
}
