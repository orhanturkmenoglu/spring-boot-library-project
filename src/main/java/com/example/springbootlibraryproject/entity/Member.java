package com.example.springbootlibraryproject.entity;

import com.example.springbootlibraryproject.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String identityNo;

    @Enumerated
    private Gender gender;
    

    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL,optional = true)
    @JsonBackReference
    private Contact contact;

}
