import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Color; 
import javax.swing.border.EmptyBorder;
import java.io.*; 
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import java.text.*;
import java.awt.event.KeyListener;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddIssues extends JFrame{

    Container cn; 
    JLabel header, book_id, student_id, success, idLabel;
    JTextField book_id_field,student_id_field, user_id;
    JButton add_user; 
    String uuidAsString; 
    JComboBox bookResults;

    AddIssues(){
        setTitle("Issue new Book"); 
        setSize(new Dimension(600, 400)); 
        setLocation(new Point(100, 100)); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        cn = getContentPane();
        cn.setLayout(null);
        
        header = new JLabel("Issue new book:");
        header.setBounds(0, 0,100,50);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBorder(new EmptyBorder(20, 50, 10, 10));
        
        JPanel headerDiv = new JPanel(new BorderLayout());
        headerDiv.setBounds(0, 0,800,70);
        headerDiv.setBackground(Color.decode("#f9f9fa"));
        headerDiv.add(header,BorderLayout.WEST );
        
        book_id = new JLabel("Book ID"); 
        book_id.setBounds(200, 90, 70, 20);
        book_id.setForeground(Color.BLACK);
        book_id.setFont(new Font("Calibri", Font.BOLD, 14));
        
        book_id_field = new JTextField(); 
        book_id_field.setBounds(200, 115, 193, 28); 
        
        
        student_id = new JLabel("Student ID"); 
        student_id.setBounds(200, 170, 100, 20);  
        student_id.setForeground(Color.BLACK); 
        student_id.setFont(new Font("Calibri", Font.BOLD, 14)); 
        
        student_id_field =  new JTextField(); 
        student_id_field.setBounds(200,195, 193, 28);
        
        //idLabel = new JLabel("Student ID"); 
        //idLabel.setBounds(200, 225, 70, 20);  
        //idLabel.setForeground(Color.BLACK); 
        //idLabel.setFont(new Font("Calibri", Font.BOLD, 14)); 
        
        //user_id =  new JTextField(); 
        //user_id.setBounds(200,250, 193, 28);
        
        
        add_user = new JButton("Add issue"); 
        add_user.setBounds(240, 250, 115, 25);
        add_user.setForeground(Color.WHITE);
        add_user.setBackground(Color.BLACK);
        
        //success = new JLabel("User added successfully!"); 
        //success.setBounds(200, 300, 150, 20); 
        //success.setForeground(Color.decode("#24B95C")); 
        //success.setFont(new Font("Calibri", Font.BOLD, 14));
        //success.setVisible(false); 
 
        cn.add(headerDiv);
        cn.add(book_id); 
        cn.add(book_id_field); 
        cn.add(student_id); 
        cn.add(student_id_field);
        //cn.add(idLabel);
        //cn.add(user_id);
        cn.add(add_user); 
        //cn.add(success);
    
        add_user.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
     
                String bookId = book_id_field.getText(); 
                String student_id = student_id_field.getText();
                SimpleDateFormat formDate = new SimpleDateFormat("dd-MM-yyyy");

                String currentDate = formDate.format(new Date());

                if(bookId.length() > 0 && student_id.length()>0){
                    try{
                     
                    String path = "C:\\Library";
                    File directory = new File(path);
                    if(!directory.exists()){
                        directory.mkdir();
                    }
                    
                    File books_file = new File(path+ "\\books.csv");
                    Scanner x = new Scanner (books_file);
                    x.useDelimiter("[,\n]");
                    boolean book_found = false;
                    int book_row = 0;
                    String search_book_name = "",search_book_author = "",search_book_status = "",search_book_id = "";
                    
                    while(x.hasNext() && !book_found) {
                        book_row++;
                        search_book_name = x.next();

                        search_book_author = x.next();
                        search_book_status = x.next();
                        search_book_id = x.next();
                        
                        if (search_book_id.equals(bookId)) {
                            book_found = true;
                        }
                    }
                    
                    File users_file = new File(path+ "\\users.csv");
                    Scanner y = new Scanner (users_file);
                    y.useDelimiter("[,\n]");
                    boolean user_found = false;
                    String search_user_name = "",search_user_class = "",search_user_id = "";
                    
                    while(y.hasNext() && !user_found) {

                        search_user_name = y.next();
                        search_user_class = y.next();
                        search_user_id = y.next();
        
                        if (search_user_id.equals(student_id)) {
                            user_found = true;
                        }
                    }
                    
                    if(book_found && user_found){
                        
                        if(search_book_status.equals("Available")){
                            FileWriter fw = new FileWriter(path+ "\\issues.csv", true);
                            fw.write(search_book_name.replaceAll("[.,]", "") + ","+ search_user_name.replaceAll("[.,]", "") + "," + currentDate + ","+ search_user_id+ "," + search_book_id+"\n"); 
                            fw.close();
                            //success.setVisible(true);
                            showMessageDialog(null, "Book issued successfully!");

                            CSVReader reader = new CSVReader(new FileReader("C:\\Library\\books.csv"));
                            List<String[]> csvBody = reader.readAll();
                            csvBody.get((book_row)-1)[2] = "Not Available".replace("\"", "");
                            reader.close();
                            
                            CSVWriter writer = new CSVWriter(new FileWriter("C:\\Library\\books.csv"), ',',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                            writer.writeAll(csvBody);
                            writer.flush();
                            writer.close();
                            
                            book_id_field.setText(""); 
                            student_id_field.setText(""); 
                        } else{
                            showMessageDialog(null, "Book is already issued.");
                        }
                        
                    } else{
                        showMessageDialog(null, "Error - Cannot find book/user with matching ID.");
                    }
  
                    
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
