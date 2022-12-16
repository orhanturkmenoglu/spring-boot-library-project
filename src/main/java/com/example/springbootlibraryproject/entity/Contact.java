package com.example.springbootlibraryproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String district; // ilçe
    private String address;
    private String phoneNumber;
    private String email;


    @ManyToOne
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    @NotNull
    private Member  member;


}
