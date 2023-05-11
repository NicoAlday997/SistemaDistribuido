package cliente;

import java.io.IOException;

public class HiloDelCliente implements Runnable
{
	public void run() {
		try {
			new ClientConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
