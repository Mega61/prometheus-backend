package com.prometheus.prometheusbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.prometheus.prometheusbackend.entity.Task;
import com.prometheus.prometheusbackend.firebase.FirebaseSearchService;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static final String COLLECTION_NAME = "Tasks";

    public String createTask(Task task) throws InterruptedException, ExecutionException {

        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, task.getTask_id());

        if (documentSnapshot != null) {
            apiAnswer = "Task creation FAILED\nThe task already exists";
        } else {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(task.getTask_id())
                    .set(task);

            apiAnswer = "Task Creation executed at: " + collectionApiFuture.get().getUpdateTime().toString();
        }

        return apiAnswer;

    }

    public Task getTaskDetails(String taskId) throws InterruptedException, ExecutionException {
        Task task = null;

        DocumentSnapshot document = FirebaseSearchService.searchDocument(COLLECTION_NAME, taskId);

        if (document != null) {
            task = document.toObject(Task.class);
            return task;
        } else {
            return null;
        }
    }

    public String updateTask(Task task) throws InterruptedException, ExecutionException {
        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, task.getTask_id());

        if (documentSnapshot != null) {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(task.getTask_id())
                    .set(task);

            apiAnswer = "Update executed at: " + collectionApiFuture.get().getUpdateTime().toString();

        } else {
            apiAnswer = "Task update FAILED\nThe task does not exists";

        }

        return apiAnswer;
    }

    public String deleteTask(String task_id) throws InterruptedException, ExecutionException {
        String apiAnswer = "";

        DocumentSnapshot documentSnapshot = FirebaseSearchService.searchDocument(COLLECTION_NAME, task_id);

        if (documentSnapshot != null) {
            ApiFuture<WriteResult> collectionApiFuture = FirebaseSearchService.getFirestoreInstance()
                    .collection(COLLECTION_NAME)
                    .document(task_id)
                    .delete();

            apiAnswer = "Task deleted at: " + collectionApiFuture.get().getUpdateTime().toString();
        } else {
            apiAnswer = "Task deletion FAILED\nThe task does not exists";
        }

        return apiAnswer;
    }

    public List<Task> getAllTaskDetails() throws InterruptedException, ExecutionException{
        Iterator<DocumentReference> iterator = FirebaseSearchService.searchAllDocuments(COLLECTION_NAME);

        List<Task> taskList = new ArrayList<>();
        Task task = null;

        while (iterator.hasNext()) {
            DocumentReference documentReference2 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference2.get();
            DocumentSnapshot documentSnapshot = future.get();

            task = documentSnapshot.toObject(Task.class);
            taskList.add(task);
        }

        return taskList;
    }

    public List<Task> getAllTasksPerUser(String client_id){
        List<Task> taskList = new ArrayList<>();
        Task task = null;

        return null;
    }

}
