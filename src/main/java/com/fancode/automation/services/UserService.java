package com.fancode.automation.service;

import com.fancode.automation.model.UserProfile;
import com.fancode.automation.network.ApiService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final ApiService apiService = new ApiService();

    public List<UserProfile> getUsers() throws Exception {
        String usersJson = apiService.getData("/users");
        JSONArray usersArray = new JSONArray(usersJson);

        List<UserProfile> users = new ArrayList<>();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userObj = usersArray.getJSONObject(i);
            JSONObject geo = userObj.getJSONObject("address").getJSONObject("geo");

            UserProfile user = new UserProfile();
            user.setId(userObj.getInt("id"));
            user.setName(userObj.getString("name"));
            user.setLatitude(Double.parseDouble(geo.getString("lat")));
            user.setLongitude(Double.parseDouble(geo.getString("lng")));

            // Add users from FanCode city
            if (user.isFromFanCodeCity()) {
                users.add(user);
            }
        }
        return users;
    }

    public void loadUserTodos(UserProfile user) throws Exception {
        String todosJson = apiService.getData("/todos?userId=" + user.getId());
        JSONArray todosArray = new JSONArray(todosJson);

        int completedTasks = 0;
        for (int i = 0; i < todosArray.length(); i++) {
            if (todosArray.getJSONObject(i).getBoolean("completed")) {
                completedTasks++;
            }
        }
        user.setTotalTasks(todosArray.length());
        user.setCompletedTasks(completedTasks);
    }

    public List<String> getUserPosts(int userId) throws Exception {
        String postsJson = apiService.getData("/posts?userId=" + userId);
        JSONArray postsArray = new JSONArray(postsJson);

        List<String> posts = new ArrayList<>();
        for (int i = 0; i < postsArray.length(); i++) {
            String postTitle = postsArray.getJSONObject(i).getString("title");
            posts.add(postTitle);
        }
        return posts;
    }

    public List<String> getPostComments(int postId) throws Exception {
        String commentsJson = apiService.getData("/comments?postId=" + postId);
        JSONArray commentsArray = new JSONArray(commentsJson);

        List<String> comments = new ArrayList<>();
        for (int i = 0; i < commentsArray.length(); i++) {
            String commentBody = commentsArray.getJSONObject(i).getString("body");
            comments.add(commentBody);
        }
        return comments;
    }

    public List<String> getUserAlbums(int userId) throws Exception {
        String albumsJson = apiService.getData("/albums?userId=" + userId);
        JSONArray albumsArray = new JSONArray(albumsJson);

        List<String> albums = new ArrayList<>();
        for (int i = 0; i < albumsArray.length(); i++) {
            String albumTitle = albumsArray.getJSONObject(i).getString("title");
            albums.add(albumTitle);
        }
        return albums;
    }

    public List<String> getAlbumPhotos(int albumId) throws Exception {
        String photosJson = apiService.getData("/photos?albumId=" + albumId);
        JSONArray photosArray = new JSONArray(photosJson);

        List<String> photos = new ArrayList<>();
        for (int i = 0; i < photosArray.length(); i++) {
            String photoUrl = photosArray.getJSONObject(i).getString("url");
            photos.add(photoUrl);
        }
        return photos;
    }

    public double calculateAverageCompletionRate(List<UserProfile> users) {
    if (users.isEmpty()) return 0;

    double totalCompletion = 0;
    for (UserProfile user : users) {
        totalCompletion += user.calculateCompletionRate();
    }
    return totalCompletion / users.size();
}

}
