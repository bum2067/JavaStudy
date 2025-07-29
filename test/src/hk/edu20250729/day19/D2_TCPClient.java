package hk.edu20250729.day19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class D2_TCPClient {

	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;		// 서버에서 전달된 메시지를 읽어들일 객체
		BufferedReader userIn = null;	// 사용자가 키보드로 입력하는 메세지르 읽어들일 객체
		
		try {
//			socket = new Socket("192.168.22.26", 9595);
			socket = new Socket("localhost", 9595);		// 접속하려는 ip주소, 포트
			System.out.println("Client : Connection to server...");
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userIn = new BufferedReader(new InputStreamReader(System.in));
			
			String inputLine;
			while ((inputLine = userIn.readLine()) != null) {
				out.println(inputLine);	// 소켓으로 출력
				System.out.println("서버에서 전달된 메세지 : " + in.readLine());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (userIn != null) {
					userIn.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}

	}

}
