package com.example.carwash.repository;

import com.example.carwash.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    public Page<Board> search(String[] types, String keyword, Pageable pageable);
}
