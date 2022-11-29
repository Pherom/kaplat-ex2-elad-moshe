public class ServerInfo {

    private ServerInfo() {}

    public static final String ADDRESS = "localhost";
    public static final String PORT = "8989";
    public static final String URL = String.format("http://%s:%s", ADDRESS, PORT);
    public static final String TEST_GET_ENDPOINT = "test_get_method";
    public static final String TEST_GET_URI = String.format("%s/%s", URL, TEST_GET_ENDPOINT);
    public static final String TEST_POST_ENDPOINT = "test_post_method";
    public static final String TEST_POST_URI = String.format("%s/%s", URL, TEST_POST_ENDPOINT);
    public static final String TEST_PUT_ENDPOINT = "test_put_method";
    public static final String TEST_PUT_URI = String.format("%s/%s", URL, TEST_PUT_ENDPOINT);
    public static final String TEST_DELETE_ENDPOINT = "test_delete_method";
    public static final String TEST_DELETE_URI = String.format("%s/%s", URL, TEST_DELETE_ENDPOINT);
}
