
 package niya.mohsan.retrofit;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.google.gson.Gson;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView name, pseudo, followers;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        pseudo = (TextView) findViewById(R.id.pseudo);
        followers = (TextView) findViewById(R.id.followers);
        avatar=(ImageView) findViewById(R.id.image);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<User> call = service.getUser("florent37");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user = response.body();
                name.setText(user.getName());
                pseudo.setText(user.getLogin());
                followers.setText(user.getFollowers());

                if(user.getAvatar_url()!=null){
                    Glide.with(getBaseContext())
                            .load(user.getAvatar_url())
                            .into(avatar);
                }


                System.out.println("responce"+ response.message());
                System.out.println("responce"+ response.body().toString());
                System.out.println("rep");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("failure" + t);

            }
        });



    }
}