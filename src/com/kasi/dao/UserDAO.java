package com.kasi.dao;

import com.kasi.entity.User;
import com.kasi.dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.kasi.entity.UserDetails;
public class UserDAO {

	public boolean checkUser(User u)
	{
		boolean result = false;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
//			con = DBConnect.getConnection();
//			pst = con.prepareStatement("select username,password from users where username=? and password=?");
//			//pst = con.prepareStatement("select username, password from users where username = ? and password = ?");
//			
//			pst.setString(1, u.getUserName());
//			pst.setString(2, u.getPassword());
//			System.out.println("User Name :" +u.getUserName());
//			System.out.println("User Name :" +u.getPassword());
//			rs = pst.executeQuery();
//			
//			if(rs.next())
//				result=true;
			
			con =DBConnect.getConnection();
			pst = con.prepareStatement("select * from users where username = ? and password = ?");
			pst.setString(1, u.getUserName());
			pst.setString(2, u.getPassword().toString());
			
			rs = pst.executeQuery();
			if(rs.next())
				result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnect.closeResultSet(rs);
			DBConnect.closeStatement(pst);
			DBConnect.closeConnection(con);
		}
		return result;
	}
	public boolean createUser(UserDetails ud)
	{

		Connection con =null;
		PreparedStatement pst = null;
		int count = 0;
		try {
			con = DBConnect.getConnection();
			pst = con.prepareStatement("insert into users values(?,?,?,?,?,?)");
			pst.setString(1, ud.getUser().getUserName());
			pst.setString(2, ud.getUser().getPassword());
			pst.setString(3, ud.getFirstName());
			pst.setString(4, ud.getLastName());
			pst.setString(5, ud.getAddress());
			pst.setLong(6,ud.getPhone());
			
			count = pst.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBConnect.closeStatement(pst);
			DBConnect.closeConnection(con);
		}
		
		return (count==1);
	}
}
