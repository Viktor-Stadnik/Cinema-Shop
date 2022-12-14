package cinema.shop.dao;

import cinema.shop.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    MovieSession save(MovieSession movieSession);

    Optional<MovieSession> get(Long id);

    List<MovieSession> getAvailableSessions(Long movieId, LocalDate date);

    MovieSession update(MovieSession movieSession);

    void delete(Long id);
}
