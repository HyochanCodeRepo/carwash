package com.example.carwash.service;

import com.example.carwash.dto.CategDTO;
import com.example.carwash.entity.Categ;
import com.example.carwash.repository.CategRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CategServiceImpl implements CategService{
    private final CategRepository categRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategDTO register(CategDTO categDTO) {
        Categ categ = modelMapper.map(categDTO, Categ.class);

        categ =
            categRepository.save(categ);
        categDTO =
            modelMapper.map(categ, CategDTO.class);

        return categDTO;
    }
}
