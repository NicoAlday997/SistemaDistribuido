package servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import oshi.SystemInfo;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;


public class EnviaIP extends Thread{

	//InetAddress inetAddress;
    private String IP;
    private DatagramSocket enviador;
	private DatagramPacket dgp;
	private byte[] dato;
	
	public EnviaIP(String IP) throws SocketException, UnknownHostException
	{
		this.IP=IP;
		enviador = new DatagramSocket();
		System.out.println("Enviando direccion...");
		enviador.setBroadcast(true);
		
	}
	
	public void run()
	{
		getRed(IP);
				
		while(true)
		{
			dato = new byte[256];

			// El destinatario es 192.20.20.255, que es la dirección de broadcast
			 try {

				dgp= new DatagramPacket(dato, dato.length, InetAddress.getByName(getRed(IP)),5432);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String saludo2 = IP;
			// buffer para enviar saludo
			
			byte[] bEnvia2 = saludo2.getBytes();
			// envio el saludo
			InetAddress address2 = dgp.getAddress();
			//System.out.println("ip: " + dgp.getAddress());
			dgp = new DatagramPacket(bEnvia2, bEnvia2.length, address2, dgp.getPort());

			//System.out.println("Direccion enviada...");
			try {
				enviador.send(dgp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public String getIP()
	{
		return IP;
	}
	
	public String getRed(String IP)
	{
		int p1=IP.indexOf('.');
		int p4=IP.lastIndexOf('.');
		
		String c2="";
		
        for(int i=(p1+1); i<IP.length();i++) {
			
        	if(IP.charAt(i)=='.') {
        		break;
        	}
        	
            c2+=String.valueOf(IP.charAt(i));
     	
		}
        
        //System.out.println("c2: " + c2);
		String c1 = IP.substring(0, p1);
		
		//System.out.println("c1: " + c1);
		int p3;	
		p3=c1.length()+c2.length()+2;
		
		String c3 = IP.substring(p3, p4);
		//System.out.println("c3: " + c3);
		
		String c4 = IP.substring((p4+1),IP.length());
		//System.out.println("c4: " + c4);
	
		String red=c1+"."+c2+"."+c3+"."+"255";
		
		System.out.println(red);
		
		return red;
	}
	
}


