package com.preordercampus.preorder_api.user.controller;

import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.dto.SchoolDTO;
import com.preordercampus.preorder_api.user.service.SchoolFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    SchoolFindService schoolFindService;


    @GetMapping("/v1/user/schools")
    public ResponseEntity<List<SchoolDTO>> getSchoolList(){
        List<SchoolVO> schools = schoolFindService.findAll();
        List<SchoolDTO> dtos = schools.stream().map(SchoolDTO::formEntity).collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }




}
