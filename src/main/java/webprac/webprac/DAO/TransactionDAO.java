package webprac.webprac.DAO;

import webprac.webprac.models.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface TransactionDAO extends GenericDAO<Transaction>{

    List<Transaction> getNotReturnedFilmsFromClient(Integer clientId);
    List<Transaction> getByClientIdForSpecifiedDates(Integer client_id, Date startDate, Date endDate);
    List<Transaction> getByFilmIdForSpecifiedDates(Integer filmId, Date startDate, Date endDate);
}
