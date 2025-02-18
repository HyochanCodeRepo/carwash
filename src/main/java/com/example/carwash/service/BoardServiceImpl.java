package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.entity.Board;
import com.example.carwash.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
