package com.example.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.emaillist.vo.EmailVo;


public class EmaillistDaoImpl implements EmaillistDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("Oracle.jdbc.driver.OracleDriver");
			// CONNECTION 가져오기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin@localhost:1521:xe", // DBURL
					"C##BITUSER", // DB user
					"1234"); // DB Pass
			} catch (ClassNotFoundException e) {
				System.err.println("드라이버 로드 실패!");
				e.printStackTrace();
			}
		return conn;
	}
	
	@Override
	public List<EmailVo> getList() {
		List<EmailVo> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn= getConnection();
			stmt = conn.createStatement();
			
			String sql = "SLECT no, last_name, first_name, email, createAt"
				+ "From emaillist ORDER BY no DESC";
				
				// 쿼리 수행
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					Long no = rs.getLong(1);
					String lastName = rs.getString(2);
					String firstName = rs.getString(3);
					String email = rs.getString(4);
					Date createdAt = rs.getDate(5);
					
					// VO 객체 생성
					EmailVo vo = new EmailVo();
					vo.setNo(no);
					vo.setLastName(lastName);
					vo.setFirstName(firstName);
					vo.setEmail(email);
					vo.setCreateAt(createdAt);
					
					// 리스트에 추가
					list.add(vo);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return list;
	}

	@Override
	public int insert(EmailVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
