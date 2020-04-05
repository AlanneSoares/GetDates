package com.company;

import java.sql.*;
import java.util.Scanner;

class ConnectDB {

    protected void getConnectionDataBase() throws ClassNotFoundException, SQLException {

        ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;

        cdb = new ConnectDataBase();

        cdb.setQuery("");

        // Drive
        cdb.setDrive("oracle.jdbc.driver.OracleDriver");
        cdb.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=)(PORT=))(CONNECT_DATA = (SERVICE_NAME=CORR)))");
        cdb.setUsername("");
        cdb.setPassword("");



        try {

            Class.forName(cdb.getDrive());

            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());
            ps = c.prepareStatement(cdb.getQuery());
            rs = ps.executeQuery();

            System.out.println();

        } catch (SQLSyntaxErrorException error) {
            System.out.println(error.getMessage() + " Conexão não sucedida, tente novamnente");
        }
    }
}
