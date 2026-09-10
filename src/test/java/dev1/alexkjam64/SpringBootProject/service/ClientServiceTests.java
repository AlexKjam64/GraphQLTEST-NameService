package dev1.alexkjam64.SpringBootProject.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev1.alexkjam64.SpringBootProject.repository.ClientName;
import dev1.alexkjam64.SpringBootProject.repository.ClientNameRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ClientNameService.class})
public class ClientServiceTests {
    @MockitoBean
    private ClientNameRepository repoSample;

    @Autowired
    private ClientNameService service;

    @Test
    public void test1(){
        ClientName test = new ClientName(1, "Bean", " ", "!");
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("Last name is null or empty!", e.getMessage());
        }
    }

    @Test
    public void test2(){
        ClientName test = new ClientName(1, "Bean", "Char!", null);
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("Last name includes special characters!", e.getMessage());
        }
    }

    @Test
    public void test3(){
        ClientName test = new ClientName(2, "Dango", "Flamingo", "Bingo");
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("Middle initial surpasses 1 character!", e.getMessage());
        }
    }

    @Test
    public void test4(){
        ClientName test = new ClientName(10, " ", null, null);
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("First name is null or empty!", e.getMessage());
        }
    }

    @Test
    public void test5(){
        ClientName test = new ClientName(10, "Not null", "null", null);
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("First name includes special characters!", e.getMessage());
        }
    }

    @Test
    public void test6(){
        ClientName test = new ClientName(0, "First", "Last", "Middle");
        try {
            service.sanitizeData(test);
        } catch (InvalidDataException e) {
            Assertions.assertEquals("Middle initial surpasses 1 character!", e.getMessage());
        }
    }
}
