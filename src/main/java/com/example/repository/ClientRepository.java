package com.example.repository;

import com.example.entity.Client;
import com.example.request.ListRequest;

import java.util.List;

public interface ClientRepository {
    int getQuantityPage(ListRequest request) throws Exception;
    List<Client> getListPage(ListRequest request) throws Exception;
    List<Client> list() throws Exception;
    Client get(String code) throws Exception;
    String register(Client client) throws Exception;
    void update(Client client) throws Exception;
    void delete(Client client) throws Exception;
}
