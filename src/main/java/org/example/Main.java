package org.example;

import util.JdbcConnection;

public class Main {
    public static void main(String[] args) {

        if(JdbcConnection.getConnection().isPresent()) {
            System.out.println("is connected");
        }else {
            System.out.println("is not connected");
        }

    }
}