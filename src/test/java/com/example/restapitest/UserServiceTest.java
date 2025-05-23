package com.example.restapitest;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testCreateUser() {
        UserModel user = new UserModel("Tom", "tom@example.com");
        Response response = UserService.createUser(user);

        System.out.println("Oluşturulan Kullanıcı Yanıtı:\n" + response.getBody().asPrettyString());

        assertEquals(201, response.getStatusCode(), "Status code 201 olmalı");
        System.out.println("Yanıt Süresi: " + response.getTime() + " ms");
        assertTrue(response.getTime() < 1000, "Yanıt süresi 1000ms altı olmalı");
    }

    @Test
    public void testDeleteUser() {
        Response response = UserService.deleteUser(2);

        System.out.println("DELETE Yanıt Kodu: " + response.getStatusCode());
        assertEquals(204, response.getStatusCode(), "Status code 204 olmalı");
        System.out.println("Yanıt Süresi: " + response.getTime() + " ms");
        assertTrue(response.getTime() < 1000, "Yanıt süresi 1000ms altı olmalı");
    }

    @Test
    public void testListUsers() {
        Response response = UserService.listUsers(2);

        System.out.println("Listeleme Yanıtı:\n" + response.getBody().asPrettyString());
        assertEquals(200, response.getStatusCode(), "Status code 200 olmalı");
        assertTrue(response.jsonPath().getList("data").size() > 0, "En az 1 kullanıcı olmalı");
        System.out.println("Yanıt Süresi: " + response.getTime() + " ms");
        assertTrue(response.getTime() < 1000, "Yanıt süresi 1000ms altı olmalı");
    }

    @Test
    public void testGetUser() {
        Response response = UserService.getUser(2);

        System.out.println("GET Yanıtı:\n" + response.getBody().asPrettyString());
        assertEquals(200, response.getStatusCode(), "Status code 200 olmalı");
        assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        System.out.println("Yanıt Süresi: " + response.getTime() + " ms");
        assertTrue(response.getTime() < 1000, "Yanıt süresi 1000ms altı olmalı");
    }
}
