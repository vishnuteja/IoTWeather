package in.tvt.persistence.simple;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Tester {

	public static void main(String args[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");

		EntityManager em = emf.createEntityManager();

		// INSERT INTO

		// Employee e = new Employee();
		//
		// e.setCity("California");
		// e.setEmail("shilpa@gmail.com");
		// e.setFirstname("Shilpa");
		// e.setLastname("Thummanapelli");
		//
		// em.persist(e);
		//
		// em.getTransaction().begin();
		// em.close();
		// em.getTransaction().commit();

		// Find by Id
//		Employee find = em.find(Employee.class, "776b66a0-8906-45ac-ab41-11fa3835ec9a");
//		System.out.println(find);
//
//		// UPDATE
//		Employee update = em.find(Employee.class, "776b66a0-8906-45ac-ab41-11fa3835ec9a");
//		update.setEmail("vishnutejat@outlook.com");
//
//		em.getTransaction().begin();
//		em.merge(update);
//		em.getTransaction().commit();
//
//		// DELETE
//		Employee delete = em.find(Employee.class, "776b66a0-8906-45ac-ab41-11fa3835ec9a");
//		
//		em.getTransaction().begin();
//		em.remove(delete);
//		em.getTransaction().commit();

//		//Find all
//		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
//		List<Employee> employee = query.getResultList();
//		System.out.println(employee);
		
		//Find by email
//		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.email=:pEmail", Employee.class);
//		query.setParameter("pEmail", "shilpa@gmail.com");
//		List<Employee> employee = query.getResultList();
//		System.out.println(employee);
		
		//Find by email - NamedQuery example
//		TypedQuery<Employee> query = em.createNamedQuery("Employee.findByEmail", Employee.class);
//		query.setParameter("pEmail", "shilpa@gmail.com");
//		List<Employee> employee = query.getResultList();
//		System.out.println(employee);
		
		emf.close();
	}
}
