package com.prometheus.prometheusbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.prometheus.prometheusbackend.controller.UserController;
import com.prometheus.prometheusbackend.entity.User;
import com.prometheus.prometheusbackend.service.UserService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApiRestTest {

    private static final String DEV = "http://localhost:8080/api";
    private static final String PROD = "https://prometheus-backend.herokuapp.com/api";

    @Mock
    UserService mockUserService;
    @InjectMocks
    UserController userController = new UserController();

    @Test
    public void givenUserIsCreated_whenCreateUserEndPointIsExecuted_thenASingleInteractionIsDone()
            throws InterruptedException, ExecutionException {
        User user = new User();
        userController.createUser(user);

        verify(mockUserService, times(1)).createUser(user);
    }

    @Test
    public void givenAUserTriesToLogIn_WhenTheLoginMethodIsExecuted_thenASingleInteractionIsDone()
            throws InterruptedException, ExecutionException {
        userController.validateUserLogin("Test", "Test");

        verify(mockUserService, times(1)).validateLogin("Test", "Test");
    }

    @Test
    public void givenATaskIsCreatedWithBadFormat_whenTheTaskCreationMethodIsExecuted_thenTheEndPointStatusCodeIs200()
            throws ClientProtocolException, IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(PROD + "/tasks");
        String json = "{" +
                "task_id:Task202," +
                "user_id:User2," +
                "title:Correr," +
                "category:Ejecucion," +
                "stimated_time:2," +
                "difficulty:3," +
                "effort:0," +
                "state:Pendiente," +
                "due_date:2022-05-18" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);

        assertEquals(response.getStatusLine().getStatusCode(), 400);
        client.close();
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
            throws ClientProtocolException, IOException {

        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet(PROD + "/users/email/password");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }


}
