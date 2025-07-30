package hk.edu20250730.day20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class D1_UDPClient {
	
	public static void main(String[] args) {
		String hostname = "localhost";
		int port = 5000;
		
		try(DatagramSocket socket = new DatagramSocket()) {
			InetAddress address = InetAddress.getByName(hostname);
			
			byte[] buffer = new byte[512];
			// 키보드로 입력된 데이터를 읽어오기 위한 스트림 생성
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String text = "";
			while (true) {
				System.out.println("Enter text : ");
				text = reader.readLine();
				buffer = text.getBytes();
				
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
				socket.send(packet);
				
				// 서버에서 전송된 데이터를 수신할 패킷 생성
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				// 받은 패킷에서 데이터 가져와서 문자열로 변환
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Received : " + received);
			}
			
		} catch (Exception e) {
			
		}
	}

}
