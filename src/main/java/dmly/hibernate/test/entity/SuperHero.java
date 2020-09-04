package dmly.hibernate.test.entity;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "SUPERHEROS")
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            mappedBy = "superHero",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SuperHeroMovie> movies = new ArrayList<>();

    public SuperHero() {
    }

    public SuperHero(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SuperHeroMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<SuperHeroMovie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHero superHero = (SuperHero) o;
        return Objects.equals(id, superHero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SuperHero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies.stream().map(movie -> movie.getMovie().getTitle()).collect(Collectors.toList()) +
                '}';
    }
}
