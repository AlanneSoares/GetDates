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

        cdb = new ConnectDataBase();
        s = new Scanner(System.in);

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

                String cpf = null;
                String matricula = null;
                String nome = null;

                System.out.print("Digite o(a) Procurador(a): ");
                s.next();

                if (s.next().equals(cpf.toUpperCase())) {
                    System.out.println(rs.getString(1));

                    System.out.println("\nNome: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3));

                } else if (s.nextLine().equals(0)) {
                    rs.getString(2);
                    System.out.println("Nome: " + rs.getString(1) + "\nCPF: " + rs.getString(2) + "\nMatrícula: " + rs.getString(3));
                }

            }


            rs.getRow();

        } catch (SQLSyntaxErrorException error) {
            System.out.println(error.getMessage() + " Conexão não sucedida, tente novamnente");
        }

        return "";
    }


    private static void Dados(String d) {

        ConnectDataBase cdb;
        Connection c;

        PreparedStatement ps;
        ResultSet rs;

        cdb = new ConnectDataBase();

        String parametroQuery = null;
        boolean isNumero = true;

        try {

            Integer.parseInt(d);

        } catch (Exception e) {

            isNumero = false;

        }

        if (isNumero && d.length() <= 8) {

            parametroQuery = "where CDMATRICULA ='" + d;

        } else if (isNumero && d.length() <= 11) {

            parametroQuery = "where CPF ='" + d;

        } else {

            //procuradores(parametro);

        }

        cdb.setQuery("select VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA\n" +
                "from MPRJ.MPRJ_VW_FUNCIONARIO vf\n" + parametroQuery);
    }
}