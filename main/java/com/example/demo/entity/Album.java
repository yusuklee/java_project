package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
@NoArgsConstructor
public class Album extends Item{
    private String artist;
    private String etc;
}
