import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Color; 
import javax.swing.border.EmptyBorder;
import java.io.*; 
import java.awt.event.KeyEvent;
import java.util.UUID;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.event.KeyListener;

public class AddBooks extends JFrame{
    Container cn; 
    JLabel header, name, authorV, success;
    JTextField name_field,author_field;
    JButton add_book; 
    String uuidAsString; 
    
    private void bookid(){
        UUID uuid = UUID.randomUUID();
        uuidAsString = uuid.toString();
    }
    
  
    AddBooks(){
        setTitle("Add Book"); 
        setSize(new Dimension(600, 400)); 
        setLocation(new Point(500, 300)); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        cn = getContentPane();
        cn.setLayout(null);
        
        header = new JLabel("Add new book:");
        header.setBounds(0, 0,100,50);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBorder(new EmptyBorder(20, 50, 10, 10));
        
        JPanel headerDiv = new JPanel(new BorderLayout());
        headerDiv.setBounds(0, 0,800,70);
        headerDiv.setBackground(Color.decode("#f9f9fa"));
        headerDiv.add(header,BorderLayout.WEST );
        
        name = new JLabel("Name"); 
        name.setBounds(200, 110, 70, 20);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Calibri", Font.BOLD, 14));
        
        name_field = new JTextField(); 
        name_field.setBounds(200, 135, 193, 28); 
        
        
        authorV = new JLabel("Author"); 
        authorV.setBounds(200, 180, 70, 20);  
        authorV.setForeground(Color.BLACK); 
        authorV.setFont(new Font("Calibri", Font.BOLD, 14)); 
        
        author_field =  new JTextField(); 
        author_field.setBounds(200,205, 193, 28);
        
        
        add_book = new JButton("Add"); 
        add_book.setBounds(240, 250, 115, 25);
        add_book.setForeground(Color.WHITE);
        add_book.setBackground(Color.BLACK);
        
        //success = new JLabel("User added successfully!"); 
        //success.setBounds(200, 300, 150, 20); 
        //success.setForeground(Color.decode("#24B95C")); 
        //success.setFont(new Font("Calibri", Font.BOLD, 14));
        //success.setVisible(false); 
 
        cn.add(headerDiv);
        cn.add(name); 
        cn.add(name_field); 
        cn.add(authorV); 
        cn.add(author_field); 
        cn.add(add_book); 
        //cn.add(success);
    
        add_book.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
     
                String book_name = name_field.getText(); 
                String book_author = author_field.getText(); 
                bookid();
                
                if(book_name.length() > 0 && book_author.length()>0){
                    try{
                     
                    String path = "C:\\Library";
                    File directory = new File(path);
                    if(!directory.exists()){
                        directory.mkdir();
                    }
                    FileWriter fw = new FileWriter(path+ "\\books.csv", true);
                    fw.write(book_name + ","+ book_author + ","+"Available" +","+ uuidAsString + "\n"); 
                    fw.close();
                    //success.setVisible(true);
                    showMessageDialog(null, "Book added successfully!");
                    name_field.setText(""); 
                    author_field.setText(""); 
                    
                 } catch(Exception exc){
                     System.out.println(exc);
                    }
                    
                } else{
                    showMessageDialog(null, "Cannot be blank.");
                }
            }
        });
        
        setVisible(true);
        setResizable(false); 
    }
}
