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
public class Board {

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

    private LocalDateTime regTime;
    private LocalDateTime modTime;

}

