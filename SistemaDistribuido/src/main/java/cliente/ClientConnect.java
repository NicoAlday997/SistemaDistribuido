package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import oshi.SystemInfo;
import servidor.Datos;
import servidor.ServidorFrame;

public class ClientConnect{

	//La IP del servidor se recibira a traves de UDP
	//mientras la IP del servidor sea igual a null no se ejecute la conexion 
	//Direccio IP del servidor se toma como una variable
	
	public String HOST = null;
	public int PUERTO = 5000;
	private Socket sc;
	private String ipSeg="";


	public ClientConnect() throws InterruptedException, IOException {
		sc = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;    
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		AsignaIP IPServ;
		Datos dat3 = null;
		boolean cli=false;
		
		do{
			IPServ=new AsignaIP(ip);
		}while(IPServ.getIP()==null);
		
		System.out.println("La ip es: " + IPServ.getIP());
		
		HOST=IPServ.getIP();
		

		while (!cli) {
			try {
	        	sc = new Socket(HOST, PUERTO);
				oos = new ObjectOutputStream(sc.getOutputStream());
				ois = new ObjectInputStream(sc.getInputStream());
				
	            while (true) {
	            	
	            	InfoSistema otro=new InfoSistema(new SystemInfo());
	            	
	            	Datos dat2=new Datos(otro);

	            	dat2.setDatos();
    				dat2.setPoints();
    				dat2.setIP(ip);
    				
	    			oos.writeObject(dat2);
	    						
	    			dat3 = (Datos) ois.readObject();
	    			
	    			DatosCliente(dat3.getArr(), ip);
	    			System.out.println("getSegLug: " + dat3.getSegLug());
	    			
	    			if(dat3.getSegLug()!=null) 
	    				{
	    				ipSeg=dat3.getSegLug();
	    				System.out.println("if  ipSeg: " + ipSeg);
	    				}
	
	            	Thread.sleep(2000);
	            }
	        } catch(Exception e) {
	            System.out.println("Esperando conexion con el servidor...");
	            ClientFrame.statusconexion.setSelected(false);
	            
	            System.out.println("Segundo lugar recibido: " + ipSeg);
	            
	            if(ipSeg.equals(ip))
	            {
		            ClientFrame.cliente.dispose();
		            cli=true;

	            }else{
	            	do{
	        			IPServ=new AsignaIP(ip);
	        		}while(IPServ.getIP()==null);
	        		
	        		System.out.println("La ip es: " + IPServ.getIP());
	        		
	        		HOST=IPServ.getIP();
	            }
	            Thread.sleep(2000);  
	        }
		}
		
		new ServidorFrame();
	}

	void DatosCliente(ArrayList<String> datos, String ip) {
		ClientFrame.hostname.setText(datos.get(7));
		ClientFrame.statusconexion.setSelected(true);
		ClientFrame.hostip.setText(ip);
		ClientFrame.table.setModel(new DefaultTableModel(
				new Object[][] {
					{" S.O", datos.get(6)},
					{" Procesador", datos.get(4)+" "+datos.get(5)},
					{" Velocidad del Procesador", datos.get(8)},
					{" Ram Total", datos.get(2)},
					{" Ram Disponible", datos.get(3)},
					{" Espacio Total", datos.get(0)},
					{" Espacio Libre", datos.get(1)},
				},
				new String[] {
					"New column", "New column"
				}
		));
	}
}
