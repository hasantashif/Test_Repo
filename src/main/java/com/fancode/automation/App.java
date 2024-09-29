package com.fancode.automation;

import com.fancode.automation.model.UserProfile;
import com.fancode.automation.service.UserService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            UserService userService = new UserService();
            List<UserProfile> fanCodeUsers = userService.getUsers();

            System.out.println("===== FanCode Users =====");
            System.out.println();

            double averageCompletionRate = userService.calculateAverageCompletionRate(fanCodeUsers);
            System.out.printf("Average Completion Rate: %.2f%%%n", averageCompletionRate);
            System.out.println();

            for (UserProfile user : fanCodeUsers) {
                userService.loadUserTodos(user);
                
                System.out.printf("User: %s%n", user.getName());
                System.out.printf("Completion Rate: %.2f%%%n", user.calculateCompletionRate());

                List<String> posts = userService.getUserPosts(user.getId());
                System.out.println("Posts:");
                for (String post : posts) {
                    System.out.printf(" - %s%n", post);
                }

                List<String> albums = userService.getUserAlbums(user.getId());
                System.out.println("Albums:");
                for (String album : albums) {
                    System.out.printf(" - %s%n", album);
                }


                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
