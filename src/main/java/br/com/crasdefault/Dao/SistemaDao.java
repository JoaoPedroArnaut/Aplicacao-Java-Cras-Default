package br.com.crasdefault.Dao;

import br.com.crasdefault.funcionalidades.Log;
import br.com.crasdefault.loocaVo.SistemaVo;

import java.awt.*;
import java.net.URI;
import java.sql.*;

public class SistemaDao {

    private Connection connectionAzure;
    private Connection connectionDocker;
    private ConnectionFactory connectionFactory;
    private SistemaVo sistemaVo;
    private LoginDao loginDao;

    public SistemaDao() {
        this.sistemaVo = new SistemaVo();
        this.loginDao = new LoginDao();
        this.connectionFactory = new ConnectionFactory();
    }

    public void inserir() {
        if (!verificaMaquinaCadastrada()){
            inserirAzure();
            inserirDocker();
            pegaGeolocalizacao();
        }
    }

    private void inserirDocker() {
        try {
            connectionDocker = connectionFactory.criaConexaoDocker();
            PreparedStatement stm = connectionDocker.prepareStatement("insert into Maquinas(hostName,sistemaoperacional,processadorInfo,qtdMemoria,qtdParticoes) values (?, ?, ?, ?, ?)");
            stm.setString(1, sistemaVo.getHostname());
            stm.setString(2, sistemaVo.getSistemaOperacional());
            stm.setString(3, sistemaVo.getProcessadorInfo());
            stm.setDouble(4, sistemaVo.getQtdMemoria());
            stm.setInt(5, sistemaVo.getQtdParticoes());
            stm.execute();

            connectionDocker.close();
        }catch (SQLException e){
            Log.criarLogs(e);

        }

    }

    private void inserirAzure() {
        try {
            connectionAzure = connectionFactory.criaConexaoAzure();
            PreparedStatement stm = connectionAzure.prepareStatement("insert into maquinas(hostName,sistemaoperacional,processadorInfo,qtdMemoria,fkCliente,qtdParticoes) values (?, ?, ?, ?, ?, ?)");
            stm.setString(1, sistemaVo.getHostname());
            stm.setString(2, sistemaVo.getSistemaOperacional());
            stm.setString(3, sistemaVo.getProcessadorInfo());
            stm.setDouble(4, sistemaVo.getQtdMemoria());
            stm.setString(5, loginDao.getFkCliente());
            stm.setInt(6, sistemaVo.getQtdParticoes());
            stm.execute();

            connectionAzure.close();
        }catch (SQLException e){
            Log.criarLogs(e);
        }


    }


    private Boolean verificaMaquinaCadastrada(){

        try {
            connectionAzure = connectionFactory.criaConexaoAzure();
            PreparedStatement stm = connectionAzure.prepareStatement("select * from maquinas where hostName = ?");
            stm.setString(1,sistemaVo.getHostname());
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                connectionAzure.close();
                return true;
            } else {
                connectionAzure.close();
                return false;
            }
        }catch (SQLException e){
            Log.criarLogs(e);
            return false;
        }

    }

    private void pegaGeolocalizacao(){
        try {
            URI link = new URI("https://cras-default.azurewebsites.net/geolocalizacao.html?hostname=" + sistemaVo.getHostname());
            Desktop.getDesktop().browse(link);
        }catch (Exception e){
            Log.criarLogs(e);
        }
    }
}
