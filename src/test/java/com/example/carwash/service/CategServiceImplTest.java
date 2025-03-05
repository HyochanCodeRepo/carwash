package com.example.carwash.service;

import com.example.carwash.dto.CategDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class CategServiceImplTest {

    @Autowired
    CategService categService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void insertTest(){
        CategDTO categDTO = new CategDTO();
        categDTO.setName("자유");


    }

}