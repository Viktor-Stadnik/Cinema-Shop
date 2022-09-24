package cinema.shop.dao.impl;

import cinema.shop.dao.MovieDao;
import cinema.shop.exceptions.DataProcessingException;
import cinema.shop.model.Movie;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory factory;

    public MovieDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Movie save(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save a movie: " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie by id: " + id, e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = factory.openSession();) {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movies from db", e);
        }
    }
}
