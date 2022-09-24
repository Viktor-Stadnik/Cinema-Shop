package cinema.shop.dao.impl;

import cinema.shop.dao.OrderDao;
import cinema.shop.exceptions.DataProcessingException;
import cinema.shop.model.Order;
import cinema.shop.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory factory;

    public OrderDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order save(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save an order: " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = factory.openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                            + "JOIN FETCH o.tickets t "
                            + "JOIN FETCH t.movieSession ms "
                            + "JOIN FETCH ms.movie "
                            + "JOIN FETCH ms.cinemaHall "
                            + "WHERE o.user =: user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find an order by user: " + user, e);
        }
    }
}
