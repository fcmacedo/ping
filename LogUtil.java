package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogUtil {

	public  void saveLog(String host, LocalDateTime data, float tempo) {

		try(FileWriter fw = new FileWriter("PingGalileo.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{

			//formata a data antes de gravar
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			out.println(host + "," + data.format(formatter) + "," + tempo);

		} catch (IOException e) {
			// exceção
		}		
	}


}
