package cinema.shop.dao.impl;

import cinema.shop.dao.MovieSessionDao;
import cinema.shop.exceptions.DataProcessingException;
import cinema.shop.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory factory;

    public MovieSessionDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public MovieSession save(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save a movie session: " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<MovieSession> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM MovieSession ms "
                            + "JOIN FETCH ms.cinemaHall ch "
                            + "JOIN FETCH ms.movie m "
                            + "WHERE ms.id = :id", MovieSession.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie session by id: " + id, e);
        }
    }

    @Override
    public List<MovieSession> getAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM MovieSession ms "
                            + "WHERE ms.movie.id = :id "
                            + "AND DATE_FORMAT(showTime,'%Y-%m-%d') = :date", MovieSession.class)
                    .setParameter("id", movieId)
                    .setParameter("date", date.toString())
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Session for movie with id "
                    + movieId + " and show date " + date + " not found", e);
        }
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update"
                    + " a movie session: " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            MovieSession movieSession = session.get(MovieSession.class, id);
            session.delete(movieSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete"
                    + " a movie session with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
