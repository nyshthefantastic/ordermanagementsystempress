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
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import static middle.docs.printDeliveryNote.PoNum;
import static middle.docs.printDeliveryNote.dateee;

/**
 *
 * @author U Computers
 */
public class printQuotation implements Printable, ActionListener {

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
    static File selectedfile = null;

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) {
            /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
 /* Now we perform our rendering */
        String newline = System.getProperty("line.separator");

        Graphics2D g2d = (Graphics2D) g;

        g2d.draw(new Rectangle2D.Double(55, 250, 500, 400));
        g2d.draw(new Rectangle2D.Double(55, 250, 500, 50));
        g2d.draw(new Rectangle2D.Double(55, 250, 300, 400));
        g2d.draw(new Rectangle2D.Double(355, 250, 100, 400));
        g2d.draw(new Rectangle2D.Double(455, 250, 100, 400));

        g2d.drawString("DESCRIPTION", 70, 280);
        g2d.drawString("QUANTITY", 370, 280);
        String eetemp = String.valueOf(Double.parseDouble(Rate) * Double.parseDouble(Quantity));
        g2d.drawString("RATE", 470, 280);
        g.drawString(cname, 55, 220);
        g.drawString(product, 55, 220);
        g.drawString(Paper, 55, 220);
        g.drawString(Color, 55, 220);
        g.drawString(refId, 55, 220);
        g.drawString(Quantity, 55, 220);
        g.drawString(Rate, 55, 220);
        g.drawString(eetemp, 55, 220);
        //   String str = FileUtils.readFileToString(selectedfile);

        //    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
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

    public static void setReceipt(String comname, String quantity, String refid, String pname, String paper, String color, String rate, File selx) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String datex = String.valueOf(localDate);
        refId = refid;
        product = pname;
        dateee = datex;
        Color = color;
        Paper = paper;
        Quantity = quantity;
        Rate = rate;
        cname = comname;
        selectedfile = selx;
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
