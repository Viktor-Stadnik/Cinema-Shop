package cinema.shop.dao;

import cinema.shop.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Movie save(Movie movie);

    Optional<Movie> get(Long id);

    List<Movie> getAll();
}
