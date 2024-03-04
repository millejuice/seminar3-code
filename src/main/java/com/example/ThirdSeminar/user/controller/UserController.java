package com.example.ThirdSeminar.user.controller;

import com.example.ThirdSeminar.user.dto.SignUpDto;
import com.example.ThirdSeminar.user.dto.SignUpResponse;
import com.example.ThirdSeminar.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; //controller는 dispatcher servlet에서 경로 요청 들어오면 service로직을 실행시켜야 함

    @PostMapping("/users") //경로 이름 restful하게 명사 복수형
    public ResponseEntity<SignUpResponse> create(@RequestBody SignUpDto signUpDto){
        SignUpResponse returnToClient = userService.addUser(signUpDto);
        return new ResponseEntity<>(returnToClient, HttpStatus.CREATED); //    ResponseEntity는 HTTP 응답 상태 코드, 헤더 및 본문을 포함하는 클래스
    }

    @GetMapping("/user/{userNum}")
    public ResponseEntity<SignUpDto> read(@PathVariable Long userNum){
        SignUpDto returnToClient = userService.getOneUser(userNum);
        return new ResponseEntity<>(returnToClient, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<SignUpResponse>> readAll(){
        List<SignUpResponse> ret = userService.findAllUser();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PatchMapping("/user/{userNum}")
    public ResponseEntity<SignUpDto> update(@PathVariable Long userNum, @RequestBody SignUpDto signUpDto){
        SignUpDto ret = userService.update(userNum, signUpDto);
        return ResponseEntity.ok().body(ret); //responseEntity.ok()는 200번대 코드를 반환. response 주는 또다른 방법
    }

    @DeleteMapping("/user/{userNum}")
    public ResponseEntity<?> delete(@PathVariable Long userNum){
        userService.delete(userNum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
