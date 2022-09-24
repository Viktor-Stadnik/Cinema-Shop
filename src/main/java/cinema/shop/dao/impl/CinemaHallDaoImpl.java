package cinema.shop.dao.impl;

import cinema.shop.dao.CinemaHallDao;
import cinema.shop.exceptions.DataProcessingException;
import cinema.shop.model.CinemaHall;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory factory;

    public CinemaHallDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public CinemaHall save(CinemaHall cinemaHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save a cinema hall: " + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<CinemaHall> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(CinemaHall.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a cinema hall by id: " + id, e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = factory.openSession()) {
            CriteriaQuery<CinemaHall> query = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            query.from(CinemaHall.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all cinema halls from db", e);
        }
    }
}
