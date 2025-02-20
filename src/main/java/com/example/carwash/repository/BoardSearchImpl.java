package com.example.carwash.repository;

import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {


    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search(String[] types, String keyword,  Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        System.out.println("쿼리문 1 : " + query);

        //type에 따른 where 문
        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();


            for (String type : types) {
                if (type.equals("t")) {
                    booleanBuilder.or(board.title.contains(keyword));
                } else if (type.equals("c")) {
                    booleanBuilder.or(board.content.contains(keyword));
                } else if (type.equals("w")) {
                    booleanBuilder.or(board.writer.contains(keyword));
                }
            }
            //위에 만들어진 booleanBuilder를 가지고
            query.where(booleanBuilder);
        }

        System.out.println("쿼리문 where 추가 : " + query);

        query.where(board.num.gt(0L));
        System.out.println("쿼리문 where 추가 : " + query);

        //페이징
        this.getQuerydsl().applyPagination(pageable, query);

        //리스트
        List<Board> boardList =
                query.fetch();

        //총게시물
        long count =
                query.fetchCount();


        return new PageImpl<>(boardList, pageable, count);
    }
}
