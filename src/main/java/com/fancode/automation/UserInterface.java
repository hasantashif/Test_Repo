package com.fancode.automation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.fancode.automation.service.UserService;
import com.fancode.automation.model.UserProfile;

import java.util.List;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        UserService userService = new UserService();
        TextArea textArea = new TextArea();

        try {
            List<UserProfile> fanCodeUsers = userService.getUsers();
            StringBuilder output = new StringBuilder();

            for (UserProfile user : fanCodeUsers) {
                userService.loadUserTodos(user);
                output.append("User: ").append(user.getName()).append("\n")
                      .append("Completion Rate: ").append(user.calculateCompletionRate()).append("%\n")
                      .append("-------------------------\n");
            }

            textArea.setText(output.toString());
        } catch (Exception e) {
            textArea.setText("Error fetching data: " + e.getMessage());
        }

        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FanCode Users");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
