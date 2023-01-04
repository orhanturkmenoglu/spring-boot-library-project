package com.example.springbootlibraryproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/*borrower : ödünç alan */
@Entity
@Table(name = "borrowers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    private LocalDate date;

    private LocalDate returnDate;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Member member;

    @OneToOne
    private Book book;

    private Long amountBorrowed;
}
