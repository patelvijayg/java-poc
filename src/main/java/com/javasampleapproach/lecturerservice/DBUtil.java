package com.javasampleapproach.lecturerservice;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class DBUtil {

    static final String sql ="CREATE TABLE test1 (id int, name varchar(10))";

    static final String connURL = "jdbc:derby:memory:memdatabase;create=true";
    private Connection conn=null;
    private AtomicInteger empid=new AtomicInteger();
    {
        try{
            conn= DriverManager.getConnection(connURL);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            System.out.println("Memory database created: ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executeSql(String sql){
        boolean success=false;

        try{
            PreparedStatement ps = this.conn.prepareStatement(sql);
            success = ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
        }
    public int addEmployee(EmployeeDAO employee){
        boolean success=false;

        try{
            PreparedStatement ps = this.conn.prepareStatement("insert into test1(id,name) values(?,?)");
            employee.setId(empid.getAndIncrement());
            ps.setInt(1,employee.getId());
            ps.setString(2,employee.getName());
            success = ps.execute();
            return employee.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public EmployeeDAO getEmployee(String s){
        EmployeeDAO emp=null;

        try{

            PreparedStatement ps = this.conn.prepareStatement("select id,name from test1 where name =?");
            ps.setString(1,s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                emp=new EmployeeDAO(rs.getInt("id"),rs.getString("name"));
                return emp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

}
