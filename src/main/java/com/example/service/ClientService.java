package com.example.service;

import com.example.entity.Client;
import com.example.request.ListRequest;

import java.util.List;

public interface ClientService {
    int getQuantityPage(ListRequest request) throws Exception;
    List<Client> getListPage(ListRequest request) throws Exception;
    Client get(String code) throws Exception;
    List<Client> list() throws Exception;
    String register(Client client) throws Exception;
    String update(Client client) throws Exception;
    String delete(String code) throws Exception;
}
