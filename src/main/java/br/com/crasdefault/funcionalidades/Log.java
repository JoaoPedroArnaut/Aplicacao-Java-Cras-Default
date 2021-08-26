package br.com.crasdefault.funcionalidades;

import br.com.crasdefault.Dao.LoginDao;
import br.com.crasdefault.Dao.MonitoramentoDao;
import br.com.crasdefault.Slack.Slack;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.net.InetAddress;

public class Log {
    MonitoramentoDao monitoramentoDao = new MonitoramentoDao();
    LoginDao loginDao = new LoginDao();

    public static void criarLogs(Exception ex){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("ddMMyyyyHHmmss");
        String str = date.toString(fmt);

        try {
            FileOutputStream fos = new FileOutputStream(str + "-Log.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            PrintWriter bw = new PrintWriter(osw);
            bw.write("--------------------------------------------");
            bw.println();
            bw.write("--------------------------------------------");
            bw.println();
            bw.write("Hostname:");
            bw.write(InetAddress.getLocalHost().getHostName());
            bw.println();
//            bw.write(login);
//            bw.newLine();
            bw.write("ERRO:");
//            bw.write();
            ex.printStackTrace(bw);
            bw.println();
            bw.write("--------------------------------------------");
            bw.println();
            bw.write("--------------------------------------------");
            bw.close();
            FileInputStream fis = new FileInputStream(str + "-Log.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String linha = br.readLine();

            while(linha != null){
                System.out.println(linha);
                linha = br.readLine();
            }

            br.close();
            Slack.EnviarMensagem("A maquina de hostname:" + InetAddress.getLocalHost().getHostName());
            Slack.EnviarMensagem("Informa: NOVO lOG DE ERRO FOI GERADO");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
