package com.example.demo.service;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Address;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Long register(MemberForm form) {
        String memberName = form.getName();

        String city = form.getCity();
        String street = form.getStreet();
        String zipcode = form.getZipcode();
        Address address = new Address(city,street,zipcode);

        Member member = new Member();
        member.setName(memberName);
        member.setAddress(address);
        memberRepository.save(member);

        return member.getId();

    }

    public List<MemberForm> showMembers() {
        List<MemberForm> memberFormList = memberRepository.findAll().stream().map(member -> new MemberForm(member.getName(),
            member.getAddress().getCity(), member.getAddress().getStreet(),member.getAddress().getZipcode())).toList();

        return memberFormList;
    }
}
