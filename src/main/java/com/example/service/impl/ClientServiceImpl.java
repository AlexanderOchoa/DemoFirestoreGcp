package com.example.service.impl;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import com.example.request.ListRequest;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public int getQuantityPage(ListRequest request) throws Exception {
        return clientRepository.getQuantityPage(request);
    }

    @Override
    public List<Client> getListPage(ListRequest request) throws Exception {
        return clientRepository.getListPage(request);
    }

    @Override
    public Client get(String code) throws Exception {
        return clientRepository.get(code);
    }

    @Override
    public List<Client> list() throws Exception {
        return clientRepository.list();
    }

    @Override
    public String register(Client client) throws Exception {
        clientRepository.register(client);
        return "Register success";
    }

    @Override
    public String update(Client client) throws Exception {
        Client clientFound = get(client.getCode());
        if (clientFound.getCode() != null) {
            clientFound.setName(client.getName());
            clientRepository.update(clientFound);
            return "Update success";
        } else {
            return "Client not found";
        }
    }

    @Override
    public String delete(String code) throws Exception {
        Client clientFound = get(code);
        if (clientFound != null) {
            clientRepository.delete(clientFound);
            return "Elimination success";
        } else {
            return "Client not found";
        }
    }

}
