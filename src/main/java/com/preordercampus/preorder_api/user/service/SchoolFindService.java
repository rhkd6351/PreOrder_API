package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.repository.SchoolRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolFindService {

    @Autowired
    SchoolRepository schoolRepository;

    @Transactional(readOnly = true)
    public SchoolVO findByIdx(Long idx) throws NotFoundException {
        return schoolRepository.findById(idx).orElseThrow(
                () -> new NotFoundException("invalid school idx"));
    }

    @Transactional(readOnly = true)
    public List<SchoolVO> findAll(){
        return schoolRepository.findAll();
    }


}
