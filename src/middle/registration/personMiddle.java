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
public class personMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public personMiddle() {
        con = dbconnct.connect();

    }

    public void registerPerson(String cname, String bname, String pname, String contactNum, String Email) {
          mess = new message();
          boolean bool=getCompany(cname, bname, pname);
          if(bool==true){
        try {
            String q = "INSERT INTO personregister(companyname,brandname,personname,telephone,email) VALUES ('" + cname + "','" + bname + "','" + pname + "','" + contactNum + "','" + Email + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

      
        mess.messageBox("REGISTRATION SUCCESSFUL");
          }else{
          
                  mess.messageBox("THIS PERSON IS ALREADY REGISTERED");

          
          }
    }

    public boolean getCompany(String cname, String bname,String pname) {

        try {
            String load = "SELECT companyname,brandname,personname FROM personregister ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {

                String comname = rs.getString("companyname");
                String branname = rs.getString("brandname");
                String pername = rs.getString("personname");

                if ((cname.equalsIgnoreCase(comname)) && (bname.equalsIgnoreCase(branname))&& (pname.equalsIgnoreCase(pername))) {

                    return false;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return true;

    }

}
