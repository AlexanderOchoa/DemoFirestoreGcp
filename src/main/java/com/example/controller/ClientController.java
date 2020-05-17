package com.example.controller;

import com.example.entity.Client;
import com.example.request.ListRequest;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/clients")
@CrossOrigin(value = "*")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/list-page", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<Client>> listPage(@RequestBody ListRequest request) throws Exception {
        List<Client> result = clientService.getListPage(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/quantity-page", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Integer> quantityPage(@RequestBody ListRequest request) throws Exception {
        int result = clientService.getQuantityPage(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Client> get(@PathVariable(value = "code") String code) throws Exception {
        Client client = clientService.get(code);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Client>> list() throws Exception {
        List<Client> list = clientService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> register(@RequestBody Client client) throws Exception {
        String result = clientService.register(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<String> update(@RequestBody Client client) throws Exception {
        String result = clientService.update(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable(value = "code") String code) throws Exception {
        String result = clientService.delete(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
