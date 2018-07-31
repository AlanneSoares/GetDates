package com.company;

import java.io.*;
import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        // chamar a classe para ConnectDB ao banco
        ConnectDB cdb = new ConnectDB();
        System.out.println(cdb.getConnectionDataBase());

    }
}