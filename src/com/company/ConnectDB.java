package com.company;

import java.sql.*;

class ConnectDB {

    protected String getConnectionDataBase() throws ClassNotFoundException, SQLException {

        String d;

        /*ConnectDataBase cdb;
        Connection c;
        PreparedStatement ps;
        ResultSet rs;

        cdb = new ConnectDataBase();


        cdb.setQuery("select VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA\n" +
                "from MPRJ.MPRJ_VW_FUNCIONARIO vf\n" +
                "where CDMATRICULA = '00810399'");

        // Drive
        cdb.setDrive("oracle.jdbc.driver.OracleDriver");
        cdb.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))");
        cdb.setUsername("TJRJ_WEBSERVICE_CON");
        cdb.setPassword("TJRJ_WEBSERVICE_CON");


        try {

            Class.forName(cdb.getDrive());

            c = DriverManager.getConnection(cdb.getUrl(), cdb.getUsername(), cdb.getPassword());
            ps = c.prepareStatement(cdb.getQuery());
            rs = ps.executeQuery();

            System.out.println(dados());

        } catch (SQLSyntaxErrorException error) {
            System.out.println(error.getMessage() + " Conexão não sucedida, tente novamnente");
        }

        return "";
    } */
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


        /*Dates procurador = new Dates();

        procurador.setCpf("");
        procurador.setProcurador("");
        procurador.setMatricula(0);

        return procurador;*/

        //}

    /*List<Dates> procuradores(String likeName){

        String
        cdb.setQuery("select VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA\n" +
                "from MPRJ.MPRJ_VW_FUNCIONARIO vf where NMFUNCIONARIO like '"+likeName.toUpperCase()+"'%'"); */
