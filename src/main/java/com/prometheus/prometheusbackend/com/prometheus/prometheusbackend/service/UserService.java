package com.prometheus.prometheusbackend.com.prometheus.prometheusbackend.service;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.prometheusbackend.entity.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveProduct(User user) throws InterruptedException, ExecutionException {

        Firestore dbFiresotre = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFiresotre.collection(COLLECTION_NAME).document(user.getEmail()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

}
