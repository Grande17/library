package com.kodilla.library.controllers;


import com.kodilla.library.domain.Book;
import com.kodilla.library.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookDbService service;
    @Autowired
    private JacksonTester<BookController> jacksonTester;

    @Test
    void getByIdTest() throws Exception {
        //Given
        Mockito.when(service.getBook(1)).thenReturn(new Book(1,"Author","Title",1999));
        //When&Then
        mvc.perform(get("/v1/books/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
