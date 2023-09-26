package webprac.webprac.DAO.impl;

import jakarta.persistence.TemporalType;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import webprac.webprac.models.Transaction;
import webprac.webprac.DAO.TransactionDAO;
import java.util.List;
import org.hibernate.Session;
import java.util.Date;


@Repository
public class TransactionDAOImpl extends GenericDAOImpl<Transaction> implements TransactionDAO {
    @Override
    public List<Transaction> getNotReturnedFilmsFromClient(Integer clientId) {
        try (Session session = sessionFactory.openSession()) {
            NativeQuery<Transaction> query = session.createNativeQuery("SELECT * FROM transaction " +
                            "WHERE client_id = :clientId AND date_of_return IS NULL ORDER BY film_title;",
                    Transaction.class).setParameter("clientId", clientId);
            query.getResultList();
            return query.list();
        }
    }

    @Override
    public List<Transaction> getByClientIdForSpecifiedDates(Integer clientId, Date startDate, Date endDate) {
        try (Session session = sessionFactory.openSession()) {
            if (startDate == null) {
                startDate = java.sql.Date.valueOf("0001-01-01");
            }
            if (endDate == null) {
                endDate = java.sql.Date.valueOf("9999-12-12");
            }
            NativeQuery<Transaction> query = session.createNativeQuery("SELECT * FROM transaction " +
                            "WHERE client_id = :clientId AND " +
                            ":startDate <= date_of_lease AND " +
                            "date_of_return <= :endDate ORDER BY film_title", Transaction.class)
                    .setParameter("clientId", clientId)
                    .setParameter("startDate", startDate, TemporalType.DATE)
                    .setParameter("endDate", endDate, TemporalType.DATE);
            query.getResultList();
            return query.list();
        }
    }

    @Override
    public List<Transaction> getByFilmIdForSpecifiedDates(Integer filmId, Date startDate, Date endDate) {
        try (Session session = sessionFactory.openSession()) {
            if (startDate == null) {
                startDate = java.sql.Date.valueOf("0001-01-01");
            }
            if (endDate == null) {
                endDate = java.sql.Date.valueOf("9999-12-12");
            }
            NativeQuery<Transaction> query = session.createNativeQuery("SELECT * FROM transaction " +
                            "WHERE film_id = :filmId AND " +
                            ":startDate <= date_of_lease AND " +
                            "date_of_return <= :endDate ORDER BY film_title", Transaction.class)
                    .setParameter("filmId", filmId)
                    .setParameter("startDate", startDate, TemporalType.DATE)
                    .setParameter("endDate", endDate, TemporalType.DATE);
            query.getResultList();
            return query.list();
        }
    }
}