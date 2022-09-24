package cinema.shop.dao.impl;

import cinema.shop.dao.TicketDao;
import cinema.shop.exceptions.DataProcessingException;
import cinema.shop.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private final SessionFactory factory;

    public TicketDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Ticket save(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save a ticket: " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
