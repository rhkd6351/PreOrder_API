package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolFindService {

    @Autowired
    SchoolRepository schoolRepository;

    @Transactional(readOnly = true)
    public SchoolVO findByIdx(Long idx){
        return schoolRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public SchoolVO findByIdxAndCategory(Long idx, Long categoryIdx){
        return schoolRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public List<SchoolVO> findAll(){
        return schoolRepository.findAll();
    }


}
