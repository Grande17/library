package com.kodilla.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.library.domain.Book;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.Validator;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureJsonTesters
@WebMvcTest(BookController.class)
public class BookControllerTestSuite {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookMapper mapper;
    @MockBean
    private BookDbService bookDbService;
    @Autowired
    private JacksonTester<Book> jsonBook;



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void canRetrieveByIdWhenExists() throws Exception{
        //Given
        when(bookDbService.getBook(3)).thenReturn(new Book(3, "Title", "Author", 1999));

        //When
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .get("/v1/books/3")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //Then
        assertEquals(200, response.getStatus());
    }
    @Test
    void getAllBooksTest() throws Exception{
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"111","111", 1234));
        books.add(new Book(2,"222","222",4321));

        when(bookDbService.getAllBooks()).thenReturn(books);
        //When
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .get("/v1/books")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //Then
        assertEquals(200, response.getStatus());
    }
    @Test
    void postNewBook() throws Exception{
        //Given
        Book book = new Book(1,"Title","Author",1999);
        when(bookDbService.saveBook(any(Book.class))).thenReturn(book);
        //When
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .post("/v1/books")
                .content(asJsonString(book).getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //Then
        assertEquals(200, response.getStatus());
    }
    @Test
    void putBookTest() throws Exception{
        //Given
        Book book = new Book(1,"Title","Author",1999);
        when(bookDbService.saveBook(any(Book.class))).thenReturn(book);
        //WHen
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .put("/v1/books")
                .content(asJsonString(book).getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //Then
        assertEquals(200, response.getStatus());
    }
    @Test
    void deleteBook() throws Exception {
        //Given
        //When
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .delete("/v1/books/1"))
                .andReturn().getResponse();
        //Then
        assertEquals(200,response.getStatus());
    }
    @Test
    void getBookByAuthorContainsTest() throws Exception {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Title","Author",1999));
        when(bookDbService.getBookByAuthorContains("or")).thenReturn(books);
        //When
        MockHttpServletResponse response =mvc.perform(MockMvcRequestBuilders
                .get("/v1/books/authorContains=")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //Then
        assertEquals(200,response.getStatus());
    }
    @Test
    void getBookByTitleContainsTest() throws Exception {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Title","Author",1999));
        when(bookDbService.getBookByTitleContains("le")).thenReturn(books);
        //When
        MockHttpServletResponse response =mvc.perform(MockMvcRequestBuilders
                        .get("/v1/books/titleContains=")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //Then
        assertEquals(200,response.getStatus());
    }
    @Test
    void getBookByPublishedTest() throws Exception {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Title","Author",1999));
        when(bookDbService.getByPublished(1999)).thenReturn(books);
        //When
        MockHttpServletResponse response =mvc.perform(MockMvcRequestBuilders
                        .get("/v1/books/published=1999")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //Then
        assertEquals(200,response.getStatus());
    }


}
