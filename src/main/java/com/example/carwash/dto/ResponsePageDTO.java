package com.example.carwash.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ResponsePageDTO<E> {

    //뷰페이지로 보낼대 뷰페이지에서 사용할 필드값들

    private int page;
    private int size;
    private int total;

    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<E> dtoList;


    public ResponsePageDTO(RequestPageDTO requestPageDTO, List<E> dtoList, int total) {
        if (total <= 0) {
            return;
        }
        this.page = requestPageDTO.getPage();
        this.size = requestPageDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0))*10; //화면의 마지막 번호
        this.start = end - 9;
        int last =   (int) (Math.ceil(   (total / (double) size  )   ) );
        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }




}
