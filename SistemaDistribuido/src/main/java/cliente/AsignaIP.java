package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AsignaIP 
{
	DatagramSocket socket;
	String saludo;

	public AsignaIP(String ip) throws IOException
	{
		
		socket=new DatagramSocket(5432);
		InetAddress address=InetAddress.getByName(ip);
		System.out.println("Asigna IP: " + address);

		//buffer para recibir la respuesta
		byte[] bRecibe=new byte[256];
		
		DatagramPacket packet=new DatagramPacket(bRecibe, bRecibe.length, address, 5432);
		
		System.out.println("entrando aca");
		
		//recibo el mensaje
		socket.receive(packet);

		//muestro el resultado
		saludo=new String(packet.getData(),0,packet.getLength());
				
		socket.close();
	}
	
	public String getIP()
	{
		return saludo;
	}

}
