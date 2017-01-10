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
public class addProductMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public addProductMiddle() {
        con = dbconnct.connect();

    }

    public void addProductTo(String cname,String bname , String refid, String refname,String cont) {
        try {
            String q = "INSERT INTO productregister(companyname,brandname,refId,refName,content) VALUES ('" + cname + "','" + bname + "','" + refid + "','" + refname + "','" + cont + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        mess = new message();
        mess.messageBox("REGISTRATION SUCCESSFUL");

    }
}
