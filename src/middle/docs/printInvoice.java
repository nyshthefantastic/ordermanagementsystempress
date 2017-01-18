/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.docs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author U Computers
 */
public class printInvoice implements Printable, ActionListener {

    static String cname = null;
    static String Id = null;
    static String refId = null;
    static String product = null;
    static String Color = null;
    static String Paper = null;
    static String Quantity = null;
    static String Rate = null;
    static String Total = null;

    static String dateee = null;
    static String PoNum = null;

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) {
            /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
        Total = String.valueOf(Double.parseDouble(Rate) * Double.parseDouble(Quantity));
        String newline = System.getProperty("line.separator");
          g.drawString(Id, 117, 83);
        g.drawString(dateee, 92, 111);
        g.drawString(PoNum, 172, 130);
         g.drawString(cname, 140, 171);
        g.drawString(Quantity, 90, 239);
        g.drawString(product + " ( " + refId + " )", 140, 240);
        g.drawString(Rate, 400, 239);
        g.drawString(Total, 430, 540);

        //   g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
 
   public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }

    }

    public static void setReceipt(String oid,String ponum, String comname, String quantity, String refid, String date, String pname, String rate) {
        refId = refid;
        product = pname;
        dateee = date;
        PoNum = ponum;
        Quantity = quantity;
        Rate = rate;
        cname = comname;
       Id=oid;
      
   
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("INVOICE PRINTER");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        JButton printButton = new JButton("INVOICE PRINTER");
        printButton.addActionListener(new printInvoice());
        f.add("Center", printButton);
        f.pack();
         f.setLocation(100, 200);
        f.setVisible(true);

    }
}
