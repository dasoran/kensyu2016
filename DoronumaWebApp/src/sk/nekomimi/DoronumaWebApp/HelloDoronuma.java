package sk.nekomimi.DoronumaWebApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class HelloDoronuma {

	public static void main(String[] args) {
		System.out.println("start >>>");
		
		PageDoronuma pageDoronuma = new PageDoronuma();
		
        try {
            ServerSocket server = new ServerSocket(10080);
            while (true) {
	            Socket socket = server.accept();
	            
	            HttpDoronumaResponse httpDoronumaResponse = new HttpDoronumaResponse();
	            pageDoronuma.doGet(new HttpDoronumaRequest(), httpDoronumaResponse);
	            
	            
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	            bw.write("HTTP/1.1 200 OK" + "\r\n");
	            bw.write("Content-Type: text/html" + "\r\n");
	            bw.write("\r\n");
	            bw.write(httpDoronumaResponse.getString());
	            bw.flush();
	            bw.close();
            }
            
        } catch (IOException e) {
        	System.out.println(e);
        }

        System.out.println("<<< end");
	}
}
