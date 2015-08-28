package supermarket;


import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BHUSHAN
 */
public class Inventory
  {
    private ArrayList<Item> list;
    
    Inventory(ArrayList<Item> l)
      {
        list=l;
      }
    
    public static void additem(String name1,String id1,double qtyinitial1,double unitprice1,double profitperunit1)
      {
        try
          {
            String sql="select * from inventory";
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql); 
            int i=0;
            while(rs.next())
              {
                i=rs.getInt("slno");
              } i++;
              sql = "Insert into inventory values("+i+", '"+name1+"', '"+id1+"', "+qtyinitial1+", "+unitprice1+", "+profitperunit1+")";
              st = global.con.createStatement();
              st.executeUpdate(sql);
          } catch (SQLException ex)
          {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    public static void removeitem(String name)
      {
        try
          {
            String sql="select * from inventory";
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            String name1,id2="";
            int sl=0,slwant=0;
            while(rs.next())
              {
                sl=rs.getInt("slno");
                name1=rs.getString("itemname");
                if(name1.equalsIgnoreCase(name)) {id2=name1;slwant=sl; }
              } 
            sql ="delete from inventory where itemname=?";
            PreparedStatement pstmt = global.con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            for(int i=slwant;i<sl;i++)
              {
                String sql2 = "UPDATE inventory " + "  SET slno = ? " + "WHERE slno = ?";
                pstmt = global.con.prepareStatement(sql2);
                pstmt.setInt(1, i);
                pstmt.setInt(2, i+1);
                pstmt.executeUpdate();
              }
          } catch (SQLException ex)
          {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    public static Item getInfoItem(String str){
        try        
            {
                String sql="select * from inventory";
                String itemname,id;
                double q,p,unitpr;
                int sl;
                Statement st=global.con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                while(rs.next()){
                    itemname=rs.getString("itemname");
                    if(itemname.equals(str)){
                        sl = rs.getInt("slno");
                        q=rs.getDouble("qtyavailable");
                        p=rs.getDouble("profitperunit");
                        id=rs.getString("id");
                        unitpr=rs.getDouble("unitprice");
                         return new Item(sl,q,unitpr,p,itemname,id);
                    }
                } 
              }catch (SQLException ex)
            {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
    public static void addsupply(String id1,double qty,double purchaseprice)
      {
        try
          {
            String sql="select * from inventory",id2,id3="";
            double qty2=0,unitpr=0,profitinitial=0;
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
              {
                id2=rs.getString("id");
                qty2=rs.getDouble("qtyavailable");
                unitpr=rs.getDouble("unitprice");
                profitinitial=rs.getDouble("profitperunit");
                if(id2.equalsIgnoreCase(id1)) {id3=id2;break;}
              }
            if(!id3.equals("")){
                double profitperunit1=(qty2*profitinitial+qty*(unitpr-purchaseprice))/(qty+qty2);
                double q=qty+qty2;
                String sql2 = "UPDATE inventory " + "  SET qtyavailable = ? " + "WHERE id = ?";
                PreparedStatement pstmt = global.con.prepareStatement(sql2);
                pstmt.setDouble(1, q );
                pstmt.setString(2, id1);
                pstmt.executeUpdate();
                sql2 = "UPDATE inventory " + "  SET profitperunit = ? " + "WHERE id = ?";
                pstmt = global.con.prepareStatement(sql2);
                pstmt.setDouble(1, profitperunit1 );
                pstmt.setString(2, id1);
                pstmt.executeUpdate();
            }
            else {
                
            }
          } catch (SQLException ex)
          {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    public static void modifyinventory(String id1,double qty)
      {
        try
          {
            String sql="select * from inventory",id2,id3="";
            double qty2=0,unitpr=0,profitinitial=0;
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
              {
                id2=rs.getString("id");
                qty2=rs.getDouble("qtyavailable");
                if(id2.equalsIgnoreCase(id1)) {id3=id2;break;}
              }
            if(!id3.equals(""))
              {
                qty2-=qty;
                String sql2 = "UPDATE inventory " + "  SET qtyavailable = ? " + "WHERE id = ?";
                PreparedStatement pstmt = global.con.prepareStatement(sql2);
                pstmt.setDouble(1, qty2 );
                pstmt.setString(2, id1);
                pstmt.executeUpdate();
              }
            else{
                
            }
          } catch (SQLException ex)
          {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    
  }
