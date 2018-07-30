// MY HOME

/* 2º comment
package com.company;

import java.sql.*;
import java.util.Scanner;

class ConnectDB {

    protected String getConnectionDataBase() throws ClassNotFoundException, SQLException {

        ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Scanner s;

        String procurador;

        cdb = new ConnectDataBase();
        s = new Scanner(System.in);


        try {

            // Drive
            cdb.setDrive("oracle.jdbc.driver.OracleDriver");
            cdb.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
            cdb.setUsername("SYSTEM");
            cdb.setPassword("Al@nne1991");
            cdb.setQuery("select * from Procurador");

            Class.forName(cdb.getDrive());
            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());

            ps = c.prepareStatement(cdb.getQuery());

            ps.setString(1, null);
            ps.setString(2, "");
            ps.setString(3, "");

            // FUNCIONOU CHAMANDO ASSIM
            rs = ps.executeQuery();

            while (rs.next()) {

                // FUNCIONOU ASSIM
                System.out.println("Nome: " + rs.getString(1) + "CPF: " + rs.getString(2) + " Matrícula: " + rs.getString(3));

/* 1º comment   System.out.print("Procurador(a): ");
                procurador = s.nextLine();

                while (procurador.isEmpty()) {

                    System.out.println("\nCampo obrigatório!");

                    System.out.print("\nProcurador(a): ");
                    procurador = s.nextLine() + "\n";

                }

                while (procurador.contains(" ")) {

                    System.out.println("Nome completo: ");
                    procurador = s.nextLine() + "\n";

                }

                while (!procurador.contains(" ")) {

                    if (procurador.length() <= 8) {

                        System.out.println("Matrícula: ");
                        procurador = s.next();

                    } else if (procurador.length() == 11) {

                        System.out.println("CPF: ");
                        procurador = s.next();

                    } else {

                        System.out.println("Dado inválido");

                        System.out.print("\nProcurador(a): ");
                        procurador = s.nextLine();
                    }
                }

                System.out.println(
                        "-------------- Resultado -------------" +
                        "Nome Completo: " + rs.getString(1) +
                        "CPF: " + rs.getString(2) +
                        "Matrícula: " + rs.getString(3)

                ); 1º comment encerra aqui
            }

        } catch (Exception e) {
            System.out.println("Erro!");

        }

        return "";
    }
} */


//-----------------------------------------------------------------------------------------------------



// MY WORK

package com.company;

        import java.sql.*;
        import java.util.Scanner;

class ConnectDB {

    protected String getConnectionDataBase() throws ClassNotFoundException, SQLException {

        ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Scanner s;
        String procurador;

        cdb = new ConnectDataBase();
        s = new Scanner(System.in);


        try {

            // Drive
            cdb.setDrive("oracle.jdbc.driver.OracleDriver");
            cdb.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))");
            cdb.setUsername("TJRJ_WEBSERVICE_CON");
            cdb.setPassword("TJRJ_WEBSERVICE_CON");
            cdb.setQuery(
                            "with parm as ( select ? as nome, ? as cpf, ? as matricula from dual) " +
                            " select  VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA from MPRJ.MPRJ_VW_FUNCIONARIO vf " +
                            " inner join parm on 1 = 1 " +
                            " where VF.CARGO = 'PROCURADOR' " +
                            " and VF.CDSITUACAOFUNC = '1' " +
                            " and VF.CDMATRICULA = nvl(parm.matricula,VF.CDMATRICULA) " +
                            " and VF.CPF = nvl (parm.cpf,VF.CPF) " +
                            " and VF.NMFUNCIONARIO like '%' || nvl(parm.nome,VF.NMFUNCIONARIO)||'%' " +
                            " order by 1,2"
            );


            Class.forName(cdb.getDrive());
            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());

            ps = c.prepareStatement(cdb.getQuery());

                ps.setString(1, null);
                ps.setString(2, null);
                ps.setString(3, null);
                rs = ps.executeQuery();


            while (rs.next()) {

                //System.out.println("Nome: " + rs.getString(1) + "CPF: " + rs.getString(2) + " Matrícula: " + rs.getString(3));

                System.out.print("Procurador(a): ");
                procurador = s.nextLine();

                int array[] = new int[0];

                for (int i = 0; i <= array.length; i++) {

                    while (procurador.isEmpty()) {

                        System.out.println("\nCampo obrigatório!");

                        System.out.print("\nProcurador(a): ");
                        procurador = s.nextLine() + "\n";

                    }

                    while (procurador.contains(" ")) {

                        System.out.println("\nNome completo digitado\n");

                        for (int p = 0; p < procurador.length(); p++) {
                            //System.out.println("\nNome completo: " + procurador + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");
                            System.out.println("Procurador " + i);
                            break;
                        }

                    }

                    while (!procurador.contains(" ")) {

                        if (procurador.length() == 11) {

                            System.out.println("\nCPF digitado\n");
                            System.out.println("\nNome completo: " + rs.getString(1) + "\nCPF: " + procurador + "\nMatrícula: " + rs.getString(3) + "\n");
                            break;

                        } else if (procurador.length() <= 8) {

                            System.out.println("\nMatrícula digitada\n");


                                System.out.println("\nNome Completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(i) + "\n");

                                break;

                        } else {

                            System.out.println("\nDados inválido");
                            System.out.print("\nProcurador(a): ");
                            procurador = s.nextLine();
                            break;

                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro!");

        }

        return "";
    }
}