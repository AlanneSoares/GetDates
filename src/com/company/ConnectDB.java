package com.company;

import java.sql.*;
import java.util.Scanner;

class ConnectDB {

    protected String getConnectionDataBase() throws ClassNotFoundException, SQLException {

        //String d;

        ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Scanner s;

        String procurador;
        String space;

        boolean isNumber;
        boolean isWords;

        long matricula;
        long cpf;

        cdb = new ConnectDataBase();
        s = new Scanner(System.in);
        space = " ";


        cdb.setQuery(

                "with parm as ( select ? as nome, ? as cpf, ? as matricula from dual) " +
                        " select  VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA from MPRJ.MPRJ_VW_FUNCIONARIO vf " +
                        " inner join parm on 1=1 " +
                        " where VF.CARGO = 'PROCURADOR' " +
                        " and     VF.CDSITUACAOFUNC = '1' " +
                        " and VF.CDMATRICULA = nvl(parm.matricula,VF.CDMATRICULA) " +
                        " and VF.CPF = nvl (parm.cpf,VF.CPF) " +
                        " and VF.NMFUNCIONARIO like '%'||nvl(parm.nome,VF.NMFUNCIONARIO)||'%' " +
                        " order by 1,2"

        );

        // Drive
        cdb.setDrive("oracle.jdbc.driver.OracleDriver");
        cdb.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))");
        cdb.setUsername("TJRJ_WEBSERVICE_CON");
        cdb.setPassword("TJRJ_WEBSERVICE_CON");


        try {

            Class.forName(cdb.getDrive());

            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());
            ps = c.prepareStatement(cdb.getQuery());
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, "");
            rs = ps.executeQuery();

            while (rs.next()) {

                System.out.print("Procurador(a): ");
                procurador = s.nextLine();


                // Verifica se o campo está vazio
                while (procurador.equals("")) {
                    System.out.println("\nCampo obrigatório!\n");

                    System.out.print("\nProcurador(a): ");
                    procurador = s.nextLine();

                } // ok funcionando


                // Verifica se os dados digitados é nome completo, sendo assim se conter espaços, identificará que é Nome Completo

                int array[] = new int[0];

                while (procurador.contains(array.toString()) && procurador.length() <= 8) {

                    for (int i = 0; i < 8; i++) {

                        System.out.println("Matrícula" + i);

                    }

                    //System.out.println("Nome: " + rs.getString(1));
                    //System.out.println("\nNome: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3));


                    // SE DIGITAR MATRÍCULA

                    // Se for um número e menor ou igual a 8 dígitos  -> considerar matrícula

                    while (!(procurador.length() <= 8)) {
                        System.out.println("\nInválido! Digite o nome completo ou CPF ou Matrícula");

                        System.out.println("\nProcurador: ");
                        procurador = s.next();

                    }

                    System.out.println("Matrícula: " + rs.getString(3));
                    System.out.println("Nome: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3));


                    // SE DIGITAR CPF

                    // Se maior que 8 até 11 -> considerar cpf

                    while (!(procurador.length() == 11)) {
                        System.out.println("\nInválido! Digite o nome completo ou CPF ou Matrícula\n");

                        System.out.println("\nProcurador: ");
                        procurador = s.nextLine();

                        System.out.println("Nome: ");
                        System.out.println("CPF: " + rs.getString(2));
                    }

                    while ((!procurador.contains(space)) && procurador.length() <= 8 && procurador.length() == 11) {

                        System.out.println("Nome: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3));
                    }
                }
            }

        } catch (SQLSyntaxErrorException error) {
            System.out.println(error.getMessage() + " Conexão não sucedida, tente novamente");
        }

        return "";
    }
}