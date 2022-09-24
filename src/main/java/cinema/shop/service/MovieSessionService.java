package cinema.shop.service;

import cinema.shop.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    MovieSession save(MovieSession movieSession);

    MovieSession get(Long id);

    List<MovieSession> findAvailableSession(Long movieId, LocalDate date);

    MovieSession update(MovieSession movieSession);

    void delete(Long id);
}
