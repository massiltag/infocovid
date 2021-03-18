package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.Test;
import com.pantheonsorbonne.infocovid.repositories.TestRepository;
import com.pantheonsorbonne.infocovid.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public void save() {
        testRepository.save(Test.builder().id(1).attrCustom("hello").build());
    }
}
