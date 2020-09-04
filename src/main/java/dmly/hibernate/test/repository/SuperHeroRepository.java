package dmly.hibernate.test.repository;

import dmly.hibernate.test.entity.SuperHero;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SuperHeroRepository {
    private final EntityManager entityManager;

    public SuperHeroRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<SuperHero> save(final SuperHero superHero) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(superHero);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<SuperHero> findById(final Long id) {
        SuperHero superHero = entityManager.find(SuperHero.class, id);
        return superHero == null ? Optional.empty() : Optional.of(superHero);
    }

    public List<SuperHero> findAll() {
        return entityManager.createQuery("select s from SuperHero s").getResultList();
    }

    public void deleteById(final Long id) {
        SuperHero superHero = entityManager.find(SuperHero.class, id);

        if (superHero == null) {
            return;
        }

        try {

            entityManager.getTransaction().begin();

            superHero.getMovies().forEach(superHeroMovie -> superHeroMovie.getMovie().getSuperHeros().remove(superHeroMovie));

            entityManager.remove(superHero);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
