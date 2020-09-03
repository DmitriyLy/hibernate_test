package dmly.hibernate.test.repository;

import dmly.hibernate.test.entity.Author;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AuthorRepository {
    private final EntityManager entityManager;

    public AuthorRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Author> findById(final Long id) {
        Author author = entityManager.find(Author.class, id);
        return author == null ? Optional.empty() : Optional.of(author);
    }

    public List<Author> findAll() {
        return entityManager.createQuery("from Author ").getResultList();
    }

    public Optional<Author> findByNane(final String name) {
        Author author = entityManager.createQuery("select a from Author a where a.name = :name", Author.class).
                setParameter("name", name)
                .getSingleResult();

        return author == null ? Optional.empty() : Optional.of(author);
    }

    public Optional<Author> save(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        return Optional.of(author);
    }
}
