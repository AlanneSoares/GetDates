package com.company;

class ConnectDataBase {

    private String query;
    private String drive;
    private String url;
    private String username;
    private String password;

    // My Home
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
}

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    // My Work

    /*

    public String getQuery() {
        return query;
    }

    public void setQuery() {
        this.query = "with parm as ( select ? as nome, ? as cpf, ? as matricula from dual) " +
                            " select  VF.NMFUNCIONARIO,VF.CPF,VF.CDMATRICULA from MPRJ.MPRJ_VW_FUNCIONARIO vf " +
                            " inner join parm on 1 = 1 " +
                            " where VF.CARGO = 'PROCURADOR' " +
                            " and VF.CDSITUACAOFUNC = '1' " +
                            " and VF.CDMATRICULA = nvl(parm.matricula,VF.CDMATRICULA) " +
                            " and VF.CPF = nvl (parm.cpf,VF.CPF) " +
                            " and VF.NMFUNCIONARIO like '%' || nvl(parm.nome,VF.NMFUNCIONARIO)||'%' " +
                            " order by 1,2"
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive() {
        this.drive = "oracle.jdbc.driver.OracleDriver";

    }

    public String getUrl() {
        return url;
    }

    public void setUrl() {
        this.url = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        this.username = "TJRJ_WEBSERVICE_CON");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = "TJRJ_WEBSERVICE_CON");
    }
     */
}
