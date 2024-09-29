package com.fancode.automation;

import com.fancode.automation.model.UserProfile;
import com.fancode.automation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    public void testGetUsersFromFanCodeCity() throws Exception {
        List<UserProfile> users = userService.getUsers();
        System.out.println("Total users fetched: " + users.size());


        for (UserProfile user : users) {
            System.out.println("User: " + user.getName() + ", Lat: " + user.getLatitude() + ", Long: " + user.getLongitude());
            Assertions.assertTrue(user.isFromFanCodeCity(), "User is not from FanCode city");
        }
    }

    @Test
    public void testSpecificUserProperties() throws Exception {
        List<UserProfile> users = userService.getUsers();
        
        UserProfile user = users.stream().filter(u -> u.getId() == 1).findFirst().orElse(null);

        Assertions.assertNotNull(user, "Expected user not found");
        Assertions.assertEquals("Leanne Graham", user.getName(), "User name does not match");
        Assertions.assertTrue(user.getLatitude() >= -40 && user.getLatitude() <= 5, "Latitude is out of range");
        Assertions.assertTrue(user.getLongitude() >= 5 && user.getLongitude() <= 100, "Longitude is out of range");
    }

    @Test
    public void testNoUsers() throws Exception {
        UserService mockUserService = Mockito.mock(UserService.class);
        Mockito.when(mockUserService.getUsers()).thenReturn(List.of());

        List<UserProfile> users = mockUserService.getUsers();
        Assertions.assertTrue(users.isEmpty(), "Expected no users to be returned");
    }

    @Test
    public void testEdgeCaseCoordinates() {
        UserProfile userOnBoundary = new UserProfile();
        userOnBoundary.setLatitude(-40);
        userOnBoundary.setLongitude(5);
        Assertions.assertTrue(userOnBoundary.isFromFanCodeCity(), "User should be considered from FanCode city at boundary coordinates.");

        userOnBoundary.setLatitude(5);
        userOnBoundary.setLongitude(100);
        Assertions.assertTrue(userOnBoundary.isFromFanCodeCity(), "User should be considered from FanCode city at boundary coordinates.");
    }
}
