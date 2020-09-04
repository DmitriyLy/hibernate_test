package dmly.hibernate.test;

import dmly.hibernate.test.entity.Movie;
import dmly.hibernate.test.entity.SuperHero;
import dmly.hibernate.test.repository.MovieRepository;
import dmly.hibernate.test.repository.SuperHeroRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class ManyToManyTest {

    @BeforeAll
    static void init() {
        TestUtils.init();
    }

    @AfterAll
    static void destroy() {
        TestUtils.destroy();
    }

    @Test
    void runManyToManyTest() {
        EntityManager entityManager = TestUtils.getEntityManager();

        MovieRepository movieRepository = new MovieRepository(entityManager);
        SuperHeroRepository  superHeroRepository = new SuperHeroRepository(entityManager);

        SuperHero ironMan = new SuperHero("Iron Man");
        SuperHero thor = new SuperHero("Thor");
        SuperHero batman = new SuperHero("Batman");

        superHeroRepository.save(ironMan);
        superHeroRepository.save(thor);
        superHeroRepository.save(batman);

        Movie avengers = new Movie("The Avengers");
        avengers.addSuperHero(ironMan);
        avengers.addSuperHero(thor);

        movieRepository.save(avengers);

        Movie infinityWar = new Movie("Avengers: Infinity War");
        infinityWar.addSuperHero(ironMan);
        infinityWar.addSuperHero(thor);
        infinityWar.addSuperHero(batman);

        movieRepository.save(infinityWar);

        // Find all movies
        System.out.println("MOVIES:");
        movieRepository.findAll().forEach(movie -> {
            System.out.println("Movie: [" + movie.getId() + "] - " + movie.getTitle());
            movie.getSuperHeros().forEach(superHeroMovie -> System.out.println(superHeroMovie.getSuperHero().getName()));
        });

        System.out.println("\nSuperheroes:");
        superHeroRepository.findAll().forEach(superHero -> {
            System.out.println(superHero.getName());
            superHero.getMovies().forEach(superHeroMovie -> System.out.println("\t" + superHeroMovie.getMovie().getTitle()));
        });

        movieRepository.deleteById(1L);
        System.out.println("\nMOVIES (AFTER DELETE):");
        movieRepository.findAll().forEach(System.out::println);

        System.out.println("\nsuperheroes (AFTER DELETE):");
        superHeroRepository.findAll().forEach(System.out::println);
    }

}
