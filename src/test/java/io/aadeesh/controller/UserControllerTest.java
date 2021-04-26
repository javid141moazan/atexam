package io.aadeesh.controller;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import io.aadeesh.model.User;

@SpringBootTest
class UserControllerTest {

	@Autowired
	UserController userController;
	
	@Autowired
	HttpSession httpSession;
	
	
	ModelMap modelMap = new ModelMap();

	User user = new User();
	ModelAndView mv;

	@Test
	void testForHome() {
		assertEquals("home", userController.home());
	}

	@Test
	void testForGetSignup() {
		assertEquals("signup", userController.getSignup());
	}

	@Test
	void testForGetLogin() {
		assertEquals("login", userController.getLogin());
	}
	
	@Test
	void testForDummy() {
		assertEquals("dummy", userController.dummy());
	}
	
	@Test
	void testForLogout_User() {
		httpSession.setAttribute("username", "rahulrocks@gmail.com");
		assertEquals("redirect:/login", userController.logout_user(httpSession));
	}

	@Test
	void testForAddUserAndLogin_User() {
		prepareUserObject();
		String view = userController.login_user(user.getUser_email(), user.getUser_pass(), httpSession, modelMap);
		assertEquals("login", view);
		mv = userController.addUser(user.getUser_email(), user);
		view = userController.login_user(user.getUser_email(), user.getUser_pass(), httpSession, modelMap);
		assertEquals("dummy", view);
		mv = userController.addUser(user.getUser_email(), user);
		assertNotNull(mv);
		
	}

	private void prepareUserObject() {
		user.setUser_id(1);
		user.getUser_id();
		user.setUser_fname("Rahul");
		user.getUser_fname();
		user.setUser_lname("Pandia");
		user.getUser_lname();
		user.setUser_email("rahulrocks@gmail.com");
		user.setUser_pass("Rahul#$");
		user.getUser_pass();
		user.setUser_mobile("9999999999");
		user.getUser_mobile();
		user.toString();
	}

}
