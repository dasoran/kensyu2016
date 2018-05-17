package sk.nekomimi.DoronumaWebApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class HelloDoronuma {

	public static void main(String[] args) {
		System.out.println("start >>>");
		
        try {
            ServerSocket server = new ServerSocket(10080);
            Socket socket = server.accept();
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("HTTP/1.1 200 OK" + "\r\n");
            bw.write("Content-Type: text/html" + "\r\n");
            bw.write("\r\n");
            bw.write("Hello world");
            bw.flush();
            bw.close();
            server.close();
            
        } catch (IOException e) {
        	System.out.println(e);
        }

        System.out.println("<<< end");
	}
}
