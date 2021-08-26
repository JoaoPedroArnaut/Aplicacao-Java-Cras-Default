package br.com.crasdefault.loocaVo;

import br.com.crasdefault.funcionalidades.ConversorDeDados;
import br.com.crasdefault.funcionalidades.Log;
import com.github.britooo.looca.api.core.Looca;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DiscoVo {

    private Integer id;
    private Timestamp momentoCaptura;
    private String pontoDeMontagem;
    private Double espacoTotal;
    private Double espacoDisponivel;
    private String fkMaquina;

    public DiscoVo(Integer i,Timestamp momentoCaptura) {
        Looca looca = new Looca();
        ConversorDeDados conversor = new ConversorDeDados();

        this.id = (i + 1);

        this.momentoCaptura = momentoCaptura;
        this.pontoDeMontagem = looca.getGrupoDeDiscos().getVolumes().get(i).getPontoDeMontagem();
        this.espacoTotal = Double.valueOf(conversor.convert(looca.getGrupoDeDiscos().getVolumes().get(i).getTotal(), 2));
        this.espacoDisponivel = Double.valueOf(conversor.convert(looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel(), 2));

        try {
            this.fkMaquina = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            Log.criarLogs(e);
        }
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getMomentoCaptura() {
        return momentoCaptura;
    }

    public String getPontoDeMontagem() {
        return pontoDeMontagem;
    }

    public Double getEspacoTotal() {
        return espacoTotal;
    }

    public Double getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }
}
