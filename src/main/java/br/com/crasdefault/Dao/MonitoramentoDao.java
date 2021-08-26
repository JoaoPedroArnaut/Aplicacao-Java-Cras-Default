package br.com.crasdefault.Dao;

import br.com.crasdefault.funcionalidades.Log;
import br.com.crasdefault.loocaVo.MonitoramentoVo;
import br.com.crasdefault.loocaVo.SistemaVo;
import com.github.britooo.looca.api.core.Looca;
import br.com.crasdefault.funcionalidades.ConversorDeDados;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;

public class MonitoramentoDao {

    private Connection connection;
    private Connection connectionDocker;
    private MonitoramentoVo monitoramentoVo;



    public MonitoramentoDao() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.monitoramentoVo = new MonitoramentoVo();
        try {
            connection = connectionFactory.criaConexaoAzure();
            connectionDocker = connectionFactory.criaConexaoDocker();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void inserir() {

        try {
            inserirAzure();
        } catch(Exception e) {
            Log.criarLogs(e);
        }

        try {
            inserirDocker();
        } catch(Exception e) {
            Log.criarLogs(e);
        }

    }

    private void inserirAzure() throws SQLException, UnknownHostException {
        PreparedStatement stmLog = connection.prepareStatement("insert into logs(momentoCaptura, usoCpu, usoMemoria, tempoUso, fkMaquina) values (?, ?, ?, ?, ?)");
        stmLog.setTimestamp(1, monitoramentoVo.getMomentoCaptura());
        stmLog.setDouble(2, monitoramentoVo.getUsoCpu());
        stmLog.setDouble(3, monitoramentoVo.getUsoMemoria());
        stmLog.setTime(4, monitoramentoVo.getTempoUso());
        stmLog.setString(5, monitoramentoVo.getFkMaquina());
        stmLog.execute();

        for (Integer i = 0; i < monitoramentoVo.getDiscos().size(); i++) {
            PreparedStatement stm = connection.prepareStatement("insert into discos(id,momentoCaptura,pontoDeMontagem,espacoTotal,espacoDisponivel,fkMaquina) values (?, ?, ?, ?, ?,?)");
            stm.setInt(1, monitoramentoVo.getDiscos().get(i).getId());
            stm.setTimestamp(2, monitoramentoVo.getDiscos().get(i).getMomentoCaptura());
            stm.setString(3, monitoramentoVo.getDiscos().get(i).getPontoDeMontagem());
            stm.setDouble(4, monitoramentoVo.getDiscos().get(i).getEspacoTotal());
            stm.setDouble(5, monitoramentoVo.getDiscos().get(i).getEspacoDisponivel());
            stm.setString(6, monitoramentoVo.getFkMaquina());
            stm.execute();
        }

        connection.close();

    }

    private void inserirDocker() throws SQLException, UnknownHostException {

        PreparedStatement stmLog = connectionDocker.prepareStatement("insert into logs(momentoCaptura, usoCpu, usoMemoria, tempoUso, fkMaquina) values (?, ?, ?, ?, ?)");
        stmLog.setTimestamp(1, monitoramentoVo.getMomentoCaptura());
        stmLog.setDouble(2, monitoramentoVo.getUsoCpu());
        stmLog.setDouble(3, monitoramentoVo.getUsoMemoria());
        stmLog.setTime(4, monitoramentoVo.getTempoUso());
        stmLog.setString(5, monitoramentoVo.getFkMaquina());
        stmLog.execute();

        for (Integer i = 0;i < monitoramentoVo.getDiscos().size();i++){
            PreparedStatement stm = connectionDocker.prepareStatement("insert into discos(id,momentoCaptura,pontoDeMontagem,espacoTotal,espacoDisponivel,fkMaquina) values (?, ?, ?, ?, ?,?)");
            stm.setInt(1, monitoramentoVo.getDiscos().get(i).getId());
            stm.setTimestamp(2, monitoramentoVo.getDiscos().get(i).getMomentoCaptura());
            stm.setString(3, monitoramentoVo.getDiscos().get(i).getPontoDeMontagem());
            stm.setDouble(4, monitoramentoVo.getDiscos().get(i).getEspacoTotal());
            stm.setDouble(5, monitoramentoVo.getDiscos().get(i).getEspacoDisponivel());
            stm.setString(6, monitoramentoVo.getFkMaquina());
            stm.execute();

        }

        connectionDocker.close();
    }
}
