package com.example.userdao;

import com.example.userdao.UserDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;


public class UserDaoTests {
	@Test
	public void get() throws SQLException, ClassNotFoundException {
		Long id = 1l;
		String name = "bellsky";
		String password = "test1234";
		ConnectionMaker connectionMaker = new JejuConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		User user = userDao.findById(id);
		assertThat(user.getId(), is(id));
		assertThat(user.getName(), is(name));
		assertThat(user.getPassword(), is(password));
	}
	@Test
	public void insert() throws SQLException, ClassNotFoundException {
		String name = "선종호";
		String password = "3333";
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		ConnectionMaker connectionMaker = new JejuConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		userDao.insert(user);
		assertThat(user.getId(), greaterThan(1l));

		User insertedUser = userDao.findById(user.getId());
		assertThat(insertedUser.getId(),  is(user.getId()));
		assertThat(insertedUser.getName(), is(name));
		assertThat(insertedUser.getPassword(), is(password));
	}
//---------------------------------------------------------------------------------------
	//for halla
	@Test
	public void getForHalla() throws SQLException, ClassNotFoundException {
		Long id = 1l;
		String name = "bellsky";
		String password = "test1234";
		ConnectionMaker connectionMaker = new HallaConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		User user = userDao.findById(id);
		assertThat(user.getId(), is(id));
		assertThat(user.getName(), is(name));
		assertThat(user.getPassword(), is(password));
	}
	@Test
	public void insertForHalla() throws SQLException, ClassNotFoundException {
		String name = "선종호";
		String password = "3333";
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		ConnectionMaker connectionMaker = new HallaConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		userDao.insert(user);
		assertThat(user.getId(), greaterThan(1l));

		User insertedUser = userDao.findById(user.getId());
		assertThat(insertedUser.getId(),  is(user.getId()));
		assertThat(insertedUser.getName(), is(name));
		assertThat(insertedUser.getPassword(), is(password));
	}
}
