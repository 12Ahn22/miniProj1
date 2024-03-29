package com.miniproj1.baseDAO;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * 모든 DAO가 공유해서 사용할 Connection
 */
public class BaseDAO {
	public static Connection conn = null;
	static {
		try {
			// Connection Pool을 사용한다.
			InitialContext ic= new InitialContext();
			//2. lookup()
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/mini_proj1_db");
			//3. getConnection()
			conn = ds.getConnection();
			conn.setAutoCommit(false); // auto Commit false 
			System.out.println("MariaDB 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
