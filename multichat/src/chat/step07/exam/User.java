package chat.step07.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class User extends Thread{
	//ChatServerView에서 넘겨받을 데이터
	Socket client;
	ChatServerView serverView;
	InputStream is;
	InputStreamReader ir;
	BufferedReader br;
	 
	OutputStream os;
	PrintWriter pw;
	
	String nickname;
	
	Vector<User> userlist;
	public User() {
		
	}
	//서버가 접속한 클라이언트의 정보를 User객체로 만들때 접속한 User의 소켓객체와 서버뷰, userlist를 전달
	public User(Socket client, ChatServerView serverView, Vector<User> userlist) {
		super();
		this.client = client;
		this.serverView = serverView;
		this.userlist = userlist;
		ioWork();
	}
	
	//접속한 클라이언트와 서버가 통신할 수 있도록 스트림객체 생성
	public void ioWork() {//처음 접속했을 때 한 번 실행되는 메소드
		try {
			is = client.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);
			
			os = client.getOutputStream();
			pw = new PrintWriter(os, true);
			
			//클라이언트와 통신할 수 있는 스트림을 생성하고 클라이언트가 입장하면 클라이언트가 전송하는 
			//nickname을 읽어서 서버창에 출력하기
			nickname = br.readLine();
			System.out.println("nickname:"+nickname);
			serverView.taclientlist.append("********"+nickname+"님이 입장*********\n");
			
			//2.=============기존 for문을 메소드 호출문으로 변경=============
			broadCast("new/"+nickname);
			//=====================================================
			
			//3.=============새로운 접속자(this)에게 기존 클라이언트의 정보를 알려주기========
			//새로운 접속자(this)에게 기존 클라이언트의 정보를 알려주기
			int size = userlist.size();//기존 접속자 인원수
			for (int i = 0; i < size; i++) {
				User user = userlist.get(i);
				sendMsg("old/"+user.nickname);
			}
			//================================================================
			userlist.add(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//1. =========기존사용자에게 메시지를 보내는 메소드를 정의===============
	private void broadCast(String msg) {
		int size = userlist.size();//기존 접속자 인원수
		for (int i = 0; i < size; i++) {
			User user = userlist.get(i);
			user.sendMsg(msg);
		}
	}
	//=========================================================
	public void sendMsg(String message) {
		pw.println(message);
	}
	public void run() {
		while(true) {
			try {
				String msg = br.readLine();
				serverView.taclientlist.append(nickname+"이 전송한 메시지:"+msg+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
