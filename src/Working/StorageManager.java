package Working;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**@author Jigyasa*/

public class StorageManager{
     Connection conn;

    public StorageManager(String str) throws ClassNotFoundException, SQLException {
       //try{
           String driver="net.ucanaccess.jdbc.UcanaccessDriver";
     //x      }
        Class.forName(driver);
        conn=DriverManager.getConnection("jdbc:ucanaccess://"+str);
       }
    //catch(Exception e){System.out.println("hellllllo");}}
    
    public ResultSet query(String SQL) throws SQLException{
            Statement stmt=conn.createStatement();
            ResultSet result=stmt.executeQuery(SQL);
            return result;
    }
    public int update(String SQL) throws SQLException{
            Statement stmt=conn.createStatement();
            int done=stmt.executeUpdate(SQL);
            return done;
    }
    public int updateReturnID(String SQL) throws SQLException{
            Statement stmt=conn.createStatement();
            int id=-1;
            stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet result=stmt.getGeneratedKeys();
            if(result.next()){
                id=result.getInt(1);
            }
            return id;
    }
}
