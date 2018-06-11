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
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableFromMySqlDatabase extends JFrame{
    
    private JTable table;
    private Vector columnNamesVector;
    private Vector dataVector;

    public TableFromMySqlDatabase(){
    
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        // Connect to MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/EMP";
        String userid = "root";
        String password = "root";
        String sql = "SELECT * FROM Employees";

        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++){
            
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next()){
            
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e){
        
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        columnNamesVector = new Vector();
        dataVector = new Vector();

        for (int i = 0; i < data.size(); i++){
        
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++){
            
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        table = new JTable(dataVector, columnNamesVector){
        
            public Class getColumnClass(int column){
            
                for (int row = 0; row < getRowCount(); row++){
                    Object o = getValueAt(row, column);
                    if (o != null){
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
    }

    //This function is currently unused. Passed to, and displayed by, DatabaseTable.java
    public void displayTable(){
    
        TableFromMySqlDatabase frame = new TableFromMySqlDatabase();
        frame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        frame.setTitle("All Client Records");
        frame.setLocation(new java.awt.Point(0, 0));
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public JTable getTable(){
        return table;
    }
    
    public Vector getData(){
        return dataVector;
    }
    public Vector getColumnNames(){
        return columnNamesVector;
    }
}
