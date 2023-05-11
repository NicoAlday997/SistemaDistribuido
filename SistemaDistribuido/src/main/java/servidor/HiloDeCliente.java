package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class HiloDeCliente implements Runnable
{
    private Socket skCliente;
    private String primLug, segLug;
    private Datos dat=null;

    public HiloDeCliente(Socket skEnviado, int num)
    {
        skCliente = skEnviado;
    }

    public void run() {
		ObjectInputStream ois;
		ObjectOutputStream oos;
		String namehost = "";
        try {
			ois = new ObjectInputStream( skCliente.getInputStream() );
			oos = new ObjectOutputStream( skCliente.getOutputStream() );
			
			while(true) {
				
				dat= (Datos)ois.readObject();
				dat.setArr();

				System.out.println(getPrimLug());
								
				Ranking rank = new Ranking(dat);
				
				rank.setLista();
				
				dat.setPrimLug(getPrimLug());
				dat.setSegLug(getSegLug());
						
				ActualizarCliente(dat.getArr());
				namehost = dat.getArr().get(7);
				
				oos.writeObject(dat);
			}
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            skCliente.close();
            Thread.sleep(2000);
            DefaultTableModel modelo = (DefaultTableModel) ServidorFrame.table.getModel();
            for (int i = 0; i < ServidorFrame.table.getRowCount(); i++) {
                if (ServidorFrame.table.getValueAt(i, 0).equals(namehost)) {
                	modelo.removeRow(i);
                } 
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
	public void ActualizarCliente(ArrayList<String> datos) {
		boolean count = false;
		
        for (int i = 0; i < ServidorFrame.table.getRowCount(); i++) {
            if (ServidorFrame.table.getValueAt(i, 0).equals(datos.get(7))) {
            	ServidorFrame.table.setValueAt(datos.get(4), i, 2);
            	ServidorFrame.table.setValueAt(datos.get(8), i, 3);
            	ServidorFrame.table.setValueAt(datos.get(3), i, 4);
            	ServidorFrame.table.setValueAt(datos.get(1), i, 5);
            	ServidorFrame.table.setValueAt(Integer.parseInt(datos.get(11)), i, 6);
            	count = true;
            } 
        }
		
        if(count == false){
        	DefaultTableModel model = (DefaultTableModel) ServidorFrame.table.getModel();
            model.addRow(new Object[]{
            		datos.get(7), 
            		datos.get(12), 
            		datos.get(4), 
            		datos.get(8), 
            		datos.get(3), 
            		datos.get(1),
            		Integer.parseInt(datos.get(11))
            	}
            );
        }
        
        DefaultTableModel model = (DefaultTableModel) ServidorFrame.table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        ServidorFrame.table.setRowSorter(sorter);
        ServidorFrame.table.getRowSorter().toggleSortOrder(6);
        ServidorFrame.table.getRowSorter().toggleSortOrder(6);
        model.getValueAt(0, 0);
        
        try {
        	primLug=String.valueOf(ServidorFrame.table.getValueAt(0, 1));
            System.out.println("1er lugar" + primLug);
		} catch (Exception e) {
			System.out.println(e);
		}
        try {
        	segLug=String.valueOf(ServidorFrame.table.getValueAt(1, 1));
            System.out.println("2do lugar" + segLug);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public String getPrimLug()
	{
		return primLug;
	}
	
	public String getSegLug()
	{
		return segLug;
	}
}
 