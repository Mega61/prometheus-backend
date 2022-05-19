package com.prometheus.prometheusbackend.firebase;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class FirebaseSearchService {

    public static Firestore getFirestoreInstance() {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        return dbFirestore;

    }

    public static DocumentSnapshot searchDocument(String collection, String documentName)
            throws InterruptedException, ExecutionException {

        DocumentReference documentReference = getFirestoreInstance().collection(collection).document(documentName);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        if (documentSnapshot.exists()) {
            return documentSnapshot;
        } else {
            return null;
        }

    }

    public static boolean validateLogin(String collection, String email, String password)
            throws InterruptedException, ExecutionException {

        boolean conclusion = false;

        CollectionReference collectionReference = getFirestoreInstance().collection(collection);
        Query query = collectionReference.whereEqualTo("email", email).whereEqualTo("password", password);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            document.get("email");
            if (document.exists()) {
                conclusion = true;
            }
        }
        return conclusion;
    }

    public static Iterator<DocumentReference> searchAllDocuments(String collection)
            throws InterruptedException, ExecutionException {

        Iterable<DocumentReference> documentReference = getFirestoreInstance().collection(collection).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        return iterator;

    }

}
