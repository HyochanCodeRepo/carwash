package com.example.carwash.repository;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.entity.Categ;
import com.example.carwash.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CategRepository categRepository;




    @Test
    public void registerTest(){

        Member member = memberRepository.findById(5L).get();


        for (int i = 0; i < 50; i++) {

            Board board = new Board();

            board.setTitle(i+"번째 제목");
            board.setContent(i+"번째 내용");
            board.setWriter("이효찬"+i);
            board.setMember(member);

            boardRepository.save(board);
        }

    }
    @Test
    @Transactional
    public void readTest(){

        Optional<Board> optionalBoard =
            boardRepository.findById(49L);

        try {
            Board board =
                    optionalBoard.orElseThrow(EntityNotFoundException::new);

                log.info(board.getMember().getName());
                log.info(board.getMember().getName());
                log.info(board.getMember().getName());
                log.info(board.getMember().getName());


        }catch(EntityNotFoundException e) {
            log.info("글 번호가 존재하지않습니다.");
            e.printStackTrace();
        }

    }

    @Test
    public void insertTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("안녕하세요!!");
        boardDTO.setContent("내용 수정하기 페이지 수정해야돼요");
        boardDTO.setWriter("홍길동");
        boardDTO.setCateg_num(1L);

    }

    @Test
    public void findallTest() {
        List<Board> boardList =
            boardRepository.findAll();

        boardList.forEach(board -> log.info(board));
    }

    @Test
    public void updateTest(){
        Board board =
            boardRepository.findById(1L).get();
        board.setTitle("제목 수정합니다~");
        board.setContent("내용 수정합니다~~");

        boardRepository.save(board);
    }

    @Test
    public void delTest(){
        boardRepository.deleteById(3L);

    }

    @Test
    @Transactional
    public void listTest(){

        Page<Board> boardList =
            boardRepository.findByCategNum(1L , PageRequest.of(0,10));

        log.info(boardList.getContent());

    }

}