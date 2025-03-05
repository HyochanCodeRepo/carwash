package com.example.carwash.dto;

import com.example.carwash.entity.Categ;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDTO {

    private Long num;

    private Long categ_num;

    private String title;

    private String content;

    private String writer;

    private Categ categ;

    private LocalDateTime regTime;
    private LocalDateTime modTime;
}
