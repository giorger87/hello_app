package com.globant.shoppingCartService.controllers;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMockSupport;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import com.globant.cartService.controllers.UserController;
import com.globant.cartService.entities.Item;
import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.entities.User;
import com.globant.cartService.exceptions.UserNotFoundException;
import com.globant.cartService.services.UserService;

public class UserControllerTest extends EasyMockSupport {
	@TestSubject
	UserController userController;
	
	private UserService userServiceMock;


@Before
public void setUp(){
	userServiceMock = createMock(UserService.class);
	this.userController = new UserController(userServiceMock);
}
	@Test
	public void testgetCarts() throws UserNotFoundException {
		
		User user= new User(1);
			Item item= new Item("bananas",1.2);
			List<Item> list = new ArrayList<Item>();
		ShoppingCart cart = new ShoppingCart("pending",list,user);
		ShoppingCart expected = new ShoppingCart("pending",list,user);
		List<ShoppingCart> expectedList = new ArrayList<ShoppingCart>() {{add(expected);}};
		expected.setCartId(1L);		
		expect(userServiceMock.findAllCarts(1L)).andReturn(expectedList);
		
		
	}
	@Test
	public void testgetCartsShouldThrowExc() {
		User user= new User(3L);
		List<Item> list = new ArrayList<Item>();
	ShoppingCart expected = new ShoppingCart("pending",list,user);
	List<ShoppingCart> expectedList = new ArrayList<ShoppingCart>() {{add(expected);}};
	expected.setCartId(1L);		
	boolean exceptionThrown = false;
//    try{
//    	expect(userServiceMock.findAllCarts(-38L)).andReturn(null);
//    	
//      }catch (UserNotFoundException e) {
//        exceptionThrown = true;
//      }
//	assertTrue(exceptionThrown);
//		
	}
}