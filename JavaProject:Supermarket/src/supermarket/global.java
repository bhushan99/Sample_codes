package supermarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vatsalya
 */
public class global {
    public static Connection con;
    
    public global(String ip){

        try {
            try {
                Class.forName("java.sql.DriverManager");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(global.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/sas", "assign", "password");
        } catch (SQLException ex) {
            Logger.getLogger(global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
