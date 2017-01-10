/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.registration;

import common.dbconnct;
import common.message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author U Computers
 */
public class companyMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public companyMiddle() {
        con = dbconnct.connect();

    }

    public void registerCompany(String cname, String address, String acNum, String contactNum, String Email) {
              try {
                String q = "INSERT INTO companyregister(name,address,accountNo,telephone,email) VALUES ('" + cname + "','" + address + "','" + acNum + "','" + contactNum + "','" + Email + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("REGISTRATION SUCCESSFUL");
        
        
        
    }
}
