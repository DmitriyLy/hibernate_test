package dmly.hibernate.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "Book.findByName",
                query = "SELECT b FROM Book b WHERE b.name = :name"),
        @NamedQuery(name = "Book.findAll",
                query = "SELECT b FROM Book b")
})
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Book() {
    }

    public Book(String name) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + ( author == null ? "" : author.getName()) +
                '}';
    }
}
