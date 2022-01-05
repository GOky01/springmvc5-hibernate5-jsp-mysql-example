package net.javaguides.springmvc.dao;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.entity.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PhoneDAOImpl implements PhoneDAO {

	private final SessionFactory sessionFactory;

	public PhoneDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Phone> getPhones() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Phone> cq = cb.createQuery(Phone.class);
		Root<Phone> root = cq.from(Phone.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void deletePhone(int id) {
		Session session = sessionFactory.getCurrentSession();
		Phone book = session.byId(Phone.class).load(id);
		session.delete(book);
	}

	@Override
	public void savePhone(Phone phone) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(phone);
	}

	@Override
	public Phone getPhone(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Phone phone = currentSession.get(Phone.class, theId);
		return phone;
	}
}
