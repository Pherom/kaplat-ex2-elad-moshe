import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        String getResponse = sendWithQueryParams(httpClient, hour, minute);
        ResponseMessage postResponse = sendDataWithBody(httpClient, hour, minute, getResponse);
        ResponseMessage putResponse = updateData(httpClient, hour, minute, postResponse.getMessage());
        deleteResource(httpClient, putResponse.getMessage());
    }

    public static String sendWithQueryParams(HttpClient httpClient, int hour, int minute) throws IOException, InterruptedException {
        URI uri = URI.create(String.format("%s?hour=%s&minute=%s", ServerInfo.TEST_GET_URI, hour, minute));
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static ResponseMessage sendDataWithBody(HttpClient httpClient, int hour, int minute, String responseIdFromGetResponse) throws IOException, InterruptedException {
        URI uri = URI.create(ServerInfo.TEST_POST_URI);
        TimeAndRequestID timeAndRequestID = new TimeAndRequestID(hour, minute, responseIdFromGetResponse);
        String jsonString = JSONUtil.OBJECT_MAPPER.writeValueAsString(timeAndRequestID);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonString)).build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return JSONUtil.OBJECT_MAPPER.readValue(response.body(), ResponseMessage.class);
    }

    public static ResponseMessage updateData(HttpClient httpClient, int hour, int minute, String messageFromPostResponse) throws IOException, InterruptedException {
        URI uri = URI.create(String.format("%s?id=%s", ServerInfo.TEST_PUT_URI, messageFromPostResponse));
        TimeCalculation timeCalculation = new TimeCalculation(hour, minute);
        String jsonString = JSONUtil.OBJECT_MAPPER.writeValueAsString(timeCalculation);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).setHeader("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(jsonString)).build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return JSONUtil.OBJECT_MAPPER.readValue(response.body(), ResponseMessage.class);
    }

    public static void deleteResource(HttpClient httpClient, String messageFromPutResponse) throws IOException, InterruptedException {
        URI uri = URI.create(String.format("%s?id=%s", ServerInfo.TEST_DELETE_URI, messageFromPutResponse));
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).DELETE().build();
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
    }
}
