package RestApi;

public class BaseManager {
    protected RestApi getRestApi(){
        RestApiClient restApiClient=new RestApiClient(BaseUrl.url);
        return restApiClient.getRestApi();
    }
}
