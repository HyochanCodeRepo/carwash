package com.example.carwash.repository;

import com.example.carwash.dto.CategDTO;
import com.example.carwash.entity.Categ;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2
class CategRepositoryTest {

    @Autowired
    CategRepository categRepository;


    @Test
    public void insertTest(){

        Categ categ = new Categ();
        categ.setName("거래");

        categRepository.save(categ);








    }

}