package hk.edu20250730.day20;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class D1_UDPServer {

	public static void main(String[] args) {
		try(DatagramSocket socket = new DatagramSocket(5000)) {
			byte[] buffer = new byte[512];
			
			// 수신용 패킷 생성
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			System.out.println("Server is listening on port 5000");
			while (true) {
				socket.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("받은 메시지 : " + received);
				
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				System.out.println("address : " + address + ", port : " + port);
				packet = new DatagramPacket(buffer, buffer.length, address, port);
				socket.send(packet);	// 서버에서 클라이언트로 데이터를 보낸다.
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
