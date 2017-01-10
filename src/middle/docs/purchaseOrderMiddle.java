/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.docs;

import common.dbconnct;
import common.message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class purchaseOrderMiddle {
    
      Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;
    Date date=null;
    public purchaseOrderMiddle() {
        con = dbconnct.connect();

    }
    
    public void markPurchase(String oid,String ponum){
        date=new Date();
     LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     String datee = String.valueOf(localDate);
       try {
                String q = "UPDATE companyorders SET purchaseOrderDate='" + datee + "',purchaseOrderNo='" + ponum +  "'WHERE id= '" + oid + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        mess = new message();
        mess.messageBox("PUCHASE ORDER MARKED AS RECEIVED");

     
    }
    
}
