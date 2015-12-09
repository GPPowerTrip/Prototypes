/**
 * Created by ricar on 24/11/2015.
 */
import com.jcraft.jsch.*;

public class SSHConn {
    private Session session = null;
    private String host = "auxiliar@student.dei.uc.pt";
    private String password;

    public SSHConn(String pass) throws JSchException {
        this.password = pass;

        String username = host.substring(0, host.indexOf('@'));
        host = host.substring(host.indexOf('@') + 1);
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, 22);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(password);
        session.connect();

    }
}
