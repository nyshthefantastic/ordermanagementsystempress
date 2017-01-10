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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class orderncMiddle {
    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = new message();
    Date date = null;

    public orderncMiddle() {
        con = dbconnct.connect();

    }
    
    public void placeOrder(String item, String pname, String Color, String Paper, String Quantity, String Rate, String Total, String duedate, String Advance) {
        date = new Date();
        
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();

        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        String datee = String.valueOf(localDate);
        try {
            String q = "INSERT INTO ncorders(itemName,personName,color,paper,quantity,rate,total,advance,orderDate,orderMonth,orderYear,paymentStatus,paidAmt) VALUES ('" + item + "','" + pname + "','" + Color + "','" + Paper + "','" + Quantity + "','" + Rate + "','" + Total + "','" + Advance + "','" + datee + "','" + monthh + "','" + yearr + "','" + "null" + "','" + "0" + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        mess.messageBox("ORDER SUCCESSFUL");

    }
}
