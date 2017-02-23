package cn.edu.gzu.fjbai;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String args[]){
        ServerSocket server = null;
        Socket you = null;
        String s = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try{
            server = new ServerSocket(4441);
            System.out.println("服务器套接字链接已建立");
        }catch(IOException e1){
            System.out.println("ERROR:" +e1);
        }
        try{
            you = server.accept();
            in = new DataInputStream(you.getInputStream());
            out = new DataOutputStream(you. getOutputStream());
            out.writeUTF("服务器套接字链接已建立");
            while(true){
                s = in.readUTF();
                if(s.length() > 0) {
                	out.writeUTF(s);
                    System.out.println(s);
                    if(s.equals("结束")) {
                    	out.writeUTF("服务器线程退出了");
                    	System.out.println("服务器线程退出了");
                    	break;
                    }
                }    
            }
            out.close();
        }
        catch(IOException e){System.out.println("ERROR:"+e);}
    }

}
