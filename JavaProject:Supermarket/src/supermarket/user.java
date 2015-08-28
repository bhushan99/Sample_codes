/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author vatsalya
 */
public class user extends javax.swing.JFrame {
    boolean flag1=false,flag2=false,flag3=false,flag4=false,flag5=false,flag6=false;
//    int one=0,two=0, three=0;
    int sel=0,loggedperson = 0;
    int setnamesonButton = 0;
    double receiptTotal = 0;
    int rowcount;
    int number=1;

    /**
     * Creates new form user
     */
    public user() {
        String ip = JOptionPane.showInputDialog("Please Enter The IP for DATABASE access.");
        if(ip != null){
            new global(ip);
            initComponents();
           // jTextField4.setVisible(false);
            currentdate();
             f();
             clearbuttons();
             setnames();
             DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
             m.setRowCount(0);
             rowcount=0;
        }
    }

    private void addinfield(String i){
//        if(i.equals(".")&& flag==false) flag=true;
//        else if(i.equals(".")&& flag==true) {return;}//show msg
        String temp;
        switch(sel){
            case 1: {
                if(i.equals(".")&& flag1==false) flag1=true;
                else if(i.equals(".")&& flag1==true) {return;}
                temp=jTextField8.getText();
                temp+=i;
                jTextField8.setText(temp);
                break;
            }
            case 2: {
                if(i.equals(".")&& flag2==false) flag2=true;
                else if(i.equals(".")&& flag2==true) {return;}
                temp=jTextField7.getText();
                temp+=i;
                jTextField7.setText(temp);
                break;
            }
            case 3: {
                if(i.equals(".")&& flag3==false) flag3=true;
                else if(i.equals(".")&& flag3==true) {return;}
                temp=jTextField4.getText();
                temp+=i;
                jTextField4.setText(temp);
                break;
            }
            case 4: {
                if(i.equals(".")&& flag4==false) flag4=true;
                else if(i.equals(".")&& flag4==true) {return;}
                temp=jTextField13.getText();
                temp+=i;
                jTextField13.setText(temp);
                break;
            }
            case 5: {
                if(i.equals(".")&& flag5==false) flag5=true;
                else if(i.equals(".")&& flag5==true) {return;}
                temp=jTextField15.getText();
                temp+=i;
                jTextField15.setText(temp);
                break;
            }
            case 6: {
                if(i.equals(".")&& flag6==false) flag6=true;
                else if(i.equals(".")&& flag6==true) {return;}
                temp=jTextField16.getText();
                temp+=i;
                jTextField16.setText(temp);
                break;
            }
        }
    }
    
    public void flagreset(){
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=false;
        flag5=false;
        flag6=false;
    }
    
    public void currentdate (){
	Thread cl = new Thread(){
            
            public void run(){
                
                for (;;){
                    
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    jLabel5.setText(day+"/"+(month+1)+"/"+year);
                    jLabel14.setText("DATE :  "+day+"/"+(month+1)+"/"+year);
                    jLabel22.setText(day+"/"+(month+1)+"/"+year);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    
                    jLabel4.setText(hour+":"+(minute)+":"+second);
                    jLabel21.setText(hour+":"+(minute)+":"+second);

                    try {
                        Thread.sleep (1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        cl.start();
    }

    public void f(){
        loginpanel.setVisible(true);
        jTextField5.setEnabled(false);
        jTextField7.setEnabled(false);
        jButton15.setEnabled(false);
        clerkpanel.setVisible(false);
        managerpanel.setVisible(false);  
        jTextField4.setEnabled(false);
        jButton17.setEnabled(false);
        jTextField3.setEditable(false);
        jTextField2.setEditable(false);
    }
    
    public void clearbuttons(){
        Buttonitem1.setText("");
        Buttonitem2.setText("");
        Buttonitem3.setText("");
        Buttonitem4.setText("");
        Buttonitem5.setText("");
        Buttonitem6.setText("");
        Buttonitem7.setText("");
        Buttonitem8.setText("");
        Buttonitem9.setText("");
        Buttonitem10.setText("");
        Buttonitem11.setText("");
        Buttonitem12.setText("");
        Buttonitem13.setText("");
        Buttonitem14.setText("");
        Buttonitem15.setText("");
    }
    
    public void setnames (){
        try        
          {
            String sql="select * from inventory";
            String itemname;
            int slno,count = 1;
            Statement st=global.con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                slno = rs.getInt("slno");
                itemname=rs.getString("itemname");
                if (slno > 15*setnamesonButton && slno <= 15*(setnamesonButton + 1)){
                    switch(count){
                        case 1: Buttonitem1.setText(itemname); break;
                        case 2: Buttonitem2.setText(itemname); break;
                        case 3: Buttonitem3.setText(itemname); break;
                        case 4: Buttonitem4.setText(itemname); break;
                        case 5: Buttonitem5.setText(itemname); break;
                        case 6: Buttonitem6.setText(itemname); break;
                        case 7: Buttonitem7.setText(itemname); break;
                        case 8: Buttonitem8.setText(itemname); break;
                        case 9: Buttonitem9.setText(itemname); break;
                        case 10: Buttonitem10.setText(itemname); break;
                        case 11: Buttonitem11.setText(itemname); break;
                        case 12: Buttonitem12.setText(itemname); break;
                        case 13: Buttonitem13.setText(itemname); break;
                        case 14: Buttonitem14.setText(itemname); break;
                        case 15: Buttonitem15.setText(itemname); break;
                    }
                    count++;
                }    
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getitemDetail(String str){
        if(!str.equals("")){
            Item temp = Inventory.getInfoItem(str);
            jTextField12.setText(temp.getID());
            jTextField13.setText(""+temp.getquantity());
            jTextField14.setText(temp.getname());
            jTextField15.setText(""+temp.getprice());
            jTextField16.setText(""+temp.getprofitperunit());
        }
        else{
            eraseareas();
        }
    }
    
    public void erasecreas(){
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        flagreset();
    }
    
    public void eraseareas(){
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        flagreset();
    }
    
    public void insertintoTransaction(String date,String id,double qty,double unitpr,double profit){
        try {
            System.out.println("transaction called");
            // String ip = "10.102.45.128";
//            Class.forName("java.sql.DriverManager");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sas", "assign", "password"); 
            String sql2 = "select * from transaction";
            Statement st1 = global.con.createStatement();
            ResultSet rs = st1.executeQuery(sql2);
            int a=0;
            while(rs.next())
            {
                a = rs.getInt("slno");
            }
            if(rowcount==0) a++;       
            String sql = "Insert into transaction values("+a+", '"+date+"', '"+id+"', "+qty+", "+unitpr+", "+profit+")";
            jLabel13.setText("Receipt no.-  "+a);
            Statement st = global.con.createStatement();
            st.executeUpdate(sql);   
        } catch (SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginpanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton20 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        clerkpanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        managerpanel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Buttonitem1 = new javax.swing.JButton();
        Buttonitem2 = new javax.swing.JButton();
        Buttonitem3 = new javax.swing.JButton();
        Buttonitem4 = new javax.swing.JButton();
        Buttonitem5 = new javax.swing.JButton();
        Buttonitem6 = new javax.swing.JButton();
        Buttonitem7 = new javax.swing.JButton();
        Buttonitem8 = new javax.swing.JButton();
        Buttonitem9 = new javax.swing.JButton();
        Buttonitem10 = new javax.swing.JButton();
        Buttonitem11 = new javax.swing.JButton();
        Buttonitem12 = new javax.swing.JButton();
        Buttonitem13 = new javax.swing.JButton();
        Buttonitem14 = new javax.swing.JButton();
        Buttonitem15 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jButton39 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SUPERMARKET 2");
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        loginpanel.setBackground(new java.awt.Color(0, 0, 0));
        loginpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new Color(240,240,240,100));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USER ID :");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel7.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PASSWORD :");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel8.setOpaque(true);

        jTextField6.setText("Enter ID");
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jPasswordField1.setText("********");
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(0, 0, 0));
        jButton20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(0, 255, 51));
        jButton20.setText("LOGIN !!");
        jButton20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPasswordField1))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        loginpanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 400, 260));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/supermarket/p3.png"))); // NOI18N
        loginpanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 940, 690));

        getContentPane().add(loginpanel, "card2");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new java.awt.GridLayout(4, 3));

        jButton7.setBackground(new java.awt.Color(153, 153, 255));
        jButton7.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7);

        jButton8.setBackground(new java.awt.Color(51, 255, 153));
        jButton8.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8);

        jButton9.setBackground(new java.awt.Color(153, 153, 255));
        jButton9.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9);

        jButton4.setBackground(new java.awt.Color(51, 255, 153));
        jButton4.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jButton5.setBackground(new java.awt.Color(153, 153, 255));
        jButton5.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);

        jButton6.setBackground(new java.awt.Color(51, 255, 153));
        jButton6.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 255, 153));
        jButton2.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton3.setBackground(new java.awt.Color(153, 153, 255));
        jButton3.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton10.setBackground(new java.awt.Color(51, 255, 153));
        jButton10.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton10.setText(".");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10);

        jButton11.setBackground(new java.awt.Color(153, 153, 255));
        jButton11.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton11.setText("0");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton11);

        jButton12.setBackground(new java.awt.Color(51, 255, 153));
        jButton12.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton12.setText("00");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton12);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setLayout(new java.awt.GridLayout(4, 1));

        jButton14.setBackground(new java.awt.Color(0, 153, 153));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 102, 255));
        jButton14.setText("Clear");
        jButton14.setMaximumSize(new java.awt.Dimension(75, 75));
        jButton14.setMinimumSize(new java.awt.Dimension(75, 75));
        jButton14.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton14);

        jButton13.setBackground(new java.awt.Color(0, 153, 153));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 102, 255));
        jButton13.setText("Quantity");
        jButton13.setMaximumSize(new java.awt.Dimension(75, 70));
        jButton13.setMinimumSize(new java.awt.Dimension(75, 70));
        jButton13.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton13);

        jButton15.setBackground(new java.awt.Color(0, 153, 153));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 102, 255));
        jButton15.setText("Unit price");
        jButton15.setMaximumSize(new java.awt.Dimension(75, 705));
        jButton15.setMinimumSize(new java.awt.Dimension(75, 70));
        jButton15.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton15);

        jButton17.setBackground(new java.awt.Color(0, 153, 153));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 102, 255));
        jButton17.setText("P.Rate");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton17);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Total :");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);

        jTextField2.setText("0.0");
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sl No.", "Name", "Unit Price", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel13.setFont(new java.awt.Font("SketchFlow Print", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 153));
        jLabel13.setText("Receipt no. : ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 0, 153));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jButton16.setBackground(new java.awt.Color(0, 153, 153));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setText("PRINT RECEIPT ");
        jButton16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton16);

        jButton48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton48.setText("Change Password");
        jButton48.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton48);

        jButton19.setBackground(new java.awt.Color(255, 0, 0));
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setText("LOGOUT !!");
        jButton19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton19);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jToggleButton1.setBackground(new java.awt.Color(102, 0, 102));
        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Sell/Add Supply");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(204, 255, 0));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("SELL");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton18.setBackground(new java.awt.Color(0, 153, 153));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton18.setText("DONE");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(5, 2));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Item ID :");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2);

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(jTextField9);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Quantity :");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel3);

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField8);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Name :");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel10);

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(jTextField5);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Unit Price :");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel11);

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7MouseClicked(evt);
            }
        });
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField7);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Purchase Rate :");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel12);

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField4);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("jLabel4");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Snap ITC", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout clerkpanelLayout = new javax.swing.GroupLayout(clerkpanel);
        clerkpanel.setLayout(clerkpanelLayout);
        clerkpanelLayout.setHorizontalGroup(
            clerkpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clerkpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(clerkpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clerkpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        clerkpanelLayout.setVerticalGroup(
            clerkpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(clerkpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clerkpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clerkpanelLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(clerkpanelLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(clerkpanel, "card3");

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setLayout(new java.awt.GridLayout(4, 3));

        jButton21.setBackground(new java.awt.Color(153, 153, 255));
        jButton21.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton21.setText("7");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton21);

        jButton22.setBackground(new java.awt.Color(51, 255, 153));
        jButton22.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton22.setText("8");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton22);

        jButton23.setBackground(new java.awt.Color(153, 153, 255));
        jButton23.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton23.setText("9");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton23);

        jButton24.setBackground(new java.awt.Color(51, 255, 153));
        jButton24.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton24.setText("4");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton24);

        jButton25.setBackground(new java.awt.Color(153, 153, 255));
        jButton25.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton25.setText("5");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton25);

        jButton26.setBackground(new java.awt.Color(51, 255, 153));
        jButton26.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton26.setText("6");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton26);

        jButton27.setBackground(new java.awt.Color(153, 153, 255));
        jButton27.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton27.setText("1");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton27);

        jButton28.setBackground(new java.awt.Color(0, 255, 153));
        jButton28.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton28.setText("2");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton28);

        jButton29.setBackground(new java.awt.Color(153, 153, 255));
        jButton29.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton29.setText("3");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton29);

        jButton30.setBackground(new java.awt.Color(51, 255, 153));
        jButton30.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton30.setText(".");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton30);

        jButton31.setBackground(new java.awt.Color(153, 153, 255));
        jButton31.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton31.setText("0");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton31);

        jButton32.setBackground(new java.awt.Color(51, 255, 153));
        jButton32.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jButton32.setText("00");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton32);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel15.setLayout(new java.awt.GridLayout(4, 1, 0, 2));

        jButton33.setBackground(new java.awt.Color(0, 153, 153));
        jButton33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 102, 255));
        jButton33.setText("Clear");
        jButton33.setMaximumSize(new java.awt.Dimension(75, 75));
        jButton33.setMinimumSize(new java.awt.Dimension(75, 75));
        jButton33.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton33);

        jButton34.setBackground(new java.awt.Color(0, 153, 153));
        jButton34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton34.setForeground(new java.awt.Color(0, 102, 255));
        jButton34.setText("Quantity");
        jButton34.setMaximumSize(new java.awt.Dimension(75, 70));
        jButton34.setMinimumSize(new java.awt.Dimension(75, 70));
        jButton34.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton34);

        jButton35.setBackground(new java.awt.Color(0, 153, 153));
        jButton35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton35.setForeground(new java.awt.Color(0, 102, 255));
        jButton35.setText("Unit price");
        jButton35.setMaximumSize(new java.awt.Dimension(75, 705));
        jButton35.setMinimumSize(new java.awt.Dimension(75, 70));
        jButton35.setPreferredSize(new java.awt.Dimension(75, 70));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton35);

        jButton36.setBackground(new java.awt.Color(0, 153, 153));
        jButton36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton36.setForeground(new java.awt.Color(0, 102, 255));
        jButton36.setText("P/Unit");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton36);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(5, 4, 5, 5));

        Buttonitem1.setText("jButton42");
        Buttonitem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem1ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem1);

        Buttonitem2.setText("jButton44");
        Buttonitem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem2ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem2);

        Buttonitem3.setText("jButton43");
        Buttonitem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem3ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem3);

        Buttonitem4.setText("jButton47");
        Buttonitem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem4ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem4);

        Buttonitem5.setText("jButton45");
        Buttonitem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem5ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem5);

        Buttonitem6.setText("jButton52");
        Buttonitem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem6ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem6);

        Buttonitem7.setText("jButton46");
        Buttonitem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem7ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem7);

        Buttonitem8.setText("jButton48");
        Buttonitem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem8ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem8);

        Buttonitem9.setText("jButton49");
        Buttonitem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem9ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem9);

        Buttonitem10.setText("jButton50");
        Buttonitem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem10ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem10);

        Buttonitem11.setText("jButton51");
        Buttonitem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem11ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem11);

        Buttonitem12.setText("jButton53");
        Buttonitem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem12ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem12);

        Buttonitem13.setText("jButton54");
        Buttonitem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem13ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem13);

        Buttonitem14.setText("jButton55");
        Buttonitem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem14ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem14);

        Buttonitem15.setText("jButton56");
        Buttonitem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buttonitem15ActionPerformed(evt);
            }
        });
        jPanel3.add(Buttonitem15);

        jPanel16.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 350, 286));

        jButton40.setBorderPainted(false);
        jButton40.setContentAreaFilled(false);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 170, 40));

        jButton44.setBorderPainted(false);
        jButton44.setContentAreaFilled(false);
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 160, 40));

        jLabel9.setBackground(new java.awt.Color(51, 255, 51));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/supermarket/a2.png"))); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel9.setOpaque(true);
        jPanel16.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 305, -1, 35));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        jButton41.setBackground(new java.awt.Color(0, 102, 204));
        jButton41.setText("Change Pr.");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton41);

        jButton42.setBackground(new java.awt.Color(0, 102, 204));
        jButton42.setText("Sale Stat.");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton42);

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 13, -1, 327));

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton38.setBackground(new java.awt.Color(255, 0, 0));
        jButton38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton38.setText("LOGOUT !!");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 160, 40));

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton39.setBackground(new java.awt.Color(153, 0, 153));
        jButton39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton39.setText("ADD to INV");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(5, 2));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Item ID :");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel15);

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.add(jTextField12);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Quantity :");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel16);

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField13MouseClicked(evt);
            }
        });
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField13);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Name :");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel17);

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.add(jTextField14);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Unit Price :");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel18);

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField15MouseClicked(evt);
            }
        });
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField15);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Profit / Unit :");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel19);

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextField16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField16MouseClicked(evt);
            }
        });
        jTextField16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField16FocusGained(evt);
            }
        });
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField16);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setBackground(new java.awt.Color(51, 51, 51));
        jLabel21.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("jLabel4");
        jLabel21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(51, 51, 51));
        jLabel22.setFont(new java.awt.Font("Snap ITC", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("jLabel5");
        jLabel22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel22.setOpaque(true);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new java.awt.GridLayout(1, 3, 2, 0));

        jButton45.setBackground(new java.awt.Color(0, 153, 153));
        jButton45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton45.setForeground(new java.awt.Color(0, 102, 204));
        jButton45.setText("Change Password");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton45);

        jButton46.setBackground(new java.awt.Color(0, 153, 153));
        jButton46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton46.setForeground(new java.awt.Color(0, 102, 204));
        jButton46.setText("ADD Employee");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton46);

        jButton47.setBackground(new java.awt.Color(0, 153, 153));
        jButton47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton47.setForeground(new java.awt.Color(0, 102, 204));
        jButton47.setText("See Inventory");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton47);

        javax.swing.GroupLayout managerpanelLayout = new javax.swing.GroupLayout(managerpanel);
        managerpanel.setLayout(managerpanelLayout);
        managerpanelLayout.setHorizontalGroup(
            managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        managerpanelLayout.setVerticalGroup(
            managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(managerpanelLayout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(managerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managerpanelLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(managerpanel, "card3");

        setSize(new java.awt.Dimension(962, 756));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String name = jTextField6.getText();
        String password = jPasswordField1.getText();
        number = 1;
        String str = null;

        if(name.isEmpty() || password.isEmpty())
            JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns first.");
        else{
            if(name.equals("manager")){
                if(Manager.login(name, password)) {
                    loggedperson = 0;
                    loginpanel.setVisible(false);
                    clerkpanel.setVisible(false);
                    managerpanel.setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns correctly.");
            }
            else{
                int slno = Salesclerk.login(name, password);
                if(slno!=0){
                    loggedperson = slno;
                    loginpanel.setVisible(false);
                    managerpanel.setVisible(false);
                    clerkpanel.setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns correctly.");
            }
            jTextField6.setText("Enter ID");
            jPasswordField1.setText("********");
            sel = 0;
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        String id = jTextField9.getText();
        String quantitystr = jTextField8.getText();
        try{
            if(jTextField3.getText().equals("SELL")){

                String tempid;
    
                if(id.isEmpty() || quantitystr.isEmpty())
                    JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns first.");
                else{             
                    Item it=Item.getDetails(id);
                    String name1=it.getname();
                    it=Inventory.getInfoItem(name1);
                    double up=it.getprice();
                    double qt=it.getquantity();
                    if(qt<Float.parseFloat(quantitystr)) {
                        JOptionPane.showMessageDialog(null, "Quantity mentioned not Available");
                        return;
                    }
                    Inventory.modifyinventory(id,Float.parseFloat(quantitystr));

                    DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
                    //m.setRowCount(0);
                    Object [] obj = new Object[4];
                    obj[0] = number;
                    obj[1] = name1;
                    obj[2] = up;
                    obj[3] = quantitystr;
                    m.addRow(obj);
    //                one=two=three=0;
                    receiptTotal += Float.parseFloat(quantitystr)*up;
                    jTextField2.setText(""+receiptTotal);


                    DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String datestr = dateFormatter.format(date);

                    double profit = Float.parseFloat(quantitystr) * it.getprofitperunit();

                    insertintoTransaction(datestr,id,Float.parseFloat(quantitystr),up,profit);
                    rowcount++;
                    number++;
                    erasecreas();
                }
            }
            else{

                String tempid,prate=jTextField4.getText();
                if(id.isEmpty() || quantitystr.isEmpty() || prate.isEmpty() )
                    JOptionPane.showMessageDialog(null, "Please fill all the coloumns first.");
                else{
                    if(Item.getDetails(id)!=null){
                        Inventory.addsupply(id,Float.parseFloat(quantitystr),Float.parseFloat(prate));
                        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
                        Item it=Item.getDetails(id);
                        String name1=it.getname();
                        double up=it.getprice();
                        //m.setRowCount(0);
                        Object [] obj = new Object[4];
                        obj[0] = number;
                        obj[1] = name1;
                        obj[2] = up;
                        obj[3] = quantitystr;
                        m.addRow(obj);


    //                    one=two=three=0;
                        number++;
                        erasecreas();

                    }
                    else{
                        jTextField5.setEnabled(true);jTextField7.setEnabled(true);

                        jTextField5.setEnabled(true);
                        jTextField7.setEnabled(true);
                        jButton15.setEnabled(true);
                        String name1=jTextField5.getText();
                        String upstr=jTextField7.getText();
                        if(!name1.equals("") && !upstr.equals("")){
                            Inventory.additem(name1, id,Float.parseFloat(quantitystr) , Float.parseFloat(upstr),Float.parseFloat(upstr)-Float.parseFloat(prate));

                            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
                            //m.setRowCount(0);
                            Object [] obj = new Object[4];
                            obj[0] = number;
                            obj[1] = name1;
                            obj[2] = upstr;
                            obj[3] = quantitystr;
                            m.addRow(obj);
    //                       one=two=three=0;


                            number++;  
                            erasecreas();
                            jTextField5.setEnabled(false);
                            jTextField7.setEnabled(false);
                            jButton15.setEnabled(false);
                        }
                        else JOptionPane.showMessageDialog(null, "Please Enter NAME and UNIT SELL PRICE of new item.");
                    }
                }

            }     
        }
        catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Input format is incorrect.");
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        addinfield("7");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        addinfield("3");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        addinfield("00");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        sel=1;
        flag1=false;
        jTextField8.setText("");
        //jTextField8.setF
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jTextField7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseClicked
        sel=2;
        flag2=false;
        jTextField7.setText("");
    }//GEN-LAST:event_jTextField7MouseClicked

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        sel=3;
        flag3=false;
        jTextField4.setText("");
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        addinfield("8");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        addinfield("9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        addinfield("4");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        addinfield("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addinfield("6");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addinfield("1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        addinfield("2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        addinfield("0");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        addinfield(".");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        jTextField6.setText("");
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        sel=3;
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        addinfield("7");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        addinfield("8");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        addinfield("9");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        addinfield("4");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        addinfield("5");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        addinfield("6");
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        addinfield("1");
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        addinfield("2");
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        addinfield("3");
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
       addinfield(".");
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        addinfield("0");
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        addinfield("00");
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        sel = 6;
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        
        String id = jTextField12.getText();
        String quantitystr =  jTextField13.getText();
        String ppu=jTextField16.getText();
        try{
            if(id.equals("")||quantitystr.equals("")||ppu.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all the coloumns first.");return;}
                float qty=Float.parseFloat(quantitystr);
                float pro=Float.parseFloat(ppu);
                if(Item.getDetails(id)!=null) {
                    Inventory.addsupply(id, qty, Item.getDetails(id).getprice()-pro);
                    eraseareas();
                } 
                else {
                    String name=jTextField14.getText();
                    String upstr=jTextField15.getText();
                    if(name.equals("")||upstr.equals("")) JOptionPane.showMessageDialog(null, "Please Enter NAME and UNIT SELL PRICE of new item.");
                    else {
                        Inventory.additem(name, id, qty,Float.parseFloat(upstr), pro);
                        eraseareas();
                    }
                }
            }
            catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Input format is incorrect.");
            }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jTextField13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseClicked
        sel=4;
        flag4=false;
        jTextField13.setText("");
    }//GEN-LAST:event_jTextField13MouseClicked

    private void jTextField15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField15MouseClicked
        sel=5;
        flag5=false;
        jTextField15.setText("");
    }//GEN-LAST:event_jTextField15MouseClicked

    private void jTextField16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField16MouseClicked
        sel=6;
        flag6=false;
        jTextField16.setText("");
    }//GEN-LAST:event_jTextField16MouseClicked

    private void jTextField16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16FocusGained

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        sel = 0;
        clerkpanel.setVisible(false);
        managerpanel.setVisible(false); 
        loginpanel.setVisible(true);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        sel = 0;
        clerkpanel.setVisible(false);
        managerpanel.setVisible(false); 
        loginpanel.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        if(setnamesonButton > 0){
            setnamesonButton -- ;
            clearbuttons();
            setnames();
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        String fifteenthButton = Buttonitem15.getText();
        if(!fifteenthButton.equals("")){
            setnamesonButton ++ ;
            clearbuttons();
            setnames();
        }
        String firstButton = Buttonitem1.getText();
        if(firstButton.equals("")){
            setnamesonButton -- ;
            clearbuttons();
            setnames();
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void Buttonitem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem1ActionPerformed
        String str = Buttonitem1.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem1ActionPerformed

    private void Buttonitem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem2ActionPerformed
        String str = Buttonitem2.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem2ActionPerformed

    private void Buttonitem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem3ActionPerformed
        String str = Buttonitem3.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem3ActionPerformed

    private void Buttonitem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem4ActionPerformed
        String str = Buttonitem4.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem4ActionPerformed

    private void Buttonitem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem5ActionPerformed
        String str = Buttonitem5.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem5ActionPerformed

    private void Buttonitem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem6ActionPerformed
        String str = Buttonitem6.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem6ActionPerformed

    private void Buttonitem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem7ActionPerformed
        String str = Buttonitem7.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem7ActionPerformed

    private void Buttonitem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem8ActionPerformed
        String str = Buttonitem8.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem8ActionPerformed

    private void Buttonitem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem9ActionPerformed
        String str = Buttonitem9.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem9ActionPerformed

    private void Buttonitem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem10ActionPerformed
        String str = Buttonitem10.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem10ActionPerformed

    private void Buttonitem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem11ActionPerformed
        String str = Buttonitem11.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem11ActionPerformed

    private void Buttonitem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem12ActionPerformed
        String str = Buttonitem12.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem12ActionPerformed

    private void Buttonitem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem13ActionPerformed
        String str = Buttonitem13.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem13ActionPerformed

    private void Buttonitem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem14ActionPerformed
        String str = Buttonitem14.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem14ActionPerformed

    private void Buttonitem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buttonitem15ActionPerformed
        String str = Buttonitem15.getText();
        getitemDetail(str);
    }//GEN-LAST:event_Buttonitem15ActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped

    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if(sel==1) {
            String st=jTextField8.getText();
            if(st.endsWith(".")) flag1 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField8.setText(st);
        }
        if(sel==2) {
            String st=jTextField7.getText();
            if(st.endsWith(".")) flag2 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField7.setText(st);
        }
        if(sel==3) {
            String st=jTextField4.getText();
            if(st.endsWith(".")) flag3 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField4.setText(st);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        sel = 1;
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        sel=2;
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
        if(jToggleButton1.isSelected()) jTextField3.setText("ADD SUPPLY");
        else jTextField3.setText("SELL");
    }//GEN-LAST:event_jToggleButton1MouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
       number = 1;
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.setRowCount(0);
        if(jToggleButton1.isSelected()) jTextField3.setText("ADD SUPPLY");
        else jTextField3.setText("SELL");
        
        if(jToggleButton1.isSelected()){
            jLabel1.setVisible(false);
            jTextField2.setVisible(false);
            jTextField4.setEnabled(true);
            //jButton15.setEnabled(true);
            jButton17.setEnabled(true);
        }
        else{
            jLabel1.setVisible(true);
            jTextField2.setVisible(true);
            jTextField4.setEnabled(false);
            jButton15.setEnabled(false);
            jButton17.setEnabled(false);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        jTextField6.setText("");
    }//GEN-LAST:event_jTextField6FocusGained

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        number=1;
        PrinterJob job = PrinterJob.getPrinterJob();
    job.setJobName("jPanel");
    job.setPrintable (new Printable() {    
        public int print(Graphics pg, PageFormat pf, int pageNum){
            if (pageNum > 0){
            return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) pg;
            g2.translate(pf.getImageableX(), pf.getImageableY());
            jPanel7.print(g2);
            return Printable.PAGE_EXISTS;
        }
     });
     boolean ok = job.printDialog();
     if (ok) {
         try {
              job.print();
         } catch (PrinterException ex) { }
        
     }
     DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
     m.setRowCount(0);
     jTextField2.setText("0.0");
     receiptTotal = 0;
     rowcount=0;
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if(sel==4) {
            String st=jTextField13.getText();
            if(st.endsWith(".")) flag4 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField13.setText(st);
        }
        if(sel==5) {
            String st=jTextField15.getText();
            if(st.endsWith(".")) flag5 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField15.setText(st);
        }
        if(sel==6) {
            String st=jTextField16.getText();
            if(st.endsWith(".")) flag6 = false;
            int l=st.length();
            if(l>0)
                st=st.substring(0, l-1);
            jTextField16.setText(st);
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        sel = 4;
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        sel = 5;
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        changePassword c=new changePassword(loggedperson);
        c.setVisible(true);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        changePassword c=new changePassword(loggedperson);
        c.setVisible(true);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        takeEmployeeDetails e = new takeEmployeeDetails();
        e.setVisible(true);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        String id = jTextField12.getText();
        if(id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please Give ID first.");
        else{
            String newprice = JOptionPane.showInputDialog("Please give new Price value.");
            if(newprice != null){
                
                try{
                    Item.changeprice(id, Float.parseFloat(newprice));
                    Item i=Inventory.getInfoItem(Item.getDetails(id).getname());
                    jTextField15.setText(Float.toString((float) i.getprice()));
                    jTextField16.setText(Float.toString((float) i.getprofitperunit()));
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Input format is incorrect.");
                }
            }
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        inventoryDetails inv = new inventoryDetails();
        inv.setVisible(true);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        String id = jTextField12.getText();
        if(id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please Give ID first.");
        else{
            statistics td = new statistics(id);
            td.setVisible(true);
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String name = jTextField6.getText();
            String password = jPasswordField1.getText();
            String str = null;

            if(name.isEmpty() || password.isEmpty())
                JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns first.");
            else{
                if(name.equals("manager")){
                    if(Manager.login(name, password)) {
                        loggedperson = 0;
                        loginpanel.setVisible(false);
                        clerkpanel.setVisible(false);
                        managerpanel.setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns correctly.");
                }
                else{
                    int slno = Salesclerk.login(name, password);
                    if(slno!=0){
                        loggedperson = slno;
                        loginpanel.setVisible(false);
                        managerpanel.setVisible(false);
                        clerkpanel.setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null, "Pleass fill all the coloumns correctly.");
                }
                jTextField6.setText("Enter ID");
                jPasswordField1.setText("********");
            }
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
//        char ch=evt.getKeyChar();
//        //if(ch=='.'&&one==0&&jTextField8.getText().charAt(0)=='0') {one=1;return;}
//        if(! Character.isDigit(ch) &&( ch!='.' ||(one==1&&ch=='.')  ) ){
//          evt.consume();
//        }
//        if(one==0 && ch=='.') one=1;//|| (one==0 &&ch=='.'&& jTextField8.getText().length()==0)
        
        char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
//        char ch=evt.getKeyChar();
//        if(! Character.isDigit(ch) &&( ch!='.' ||(two==1&&ch=='.') || (two==0 &&ch=='.'&& jTextField7.getText().length()==0)) ){
//          evt.consume();
//        }
//        if(two==0 &&ch=='.') two=1;
        
        char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
//        char ch=evt.getKeyChar();
//        if(! Character.isDigit(ch) &&( ch!='.' ||(three==1&&ch=='.') || (three==0 &&ch=='.'&& jTextField4.getText().length()==0)) ){
//          evt.consume();
//        }
//        if(three==0 &&ch=='.') three=1;
        
        char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
       char ch=evt.getKeyChar();
        if(! Character.isDigit(ch) && ch!='.'){
          evt.consume();
        }
    }//GEN-LAST:event_jTextField16KeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buttonitem1;
    private javax.swing.JButton Buttonitem10;
    private javax.swing.JButton Buttonitem11;
    private javax.swing.JButton Buttonitem12;
    private javax.swing.JButton Buttonitem13;
    private javax.swing.JButton Buttonitem14;
    private javax.swing.JButton Buttonitem15;
    private javax.swing.JButton Buttonitem2;
    private javax.swing.JButton Buttonitem3;
    private javax.swing.JButton Buttonitem4;
    private javax.swing.JButton Buttonitem5;
    private javax.swing.JButton Buttonitem6;
    private javax.swing.JButton Buttonitem7;
    private javax.swing.JButton Buttonitem8;
    private javax.swing.JButton Buttonitem9;
    private javax.swing.JPanel clerkpanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel loginpanel;
    private javax.swing.JPanel managerpanel;
    // End of variables declaration//GEN-END:variables
}
