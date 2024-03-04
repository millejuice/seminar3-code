package com.example.ThirdSeminar.user.service;

import com.example.ThirdSeminar.user.dto.SignUpDto;
import com.example.ThirdSeminar.user.dto.SignUpResponse;
import com.example.ThirdSeminar.user.entity.UserEntity;
import com.example.ThirdSeminar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    Create
    public SignUpResponse addUser(SignUpDto signUpDto){ //Controller에서 SignUpDto형식을 받고, 결과도 SignUpDto형식으로 돌려준다
//        userRepository.save(signUpDto); 우리의 repository에 dto를 그냥 넣을 수 있을까?

//        dto를 repository에 저장할 수 있게 entity로 바꾸는 방법 1 -> Builder를 사용한
        UserEntity user = UserEntity.builder()
                .userEmail(signUpDto.getUserEmail())
                .userPassword(signUpDto.getUserPassword())
                .build();
        userRepository.save(user); //userRepository에 save라는 함수를 안만들었는데 JPA를 참조했기 때문에 사용가능하다
//        return 해주는 객체에 맞게 만들어줘야 함
        SignUpResponse ret = SignUpResponse.builder()
                .userNum(user.getUserId())
                .userEmail(user.getUserEmail())
                .build();
        return ret;
    }

//    READ -> userNum으로 이메일, 비밀번호 찾기
    public SignUpDto getOneUser(Long userNum){
        UserEntity userEntity = userRepository.findById(userNum) //userRepository에 findById라는 함수를 안만들었는데 JPA를 참조했기 때문에 사용가능하다
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다.")); //userNum에 해당하는 유저가 없으면 예외처리
        SignUpDto ret = new SignUpDto(userEntity.getUserEmail(), userEntity.getUserPassword());
        return ret;
    }

//    READ -> 모든 user의 이메일, 비밀번호 찾기
    public List<SignUpResponse> findAllUser(){
        List<UserEntity> users = userRepository.findAll();
        List<SignUpResponse> ret = new ArrayList<>();
        for(UserEntity user : users){
            ret.add(SignUpResponse.builder()
                    .userNum(user.getUserId())
                    .userEmail(user.getUserEmail())
                    .build());
        }
        return ret;
    }
//    UPDATE
    public SignUpDto update(Long userNum, SignUpDto signUpDto){
        Optional<UserEntity> user = userRepository.findById(userNum);
        if(user.isPresent()){
            user.get().setUserEmail(signUpDto.getUserEmail());
            user.get().setUserPassword(signUpDto.getUserPassword());
            userRepository.save(user.get());
            SignUpDto ret = SignUpDto.builder()
                    .userEmail(user.get().getUserEmail())
                    .userPassword(user.get().getUserPassword())
                    .build();
            return ret;
        }
        else{
            throw new IllegalArgumentException("해당 유저가 없습니다.");
        }
    }

//    DELETE

    public void delete(Long userNum){
        userRepository.deleteById(userNum);
    }
}
