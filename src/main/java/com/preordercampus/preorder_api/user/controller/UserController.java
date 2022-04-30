package com.preordercampus.preorder_api.user.controller;

import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.dto.CreateUser;
import com.preordercampus.preorder_api.user.dto.UserDTO;
import com.preordercampus.preorder_api.user.service.UserFindService;
import com.preordercampus.preorder_api.user.service.UserUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    UserUpdateService userUpdateService;
    UserFindService userFindService;

    public UserController(UserUpdateService userUpdateService, UserFindService userFindService) {
        this.userUpdateService = userUpdateService;
        this.userFindService = userFindService;
    }

    @PostMapping("/v1/user")
    public ResponseEntity<CreateUser.Response> signUp(
            @RequestBody @Validated CreateUser.Request request
    ) {
        Long idx = userUpdateService.saveStudentUser(request);

        return new ResponseEntity<>(new CreateUser.Response(idx), HttpStatus.CREATED);
    }

    @GetMapping("/v1/user/verify/{verify-code}")
    public ResponseEntity<String> certifyEmail(
            @PathVariable("verify-code") String code) {
        userUpdateService.verifyUserEmail(code);

        return new ResponseEntity<>("user email verified", HttpStatus.OK);
    }

    @GetMapping("/v1/user")
    public ResponseEntity<UserDTO> getMyInfo() {
        UserVO vo = userFindService.getMyUserWithAuthorities();
        UserDTO dto = UserDTO.fromEntity(vo);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
