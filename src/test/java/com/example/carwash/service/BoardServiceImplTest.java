package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Categ;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2

class BoardServiceImplTest {

    @Autowired
    BoardService boardService;


    @Test
    public void listPageTest(){
        RequestPageDTO requestPageDTO = new RequestPageDTO();
        requestPageDTO.setKeyword("1");
        requestPageDTO.setType("c");

        ResponsePageDTO<BoardDTO> responsePageDTO =
                boardService.listPage(requestPageDTO);

        responsePageDTO.getDtoList().forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void readTest(){
        BoardDTO boardDTO =
            boardService.read(1L);
        log.info(boardDTO);

    }

    @Test
    public void insertTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("카테1테스트");
        boardDTO.setContent("카테1");
        boardDTO.setWriter("카테1");


        //부모 카테고리의 id <option th:value="${caDTO.id}" th:text={caDTO.name}> </option>
        boardDTO.setCateg_num(1L);

        String email = "hyo@a.a";


        boardService.register(boardDTO, email);


    }

}