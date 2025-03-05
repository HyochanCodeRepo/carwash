package com.example.carwash.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_categ")
public class Categ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categ_num")
    private Long num;

    @Column(length = 10, nullable = false)
    private String name;
}
