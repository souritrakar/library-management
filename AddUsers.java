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

public class AddUsers extends JFrame{
    Container cn; 
    JLabel header, name, classV, success;
    JTextField name_field,classField;
    JButton add_user; 
    String uuidAsString; 
    
    private void memberid(){
        UUID uuid = UUID.randomUUID();
        uuidAsString = uuid.toString();
    }
    
  
    AddUsers(){
        setTitle("Add Users"); 
        setSize(new Dimension(600, 400)); 
        setLocation(new Point(500, 300)); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        cn = getContentPane();
        cn.setLayout(null);
        
        header = new JLabel("Add new user:");
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
        
        
        classV = new JLabel("Class"); 
        classV.setBounds(200, 180, 70, 20);  
        classV.setForeground(Color.BLACK); 
        classV.setFont(new Font("Calibri", Font.BOLD, 14)); 
        
        classField =  new JTextField(); 
        classField.setBounds(200,205, 193, 28);
        
        
        add_user = new JButton("Add"); 
        add_user.setBounds(240, 250, 115, 25);
        add_user.setForeground(Color.WHITE);
        add_user.setBackground(Color.BLACK);
        
        //success = new JLabel("User added successfully!"); 
        //success.setBounds(200, 300, 150, 20); 
        //success.setForeground(Color.decode("#24B95C")); 
        //success.setFont(new Font("Calibri", Font.BOLD, 14));
        //success.setVisible(false); 
 
        cn.add(headerDiv);
        cn.add(name); 
        cn.add(name_field); 
        cn.add(classV); 
        cn.add(classField); 
        cn.add(add_user); 
        //cn.add(success);
    
        add_user.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
     
                String user_name = name_field.getText(); 
                String user_class = classField.getText(); 
                memberid();
                
                if(user_name.length() > 0 && user_class.length()>0){
                    try{
                     
                    String path = "C:\\Library";
                    File directory = new File(path);
                    if(!directory.exists()){
                        directory.mkdir();
                    }
                    FileWriter fw = new FileWriter(path+ "\\users.csv", true);
                    fw.write(user_name.replaceAll("[.,]", "") + ","+ user_class.replaceAll("[.,]", "") + "," + uuidAsString + "\n"); 
                    fw.close();
                    //success.setVisible(true);
                    showMessageDialog(null, "User added successfully!");
                    name_field.setText(""); 
                    classField.setText(""); 
                    
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
