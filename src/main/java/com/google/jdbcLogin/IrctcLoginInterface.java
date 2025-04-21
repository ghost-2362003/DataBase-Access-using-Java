package com.google.jdbcLogin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IrctcLoginInterface {
    private static final String login_query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME = ? AND UPWD = ?";

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

    }
}