package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;
import com.epam.volodko.entity.user.Role;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ClientDAOTest {

    UserDAO<Client> clientDAO = DAOFactory.getInstance().getClientDAO();

    @Test
    public void testFindById() throws DAOException{
        int clientId = 4;
        int otherId = 1;
        Client client = clientDAO.findById(clientId);
        Client otherUser = clientDAO.findById(otherId);

        assertNotNull(client);
        assertNull(otherUser);
    }

    @Test
    public void testFindByLogin() throws DAOException{
        String clientLogin = "client1";
        String otherLogin = "admin1";
        Client client = clientDAO.findByLogin(clientLogin);
        Client otherUser = clientDAO.findByLogin(otherLogin);

        assertNotNull(client);
        assertNull(otherUser);
    }

    @Test
    public void testFindAll() throws DAOException{
        List<Client> clients = clientDAO.findAll();

        for (Client client : clients){
            assertNotNull(client);
            assertEquals(Role.CLIENT, client.getRole());
        }
    }

    @Test
    public void testSaveInfo() throws DAOException{
        Client client = new Client();
        client.setLogin("clientTestLogin0912340");
        client.setPassword("asdasd");
        client.setName("client name");
        client.setPhone("+12412341");
        clientDAO.saveNew(client);
        client.setPassword(null);

        client.setCompany("Company");
        client.setNote("client note");
        int rowsAffected = clientDAO.saveInfo(client);
        int expectedAffect = 1;
        Client actualClient = clientDAO.findById(client.getId());

        assertEquals(client, actualClient);
        assertEquals(expectedAffect, rowsAffected);
    }


}