package com.example.carwash.controller;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.repository.BoardRepository;
import com.example.carwash.repository.CategRepository;
import com.example.carwash.service.BoardService;
import com.example.carwash.service.CategService;
import jakarta.persistence.EntityNotFoundException;
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

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    public String registerPost(BoardDTO boardDTO, Principal principal) {
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);
        log.info("등록 포스트 진입 : " + boardDTO);

        log.info(principal.getName());
        log.info(principal.getName());
        log.info(principal.getName());
        log.info(principal.getName());
        log.info(principal.getName());
        boardDTO.setWriter(principal.getName());

        boardDTO =
                boardService.register(boardDTO, principal.getName());
        log.info("저장된 데이터 :" + boardDTO);
        log.info(boardDTO.getCateg_num());
        log.info(boardDTO.getCateg_num());

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

    @GetMapping("/listB")
    public String listB(Model model, RequestPageDTO requestPageDTO) {
        log.info(requestPageDTO);
        log.info(requestPageDTO.getLink());
        log.info(requestPageDTO.getPageable());

        if(requestPageDTO.getCateg_num() == null){
            requestPageDTO.setCateg_num(2L );

        }

        ResponsePageDTO<BoardDTO> responsePageDTO=
            boardService.categList(requestPageDTO);




        model.addAttribute("responsePageDTO",responsePageDTO);
        return "board/listB";
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

    @PostMapping("/modify")
    public String modifyPost(BoardDTO boardDTO, Model model, RequestPageDTO requestPageDTO) {
        log.info("modify Post 진입" + boardDTO);
        log.info("modify Post 진입" + requestPageDTO);

        boardDTO =
                boardService.update(boardDTO);

        log.info("수정된 데이터 출력 " + boardDTO);

        return "redirect:/board/read?num=" + boardDTO.getNum() + '&' + requestPageDTO.getLink();
    }

    @PostMapping("/del")
    public String del(BoardDTO boardDTO) {
        log.info("삭제 post 진입!" + boardDTO);
        log.info("삭제 post 진입!" + boardDTO.getNum());

        boardService.del(boardDTO.getNum());


        return "redirect:/board/listA";

    }



}
