package supermarket;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BHUSHAN
 */
public class Item
  {
    private int slno;
    private double qtyavailable;
    private double unitprice;
    private double profitperunit;
    private String itemname;
    private String id;
    
    Item(int s,double q,double u,double p,String i,String id1)
      {
        slno=s;
        qtyavailable=q;
        unitprice=u;
        profitperunit=p;
        itemname=i;
        id=id1;
      }
    
    public double getprice() {return unitprice;}
    public String getname() {return itemname;}
    
    public double getquantity(){return qtyavailable;}
                
    public String getID(){return id;}
    
    public double getprofitperunit(){return profitperunit;}
    
    public static Item getDetails(String id1)
      {
        try        
          {
            String sql="select * from inventory",id2,id3="",name="";
            double unitpr=0;
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
              {
                id2=rs.getString("id");
                name=rs.getString("itemname");
                unitpr=rs.getDouble("unitprice");
                if(id2.equalsIgnoreCase(id1)) {id3=id2;break;}
              }
            if(!id3.equals("")){
                return new Item(0,0,unitpr,0,name,id1);
            }
            else{
                return null;
            }
          } catch (SQLException ex)
          {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
        
      } 
    
    public static void changeprice(String id,double newprice)
    {
        try
          {
            String sql="select * from inventory";
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql); 
            String tempid;
            double d=0.0,ip=0.0,diff=0.0;
            while(rs.next())
              {
                tempid=rs.getString("id");
                d=rs.getDouble("profitperunit");
                ip=rs.getDouble("unitprice");
                diff=newprice-ip;
                d+=diff;
                if(tempid.equalsIgnoreCase(id)) {break;}
              } 
            
              String sql2 = "UPDATE inventory " + "  SET unitprice = ? " + "WHERE id = ?";
              PreparedStatement pstmt = global.con.prepareStatement(sql2);
              pstmt.setDouble(1, newprice);
              pstmt.setString(2, id);
              pstmt.executeUpdate();
              sql2 = "UPDATE inventory " + "  SET profitperunit = ? " + "WHERE id = ?";
              pstmt = global.con.prepareStatement(sql2);
              pstmt.setDouble(1, d);
              pstmt.setString(2, id);
              pstmt.executeUpdate();
              JOptionPane.showMessageDialog(null, "New price is set.");
          } catch (Exception ex)
          {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    
    
  }
