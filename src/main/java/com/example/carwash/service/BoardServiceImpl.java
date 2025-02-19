package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private ModelMapper modelMapper = new ModelMapper();
    private final BoardRepository boardRepository;


    @Override
    public BoardDTO register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);

        board =
            boardRepository.save(board);
        boardDTO = modelMapper.map(board, BoardDTO.class);


        return boardDTO;
    }

    @Override
    public int boardCount(Pageable pageable) {

        Page<Board> boardPage =
            boardRepository.pagination2(pageable);
        int count =
            boardPage.getTotalPages();

        return count;
    }

    @Override
    public ResponsePageDTO<BoardDTO> listPage(RequestPageDTO requestPageDTO) {

        log.info(requestPageDTO);

        Pageable pageable =
            requestPageDTO.getPageable("num");

        //검색처리
        Page<Board> boardPage =
                boardRepository.search(requestPageDTO.getType(), requestPageDTO.getKeyword(), pageable);

        int total = (int) boardPage.getTotalElements();//총게시물 수

        List<Board> boardList = boardPage.getContent();

        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            boardDTOList.add(boardDTO);

        }
        ResponsePageDTO<BoardDTO> responsePageDTO =
                new ResponsePageDTO<>(requestPageDTO, boardDTOList, total);

        return responsePageDTO;
    }

    @Override
    public BoardDTO read(Long num) {
        Optional<Board> optionalBoard =
                boardRepository.findById(num);
        Board board =
            optionalBoard.get();

        //dto로 변환
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO) {
        Optional<Board> optionalBoard =
            boardRepository.findById(boardDTO.getNum());
        Board board =
            optionalBoard.get();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());

        boardDTO = modelMapper.map(board, BoardDTO.class);


        return boardDTO;
    }
}
