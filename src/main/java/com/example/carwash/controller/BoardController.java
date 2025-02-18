package com.example.carwash.controller;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.repository.BoardRepository;
import com.example.carwash.service.BoardService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/register")
    public String register() {

        return "board/register";

    }

    @PostMapping("/register")
    public String registerPost(Board board) {
        log.info("등록 포스트 진입 : " + board);
        log.info("등록 포스트 진입 : " + board);
        log.info("등록 포스트 진입 : " + board);
        log.info("등록 포스트 진입 : " + board);
        log.info("등록 포스트 진입 : " + board);


        boardRepository.save(board);
        return "redirect:/board/register";

    }

    @GetMapping("/list")
    public String list(Model model) {
        log.info("리스트페이지 진입!!");
        List<Board> boardList=
            boardRepository.findAll();

        boardList.forEach(board -> log.info(board));

        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/main")
    public void main() {

    }



}
