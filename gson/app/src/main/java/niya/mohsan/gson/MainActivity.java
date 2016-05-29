package niya.mohsan.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;

public class MainActivity extends AppCompatActivity {

    //static final String URL_GITHUB = "https://api.github.com/users/Mohsan1995";
    static final String URL_GITHUB = "https://api.github.com/users/florent37";

    TextView name,login,avatar_url,followers;

    //only 1 instance
    OkHttpClient client;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        login = (TextView) findViewById(R.id.login);
        avatar_url = (TextView) findViewById(R.id.avatar_url);
        followers = (TextView) findViewById(R.id.followers);

        this.client = new OkHttpClient();
        this.gson = new GsonBuilder().create();

        final Request request = new Request.Builder()
                .url(URL_GITHUB)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        User user = gson.fromJson(body,User.class);

                        System.out.println(user.getName());

                        Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, user.getFollowers(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, user.getLogin(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, user.getAvatarUrl(), Toast.LENGTH_SHORT).show();
                        name.setText(user.getName());
                        login.setText(user.getLogin());
                        followers.setText(user.getFollowers());
                        avatar_url.setText(user.getAvatarUrl());
                    }
                });
            }
        });

    }

}
