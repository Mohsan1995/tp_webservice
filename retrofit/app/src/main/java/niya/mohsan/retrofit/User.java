package niya.mohsan.retrofit;

/**
 * Created by mohsan on 10/05/16.
 */

public class User {

    public String name;
    public String login;
    public String avatar_url;
    public String followers;

    public User(String name, String login, String avatar_url, String followers) {
        this.name = name;
        this.login = login;
        this.avatar_url = avatar_url;
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getFollowers() {
        return followers;
    }
}
