package com.prometheus.prometheusbackend.service;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.prometheus.prometheusbackend.entity.User;
import com.prometheus.prometheusbackend.firebase.FirebaseSearchService;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String createProduct(User user) throws InterruptedException, ExecutionException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, user.getEmail());

        if (documentSnapshot.exists()) {
            apiAnswer = "User creation FAILED\nThe user already exists";
        } else {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(user.getEmail())
                    .set(user);

            apiAnswer = "Creation executed at: " + collectionApiFuture.get().getUpdateTime().toString();
        }

        return apiAnswer;
    }

    public User getUserDetails(String email) throws InterruptedException, ExecutionException {

        User user = null;

        DocumentSnapshot document = FirebaseSearchService.searchDocument(COLLECTION_NAME, email);

        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }

    public String updateProduct(User user) throws InterruptedException, ExecutionException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, user.getEmail());

        if (documentSnapshot != null) {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(user.getEmail())
                    .set(user);

            apiAnswer = "Update executed at: " + collectionApiFuture.get().getUpdateTime().toString();

        } else {
            apiAnswer = "User update FAILED\nThe user does not exists";

        }

        return apiAnswer;
    }

}
