package in.tvt.persistence.relations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import in.tvt.persistence.simple.Employee;

public class Tester {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");

		EntityManager em = emf.createEntityManager();

//		Owner user = new Owner();
//		user.setFirstname("vishnu");
//		user.setLastname("Teja");
//		
//		Address address = new Address();
//		address.setStreet("6470 Skimmer");
//		address.setCity("Columbus");
//		address.setState("OH");
//		address.setZip(44114);
//		
//		em.getTransaction().begin();
//		em.persist(address);
//		user.setAddress(address);
//		em.persist(user);
//		em.getTransaction().commit();
		
		Owner own = em.find(Owner.class, "8f41732e-6992-49b9-bd1e-26545fbb7609");
		System.out.println(own);
		
		em.close();
		emf.close();
	}

}
