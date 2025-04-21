package com.wipro.jdbc;

import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class JdbcApp {

    private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES (?, ?, ?)";
    public static void main(String[] args){

        try(Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            PreparedStatement pst = con.prepareStatement(STUDENT_INSERT_QUERY)
        ){

            int count = 0;
            if(sc != null) {
                System.out.println("enter student count: ");
                count = sc.nextInt();
            }

            if(pst != null && con != null)
                for(int i = 1; i<=count; i++){
                    System.out.println("student #" + i+1 + " details...");
                    System.out.println("student number: ");
                    int sno = sc.nextInt();

                    System.out.println("student name: ");
                    String sname = sc.next();

                    System.out.println("student address: ");
                    String saddr = sc.next();

                    pst.setInt(1, sno); pst.setString(2, sname);
                     pst.setString(3, saddr);

                    int result = pst.executeUpdate();

                    if(result == 0)
                        System.out.println(i + " student details not inserted");
                    else
                        System.out.println(i + " student details inserted");
                }
        } catch (SQLException se) {
            if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
                System.out.println("invalid sql name for sql keyword");
            if(se.getErrorCode() == 1438)
                System.out.println("value larger than specified precision allowed for this table");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}