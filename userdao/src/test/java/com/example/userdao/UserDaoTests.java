package com.example.userdao;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;


public class UserDaoTests {

	private static UserDao userDao;

	@BeforeAll
	public static void setup(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		userDao = applicationContext.getBean("userDao", UserDao.class);

	}
	@Test
	public void get() throws SQLException, ClassNotFoundException {
		Long id = 1l;
		String name = "bellsky";
		String password = "test1234";
//		ConnectionMaker connectionMaker = new JejuConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);
//
//		DaoFactory daoFactory = new DaoFactory();
//		UserDao userDao = daoFactory.getUserDao();


		User user = userDao.findById(id);
		assertThat(user.getId(), is(id));
		assertThat(user.getName(), is(name));
		assertThat(user.getPassword(), is(password));
	}
	@Test
	public void insert() throws SQLException, ClassNotFoundException {
		String name = "종호";
		String password = "3333";
		User user = new User();
		user.setName(name);
		user.setPassword(password);


//		ConnectionMaker connectionMaker = new JejuConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);

//		DaoFactory daoFactory = new DaoFactory();
//		UserDao userDao = daoFactory.getUserDao();

		userDao.insert(user);
		assertThat(user.getId(), greaterThan(1l));

		User insertedUser = userDao.findById(user.getId());
		assertThat(insertedUser.getId(),  is(user.getId()));
		assertThat(insertedUser.getName(), is(name));
		assertThat(insertedUser.getPassword(), is(password));
	}
	@Test
	public void update() throws SQLException, ClassNotFoundException {
		User user = insertedUser();
		String updatedName = "updatedSJH";
		String updatedPassword = "4444";
		user.setName(updatedName);
		user.setPassword(updatedPassword);
		userDao.update(user);

		User updatedUser = userDao.findById((user.getId()));
		assertThat(updatedUser.getName(), is(updatedName));
		assertThat(updatedUser.getPassword(), is(updatedPassword));
	}

	private User insertedUser() throws SQLException, ClassNotFoundException {
		String name = "updatedSJH";
		String password = "4444";
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		userDao.insert(user);
		return user;
	}

	@Test
	public void delete() throws SQLException, ClassNotFoundException {
		User user = insertedUser();
		userDao.delete(user.getId());

		User deletedUser = userDao.findById(user.getId());

		assertThat(deletedUser, IsNull.nullValue());
	}
	/*
//---------------------------------------------------------------------------------------
	//for halla
//	@Test
//	public void getForHalla() throws SQLException, ClassNotFoundException {
//		Long id = 1l;
//		String name = "bellsky";
//		String password = "test1234";
//		ConnectionMaker connectionMaker = new HallaConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);
//		User user = userDao.findById(id);
//		assertThat(user.getId(), is(id));
//		assertThat(user.getName(), is(name));
//		assertThat(user.getPassword(), is(password));
//	}
//	@Test
//	public void insertForHalla() throws SQLException, ClassNotFoundException {
//		String name = "선종호";
//		String password = "3333";
//		User user = new User();
//		user.setName(name);
//		user.setPassword(password);
//		ConnectionMaker connectionMaker = new HallaConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);
//		userDao.insert(user);
//		assertThat(user.getId(), greaterThan(1l));
//
//		User insertedUser = userDao.findById(user.getId());
//		assertThat(insertedUser.getId(),  is(user.getId()));
//		assertThat(insertedUser.getName(), is(name));
//		assertThat(insertedUser.getPassword(), is(password));
//	}

	*/
}
