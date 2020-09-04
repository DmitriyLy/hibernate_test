package dmly.hibernate.test;

import dmly.hibernate.test.entity.Author;
import dmly.hibernate.test.entity.Book;
import dmly.hibernate.test.repository.AuthorRepository;
import dmly.hibernate.test.repository.BookRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OneToManyTest {

    @BeforeAll
    static void init() {
        TestUtils.init();
    }

    @AfterAll
    static void destroy() {
        TestUtils.destroy();
    }

    @Test
    void runOneToManyTest() {
        EntityManager entityManager = TestUtils.getEntityManager();

        AuthorRepository authorRepository = new AuthorRepository(entityManager);
        BookRepository bookRepository = new BookRepository(entityManager);

        Author author = new Author("Author 1");
        author.addBook(new Book("Book 1"));
        author.addBook(new Book("Book 2"));
        author.addBook(new Book("Book 3"));

        Optional<Author> savedAuthor = authorRepository.save(author);
        System.out.println("Saved author: " + savedAuthor.get());

        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);

        Optional<Author> authorByName = authorRepository.findByNane("Author 1");
        System.out.println("find author by name: ");
        authorByName.ifPresent(System.out::println);

        Optional<Book> bookById = bookRepository.findById(2L);
        bookById.ifPresent(System.out::println);

        Optional<Book> bookByInvalidId = bookRepository.findById(99L);
        bookByInvalidId.ifPresent(System.out::println);

        List<Book> books = bookRepository.findAll();
        System.out.println("Books in database:");
        books.forEach(System.out::println);

        Optional<Book> bookByName = bookRepository.findByName("Book 2");
        System.out.println("Book by name 'Book 2'");
        bookByName.ifPresent(System.out::println);

        Optional<Book> bookByNamedQuery = bookRepository.findByNameNamedQuery("Book 3");
        System.out.println("Book by name 'Book 3'");
        bookByNamedQuery.ifPresent(System.out::println);

        Optional<Author> author1 = authorRepository.findById(1L);
        author1.ifPresent(a -> {
            a.addBook(new Book("Book 4"));
            System.out.println("Saved author: " + authorRepository.save(a));
        });

        authors = authorRepository.findAll();
        authors.forEach(System.out::println);

    }

}
