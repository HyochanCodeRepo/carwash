package com.example.carwash.controller;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.MemberDTO;
import com.example.carwash.entity.Member;
import com.example.carwash.repository.MemberRepository;
import com.example.carwash.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/signUp")
    public String signUp(MemberDTO memberDTO) {

        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String signUpPost(@Valid MemberDTO memberDTO, BindingResult bindingResult) {
        log.info("회원가입 포스트 진입");
        log.info("회원가입 포스트 진입");
        log.info(memberDTO);

        //에러 있으면 보낼 경로
        if (bindingResult.hasErrors()) {
            log.info("유효성 검사 에러!");
            log.info(bindingResult.getAllErrors());
            return "user/signUp";
        }

        try {
            memberService.signUp(memberDTO);


        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "user/signUp";
        }


        return "redirect:/user/signUp";
    }

    @GetMapping("/login")
    public String login() {

        return "user/login";

    }

    @GetMapping("/mypage")
    public String mypage(Principal principal, Model model){
        log.info("마이페이지 진입!");
        log.info("마이페이지 진입!");
        log.info(principal.getName());

        if (principal.getName() == null) {
            return "redirect:/user/login";
        }

        MemberDTO memberDTO =
            memberService.read(principal.getName());


        log.info(memberDTO);
        log.info(memberDTO);
        log.info(memberDTO);
        log.info(memberDTO);

        model.addAttribute("memberDTO",memberDTO);




        return "user/mypage";
    }

    @PostMapping("/mypage")
    public String mypage(BoardDTO boardDTO, Principal principal) {

        log.info(principal.getName());
        log.info(principal.getName());
        log.info(boardDTO);
        log.info(boardDTO);
        log.info(boardDTO);
        return "user/list";

    }
}
