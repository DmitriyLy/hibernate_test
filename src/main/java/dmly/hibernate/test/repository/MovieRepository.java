package dmly.hibernate.test.repository;

import dmly.hibernate.test.entity.Movie;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private final EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Movie> save(Movie movie) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();
            return Optional.of(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("from Movie").getResultList();
    }

    public Optional<Movie> findById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie == null ? Optional.empty() : Optional.of(movie);
    }

    public void deleteById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);

        if (movie == null) {
            return;
        }

        try {
            entityManager.getTransaction().begin();

            movie.getSuperHeros().forEach(superHeroMovie -> superHeroMovie.getSuperHero().getMovies().remove(superHeroMovie));

            entityManager.remove(movie);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
