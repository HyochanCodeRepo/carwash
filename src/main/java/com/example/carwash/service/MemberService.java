package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;

public interface MemberService {

    public String signUp(MemberDTO memberDTO);

    public void mypage(MemberDTO memberDTO, Principal principal);

    public MemberDTO read(String email);



}
