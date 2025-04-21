package com.wipro.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteTest {

    private static final String DELETE_BY_ID = "DELETE FROM STUDENT WHERE SNO=?";
    private static final String DELETE_BY_NAME = "DELETE FROM STUDENT WHERE SNAME=?";
    private static final String DELETE_BY_ADDR = "DELETE FROM STUDENT WHERE SADDR=?";
    private static final String DELETE_BY_ALL = "DELETE FROM STUDENT";

    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            PreparedStatement pstId = con.prepareStatement(DELETE_BY_ID);
            PreparedStatement pstName = con.prepareStatement(DELETE_BY_NAME);
            PreparedStatement pstAddr = con.prepareStatement(DELETE_BY_ADDR);
            PreparedStatement pstAll = con.prepareStatement(DELETE_BY_ALL)
        ){
            while(true){
                System.out.println("<|====================STUDENT DELETE MENU====================|>");
                System.out.println("1. Delete by ID");
                System.out.println("2. Delete by NAME");
                System.out.println("3. Delete by ADDRESS");
                System.out.println("4. Delete all records");
                System.out.println("5. Exit program");

                System.out.print("enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();      //This is done to consume the "enter" character when the choice is entered

                PreparedStatement pst = null;       //A reference variable

                switch(choice) {
                    case 1:     //Delete by ID
                        System.out.print("enter the ID of student to be deleted: ");
                        int stdId = sc.nextInt();
                        pst = pstId;
                        pst.setInt(1, stdId);
                        break;

                    case 2:     //Delete by NAME
                        System.out.print("enter the NAME of student to be deleted: ");
                        String stdName = sc.nextLine();
                        pst = pstName;
                        pst.setString(1, stdName);
                        break;

                    case 3:
                        System.out.print("enter the ADDRESS of student to be deleted: ");
                        String stdAddr = sc.nextLine();
                        pst = pstAddr;
                        pst.setString(1, stdAddr);
                        break;

                    case 4:
                        pst = pstAll;
                        break;

                    case 5:
                        System.out.println("Thank you for using our program");
                        System.exit(0);

                    default:
                        System.out.println("invalid choice");
                        continue;
                }

                int count = pst.executeUpdate();
                if(count == 0)
                    System.out.println("no matching record for deletion");
                else
                    System.out.println(count + " record(s) deleted");
            }
        } catch (SQLException se) {
            if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
                System.out.println("invalid table name or SQL keyword");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}