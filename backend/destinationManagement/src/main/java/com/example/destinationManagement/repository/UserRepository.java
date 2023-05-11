//package com.example.destinationManagement.repository;
//
//import com.example.destinationManagement.domain.User;
//import org.springframework.stereotype.Repository;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserRepository {
//
//    private List<User> users = new ArrayList<>();
//
//    public User add(User newUser) {
//        newUser.setUser_id(users.size() + 1);
//        users.add(newUser);
//        return newUser;
//    }
//
//    public void remove(int user_id) {
//        users.removeIf(user -> user.getUser_id() == user_id);
//    }
//
//    public User update(User updatedUser) {
//        int index = users.indexOf(findUserById(updatedUser.getUser_id()));
//        if (index != -1) {
//            users.set(index, updatedUser);
//            return updatedUser;
//        }
//        return null;
//    }
//
//    public List<User> findAllUsers() {
//        return users;
//    }
//
//    public User findUserById(int user_id) {
//        for (User user : users) {
//            if (user.getUser_id() == user_id) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public List<User> findAllAdmins() {
//        List<User> admins = new ArrayList<>();
//        for (User user : users) {
//            if (user.is_admin()) {
//                admins.add(user);
//            }
//        }
//        return admins;
//    }
//
//    public User getUser(String userName, String password) {
//        for (User user : users) {
//            if (user.getUser_name().equals(userName) && user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//        return null;
//    }
//}
//
