/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vatsalya
 */
public class Transaction {
    private String date;
    private int SlNo;
    private ArrayList<Item> items;
    private ArrayList<Double> quantities;
    private ArrayList<Double> unitPrices;
    private ArrayList<Double> profits;

}
