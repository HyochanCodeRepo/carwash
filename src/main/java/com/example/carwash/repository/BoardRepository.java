package com.example.carwash.repository;

import com.example.carwash.dto.BoardDTO;
import com.example.carwash.dto.ResponsePageDTO;
import com.example.carwash.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardSearch {


    @Query("select b from Board b")
    public Page<Board> pagination2(Pageable pageable);





}
