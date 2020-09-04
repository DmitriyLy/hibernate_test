package dmly.hibernate.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SUPERHERO_MOVIE")
public class SuperHeroMovie implements Serializable {

    @Id
    @ManyToOne
    private SuperHero superHero;

    @Id
    @ManyToOne
    private Movie movie;

    public SuperHeroMovie() {
    }

    public SuperHeroMovie(SuperHero superHero, Movie movie) {
        this.superHero = superHero;
        this.movie = movie;
    }

    public SuperHero getSuperHero() {
        return superHero;
    }

    public void setSuperHero(SuperHero superHero) {
        this.superHero = superHero;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHeroMovie that = (SuperHeroMovie) o;
        return Objects.equals(superHero, that.superHero) &&
                Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superHero, movie);
    }
}
