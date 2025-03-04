package com.example.carwash.service;

import com.example.carwash.constant.Role;
import com.example.carwash.dto.MemberDTO;
import com.example.carwash.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    public void signUpTest(){

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("이효찬");
        memberDTO.setEmail("hyo@a.a");
        memberDTO.setAddress("경기도 부천");
        memberDTO.setRole(Role.ADMIN);
        memberDTO.setPassword("1234");

        memberService.signUp(memberDTO);

    }



}