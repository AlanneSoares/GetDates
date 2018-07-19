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

        cdb.setQuery("with parm as" +
                "(select null as matricula, null as cpf, 'Alanne Soares' as nome from dual" +
                "select  VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA" +
                "from MPRJ.MPRJ_VW_FUNCIONARIO vf" +
                "inner join parm on 1=1" +
                "where VF.CARGO = 'PROCURADOR'" +
                "and     VF.CDSITUACAOFUNC = '1'" +

                "/* se matrícula */" +
                "and VF.CDMATRICULA = nvl(parm.matricula,VF.CDMATRICULA)" +

                "/* se cpf */" +
                "and VF.CPF = nvl (parm.cpf,VF.CPF)" +

                "/* se nome */" +
                "and VF.NMFUNCIONARIO like '%'||nvl(parm.nome,VF.NMFUNCIONARIO)||'%'" +
                "order by 1,2");

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

            System.out.println();

        } catch (SQLSyntaxErrorException error) {
            System.out.println(error.getMessage() + " Conexão não sucedida, tente novamnente");
        }
    }
}
