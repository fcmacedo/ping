import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.LogUtil;

public class App {

	//Host destino
	private static String HOST[] = new String[10];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Process process = null;
		InputStream stream = null;
		byte[] content = null;
		float tempo = 0f;
		int i=0;
		
		HOST[0] = "www.uol.com.br";
		HOST[1] = "www.facebook.com.br";
		HOST[2] = "www.terra.com.br";
		HOST[3] = "nubank.com.br";
		
	    LogUtil log = new LogUtil();
		
		try {
			while(true) {

				//executa um comando do console (no caso o Ping)
				process = Runtime.getRuntime().exec("ping " + HOST[i++]);
				process.waitFor();

				//Recupera o resultado do ping no console (criam um stream de dados)
				stream = process.getInputStream();
				content = new byte[stream.available()];
				stream.read(content);

				//Converte para String e já quebra o resultado para isolar o tempo do ping
				String part[] = (new String(content)).split("tempo=");

				//Se houve resultado mostra SOMENTE o tempo (esse deve se gravado no banco ou arquivo TXT)
				if(part.length > 1) {

					//extrai o tempo sem o "ms TTL=128" (por exemplo). Converte para float
					tempo = Float.parseFloat(part[1].substring(0, part[1].indexOf("ms")));
					
										System.out.println(tempo);
										
										LocalDateTime timePoint = LocalDateTime.now(); 
										
										log.saveLog(HOST[i], timePoint, tempo);
										

				}else {
					System.out.println("Host não alcançado...");
				}

			    //Thread.sleep(1000);
				if(i==4) i = 0;
			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}



}




