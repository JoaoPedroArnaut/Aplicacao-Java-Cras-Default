package br.com.crasdefault.Dao;

import br.com.crasdefault.funcionalidades.Log;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

    public DataSource dataSources;
    public DataSource docker;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:sqlserver://cras-default.database.windows.net:1433;database=cras-default;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        comboPooledDataSource.setUser("crasdefault");
        comboPooledDataSource.setPassword("#Gfgrupo8");

        ComboPooledDataSource comboPooledDocker = new ComboPooledDataSource();
        comboPooledDocker.setJdbcUrl("jdbc:mysql://localhost:3306/crasdefault");
        comboPooledDocker.setUser("root");
        comboPooledDocker.setPassword("urubu100");

        this.dataSources = comboPooledDataSource;
        this.docker = comboPooledDocker;

    }


    public Connection criaConexaoAzure() throws SQLException {
        return dataSources.getConnection();
    }

    public Connection criaConexaoDocker() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            Log.criarLogs(e);
        }
        return docker.getConnection();
    }

}
