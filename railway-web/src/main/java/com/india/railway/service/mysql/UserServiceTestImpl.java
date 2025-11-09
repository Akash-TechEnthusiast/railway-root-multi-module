package com.india.railway.service.mysql;

import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceTestImpl implements UserServiceTests {

    private final Map<String, String> userDatabase = new HashMap<>();

    private final Map<Long, User> userData = new HashMap<>(); // Simulating a database

    @Override
    public String saveUser(String name, String email) {
        userDatabase.put(name, email);
        return "User Created Successfully";
    }

    @Override
    public String deleteUser(Long id) {
        return "User deleted successfully";

    }

    @Override
    public User saveUsertest(User user) {
        userData.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userData.get(id);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        return list;

    }

}
