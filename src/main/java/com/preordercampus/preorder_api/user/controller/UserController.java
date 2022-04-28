package com.preordercampus.preorder_api.user.controller;

import com.preordercampus.preorder_api.user.dto.CreateUser;
import com.preordercampus.preorder_api.user.service.UserUpdateService;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    UserUpdateService userUpdateService;

    public UserController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping("/v1/user")
    public ResponseEntity<CreateUser.Response> signUp(
            @RequestBody @Validated CreateUser.Request request
    ) throws NotFoundException, DuplicateMemberException {
        Long idx = userUpdateService.saveStudentUser(request);

        return new ResponseEntity<>(new CreateUser.Response(idx), HttpStatus.CREATED);
    }

    @GetMapping("/v1/user/verify/{verify-code}")
    public ResponseEntity<String> certifyEmail(
            @PathVariable("verify-code") String code) throws NotFoundException {
        userUpdateService.verifyUserEmail(code);

        return new ResponseEntity<>("user email verified", HttpStatus.OK);
    }


}
