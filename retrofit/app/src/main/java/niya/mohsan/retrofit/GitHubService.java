package niya.mohsan.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by mohsan on 10/05/16.
 */
public interface GitHubService {

    String END_POINT = "https://api.github.com";

    @GET("users/{username}?token=6716f56bafc7877ba5c040aadfcecb77a295d0fb")
    Call<User> getUser(@Path("username") String username);


}
