package com.globant.shoppingCartService.controllers;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.easymock.EasyMockSupport;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.globant.cartService.controllers.UserController;
import com.globant.cartService.entities.Item;
import com.globant.cartService.entities.ShoppingCart;
import com.globant.cartService.entities.User;
import com.globant.cartService.exceptions.UserNotFoundException;
import com.globant.cartService.services.ItemService;
import com.globant.cartService.services.UserService;

public class UserControllerTest extends EasyMockSupport {
	@TestSubject
	UserController userController;
	@Mock
	private UserService userServiceMock;
	@Mock
	private ItemService itemServiceMock;


@Before
public void setUp(){
	this.userController = new UserController(userServiceMock);
}
	@Test
	public void addCart() throws UserNotFoundException {
		
		User user= new User(1);
		Item item= itemServiceMock.getItemById(1L);
		List<Item> list = new ArrayList<Item>();
		ShoppingCart cart = new ShoppingCart("pending",list,user);
		ShoppingCart expected = new ShoppingCart("pending",list,user);
		expected.setCartId(1L);		
		List<ShoppingCart> expectedList = new ArrayList<ShoppingCart>(){{add(expected);}};
		expect(userServiceMock.findAllCarts(1L)).andReturn(expectedList);
	}

}