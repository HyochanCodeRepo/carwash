package com.example.carwash.service;

import com.example.carwash.dto.MemberDTO;
import org.springframework.stereotype.Service;

public interface MemberService {

    public String signUp(MemberDTO memberDTO);
}
