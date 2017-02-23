package cn.edu.gzu.fjbai;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class ClientAndServerTest extends Applet implements Runnable, ActionListener{

	JButton button;
	JTextField textf;
	JTextArea texta;
	ServerSocket server = null;
	Socket socket,client;
	Thread thread;
	DataInputStream in1,in;
	DataOutputStream out1,out;

	public void init()
	{
	this.setBackground(new Color(120,153,137));
	this.setLayout(new BorderLayout());
	button = new JButton("发送信息");
	textf = new JTextField(20);
	texta = new JTextArea(20,30);
	setSize(450,350);
	JPanel p = new JPanel();
	p.add(textf);
	p.add(button);
	add(texta, "Center");
	add(p, "South");
	button.addActionListener(this);
	}

	public void start()
	{
	try
	{
	server = new ServerSocket(4441);
	client = new Socket(this.getCodeBase().getHost(), 4441);
	in1 = new DataInputStream(client.getInputStream());
	out1 = new DataOutputStream(client.getOutputStream());
	}
	catch (IOException e)
	{
	System.out.println("error "+e);
	}

	if (thread == null)
	{
	thread = new Thread(this);
	thread.setPriority(Thread.MIN_PRIORITY);
	thread.start();
	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource() == button)
	{
	try
	{
	String s = textf.getText();

	if ((s != null) && (!s.equals(""))){
	out1.writeUTF(s);
	}
	else
	{
	out1.writeUTF("请说话");
	}
	}catch(IOException x)
	{
	System.out.println(x);
	}

	}
	}

	@Override
	public void run() {
	// TODO Auto-generated method stub
	try
	{
	socket = server.accept();
	in = new DataInputStream(socket.getInputStream());
	out = new DataOutputStream(socket.getOutputStream());
	}
	catch (IOException e)
	{
	System.out.println("error "+e);
	}

	String s = null;
	try{
	while(true){
	s = in.readUTF();
	if (s != null)
	{
	texta.append(s + "\n");
	if (s.equals("结束"))
	{
	texta.append("服务器线程退出了\n");
	break;
	}
	}
	}
	socket.close();
	}
	catch(IOException e)
	{
	System.out.println("error2 "+e);
	}
	}

}
