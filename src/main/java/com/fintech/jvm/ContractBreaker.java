package com.fintech.jvm;

import java.util.HashSet;
import java.util.Set;

public class ContractBreaker {

    static class User {
        private String email;

        public User(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return email.equals(user.email);
        }

        @Override
        public int hashCode() {
            return email.hashCode();
        }
        
        @Override
        public String toString() {
            return "User{email='" + email + "'}";
        }
    }

    public static void main(String[] args) {
        Set<User> uniqueUsers = new HashSet<>();

        User user1 = new User("alice@fintech.com");
        User user2 = new User("alice@fintech.com");

        uniqueUsers.add(user1);
        uniqueUsers.add(user2);

        System.out.println("=== HASHSET DUPLICATE TEST ===");
        System.out.println("Are the emails the same? " + user1.email.equals(user2.email));
        System.out.println("Total users in Set (Should be 1): " + uniqueUsers.size());

        System.out.println("\nContents of the Set:");
        for (User u : uniqueUsers) {
            System.out.println(u);
        }
    }
}