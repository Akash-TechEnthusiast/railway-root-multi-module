package com.india.railway.service.mysql;

import java.util.List;

import com.india.railway.model.mysql.User;

public interface UserServiceTests {
    String saveUser(String name, String email);

    User saveUsertest(User user);

    User getUserById(Long id);

    String deleteUser(Long id);

    List<User> getAllUsers();

    // User getUserById(Long id);
}
