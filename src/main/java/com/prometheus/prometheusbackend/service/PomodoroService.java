package com.prometheus.prometheusbackend.service;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentSnapshot;
import com.prometheus.prometheusbackend.entity.Pomodoro;
import com.prometheus.prometheusbackend.firebase.FirebaseSearchService;

import org.springframework.stereotype.Service;

@Service
public class PomodoroService {
    
    private static final String COLLECTION_NAME = "Tasks";
    private static final String SUB_COLLECTION_NAME = "Pomodoros";

    public String createPomodoro(Pomodoro pomodoro, String task_id) throws InterruptedException, ExecutionException{
        String apiAnswer = "";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, task_id);

        if (documentSnapshot == null) {
            apiAnswer = "Pomodoro Cicle creation FAILED\nThe associated task does not exists";
        } else {
            FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(task_id).collection(SUB_COLLECTION_NAME).add(pomodoro);
                    

            apiAnswer = "Pomodoro cicle Creation executed at: " + timestamp.toString();
        }

        return apiAnswer;
    }

}
