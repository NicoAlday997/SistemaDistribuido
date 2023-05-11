package cliente;

import java.io.IOException;
import java.io.Serializable;
import oshi.SystemInfo;

//CLIENTE
public class InfoSistema extends SystemInfo implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	private SystemInfo systemInfo;
	
	public InfoSistema(SystemInfo systemInfo)
	{
		this.systemInfo=systemInfo;
	}
	
	
	public SystemInfo getSystemInfo()
	{
		return systemInfo;
	}
	
	private void readObject(java.io.ObjectInputStream stream)
		     throws IOException, ClassNotFoundException
		{
		   // Aqui debemos leer los bytes de stream y reconstruir el objeto
		}

		private void writeObject(java.io.ObjectOutputStream stream)
		     throws IOException
		{
		   // Aquí escribimos en stream los bytes que queramos que se envien por red.
		}
	

}
