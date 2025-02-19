package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
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

}