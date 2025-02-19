package com.example.carwash.controller;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.repository.BoardRepository;
import com.example.carwash.service.BoardService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
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
    private final BoardService boardService;

    @GetMapping("/register")
    public String register() {

        return "board/register";

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO) {
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);


        boardDTO =
                boardService.register(boardDTO);
        log.info("저장된 데이터 :" + boardDTO);
        return "redirect:/board/listA";

    }

    @GetMapping("/list")
    public String list(Model model) {
        log.info("리스트페이지 진입!!");
        List<Board> boardList =
                boardRepository.findAll();

        boardList.forEach(board -> log.info(board));

        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/listA")
    public String listA(Model model, RequestPageDTO requestPageDTO) {
        log.info(requestPageDTO);
        log.info(requestPageDTO.getLink());
        log.info(requestPageDTO.getPageable());

        ResponsePageDTO responsePageDTO =
                boardService.listPage(requestPageDTO);
        model.addAttribute("responsePageDTO", responsePageDTO);

        return "board/listA";


    }

    @GetMapping("/read")
    public String read(Long num, Model model, RequestPageDTO requestPageDTO) {

        log.info("read 진입 num :" + num);
        log.info("read 진입 num :" + num);
        log.info("read 진입 num :" + num);
        if (num == null) {
            return "redirect:/board/listA";
        }
        BoardDTO boardDTO =
                boardService.read(num);
        log.info(boardDTO);

        model.addAttribute("boardDTO", boardDTO);

        return "board/read";
    }

//    @GetMapping("listA")
//    public String listA(Model model, RequestPageDTO requestPageDTO) {
//        log.info(requestPageDTO);
//        log.info(requestPageDTO.getLink());
//        log.info(requestPageDTO.getPageable());
//
//        Page<Board> boardPage =
//                boardRepository.findAll(requestPageDTO.getPageable("num"));
//
//        List<Board> boardList=
//        boardPage.getContent();
//
//        model.addAttribute("boardList",boardList);
//        model.addAttribute("count",boardPage.getTotalElements());
//
//
//    }

    //수정페이지
    @GetMapping("/modify")
    public String modify(Long num, Model model, RequestPageDTO requestPageDTO) {
        log.info("modify 진입" + num);
        log.info("modify 진입" + requestPageDTO);

        if (num == null) {
            return "redirect:/board/listA";
        }
        BoardDTO boardDTO =
            boardService.read(num);

        model.addAttribute("boardDTO", boardDTO);



        return "board/modify";
    }
}
