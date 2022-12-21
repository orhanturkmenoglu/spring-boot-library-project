package com.example.springbootlibraryproject;

import com.example.springbootlibraryproject.repository.BookRepository;
import com.example.springbootlibraryproject.repository.BorrowerRepository;
import com.example.springbootlibraryproject.repository.ContactRepository;
import com.example.springbootlibraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootLibraryProjectApplication implements CommandLineRunner {


    private final BookRepository bookRepository;
    private final ContactRepository contactRepository;
    private final BorrowerRepository borrowerRepository;
    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLibraryProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*		Book book1 = Book.builder()
				.name("Bir Ömür Nasıl Yaşanır ?")
				.amountOfStock(5L)
				.barcode("12345678900")
				.creationDate(LocalDate.now())
				.numbersOfPages(285L)
				.publisher("Kronik Yayıncılık")
				.author("İlber Ortaylı")
				.build();

		bookRepository.save(book1);


		Contact contact1 = Contact.builder()
				.address("Gazi Mah")
				.city("Hatay")
				.district("Kırıkhan")
				.email("example@example.com")
				.phoneNumber("5417678686")
				.build();

		List<Contact> contactList = Arrays.asList(contact1);

		contactRepository.saveAll(contactList);

		Member member1 = Member.builder()
				.firstName("Orhan")
				.lastName("Türkmenoğlu")
				.gender(Gender.MALE)
				.identityNo("12345678900")
				.contactList(contactList)
				.build();


		memberRepository.save(member1);



		contact1.setMember(member1);
		contactRepository.saveAll(contactList);


		Borrower borrower = Borrower.builder()
				.book(book1)
				.member(member1)
				.date(LocalDate.now())
				.status(false)
				.returnDate(LocalDate.of(2022,12,29))
				.build();

		borrowerRepository.save(borrower);*/
    }
}
