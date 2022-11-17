import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.List; 
import javax.swing.table.DefaultTableModel;
import java.awt.Color; 
import javax.swing.border.EmptyBorder;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.*;
import java.io.*;
import com.opencsv.CSVReader;
 
public class Issues extends JFrame
{
    JLabel header, usersImg, empty;
    JTable table;
    JScrollPane scrollPane;
    Container c;
    Object[][] user_data;
    List<String[]> myEntries;
    String[][] rowData;
    boolean isContentVisible = false;
    
    
    public class Issue{
        
        private String user_name;
        private String user_class;
        private String user_id;
        public Issue(String user_name, String user_class, String user_id){
            this.user_name = user_name;
            this.user_class = user_class;
            this.user_id = user_id;
        }
    }

    
    Issues(){
        setTitle("Issues - Library Manager"); 
        setSize(new Dimension(800, 500)); 
        setLocation(new Point(700, 200)); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        c = getContentPane();
        c.setLayout(null); 
        
        JPanel headerDiv = new JPanel(new BorderLayout());
                
        header = new JLabel("All issues:");
        header.setBounds(0, 0,100,50);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBorder(new EmptyBorder(20, 50, 10, 10));
        
        JButton addU = new JButton("Add issue");
        addU.setBounds(0,0,20,10);    
        addU.setBackground(Color.decode("#f9f9fa"));
        addU.setForeground(Color.BLACK);
        addU.setFocusPainted(false);
        addU.setIcon((new ImageIcon("Images/add.png")));
        addU.setBorder(new EmptyBorder(20, 30, 25, 50));
        
        addU.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                  AddIssues au = new AddIssues();      
                }
        }); 
        
        headerDiv.setBounds(0, 0,800,70);
        headerDiv.setBackground(Color.decode("#f9f9fa"));
        headerDiv.add(header,BorderLayout.WEST );
        headerDiv.add(addU, BorderLayout.EAST);
        
        empty = new JLabel("No created book issues to show.");
        empty.setBounds(265, 200,280,50);
        empty.setForeground(Color.BLACK);
        empty.setFont(new Font("Calibri", Font.BOLD, 20));
        
        String[] columnNames = {"Book", "User", "Date Issued" ,"User_ID", "Book_ID"};
        File users_file = new File("C:\\Library\\issues.csv");
        
        try{
        
        if(users_file.exists()){
            empty.setVisible(false);
            CSVReader reader = new CSVReader(new FileReader("C:\\Library\\issues.csv"));
            List<String[]> myEntries = reader.readAll();
            rowData = myEntries.toArray(new String[0][]);
            
        } else{ 
            empty.setVisible(true);
            
        }
        
        } catch (Exception ef) {
            ef.printStackTrace();
        }
        
        
        if(users_file.exists()){
            table = new JTable(rowData, columnNames);
            table.setFont(new Font("Calibri", Font.BOLD, 14));
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0,70,800,500);
            scrollPane.setSize(800, 500);
            table.setFillsViewportHeight(true);
            c.add(scrollPane);
        }
        
        c.add(empty);
        c.add(headerDiv);
        
        setVisible(true);
        setResizable(false);
    }
}
