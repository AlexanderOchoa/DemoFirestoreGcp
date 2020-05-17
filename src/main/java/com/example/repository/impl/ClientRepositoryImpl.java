package com.example.repository.impl;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import com.example.repository.reusable.ReusableRepositoryImpl;
import com.example.request.ListRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl extends ReusableRepositoryImpl implements ClientRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryImpl.class);

    private Firestore firestore;

    @Autowired
    public ClientRepositoryImpl(Firestore firestore) {
        this.firestore = firestore;
    }

    private CollectionReference getCollection() {
        return firestore.collection("client_collection");
    }

    private Query getQueryInstance() throws Exception {
        return firestore.collection("client_collection").get().get().getQuery();
    }

    @Override
    public int getQuantityPage(ListRequest request) throws Exception {
        Query query = getQueryInstance();
        query = addGenericFilter(query, FIELD_TYPE, request.getType());

        return query.get().get().size();
    }

    @Override
    public List<Client> getListPage(ListRequest request) throws Exception {
        Query query = getQueryInstance();
        query = addGenericFilter(query, FIELD_TYPE, request.getType());
        query = addPagination(query, request);
        query = addSort(query, request);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<Client> resultList = new ArrayList<>();

        for (DocumentSnapshot documento : querySnapshot.get().getDocuments()) {
            resultList.add(documento.toObject(Client.class));
        }

        return resultList;
    }

    @Override
    public List<Client> list() throws Exception {
        Query query = getQueryInstance();

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<Client> resultList = new ArrayList<>();

        for (DocumentSnapshot documento : querySnapshot.get().getDocuments()) {
            resultList.add(documento.toObject(Client.class));
        }

        return resultList;
    }

    @Override
    public Client get(String code) throws Exception {
        Query query = getQueryInstance();
        query = addGenericFilter(query, FIELD_CODE, code);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot documento : querySnapshot.get().getDocuments()) {
            return documento.toObject(Client.class);
        }

        return null;
    }

    @Override
    public String register(Client client) throws Exception {
        String autogenerateId = getCollection().document().getId();

        client.setId(autogenerateId);
        getCollection().document(autogenerateId).set(client).get();

        LOGGER.info("Register success.");

        return autogenerateId;
    }

    @Override
    public void update(Client client) throws Exception {
        getCollection().document(client.getId()).set(client);
        LOGGER.info("Update success.");
    }

    @Override
    public void delete(Client client) throws Exception {
        getCollection().document(client.getId()).delete();
        LOGGER.info("Delete success.");
    }

}
