import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Color;
import static javax.swing.JOptionPane.showMessageDialog; 

class Login extends JFrame{
    Container c; 
    JLabel label1, label2, header;
    JTextField user; 
    JPasswordField pass;
    JButton button;
    
    Login(){
        
        setTitle("Login"); 
        setSize(new Dimension(600, 400)); 
        setLocation(new Point(500, 300)); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(Color.decode("#202C33")); 
        
        c = getContentPane();
        c.setLayout(null); 
        
        label1 = new JLabel("Username"); 
        label2 = new JLabel("Password"); 
        header = new JLabel("Sign in:");
        
        label1.setBounds(200, 110, 70, 20);
        label1.setForeground(Color.WHITE);
        label2.setFont(new Font("Calibri", Font.BOLD, 14));
        
        label2.setBounds(200, 180, 70, 20); 
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Calibri", Font.BOLD, 14));
        
        header.setBounds(200, 60,150, 40);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Calibri", Font.BOLD, 26));
        
        user = new JTextField(); 
        user.setBounds(200, 135, 193, 28); 
        
        pass = new JPasswordField(); 
        pass.setBounds(200,205, 193, 28);

        button = new JButton("Login"); 
        button.setBounds(240, 250, 115, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setIcon((new ImageIcon("Images/lock.png")));
        
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String username_V = user.getText();
                String password_V = pass.getText();
                
                if(username_V.equals("admin") && password_V.equals("admin")){
                    AdminWindow adWin = new AdminWindow();
                    closeWin();
                } else{
                    showMessageDialog(null, "Wrong username/password");
                } //dummy auth, can be any preset username/password
                
            }
        });

        c.add(label1); 
        c.add(label2); 
        c.add(header);
        c.add(user);
        c.add(pass);
        c.add(button); 
        
        setVisible(true);
        setResizable(false);        
    }
    
    public void closeWin() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
    
  }

class loginproto{
    public static void main(){
            Login frame = new Login(); 
    }
}
