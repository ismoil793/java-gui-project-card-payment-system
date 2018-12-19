import java.sql.*;
import javax.swing.JOptionPane;
public class javaconnect {
    //Connection conn;
    public static Connection ConnectDb(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:D:\\JAVA\\Projects\\Easy\\db\\PaymentNew.sqlite"); // address of DB
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Could not connect to DataBase");
            return null;
        }
    }
}