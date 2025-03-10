package com.example.carwash.entity;

import com.example.carwash.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_num")
    private Long num;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;


    @Enumerated(EnumType.STRING)
    private Role role;
}
