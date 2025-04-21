package com.google.jdbcLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class IrctcLoginInterface {

    private static final String LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME = ? AND UPWD = ?";

    public static ResultSet RunQuery(PreparedStatement pst, Scanner sc) throws SQLException{
        if(pst != null && sc != null){
            System.out.println("enter username: ");
            pst.setString(1, sc.nextLine());
            System.out.println("enter password: ");
            pst.setString(2, sc.nextLine());
        }

        return pst.executeQuery();
    }

    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            PreparedStatement pst = con.prepareStatement(LOGIN_QUERY);
            ResultSet rs = RunQuery(pst, sc);
            ){

            if(rs != null){
                rs.next(); //move the pointer to first record from BFR
                //process the result set
                int count = rs.getInt(1);

                if(count == 0)
                    System.out.println("Invalid Credentials");
                else
                    System.out.println("Valid Credentials");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}