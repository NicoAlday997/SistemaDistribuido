package servidor;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import oshi.SystemInfo;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;

public class ServidorConnect 
{	
	
private static final int PUERTO = 5000;
	
	public ServidorConnect()
	{
        try {
            @SuppressWarnings("resource")
			ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho en el puerto " + PUERTO);
            int numCli = 0;
            
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();            		
            NetworkParams networkParams=os.getNetworkParams();
            
            String hostName=networkParams.getDomainName();
            String ip=InetAddress.getByName(hostName).getHostAddress();
            
            System.out.println("ando por aca: " + ip);
                          
            (new EnviaIP(ip)).start();
                    
            while (true) {

                Socket socket = skServidor.accept();

                (new Thread(new HiloDeCliente(socket, ++numCli))).start();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
}
