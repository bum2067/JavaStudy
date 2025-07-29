package hk.edu20250729.day19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class D2_TCPServer {

	public static void main(String[] args) {
		Socket clientSocket = null;		// 클라이언트 소켓
		PrintWriter out = null;		// 클라이언트로 출력할 때 사용할 객체
		
		BufferedReader in = null;		//서버로 읽어들일때 사용할 객체
		
		// 서버 소켓 생성
		try {
			ServerSocket serverSocket = new ServerSocket(9595);
			System.out.println("Server is running");
			
			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println("클라이언트 연결됨 : " + clientSocket.getInetAddress().getHostName());
				out = new PrintWriter(clientSocket.getOutputStream(), true);	// true -> autoflush 설정
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				String inputLine;
				while ((inputLine = in.readLine())!= null) {
					System.out.println("클라이언트로부터 전달받은 메세지 : " + inputLine);
					out.println("니가 보낸 메시지 : " + inputLine);
				}
			}
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}

}
