package com.company;

import java.sql.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // chamar a classe para ConnectDB ao banco
        //ConnectDB cdb = new ConnectDB();
        //cdb.getConnectionDataBase();
        Scanner s;
        s = new Scanner(System.in);

        try {
            System.out.print("Nome do(a) Procurador(a): ");
            System.out.println(s.nextLine());

            //verifica se o campo está vazio
            while (s.nextLine().equals("")) {
                System.out.println("\nCampo obrigatório!\n");
                System.out.print("Nome do(a) Procurador(a): ");
                s.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Tente novamente" + e);
        }
    }
}
