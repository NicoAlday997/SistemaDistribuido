package servidor;

public class HiloDelServidor implements Runnable
{
	public void run() 
	{
		new ServidorConnect();
	}
}
