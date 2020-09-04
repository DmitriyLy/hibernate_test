package dmly.hibernate.test.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
   private List<SuperHeroMovie> superHeros = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SuperHeroMovie> getSuperHeros() {
        return superHeros;
    }

    public void setSuperHeros(List<SuperHeroMovie> superHeros) {
        this.superHeros = superHeros;
    }

    public void addSuperHero(SuperHero superHero) {
        SuperHeroMovie superHeroMovie = new SuperHeroMovie(superHero, this);
        superHeros.add(superHeroMovie);
        superHero.getMovies().add(superHeroMovie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
