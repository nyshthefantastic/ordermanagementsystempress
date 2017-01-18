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
        mess = new message();
        boolean bool = getCompany(cname);
        if (bool == true) {
            try {
                String q = "INSERT INTO companyregister(name,address,accountNo,telephone,email) VALUES ('" + cname + "','" + address + "','" + acNum + "','" + contactNum + "','" + Email + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess.messageBox("REGISTRATION SUCCESSFUL");
        } else {

            mess.messageBox("THE COMPANY IS ALREADY REGISTERED");

        }

    }

    public boolean getCompany(String cname) {

        try {
            String load = "SELECT name FROM companyregister ";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {

                String namee = rs.getString("name");
                if (cname.equalsIgnoreCase(namee)) {

                    return false;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return true;

    }
}
