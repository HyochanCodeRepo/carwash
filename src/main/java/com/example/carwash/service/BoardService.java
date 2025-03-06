package com.example.carwash.service;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.RequestPageDTO;
import com.example.carwash.dto.ResponsePageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    public BoardDTO register(BoardDTO boardDTO, String email);


    public int boardCount(Pageable pageable);

    public ResponsePageDTO<BoardDTO> listPage(RequestPageDTO requestPageDTO);

    public ResponsePageDTO<BoardDTO> categList(RequestPageDTO requestPageDTO);


    public BoardDTO read(Long num);

    public BoardDTO update(BoardDTO boardDTO);

    public Long del(Long num);

}
