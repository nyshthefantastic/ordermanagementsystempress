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
public class printDeliveryNote implements Printable, ActionListener {

 static String cname = null;
    static String bname = null;
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
        String newline = System.getProperty("line.separator");
        g.drawString(dateee, 120, 100);
        g.drawString(PoNum, 120, 100);
        g.drawString(Quantity, 120, 100);
        g.drawString(product + " ( " + refId + " )", 120, 100);

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

    public static void setReceipt(String ponum, String comname, String quantity, String refid, String date, String pname) {
        refId = refid;
        product = pname;
        dateee = date;
        PoNum = ponum;
        Quantity = quantity;

        cname = comname;

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("RECEIPT PRINTER");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        JButton printButton = new JButton("PRINT RECEIPT ");
        printButton.addActionListener(new printQuotation());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
    }
}