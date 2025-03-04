package com.example.carwash.repository;

import com.example.carwash.entity.Board;
import com.example.carwash.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}