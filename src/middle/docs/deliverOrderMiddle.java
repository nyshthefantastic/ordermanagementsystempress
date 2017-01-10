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
public class deliverOrderMiddle {
    
     
      Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;
    Date date=null;
    printDeliveryNote pdn;
    printInvoice pinv;
     String x;
    String []das=new String[6];
     String []prin=new String[6];
    public deliverOrderMiddle() {
        con = dbconnct.connect();

    }
     public void markDelivered(String oid){
         das=getOrderDetails(oid);
         String product=getProduct(das[3]);
         date=new Date();
         pdn=new printDeliveryNote();
     LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     String datee = String.valueOf(localDate);
       try {
                String q = "UPDATE companyorders SET deliveredDate='" + datee + "'WHERE id= '" + oid + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        mess = new message();
        mess.messageBox("ORDER DELIVERED");
        pdn.setReceipt(das[0],das[1],das[2],das[3],datee,product);
        pinv.setReceipt(das[0],das[1],das[2],das[3],datee,product,das[4]);
    }
     public String getProduct(String ref){
    
       try {
            String load = "SELECT refName FROM productregister where refId='" + ref + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
               x=rs.getString("refName");
               
            }
                return x;
        } catch (Exception e) {
            System.out.println(e);
        }

     return x;
     
     
     
     
     }
     private String[] getOrderDetails(String oid){
       try {
            String load = "SELECT * FROM companyorders where id='" + oid + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();

            while (rs.next()) {
               prin[0]=rs.getString("purchaseOrderNo");
               prin[1]=rs.getString("companyName");
               prin[2]=rs.getString("quantity");
               prin[3]=rs.getString("refId");
                prin[4]=rs.getString("rate");
               
            }
                return prin;
        } catch (Exception e) {
            System.out.println(e);
        }

     return prin;
     
     
     
     
     }
}
