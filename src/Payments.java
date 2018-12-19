import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;//to change combobox which is invisible at first then it appears if payment is for Mobile operator and Internet provider
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Payments extends javax.swing.JFrame {
    Connection conn,conn2;  // first-connect to DB 
    PreparedStatement pst,pst2;//second-prepare sql statement
    ResultSet rs;//third execute code in a resulting set
    public static String userName;
    public static String cardNum,balance;
    double currentBalance,payment;
    
    public Payments() {
        super("Payments");
        initComponents();
        editableComboBox.setVisible(false);
        //System.out.println(userName+"     "+cardNum);
    }

    public void CardPayment(){                         // Function for button pay when it is pressed
        String sql="INSERT INTO `Payments` (`Username`,`Card`,`Transaction`,`Emount`,`Company`) VALUES(?,?,?,?,?)";
        try{
        conn=javaconnect.ConnectDb();// Connection to DB
        pst=conn.prepareStatement(sql);
        pst.setString(1,userName);
        pst.setString(2,cardNum);
        pst.setString(3,(String)jComboBox1.getSelectedItem());
        pst.setString(4, emount.getText());
        int index=jComboBox1.getSelectedIndex();       // if index is 0 there is no company name for transfers, we write card number
        String forCombobox;
        if(index==0){
            forCombobox="8600"+jTextField1.getText();
        }
        else{
            forCombobox=(String)editableComboBox.getSelectedItem();
        }
        pst.setString(5,forCombobox);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Payed Successfully!");
        
        }
        catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
//        finally{
//            try{
//                pst.close();
//                rs.close();
//            }catch(SQLException e){
//                JOptionPane.showMessageDialog(null, e);  // it is not used here cuz we apply single operation
//            }
//            catch(Exception e){
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
    }
    public void updateAccount(){
        String sql = "UPDATE Account SET Balance = ?"+"WHERE Username = ?";
        try{
        conn2=javaconnect.ConnectDb();
        currentBalance=Double.parseDouble(balance);
        payment=Double.parseDouble(emount.getText());
        double cashback=payment*0.02;
        double newBalance=currentBalance-payment+cashback;
        newBalance=Math.floor(newBalance*1000)/1000;         // round new balance
        pst2=conn2.prepareStatement(sql);
        pst2.setDouble(1, newBalance);//Math.floor(newBalance*1000)/1000
        pst2.setString(2, userName);
        
        pst2.execute();
        JOptionPane.showMessageDialog(null, "Cashback: +"+cashback+"\nAccount Updated");
        }
        catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void cardToCardTransaction(){                                // Transfer money to anoyher card
        double newPayment=Math.floor(payment*1000)/1000;
        String sql="UPDATE Account SET Balance=Balance+'"+newPayment+"'"+ "WHERE CardNum=?";
        try{
            String card="8600"+jTextField1.getText();
            conn=javaconnect.ConnectDb();
            pst=conn.prepareStatement(sql);
            pst.setString(1,card);
            pst.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        editableComboBox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        emount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setBackground(new java.awt.Color(54, 148, 223));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transfers to another card", "Mobile operator", "Internet provider" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        editableComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));

        jButton1.setBackground(new java.awt.Color(54, 148, 223));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Card number        8600");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\JAVA\\Projects\\Easy\\img\\cash.png")); // NOI18N
        jLabel1.setText("Choose type of payment");

        jPanel2.setBackground(new java.awt.Color(54, 148, 223));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 31)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Payments");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(249, 148, 6));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("D:\\JAVA\\Projects\\Easy\\img\\back.png")); // NOI18N
        jButton3.setText("Back");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sum");

        emount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(230, 230, 230))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(editableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(emount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(131, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emount, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);
        HomePage ob=new HomePage();
        ob.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jTextField1.setText("");
        int index=jComboBox1.getSelectedIndex();       // if index is 0 there is no company name for transfers
        if(index==0){
            jLabel3.setText("Card number        8600");
            editableComboBox.setVisible(false);
        }
        else if(index==1){                                      // if index 1 then thre are mobile operators
            jLabel3.setText("Phone number    +998");
            String[] s=new String[3];
            s[0]="UMS";
            s[1]="Ucell";
            s[2]="Beeline";
            editableComboBox.setVisible(true);
            DefaultComboBoxModel model = new DefaultComboBoxModel(s);
            editableComboBox.setModel( model );
        }
        else if(index==2){                      //  // if index 2 then thre are Internet providers
            jLabel3.setText("Account number");
            String[] s=new String[3];
            s[0]="Sarcor Telecom";
            s[1]="Turon Telecom";
            s[2]="Uzoffline";
            editableComboBox.setVisible(true);
            DefaultComboBoxModel model = new DefaultComboBoxModel(s);
            editableComboBox.setModel( model );
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char c=evt.getKeyChar();                                    // FOR CARD NUMBER very useful thing to learn field cannot exeed 16 digits or be a char 
        String Fieldlength=jTextField1.getText();                            // if so you will hear a BEEP
        int index=jComboBox1.getSelectedIndex();
        if(index==1){
            if(!(Character.isDigit(c))||Fieldlength.length()>8){
                if(c==KeyEvent.VK_BACK_SPACE){}
                else{getToolkit().beep();}
                evt.consume();
            }
        }
        else if(index==2){
            if(Fieldlength.length()>6){
                if(c==KeyEvent.VK_BACK_SPACE){}
                else{getToolkit().beep();}
                evt.consume();
            }
        }
        else{
            if(!(Character.isDigit(c))||Fieldlength.length()>11){
                if(c==KeyEvent.VK_BACK_SPACE){}
                else{getToolkit().beep();}
                evt.consume();
            }
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean flag=false;                                                                     // PAY button
        String Fieldlength=jTextField1.getText();
        int index=jComboBox1.getSelectedIndex();
        try{
            currentBalance=Double.parseDouble(balance);         // currnebt balance of user
            payment=Double.parseDouble(emount.getText());       // emount to be payed
            if(payment>currentBalance){
                throw new ArithmeticException();
            }
            if(jTextField1.getText().endsWith(" ")||jTextField1.getText().isEmpty()){   // if form doesn't satisfy flag=false
                throw new IllegalArgumentException();
            }
            if(jTextField1.getText().equals(cardNum.substring(4,16))||"0".equals(emount.getText())){
                throw new IllegalStateException();
            }
            if(index==0&&Fieldlength.length()<11){
                throw new NoSuchFieldError();
            }
            if(index==1&&Fieldlength.length()<8){
                throw new NoSuchFieldError();
            }
            if(index==2&&Fieldlength.length()<6){
                throw new NoSuchFieldError();
            }
            else{                                           // else flag is true
                flag=true;
            }
        }
        //Exception handling
        catch(IllegalStateException e){
            JOptionPane.showMessageDialog(null, "Card number can not be same as yours\nEmount can not be equal to 0");
        }
        catch(ArithmeticException e){
         JOptionPane.showMessageDialog(null, "You do not have enough money to pay");
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Field can not be  empty or contain a space");
        }catch(NoSuchFieldError e){
            if(index==0){
                JOptionPane.showMessageDialog(null, "Field should contain "+12+" entries");
            }
            if(index==1){
                JOptionPane.showMessageDialog(null, "Field should contain "+9+" entries");
            }
            if(index==2){
                JOptionPane.showMessageDialog(null, "Field should contain "+7+" entries");
            }
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
        }
         //end of Exception handling
        if(flag==true){
            if(index==0){
                CardPayment();
                updateAccount();
                cardToCardTransaction();
            }
            else{
                CardPayment();
                updateAccount();
            }
        }
        jTextField1.setText("");
        emount.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void emountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emountKeyTyped
       char c=evt.getKeyChar();                                    
        String Fieldlength=emount.getText();                            // if so you will hear a BEEP
        if(!(Character.isDigit(c))||Fieldlength.length()>4){
            if(c==KeyEvent.VK_BACK_SPACE){}
            else{getToolkit().beep();}
            evt.consume();
        }
    }//GEN-LAST:event_emountKeyTyped

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
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox editableComboBox;
    private javax.swing.JTextField emount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
