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
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class paymentncMiddle {
   Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = null;
    Date date = null;
    double adv;

    public paymentncMiddle() {
        con = dbconnct.connect();

    }
    public void markPayment(String value,String amt,String advance){
    
       String paidamt = checkIfPaid(value);
        System.err.println(paidamt);
        String tot=getBillAmt(value);
         System.err.println(tot);
        if(advance==null){
            adv=0.0;
        }
        else{
            adv=Double.parseDouble(advance);
        }
        if (paidamt != null) {
            if (Double.parseDouble(tot) > (Double.parseDouble(amt)+adv+Double.parseDouble(paidamt))) {
                String q = "UPDATE ncorders SET paidAmt=paidAmt+'" + amt + "'WHERE oid= '" + value + "'";
                System.err.println("first");
                pay(value, amt, q);

            } else if (Double.parseDouble(tot) == (Double.parseDouble(amt)+adv+Double.parseDouble(paidamt))) {
                String q = "UPDATE companyorders SET paidAmt=paidAmt+'" + amt + "',paymentStatus='" + "paid" + "'WHERE oid= '" + value + "'";
                  System.err.println("second");
                pay(value, amt, q);

            } else {

                mess = new message();
                mess.messageBox("PAYMENT IS GREATER THAN THE TOTAL TO PAY.PAYMENT NOT ACCEPTED");

            }

        }
        System.err.println("x");
    }
    private String getBillAmt(String val){
    
    
     try {
            String load = "SELECT total FROM ncorders WHERE oid='" + val + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
               
                String paid = rs.getString("total");
               return paid;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    
    
    
    
    }
    private void pay(String value, String amt, String q) {
        try {

            pst = con.prepareStatement(q);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        mess = new message();
        mess.messageBox("PAYMENT RECEIVED");

    }

    private String checkIfPaid(String value) {

        try {
            String load = "SELECT paidAmt,paymentStatus FROM ncorders WHERE oid='" + value + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
                String sta = rs.getString("paymentStatus");
                String paidam = rs.getString("paidAmt");
                if (sta.equals("paid")) {
                    return null;

                } else {

                    return paidam;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }
}
