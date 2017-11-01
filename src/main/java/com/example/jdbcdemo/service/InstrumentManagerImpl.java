package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Instrument;

public class InstrumentManagerImpl implements InstrumentManager{
	
	private Connection conn;
	
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTable = "CREATE TABLE instrument(id bigint GENERATED BY DEFAULT AS IDENTITY, brand varchar(20), name varchar(20), price decimal(8,2))";

	private Statement statement;
	
	public InstrumentManagerImpl(){
		connect();
	}
	
	private void connect(){
		try {
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("instrument".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTable);

//			addPersonStmt = connection
//					.prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)");
//			deleteAllPersonsStmt = connection
//					.prepareStatement("DELETE FROM Person");
//			getAllPersonsStmt = connection
//					.prepareStatement("SELECT id, name, yob FROM Person");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void clearInstruments() {
		try {
			PreparedStatement stmt = conn.prepareStatement("delete from instrument");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addInstrument(Instrument inst) {
		int count = 0;
		try{		
			String query = "insert into instrument (brand, name, price) values (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, inst.getBrand());
            stmt.setString(2, inst.getName());
            stmt.setDouble(3, inst.getPrice());
            
            count = stmt.executeUpdate();
            
            
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
}

	@Override
	public boolean deleteInstrument(long id) {
		String query = "delete from instrument where id = " + (int)id;
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Instrument getInstrument(String name) {
		Instrument inst = new Instrument();

		try{
			String query = "select * from instrument where name = '" + name + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				inst.setId(rs.getLong("id"));
				inst.setBrand(rs.getString("brand"));
				inst.setName(rs.getString("name"));
				inst.setPrice(rs.getDouble("price"));
			}

		} catch(SQLException e){
			e.printStackTrace();
		}
		return inst;
	}

	@Override
	public List<Instrument> getAllInstrument() {
		List<Instrument> instruments = new ArrayList<>();
		try{		
			String query = "select * from instrument;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
            	long id = rs.getLong("id");
            	String brand = rs.getString("brand");
            	String name = rs.getString("name");
            	double price = rs.getDouble("price");
            	
            	instruments.add(new Instrument(id, brand, name, price));
            }
            
		} catch(SQLException e){
			e.printStackTrace();
		}
		return instruments;
	}

	@Override
	public boolean updateInstrument(Instrument inst) {
		try{
			String query = "update instrument set brand = ?, name = ?, price = ? where id = " + inst.getId();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, inst.getBrand());
			stmt.setString(2, inst.getName());
			stmt.setDouble(3, inst.getPrice());
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	Connection getConnection() {
		return conn;
	}
	
	
}
