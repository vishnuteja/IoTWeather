package in.tvt.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import in.tvt.api.entity.Usr;
import in.tvt.api.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Usr> findAll() {
		TypedQuery<Usr> query = em.createNamedQuery("Usr.findAll", Usr.class);
		return query.getResultList();
	}

	@Override
	public Usr findByEmail(String email) {
		TypedQuery<Usr> query = em.createNamedQuery("Usr.findByEmail", Usr.class);
		query.setParameter("pEmail", email);
		List<Usr> users = query.getResultList();
		
		if(!users.isEmpty())
			return users.get(0);
		else
			return null;
	}
	
	@Override
	public Usr findOne(String id) {
		return em.find(Usr.class, id);
	}

	@Override
	public Usr create(Usr usr) {
		em.persist(usr);
		return usr;
	}

	@Override
	public Usr update(Usr user) {
		return em.merge(user);
	}

	@Override
	public void delete(Usr user) {
		em.remove(user);
	}

}
