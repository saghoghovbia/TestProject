package com.example.TestProject.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Characters {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    private String series;

    private String bio;
}
// "id":7,"name":"Tenma,"age":28,"series":"Monster","bio":"Testing out the post map"