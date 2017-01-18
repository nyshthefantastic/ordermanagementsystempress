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
public class brandMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public brandMiddle() {
        con = dbconnct.connect();

    }

    public void registerBrand(String cname, String bname, String acNum, String contactNum, String Email) {
         mess = new message();
        boolean bool=getCompany(cname, bname);
        if(bool==true){
        try {
            String q = "INSERT INTO brandregister(companyname,brandname,accountNo,telephone,email) VALUES ('" + cname + "','" + bname + "','" + acNum + "','" + contactNum + "','" + Email + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

       
        mess.messageBox("REGISTRATION SUCCESSFUL");
        }else{
        
                mess.messageBox("THIS BRAND IS ALREADY REGISTERED");

        
        }
    }

    public boolean getCompany(String cname, String bname) {

        try {
            String load = "SELECT companyname,brandname FROM brandregister ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {

                String comname = rs.getString("companyname");
                String branname = rs.getString("brandname");

                if ((cname.equalsIgnoreCase(comname))&&(bname.equalsIgnoreCase(branname))) {

                    return false;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return true;

    }
}
