package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/회원가입")
    public ResponseEntity<?> createUser(@RequestBody MemberForm form){
          Long id=memberService.register(form);
          return ResponseEntity.ok(id);

    }

    @GetMapping("/회원목록")
    public List<MemberForm> showMembers(){
        return memberService.showMembers();
    }
}
