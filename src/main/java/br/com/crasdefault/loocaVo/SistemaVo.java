package br.com.crasdefault.loocaVo;

import br.com.crasdefault.Dao.LoginDao;
import br.com.crasdefault.funcionalidades.ConversorDeDados;
import br.com.crasdefault.funcionalidades.Log;
import com.github.britooo.looca.api.core.Looca;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SistemaVo {

    private String hostname;
    private String sistemaOperacional;
    private String processadorInfo;
    private Double qtdMemoria;
    private Integer qtdParticoes;

    public SistemaVo() {
        Looca looca = new Looca();
        ConversorDeDados convesor = new ConversorDeDados();

        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            Log.criarLogs(e);
        }

        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.processadorInfo = looca.getProcessador().getNome();
        this.qtdMemoria = Double.valueOf(convesor.convert(looca.getMemoria().getTotal(), 2).replaceAll(",", "."));
        this.qtdParticoes = looca.getGrupoDeDiscos().getVolumes().size();
    }

    public String getHostname() {
        return hostname;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public String getProcessadorInfo() {
        return processadorInfo;
    }

    public Double getQtdMemoria() {
        return qtdMemoria;
    }

    public Integer getQtdParticoes() {
        return qtdParticoes;
    }
}
