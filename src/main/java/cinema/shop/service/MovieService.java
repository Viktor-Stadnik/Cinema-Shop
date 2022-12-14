package cinema.shop.service;

import cinema.shop.model.Movie;
import java.util.List;

public interface MovieService {
    Movie save(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
