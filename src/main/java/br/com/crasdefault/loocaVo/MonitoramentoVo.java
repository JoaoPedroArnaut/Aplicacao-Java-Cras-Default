package br.com.crasdefault.loocaVo;

import br.com.crasdefault.funcionalidades.ConversorDeDados;
import com.github.britooo.looca.api.core.Looca;
import org.joda.time.DateTimeZone;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class MonitoramentoVo {

    private Timestamp momentoCaptura;
    private Double usoCpu;
    private Double usoMemoria;
    private Time tempoUso;
    private String fkMaquina;
    private List<DiscoVo> discos;

    public MonitoramentoVo() {
        Looca looca = new Looca();
        ConversorDeDados conversor = new ConversorDeDados();

        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        DateTimeZone.setDefault(DateTimeZone.forID("America/Sao_Paulo"));
        this.momentoCaptura = Timestamp.valueOf(LocalDateTime.now());

        this.usoCpu = Double.valueOf(String.format("%.2f", looca.getProcessador().getUso()).replaceAll(",", "."));
        this.usoMemoria = Double.valueOf(conversor.convert(looca.getMemoria().getEmUso(),2));
        this.tempoUso = new Time(looca.getSistema().getTempoDeAtividade());
        this.fkMaquina = new SistemaVo().getHostname();

        this.discos = new ArrayList<>();

        for (Integer i = 0; i < looca.getGrupoDeDiscos().getVolumes().size(); i++){
            discos.add(new DiscoVo(i,this.momentoCaptura));
        }
    }

    public Timestamp getMomentoCaptura() {
        return momentoCaptura;
    }

    public Double getUsoCpu() {
        return usoCpu;
    }

    public Double getUsoMemoria() {
        return usoMemoria;
    }

    public Time getTempoUso() {
        return tempoUso;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public List<DiscoVo> getDiscos() {
        return discos;
    }
}
