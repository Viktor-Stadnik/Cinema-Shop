package cinema.shop.service.impl;

import cinema.shop.dao.MovieSessionDao;
import cinema.shop.model.MovieSession;
import cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession save(MovieSession movieSession) {
        return movieSessionDao.save(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get movie session by id " + id));
    }

    @Override
    public List<MovieSession> findAvailableSession(Long movieId, LocalDate date) {
        return movieSessionDao.getAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        return movieSessionDao.update(movieSession);
    }

    @Override
    public void delete(Long id) {
        movieSessionDao.delete(id);
    }
}
