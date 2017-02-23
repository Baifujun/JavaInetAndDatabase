package cn.edu.gzu.fjbai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionByJdbcOdbc {

	static Connection con = null;
	public static void main(String[] args) {
		if ((con = connectByjdbcOdbc("jdbc:odbc:redsun", "xia", "1234")) != null){
		    JOptionPane.showMessageDialog(null, "数据库连接成功");
		    try{
		        con.close();
		        con = null;
		    }
		    catch (SQLException e){}
		}
		else
		    JOptionPane.showMessageDialog(null, "数据库连接失败");
	}

	public static Connection connectByjdbcOdbc(String url, String username, String password){
	    Connection con = null;
	    try{
	        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");   //加载ODBC驱动程序
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
	        return null; //连接不成功
	    }
	    return con; //连接成功
	}
}
