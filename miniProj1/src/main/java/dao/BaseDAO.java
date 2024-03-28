package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
	public static Connection conn = null;

	static {
		try {
			InitialContext ic= new InitialContext();
			 
			//2. lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/mini_proj1_db");
			 
			//3. getConnection()
			conn = ds.getConnection();
			
			System.out.println("MariaDB 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
