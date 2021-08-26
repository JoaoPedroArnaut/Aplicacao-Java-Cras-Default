//import br.com.crasdefault.Dao.HomeDao;
//import br.com.crasdefault.Dao.LoginDao;
//import br.com.crasdefault.loocaVo.MonitoramentoVo;
//import br.com.crasdefault.loocaVo.SistemaVo;
//import com.github.britooo.looca.api.core.Looca;
//import br.com.crasdefault.funcionalidades.ConversorDeDados;
//import org.junit.Test;
//import oshi.SystemInfo;
//import oshi.hardware.HardwareAbstractionLayer;
//
//
//import java.sql.SQLException;
//
//import static org.junit.Assert.*;
//
//public class LoginDaoTest {
//
//    LoginDao dao = new LoginDao();
//    Looca looca = new Looca();
//    ConversorDeDados conversor = new ConversorDeDados();
//    HomeDao homeDao = new HomeDao();
//    private SystemInfo si = new SystemInfo();
//    private HardwareAbstractionLayer hal = si.getHardware();
//
//    @Test
//    public void verificaLogin() throws SQLException {
//
//        assertFalse(dao.VerificarLogin("", ""));
//    }
//
//
//    @Test
//    public void teste() {
//        assertEquals(new MonitoramentoVo().getDiscos().size(),looca.getGrupoDeDiscos().getVolumes().size());
//    }
//}