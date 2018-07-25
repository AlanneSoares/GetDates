package com.company;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        // chamar a classe para ConnectDB ao banco
        ConnectDB cdb = new ConnectDB();
        System.out.println(cdb.getConnectionDataBase());

        // declaração de atributo
        //Scanner s;

        // declaração de variável
        //s = new Scanner(System.in);

        //try {

        //System.out.print("\nNome do(a) Procurador(a): ");
        //System.out.println();

        //verifica se o campo está vazio
        //while (s.nextLine().equals("")) {

                /*System.out.println("\nCampo obrigatório!\n");
                System.out.print("Nome do(a) Procurador(a): ");
                s.next(cdb.getConnectionDataBase());
                //s.nextLine();

            }

        } catch (Exception e) {

            System.out.println("Tente novamente" + e);

        }

    }*/


    }
}