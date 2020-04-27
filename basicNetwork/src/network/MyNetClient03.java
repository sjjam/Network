package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyNetClient03 {

	public static void main(String[] args) {
		Socket socket;
		InputStream is = null;
		DataInputStream dis = null;
		OutputStream os = null;
		DataOutputStream dos = null;
		
		try {
			socket = new Socket("70.12.115.71", 12345);
			System.out.println("서버접속완료..."+socket);
			
			//InputStream 얻기
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			
			//OutputStream 얻기
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			
			//1. 클라이언트 <- 서버
			String msg = dis.readUTF();
			System.out.println(msg);//서버가 보내는 환영메시지 출력
			
			//2. 클라이언트 <- 서버
			int gugu = dis.readInt();
			for (int i = 1; i < 10; i++) {
				System.out.println(gugu+"*"+i+" = "+(gugu*i));
			}
			
			//3. 클라이언트 -> 서버
			String oe = "";
			if(gugu%2==0) {
				oe = "짝수";
			}else {
				oe = "홀수";
			}
			
			dos.writeUTF(oe);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
