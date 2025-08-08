package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer year;

    @ManyToMany
    @JoinTable(name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "films_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "films_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors = new HashSet<>();

    private Double rating;

    private Integer stock;

    @Override
    public String toString() {
        return "\nФильм: " + id +
                ". Название: " + name +
                ". Год выхода: " + year +
                ". Жанр: " + genres +
                ". Режиссёр: " + directors +
                ". Актёры: " + actors +
                ". Рейтинг: " + rating +
                ". Количество на складе: " + stock;
    }
}