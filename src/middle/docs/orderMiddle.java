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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author U Computers
 */
public class orderMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    message mess = new message();
    Date date = null;

    public orderMiddle() {
        con = dbconnct.connect();

    }

    public void placeOrder(String refid, String pname, String Color, String Paper, String Quantity, String Rate, String Total, String duedate, String Advance) {
        date = new Date();
        String cname = getCompanyName(pname);
        String bname = getBrandName(pname);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthh = localDate.getMonthValue();

        int yearr = Calendar.getInstance().get(Calendar.YEAR);
        String datee = String.valueOf(localDate);
        try {
            String q = "INSERT INTO companyorders(companyName,brandName,refId,personName,color,paper,quantity,rate,total,dueDate,advance,orderDate,orderMonth,orderYear,purchaseOrderDate,purchaseOrderNo,deliveredDate,paymentStatus,paidAmt) VALUES ('" + cname + "','" + bname + "','" + refid + "','" + pname + "','" + Color + "','" + Paper + "','" + Quantity + "','" + Rate + "','" + Total + "','" + duedate + "','" + Advance + "','" + datee + "','" + monthh + "','" + yearr + "','" + "null" + "','" + "null" + "','" + "null" + "','" + "null" + "','" + "0" + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        mess.messageBox("ORDER SUCCESSFUL");

    }

    public String getCompanyName(String pname) {
        try {
            String load = "SELECT companyname FROM personregister WHERE personname='" + pname + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
                String sta = rs.getString("companyname");

                return sta;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public String getBrandName(String pname) {
        try {
             String load = "SELECT brandname FROM personregister WHERE personname='" + pname + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            if (rs.next()) {
                String sta = rs.getString("brandname");

                return sta;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}
