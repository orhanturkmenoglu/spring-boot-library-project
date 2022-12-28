package com.example.springbootlibraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootLibraryProjectApplication implements CommandLineRunner {


/*
    private final BookRepository bookRepository;
    private final ContactRepository contactRepository;
    private final BorrowerRepository borrowerRepository;
    private final MemberRepository memberRepository;
*/


    public static void main(String[] args) {
        SpringApplication.run(SpringBootLibraryProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

   /*     Book book2 = Book.builder()
                .name("Bir Ömür Nasıl Yaşanır ?")
                .amountOfStock(5L)
                .barcode("12345678900")
                .creationDate(LocalDate.now())
                .numbersOfPages(285L)
                .publisher("Kronik Yayıncılık")
                .author("İlber Ortaylı")
                .build();

        bookRepository.save(book2);


        Contact contact2 = Contact.builder()
                .address("Gazi Mah")
                .city("Hatay")
                .district("Kırıkhan")
                .email("example@example.com")
                .phoneNumber("5417678686")
                .build();

        List<Contact> contactList = Arrays.asList(contact2);

        contactRepository.saveAll(contactList);

        Member member2 = Member.builder()
                .firstName("Orhan")
                .lastName("Türkmenoğlu")
                .gender(Gender.MALE)
                .identityNo("12345678900")
                .build();

        memberRepository.save(member2);


        contact2.setMember(member2);
        contactRepository.saveAll(contactList);


        Borrower borrower = Borrower.builder()
                .book(book2)
                .date(LocalDate.now())
                .status(true)
                .member(member2)
                .returnDate(LocalDate.of(2023, 01, 15))
                .build();

        borrowerRepository.save(borrower);


        borrowerRepository.save(borrower);
*/

    }
}
