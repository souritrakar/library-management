import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Color; 
import javax.swing.border.EmptyBorder;
import java.io.FileWriter;  

public class AdminWindow extends JFrame {
    JLabel header, usersImg;
    Container c; 
  
    public JPanel createDiv(String title, String img, int x, int y ) {
        
        JPanel tabDiv = new JPanel();
        tabDiv.setBounds(x,y,140,130);
        tabDiv.setBackground(Color.decode("#f9f9fa"));

        JLabel iconImg = new JLabel(new ImageIcon("Images/"+img));
        iconImg.setBounds(10, 100,200,100);
        
        JButton b1 = new JButton(title);     
        b1.setBounds(50,130,80,30);    
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                try{
                    String mt = title.split("\\s+")[1];
                    switch(mt){
                        case "Users":
                            Users users = new Users();
                            break;
                        case "Books":
                            Books books = new Books();
                            break;
                        case "Issues":
                            Issues issues = new Issues();
                            break;
                        case "Returns":
                            Returns returns = new Returns();
                            break;
                        default:
                            System.out.println("");
                            break;
                    }
                    }
                 catch (Exception ef){
                    System.out.println("f");
                }
            }
        });
        
        tabDiv.add(iconImg);
        tabDiv.add(b1);
        
        return tabDiv;
    }
        
    AdminWindow(){
        setTitle("Admin - Library Manager"); 
        setSize(new Dimension(800, 500)); 
        setLocation(new Point(500, 300)); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        c = getContentPane();
        c.setLayout(null); 
        
        JPanel headerDiv = new JPanel(new BorderLayout());
        headerDiv.setBounds(0, 0,800,70);
        headerDiv.setBackground(Color.decode("#f9f9fa"));
        
        header = new JLabel("Welcome, Admin");
        header.setBounds(0, 0,100,50);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBorder(new EmptyBorder(20, 50, 10, 10));
        
        JButton add = new JButton("Add user");
        add.setBounds(0,0,20,10);    
        add.setBackground(Color.decode("#f9f9fa"));
        add.setForeground(Color.BLACK);
        add.setFocusPainted(false);
        add.setIcon((new ImageIcon("Images/add.png")));
        add.setBorder(new EmptyBorder(20, 30, 25, 50));
        
        headerDiv.add(header,BorderLayout.WEST );
        headerDiv.add(add, BorderLayout.EAST);

        c.add(headerDiv);
        c.add(createDiv("View Users", "users.png", 50, 120 ));
        c.add(createDiv("View Books", "books.png", 320, 120 ));
        c.add(createDiv("View Issues", "issues.png", 600, 120 ));
        c.add(createDiv("View Returns", "returns.png", 320, 300 ));
        setVisible(true);
        setResizable(false);
        
        
        add.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {

                AddUsers au = new AddUsers();      
            }
        }); 
    }
}
