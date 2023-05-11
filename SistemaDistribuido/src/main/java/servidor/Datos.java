package servidor;

import java.util.ArrayList;
import java.util.List;
import cliente.InfoSistema;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

//SERVIDOR
public class Datos extends Thread implements SerComp
{
	private ArrayList<String> arr;
	private static final long serialVersionUID = 4L;
	private InfoSistema systemInfo;
	private String espTot,espLib, ramTot, ramDis, nomProc, micrProc, oS,hostName, segLug, primLug;
	private String IP=null;
	private double freqProc;
	private int procFis, procLog, points;
	
	public Datos(InfoSistema systemInfo)
	{
		this.systemInfo=systemInfo;
		arr=new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return "Datos [espTot=" + espTot + ", espLib=" + espLib + ", ramTot=" + ramTot + ", ramDis=" + ramDis
				+ ", nomProc=" + nomProc + ", micrProc=" + micrProc + ", oS=" + oS + ", hostName=" + hostName
				+ ", freqProc=" + freqProc + ", procFis=" + procFis + ", procLog=" + procLog + ", points=" + points
				+ "]";
	}

	public void setDatos()
	{
		OperatingSystem operatingSystem=systemInfo.getOperatingSystem();
		
		FileSystem fileSystem = operatingSystem.getFileSystem();
		List<OSFileStore> osFileStores = fileSystem.getFileStores();
                
		HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory globalMemory = hardware.getMemory();
                        
        CentralProcessor processor = hardware.getProcessor();
        CentralProcessor.ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
		
		//Espacio total y libre de disco duro
		for(OSFileStore fileStore : osFileStores)
        {
		   espTot=FormatUtil.formatBytes(fileStore.getTotalSpace());
		   espLib=FormatUtil.formatBytes(fileStore.getFreeSpace());
           
        }
		
		//Memoria Ram en disponible y total
		
		ramTot=FormatUtil.formatBytes(globalMemory.getTotal());
		ramDis=FormatUtil.formatBytes(globalMemory.getAvailable());
		
		//Nombre procesador, microarquitectura y frecuencia
		nomProc=processorIdentifier.getName();
		micrProc=processorIdentifier.getMicroarchitecture(); 
		freqProc=processorIdentifier.getVendorFreq() / 1000000000.0;
		
		//Nombre sistema operativo
		oS=operatingSystem.toString();
		
		//Procesadores fisicos y logicos
		procFis=processor.getPhysicalProcessorCount();
		procLog=processor.getLogicalProcessorCount();
		
		//Hostname
		
		hostName=operatingSystem.getNetworkParams().getHostName();
		
	}
	
	public void setSegLug(String segLug)
	{
		this.segLug=segLug;
	}
	
	public String getSegLug()
	{
		return segLug;
	}
	
	public void setPrimLug(String primLug)
	{
		this.primLug=primLug;
	}
	
	public String getPrimLug()
	{
		return primLug;
	}
	
	
	
	
	 
	public void setPoints() {
		//this.points+=points;
			 //Puntos para frecuencia
			 for(int i=1; i<=4; i++)
			 {
				 if(freqProc>i-1 && freqProc<i) points+=i;
			 }
			 
			 //Puntos para nucleos fisicos
			 
			 for(int i=1; i<=16; i++)
			 {
				 if(procFis==i)points+=(i*2);

			 }
			 
			 //Puntos para nucleos logicos (hilos)
			 for(int i=1; i<=32; i++)
			 {
				 if(procLog==i) points+=i;
			 }
			 
			 
			 //Puntos por ram	 
			 float aux = Float.parseFloat(ramDis.split(" ")[0]);
			 if (aux >= 1000) {
				 aux = aux * 2;
			 }else {
				 aux = 0;
			 }
			 points += (int) aux;
			 
			//Puntos por espacio
			 aux = Float.parseFloat(espLib.split(" ")[0]) / 100;
			 points += (int) aux;
			 
	}
	  
	public void setIP(String ip2)
	{
		this.IP=ip2;
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}


	public String getEspTot() {
		return espTot;
	}


	public String getEspLib() {
		return espLib;
	}


	public String getRamTot() {
		return ramTot;
	}


	public String getRamDis() {
		return ramDis;
	}


	public String getNomProc() {
		return nomProc;
	}


	public String getMicrProc() {
		return micrProc;
	}


	public String getoS() {
		return oS;
	}


	public String getHostName() {
		return hostName;
	}


	public double getFreqProc() {
		return freqProc;
	}


	public int getProcFis() {
		return procFis;
	}


	public int getProcLog() {
		return procLog;
	}


	public double getPoints() {
		return points;
	}
	
	public String getIP()
	{
		return String.valueOf(IP);
	}


	public int compareTo(Datos datos) {
		
		return points - datos.points;
	}
	
	public void setArr()
	{
		arr.add(espTot); // 0
		arr.add(espLib); // 1
		arr.add(ramTot); // 2
		arr.add(ramDis); // 3
		arr.add(nomProc); // 4
		arr.add(micrProc); // 5
		arr.add(oS); // 6
		arr.add(hostName); //7
		arr.add(String.valueOf(freqProc)); //8
		arr.add(String.valueOf(procFis)); //9
		arr.add(String.valueOf(procLog)); //10
		arr.add(String.valueOf(points)); //11
		arr.add(String.valueOf(IP));//12
	
	}
	
	public void setHost(String host1, String host2) {
		arr.set(13, host1);
		arr.set(14, host2);
	}
	
	public ArrayList<String> getArr()
	{
		return arr;
	}

}
