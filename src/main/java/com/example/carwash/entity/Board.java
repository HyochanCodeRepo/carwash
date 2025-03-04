package com.example.carwash.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_board")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Long num;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 2000, nullable = true)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;


}

