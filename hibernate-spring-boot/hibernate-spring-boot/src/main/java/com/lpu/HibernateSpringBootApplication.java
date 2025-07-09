package com.lpu;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lpu.algo.MyPasswordAlgo;
import com.lpu.algo.MyService;
import com.lpu.algo.PasswordHasher;
import com.lpu.lib.dao.StudentRepository;
import com.lpu.lib.service.BookService;
import com.lpu.lib.service.BookServiceImpl;
import com.lpu.model.Department;
import com.lpu.model.Employee;
import com.lpu.model.Faculty;
import com.lpu.model.Parking;
import com.lpu.model.Person;
import com.lpu.model.Project;
import com.lpu.model.Student;
import com.lpu.model.Student2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

@SpringBootApplication
public class HibernateSpringBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HibernateSpringBootApplication.class, args);
		MyPasswordAlgo algo = context.getBean(MyPasswordAlgo.class);
		String rev = algo.encrypt("ThisPass@34");
		System.out.println(rev);
		
//		PasswordHasher bean = context.getBean(PasswordHasher.class);
//		System.out.println(bean);
		
		Object bean2 = context.getBean("myXmlBeanSimple");
		System.out.println("xml bean : " + bean2);

		
		String[] beans = context.getBeanDefinitionNames();
		for(String beanName : beans)
		{
			Object bean3 = context.getBean(beanName);
			if(bean3 instanceof PasswordHasher || bean3 instanceof MyPasswordAlgo)
				System.out.println(beanName);

		}
		MyService myService = context.getBean(MyService.class);
		myService.show();
//		testBookService(context);
		testStudentRepo(context);

	}
	public static void testStudentRepo(ConfigurableApplicationContext context)
	{
		StudentRepository sRepo = context.getBean(StudentRepository.class);
		sRepo.findAll().forEach(System.out::println);
	}
	public static void testBookService(ConfigurableApplicationContext context)
	{
		BookService bean = context.getBean(BookService.class);
		BookService service = new BookServiceImpl(); 
		bean.findByAuthor("Herebert Schildt").forEach(System.out::println);
	}
	public static void testCustomQuery(ConfigurableApplicationContext context)
	{
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		Query query2 = em.createQuery("select p, p.employee.name from Parking p where p.employee.name = :name");
		query2.setParameter("name", "Priyanshu");
		List<Object[]> resultList2 = query2.getResultList();
		resultList2.forEach(ar -> System.out.println(Arrays.toString(ar)));
		
		Query query = em.createQuery("select e from Employee e where e.name= :name");
		query.setParameter("name", "Kalyan");
		query.getResultList().forEach(System.out::println);
		
		Query nativeQuery = em.createNativeQuery("select * from issue,book where issue.bid = book.bid");
		List<Object[]> resultList = nativeQuery.getResultList();
		resultList.forEach(ar -> System.out.println(Arrays.toString(ar)));
	}
	public static void testSingleTable(ConfigurableApplicationContext context)
	{
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Person p1 =new Person(0,"Rajan Manocha", "9865548798");
		Student2 s = new Student2(0, "Pratyush", "8798658744", 11025,"CSE");
		Faculty f = new Faculty(0, "Dinesh", "9865872020", null, null);
		em.persist(f);
		em.persist(s);
		em.persist(p1);
		em.getTransaction().commit();
		
	}
	public static void testProject(ConfigurableApplicationContext context)
	{
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Project p1 = new Project(0,"Indigo", Date.valueOf("2020-05-05"), Date.valueOf("2026-08-07"), 4500000.0);
		Project p2 = new Project(0,"SBI", Date.valueOf("2023-05-05"), Date.valueOf("2025-08-07"), 8500000.0);
		em.persist(p1);
		em.persist(p2);
		em.getTransaction().commit();

	}
	public static void testEmployeeDept(ConfigurableApplicationContext context)
	{
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		Employee employee = em.find(Employee.class, 1108);
		Department meDept = employee.getDepartment();
		System.out.println(employee);
		
		Department department = em.find(Department.class, 1);
		System.out.println();
		System.out.println("working in " + department.getName());
		department.getEmployees().forEach(System.out::println);
		em.getTransaction().begin();
		Employee krishan = em.find(Employee.class,1);
		krishan.setDesignation("Machine Operator");
//		em.persist(krishan);
		Employee emp = new Employee();
		emp.setDesignation("SE");
		emp.setMgrid(null);
		emp.setName("Krishan Manocha");
		emp.setDepartment(meDept);
//		em.persist(emp);
		em.getTransaction().commit();

	}
	public static void testParking(ConfigurableApplicationContext context)
	{
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		try
		{
			Parking parking = em.find(Parking.class,1101);
			System.out.println(parking);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
	}
	public static void testStudent(ConfigurableApplicationContext context)
	{
		// dataSource, entityManagerFactory
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		Student student = em.find(Student.class, 112 );

		//		student.setSname("Nishi Sachdeva");
		em.getTransaction().begin(); 
		//		em.remove(student);
		System.out.println(student);
		Integer marks = 81;
		Date dob = Date.valueOf("2001-08-08");
		//		Student s =new Student(112, "Amit Gupta", "CSE", "1212458785",dob, marks);
		//		em.persist(s);

		Query query = em.createQuery("select s from Student s");
		query.getResultList().forEach(System.out::println);
		em.getTransaction().commit();
		em.close();


	}

}
