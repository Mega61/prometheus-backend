package com.prometheus.prometheusbackend.firebase;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
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

}
