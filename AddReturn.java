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

public class AddReturn extends JFrame{

    Container cn; 
    JLabel header, book_id, student_id, success, idLabel;
    JTextField book_id_field,student_id_field, user_id;
    JButton add_user; 
    String uuidAsString; 
    JComboBox bookResults;

    AddReturn(){
        setTitle("Return Book"); 
        setSize(new Dimension(600, 400)); 
        setLocation(new Point(100, 100)); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        cn = getContentPane();
        cn.setLayout(null);
        
        header = new JLabel("Return book:");
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
        
        add_user = new JButton("Return book"); 
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
                    
                    File issues_file = new File(path+ "\\issues.csv");
                    Scanner z = new Scanner (issues_file);
                    z.useDelimiter("[,\n]");
                    boolean issue_found = false;
                    int issue_row = 0;
                    String issue_date = "", name = "", user, user_id = "", issue_book_id = "";
                    
                    while(z.hasNext() && !issue_found) {
                        issue_row++;
                        name = z.next();
                        user = z.next();
                        issue_date = z.next();
                        user_id = z.next();
                        issue_book_id = z.next();
                        
        
                        if (issue_book_id.equals(bookId)) {
                            issue_found = true;
                        }
                    }
                    
                    if(book_found && user_found && issue_found){
                        
                        if(user_id.equals(student_id)){
                            
                            if(search_book_status.equals("Not Available")){
                            FileWriter fw = new FileWriter(path+ "\\returns.csv", true);
                            fw.write(search_book_name.replaceAll("[.,]", "") + ","+ search_user_name.replaceAll("[.,]", "") + "," + currentDate + ","+ search_user_id+ "\n"); 
                            fw.close();
                            
                            Date date1 = formDate.parse(issue_date);
                            Date date2 = formDate.parse(currentDate);
                            
                            long difference = ((date2.getTime() - date1.getTime()) / (1000*60*60*24));
                            long rate = 50;

                            showMessageDialog(null, "Book returned successfully!");
                            
                            if(difference- 7 != 0){
                                showMessageDialog(null,  "Fine of ₹"+(rate*difference) +" for "+difference+" extra days");
                            }
   
                            CSVReader reader = new CSVReader(new FileReader("C:\\Library\\books.csv"));
                            List<String[]> csvBody = reader.readAll();
                            csvBody.get((book_row)-1)[2] = "Available".replace("\"", "");
                            reader.close();
                            
                            CSVWriter writer = new CSVWriter(new FileWriter("C:\\Library\\books.csv"), ',',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                            writer.writeAll(csvBody);
                            writer.flush();
                            writer.close();
                            
                            CSVReader issue_reader = new CSVReader(new FileReader("C:\\Library\\issues.csv"));
                            List<String[]> issue_body = issue_reader.readAll();
                            issue_body.remove((issue_row)-1);
                            issue_reader.close();
                            
                            CSVWriter issue_writer = new CSVWriter(new FileWriter("C:\\Library\\issues.csv"), ',',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                            issue_writer.writeAll(issue_body);
                            issue_writer.flush();
                            issue_writer.close();
                            
                            book_id_field.setText(""); 
                            student_id_field.setText(""); 
                        } else{
                            showMessageDialog(null, "No issue of this book found.");
                        }
                        } else {
                            showMessageDialog(null, "No issue found for this user. ");
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
