import java.io.IOException;
import java.io.InputStream;

public class App {

	//Host destino
	private static final String HOST = "www.uol.com.br";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Process process = null;
		InputStream stream = null;
		byte[] content = null;
		float tempo = 0f;
		
		
		
		try {

			while(true) {

				//executa um comando do console (no caso o Ping)
				process = Runtime.getRuntime().exec("ping " + HOST);
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

				}else {
					System.out.println("Host não alcançado...");
				}

			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	}



}




