// MY HOME

/*
package com.company;

import java.sql.*;
import java.util.Scanner;

class ConnectDB {

    protected String getConnectionDataBase() {

        ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Scanner s;
        String procurador = null;

        cdb = new ConnectDataBase();
        s = new Scanner(System.in);

        try {

            cdb.setDrive("oracle.jdbc.driver.OracleDriver");
            cdb.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
            cdb.setUsername("SYSTEM");
            cdb.setPassword("Al@nne1991");
            cdb.setQuery("select * from Procurador");

            Class.forName(cdb.getDrive());

            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());
            ps = c.prepareStatement(cdb.getQuery());
            rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("********************\n" +
                        "BUSCA FUNCIONÁRIO\n" +
                        "********************\n" +
                        "\nDigite o Nome Completo ou CPF ou Matrícula do Funcionário\n");

                System.out.print("Procurador(a): ");
                procurador = s.nextLine();

                while (procurador.isEmpty()) {

                    System.out.println("\nCampo obrigatório!");
                    System.out.print("\nProcurador(a): ");
                    procurador = s.nextLine();

                }

                while (procurador.contains(" ")) {

                    System.out.println("\nNome completo digitado\n");
                    System.out.println("\nNome completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");
                    break;

                }

                while (!procurador.contains(" ") && procurador.length() == 11) {

                    System.out.println("\nCPF digitado\n");
                    System.out.println("\nNome completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");
                    break;

                }

                while (procurador.length() > 1 && procurador.length() == 8) {

                    System.out.println("\nMatrícula digitada\n");
                    System.out.println("\nNome Completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");
                    break;
                }

                while (procurador.length() < 2)
                    System.out.println("Inválido, deverá conter no mínimo 2 dígitos");
            }

            while (!(procurador.length() > 1 && procurador.length() == 8)){
                System.out.println("Não encontrado!");
            }

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        return "";
    }
}

*/
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
        String procurador = null;

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
                            " and VF.NMFUNCIONARIO like '%' || nvl(upper(parm.nome),VF.NMFUNCIONARIO)||'%' " +
                            " order by 1,2"
            );


            Class.forName(cdb.getDrive());
            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());

            ps = c.prepareStatement(cdb.getQuery());

            ps.setString(1, null);
            ps.setString(2, null);
            ps.setString(3, null);


            System.out.println("********************\n" +
                    "BUSCA FUNCIONÁRIO\n" +
                    "********************\n" +
                    "\nDigite o Nome Completo ou CPF ou Matrícula do Funcionário\n");

            System.out.print("Procurador(a): ");
            procurador = s.nextLine();


            // PARA CAMPO VAZIO
            while (procurador.isEmpty()) {

                System.out.println("\nCampo obrigatório!");
                System.out.print("\nProcurador(a): ");
                procurador = s.nextLine();

            }

            // PARA CAMPO COM ESPAÇO
            while (procurador.contains(" ")) {

                ps.setString(1, procurador);
                rs = ps.executeQuery();

                while (rs.next()) {

                    System.out.println("\nNome completo digitado\n");
                    System.out.println("\nNome completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");

                }

                break;

            }

            // PARA CAMPO COM 11 DÍGITOS
            while (!procurador.contains(" ") && procurador.length() == 11) {

                ps.setString(2, procurador);
                rs = ps.executeQuery();

                while (rs.next()) {

                    System.out.println("\nCPF digitado\n");
                    System.out.println("\nNome completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");

                }

                break;

            }

            // PARA CAMPO COM 8 DÍGITOS
            while (procurador.length() > 1 && procurador.length() == 8) {

                ps.setString(3, procurador);
                rs = ps.executeQuery();

                while (rs.next()) {

                    System.out.println("\nMatrícula digitada\n");
                    System.out.println("\nNome Completo: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3) + "\n");

                }

                break;
            }

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        return "";

    }
}