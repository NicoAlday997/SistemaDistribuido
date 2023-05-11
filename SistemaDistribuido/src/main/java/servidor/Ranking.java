package servidor;

import java.io.Serializable;
import java.util.TreeSet;

//SERVIDOR

public class Ranking implements Serializable
{

   private static final long serialVersionUID = 1L;
   private Datos dat;
   private static TreeSet<Datos> list=new TreeSet<Datos>();
   
   public Ranking(Datos dat)
   {
	   this.dat=dat;
   }
   
   public void setLista()
   {
	   list.add(dat);
   }
	
	public TreeSet<Datos> getRanking()
	{
		return list;	
	}

	public Datos getDato()
	{
		return dat;
	}

}
