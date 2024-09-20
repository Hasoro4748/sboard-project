package com.sboard.service;

import com.sboard.dto.TermsDTO;
import com.sboard.entity.Terms;
import com.sboard.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TermsService {
    private final TermsRepository termsRepository;

    public TermsDTO selectTerms(){
        Terms terms = termsRepository.findAll().get(0);
        return terms.toDTO();
    }
}
