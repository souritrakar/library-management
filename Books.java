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
 
public class Books extends JFrame
{
    JLabel header, empty;
    JTable table;
    JScrollPane scrollPane;
    Container c;
    List<String[]> myEntries;
    String[][] rowData;
    boolean isContentVisible = false;
    
    public class Book{
        
        private String book_name;
        private String book_author;
        public Book(String book_name, String book_author){
            this.book_name = book_name;
            this.book_author = book_author ;
        }
    }

    
    Books(){
        setTitle("Books - Library Manager"); 
        setSize(new Dimension(800, 500)); 
        setLocation(new Point(700, 200)); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        c = getContentPane();
        c.setLayout(null); 
        
        JPanel headerDiv = new JPanel(new BorderLayout());
        headerDiv.setBounds(0, 0,800,70);
        headerDiv.setBackground(Color.decode("#f9f9fa"));
        
        header = new JLabel("All Books");
        header.setBounds(0, 0,100,50);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBorder(new EmptyBorder(20, 50, 10, 10));
        
        JButton addU = new JButton("Add book");
        addU.setBounds(0,0,20,10);    
        addU.setBackground(Color.decode("#f9f9fa"));
        addU.setForeground(Color.BLACK);
        addU.setFocusPainted(false);
        addU.setIcon((new ImageIcon("Images/add.png")));
        addU.setBorder(new EmptyBorder(20, 30, 25, 50));
        
        addU.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                  AddBooks ab = new AddBooks();      
                }
        });
        
        headerDiv.add(header,BorderLayout.WEST );
        headerDiv.add(addU, BorderLayout.EAST);
        
        empty = new JLabel("No added books to show.");
        empty.setBounds(275, 180,250,50);
        empty.setForeground(Color.BLACK);
        empty.setFont(new Font("Calibri", Font.BOLD, 20));
        
        String[] columnNames = {"Name", "Author", "Status", "Book_ID"};
        File books_file = new File("C:\\Library\\books.csv");
        
        try{
        
        if(books_file.exists()){
            empty.setVisible(false);
            CSVReader reader = new CSVReader(new FileReader("C:\\Library\\books.csv"));
            List<String[]> myEntries = reader.readAll();
            rowData = myEntries.toArray(new String[0][]);
        } else{ 
            empty.setVisible(true);
            
        }
        } catch (Exception ef) {
            ef.printStackTrace();
        }
        
        
        if(books_file.exists()){
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
