package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class testDao {
	@Autowired
	private ComboPooledDataSource ds;

	public String getStr() {
		Connection conn = null;

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into book values (1,'a',10)";
		PreparedStatement pstmt = null, pstmt1 = null;
		try {
			pstmt = conn.prepareStatement(sql);
			String sql1 = "insert into book values (2,'b',10)";
			pstmt1 = conn.prepareStatement(sql1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 1 / 0;
		System.out.println("aaaaaaaaa");
		try {
			pstmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(conn);
		return "";
	}
}
