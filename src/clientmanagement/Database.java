/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmanagement;

/**
 *
 * @author Dmoney
 */

import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Database {
    
    /*
    example of INSERT using variables:

    String sqlInsert;
            sqlInsert = "INSERT INTO ClientRecord VALUES (" + "'" + firstName + "'" 
                                                            + ", " + "'" + lastName + "'" 
                                                            + ", " + "'" + email + "'"+ " )";

    -----------------------------------------------------------------------------------------------

    Get column names and row data from the database

    ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //Get column names
            for (int i = 1; i <= columns; i++){
                columnNames.add(md.getColumnName(i));
            }

            //Get row data
            while (rs.next()){
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++){
                    row.add(rs.getObject(i));
                }

                data.add(row);
            }

               // stmt1.setString(1,arguser); for later


    */

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/EMP";

    //Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    //Arrays
    private ArrayList columnNames = new ArrayList();
    private ArrayList data = new ArrayList();
    private Vector columnNamesVector = new Vector();
    private Vector dataVector = new Vector();
   
    // Checks if user has a valid access code during account creation
    public int accessCode(String argAccess){
        String accessCheck;
        Connection conn = null;
        PreparedStatement stmt1 = null;
        int qResult = 0;
        
        try{
            //Connecting to the database          
            System.out.println("Connecting to database..."); 
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            System.out.println("Creating statement...");
            System.out.println("access code passed: " + argAccess); // test use
               
            String strQuery = "SELECT accesscode FROM accesstable"; // WHERE username = ?";
            stmt1 = conn.prepareStatement(strQuery);
            ResultSet rs = stmt1.executeQuery();
            
           while(rs.next())
           {
                   accessCheck = rs.getString("accesscode");
                   System.out.println("code checked: " + accessCheck); // test use
                   
                   if(accessCheck.equals(argAccess))
                   {
                       qResult = 1;
                       return qResult;
                   }      
           }
           return qResult;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(ClassNotFoundException e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt1!=null)
                stmt1.close();
            }catch(SQLException se2){
                //nothing we can do
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return qResult;
    }//end Access code check
    
    // Deletes the access code after it has been successfully validated and the account has been created
     public void deleteAccess(String argAccess){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            //STEP 5: Delete all matching entries
            String sql = "DELETE FROM accesstable WHERE accesscode = '" + argAccess + "'";
            
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        }
        catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }
        catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
        finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
  
     }//end delete accesscode
     
     // Creates a database entry into testusers1 for a user upon account creation.
     public void createAccount(String argUser, String argPass){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            //STEP 5: Insert username & password into users table
            String sql = "Insert into testusers1 (username,password) values ('" + argUser + "','" + argPass + "')";
            
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        }
        catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }
        catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
        finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
  
     }//end delete accesscode
     
     // Authenticates a users login by checking the username and password - currently disabled.
    public int authenticate(String argUser, String argPass){
        
        String passCheck, userCheck;
        Connection conn = null;
        PreparedStatement stmt1 = null;
        int qResult = 0;
        
        try{
            //Connecting to the database          
            System.out.println("Connecting to database..."); 
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            System.out.println("Creating statement...");
            System.out.println("username passed: " + argUser); // test use
            System.out.println(" password passed: " + argPass); // test use
            
            //Selecting all usernames and passwords    
            String strQuery = "SELECT username, password FROM testusers1"; // WHERE username = ?";
            stmt1 = conn.prepareStatement(strQuery);
            ResultSet rs = stmt1.executeQuery();
            
            //Checking usernames and passwords against those in the database, returns 1 if match found.
            while(rs.next())
            {
                   userCheck = rs.getString("username");
                   passCheck = rs.getString("password");
                   System.out.println("username checked: " + userCheck); // test use
                   System.out.println(" password checked: " + passCheck); // test use
                   
                   if(userCheck.equals(argUser) && passCheck.equals(argPass))
                   {
                       qResult = 1;
                       return qResult;
                   }      
            }
            return qResult;
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(ClassNotFoundException e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt1!=null)
                stmt1.close();
            }catch(SQLException se2){
                //nothing we can do
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return qResult;
    }//end querylogin
    
    // **Query All Table Elements**
    public void queryAllElements(){

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sqlSelect;
            sqlSelect = "SELECT * FROM Employees";
            ResultSet rs = stmt.executeQuery(sqlSelect);

            //STEP 5: Extract data from result set
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //Get column names
            for (int i = 1; i <= columns; i++){
                columnNames.add(md.getColumnName(i));
            }

            //Get row data
            while (rs.next()){
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++){
                    row.add(rs.getObject(i));
                }

                data.add(row);
            }
            
            // Create Vectors and copy over elements from ArrayLists to them.
            // Vector is deprecated but I am using them to keep 
            // things simple - the best practice would be to create a custom defined
            // class which inherits from the AbstractTableModel class

            for (int i = 0; i < data.size(); i++){
                ArrayList subArray = (ArrayList)data.get(i);
                Vector subVector = new Vector();

                for (int j = 0; j < subArray.size(); j++){
                    subVector.add(subArray.get(j));
                }
                dataVector.add(subVector);
            }

            for (int i = 0; i < columnNames.size(); i++ ){
                columnNamesVector.add(columnNames.get(i));
            }
           
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
                 
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                stmt.close();
            }
            catch(SQLException se2){
                //nothing we can do
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

    }//end queryAllElements

    public void addElement(String id, String age, String firstName, String lastName){
        Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();

                String sqlInsert;
                //currently on CM_UI: Email = id; Phone# = age
                sqlInsert = "INSERT INTO Employees VALUES (" + "'" + id + "'" 
                                                            + ", " + "'" + age + "'"
                                                            + ", " + "'" + firstName + "'"
                                                            + ", " + "'" + lastName + "'"+ " )";
                stmt.executeUpdate(sqlInsert);


                //STEP 5: Clean-up environment
                stmt.close();
                conn.close();
            }
            catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }
            finally{
            //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }
                catch(SQLException se2){
                    //nothing we can do
                }
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }
                catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
    }//end addElement

    public void deleteEntry(String [] id){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            //STEP 5: Delete all matching entries
            for (int i = 0; i < id.length; i++){
                String sql = "DELETE FROM Employees " +
                             "WHERE id = " + id[i];
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.close();

        }
        catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }
        catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
        finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
  
     }//end JDBCExample
    

    public Vector getData(){
        return dataVector;
    }

    public Vector getColumnNames(){
        return columnNamesVector;
    }
   
   
}
