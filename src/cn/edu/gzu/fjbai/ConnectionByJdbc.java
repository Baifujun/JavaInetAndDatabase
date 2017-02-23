package cn.edu.gzu.fjbai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionByJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Connection conectByJdbc(String url, String username, String password){
	    Connection con = null;
	    try{
	        Class.forName( //加载特定的驱动程序
	        "com.microsoft.jdbc.sqlserver.SQLServerDriver");
	    }
	    catch (Exception e){
	        e.printStackTrace();
	        return null; //连接失败
	    }
	    try{
	        con = DriverManager.getConnection(url, username, password);
	    }
	    catch (SQLException e){
	        e.printStackTrace();
	        return null; //连接失败
	    }
	    return con; //连接成功
	}
}
