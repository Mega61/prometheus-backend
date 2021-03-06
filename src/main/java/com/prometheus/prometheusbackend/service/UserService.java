package com.prometheus.prometheusbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.prometheus.prometheusbackend.entity.User;
import com.prometheus.prometheusbackend.firebase.FirebaseSearchService;
import com.prometheus.prometheusbackend.model.LoginResponse;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String createUser(User user) throws InterruptedException, ExecutionException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, user.getClient_id());

        if (documentSnapshot != null) {
            apiAnswer = "User creation FAILED\nThe user already exists";
        } else {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(user.getClient_id())
                    .set(user);

            apiAnswer = "Creation executed at: " + collectionApiFuture.get().getUpdateTime().toString();
        }

        return apiAnswer;
    }

    public User getUserDetails(String userId) throws InterruptedException, ExecutionException {

        User user = null;

        DocumentSnapshot document = FirebaseSearchService.searchDocument(COLLECTION_NAME, userId);

        if (document != null) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }

    public String updateUser(User user) throws InterruptedException, ExecutionException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, user.getClient_id());

        if (documentSnapshot != null) {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(user.getClient_id())
                    .set(user);

            apiAnswer = "Update executed at: " + collectionApiFuture.get().getUpdateTime().toString();

        } else {
            apiAnswer = "User update FAILED\nThe user does not exists";

        }

        return apiAnswer;
    }

    public String deleteUser(String client_id) throws ExecutionException, InterruptedException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, client_id);

        if (documentSnapshot != null) {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(client_id)
                    .delete();

            apiAnswer = "User deleted at: " + collectionApiFuture.get().getUpdateTime().toString();
        } else {
            apiAnswer = "User deletion FAILED\nThe user does not exists";
        }

        return apiAnswer;

    }

    public List<User> getAllUserDetails() throws InterruptedException, ExecutionException {

        Iterator<DocumentReference> iterator = FirebaseSearchService.searchAllDocuments(COLLECTION_NAME);

        List<User> userList = new ArrayList<>();
        User user = null;

        while (iterator.hasNext()) {
            DocumentReference documentReference2 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference2.get();
            DocumentSnapshot documentSnapshot = future.get();

            user = documentSnapshot.toObject(User.class);
            userList.add(user);
        }

        return userList;
    }

    public LoginResponse validateLogin(String email, String password) throws InterruptedException, ExecutionException {

        return FirebaseSearchService.validateLogin(COLLECTION_NAME, email, password);
    }

}
