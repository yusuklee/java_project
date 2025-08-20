package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberForm {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
