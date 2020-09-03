package dmly.hibernate.test.repository;

import dmly.hibernate.test.entity.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private final EntityManager entityManager;

    public BookRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Book> findById(final Long id) {
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public List<Book> findAll() {
        return entityManager.createQuery("from Book").getResultList();
    }

    public Optional<Book> findByName(final String name) {
        Book book = entityManager.createQuery("select b FROM  Book b where b.name = :name", Book.class)
                .setParameter("name", name)
                .getSingleResult();

        return book != null ? Optional.of(book) : Optional.empty();
    }

    public Optional<Book> findByNameNamedQuery(final String name) {
        Book book = entityManager.createNamedQuery("Book.findByName", Book.class)
                .setParameter("name", name)
                .getSingleResult();

        return book == null ? Optional.empty() : Optional.of(book);
    }

    public Optional<Book> save(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return Optional.of(book);
    }


}
