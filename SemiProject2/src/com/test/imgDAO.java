package com.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class imgDAO  {
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	
	Properties pro;
	int i = 1;
	
	public imgDAO() {
		pro = new Properties();
		try {
			pro.load(new FileReader("SemiProject2/conn/conn.properties"));
			Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean imgAdd(byte[] img) {
		connect();
		try {
			String sql = "insert into img_test values (?,?)";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, i);
				stmt.setBytes(2, img);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();			
		}
		return false;
	}
	
	public ImageIcon imgLead() {
		connect();
		try {
			String sql = "select img_blob from img_test where no = ?";
			stmt = conn.prepareStatement(sql);
				stmt.setInt(1, i);
				i+=1;
			rs = stmt.executeQuery();
			if(rs.next()) {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				InputStream in = rs.getBinaryStream("img_blob");
				byte[] buf = new byte[1024];
				int read = 0;
				while((read=in.read(buf,0,buf.length))!=-1) {
					bout.write(buf, 0, read);
				}
//				BufferedImage bimg = ImageIO.read(in);
				
				ImageIcon icon = new ImageIcon(bout.toByteArray());
				in.close();
				return icon;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();			
		}
		return null;
	}
	
	
	private void connect() {
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"),pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void disconnect() {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
