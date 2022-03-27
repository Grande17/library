package com.kodilla.library;

import com.kodilla.library.controllers.BookController;
import com.kodilla.library.controllers.BorrowedController;
import com.kodilla.library.controllers.LibraryQuantityController;
import com.kodilla.library.controllers.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private BookController bookController;
	@Autowired
	private BorrowedController borrowedController;
	@Autowired
	private LibraryQuantityController libraryQuantityController;
	@Autowired
	private UserController userController;


	@Test
	void contextLoads() throws Exception{
		assertNotNull(bookController);
		assertNotNull(borrowedController);
		assertNotNull(libraryQuantityController);
		assertNotNull(userController);
	}

}
