package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static String user="myshop",pwd="tiger";
	
	
	static {
		//static 블럭은 클래스 로딩 타입에 가장 먼저 메모리에 올라가는 영역
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//static initializer
	
	public static Connection getCon() throws SQLException
	{
		Connection con = DriverManager.getConnection(url,user,pwd);
		return con;
	}
}
