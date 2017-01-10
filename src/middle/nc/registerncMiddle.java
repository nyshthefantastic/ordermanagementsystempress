/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.nc;

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
public class registerncMiddle {
       Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;

    public registerncMiddle() {
        con = dbconnct.connect();

    }
    public void registernc(String Name,String num){
    
      try {
                String q = "INSERT INTO ncregister(name,mobile) VALUES ('" + Name + "','" + num + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }

            mess = new message();
            mess.messageBox("REGISTRATION SUCCESSFUL");
        
    
    
    }
}
