package userInterface;

import hashTable.HashTable;
import hashTable.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SignUpPage extends JFrame implements ActionListener {

    JPanel left, right;
    JLabel icon, usernameLabel, passwordLabel, repasswordLabel, error, error1, error2, error3;
    JTextField username;
    JPasswordField password, repassword;
    JButton continues, home;
    HashTable hash;
    Node user;

    SignUpPage(){

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\Icon.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo.png");
        ImageIcon homeIcon = new ImageIcon(file.getAbsolutePath()+"\\Images\\home.png");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Sign Up Page");
        this.setSize(1140,690);
        this.setLocation(200,100);

        left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(450,0));
        left.setBackground(new Color(191, 216, 128));
        this.add(left,BorderLayout.WEST);

        right = new JPanel(null);
        right.setBackground(new Color(0xFFFFFF));
        right.setPreferredSize(new Dimension(675,0));
        this.add(right,BorderLayout.EAST);

        icon = new JLabel(img,JLabel.CENTER);
        icon.setOpaque(false);
        left.add(icon);

        usernameLabel= new JLabel("User Name");
        usernameLabel.setBounds(170,220,100,30);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        right.add(usernameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(170,280,100,30);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 16));
        right.add(passwordLabel);

        repasswordLabel = new JLabel("Re-enter password");
        repasswordLabel.setBounds(170,340,130,30);
        repasswordLabel.setFont(new Font("Serif", Font.BOLD, 16));
        right.add(repasswordLabel);

        username = new JTextField();
        username.setPreferredSize(new Dimension(200,20));
        username.setBounds(170,250,250,20);
        right.add(username);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200,20));
        password.setBounds(170,310,250,20);
        right.add(password);

        repassword = new JPasswordField();
        repassword.setPreferredSize(new Dimension(200,20));
        repassword.setBounds(170,370,250,20);
        right.add(repassword);

        continues = new JButton("Continue");
        continues.setBackground(new Color(191, 216, 128));
        continues.setBounds(320,400,100,30);
        continues.setFocusable(false);
        continues.setFont(new Font("Segoe UI", Font.BOLD,16));
        continues.setMargin(new Insets(-2,0,0,0));
        continues.addActionListener(this);
        right.add(continues);

        error = new JLabel("Username can't be empty!");
        error.setForeground(Color.red);
        error.setVisible(false);
        error.setBounds(175,270,250,20);
        right.add(error);

        error1 = new JLabel("Password can't be empty!");
        error1.setForeground(Color.red);
        error1.setVisible(false);
        error1.setBounds(175,328,250,20);
        right.add(error1);

        error2 = new JLabel("Username is already signed in!");
        error2.setForeground(Color.red);
        error2.setVisible(false);
        error2.setBounds(175,270,250,20);
        right.add(error2);

        error3 = new JLabel("Password is not the same!");
        error3.setForeground(Color.red);
        error3.setVisible(false);
        error3.setBounds(175,330,250,20);
        right.add(error3);

        home = new JButton(homeIcon);
        home.addActionListener(this);
        home.setBackground(new Color(191, 216, 128));
        home.setFocusable(false);
        home.setOpaque(true);
        home.setMargin(new Insets(-2,0,0,0));
        home.setBounds(5,5,50,50);
        right.add(home);

        this.setVisible(true);
        this.setResizable(false);

        hash = HashTable.getTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==continues){
            if(username.getText().isBlank()&& String.valueOf(password.getPassword()).isBlank()){
                error.setVisible(true);
                error1.setVisible(true);
                error2.setVisible(false);
                error3.setVisible(false);
                return;
            }
            else if(username.getText().isBlank()){
                error.setVisible(true);
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(false);
                return;
            }
            else if(String.valueOf(password.getPassword()).isBlank()){
                error.setVisible(false);
                error1.setVisible(true);
                error2.setVisible(false);
                error3.setVisible(false);
                return;
            }
            else {
                error.setVisible(false);
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(false);
            }

            hash = HashTable.getTable();
            if(hash.exists(username.getText())){
                error.setVisible(false);
                error1.setVisible(false);
                error2.setVisible(true);
                error3.setVisible(false);
                return;
            }

            if(!String.valueOf(password.getPassword()).equals(String.valueOf(repassword.getPassword()))){
                error.setVisible(false);
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(true);
                return;
            }

            error.setVisible(false);
            error1.setVisible(false);
            error2.setVisible(false);
            error3.setVisible(false);

            user = hash.insert(username.getText(),String.valueOf(password.getPassword()));
            HashTable.setTable(hash);

            this.dispose();
            new AccountPage(user);
        }
        if(e.getSource()==home){
            new HomePage();
            this.dispose();
        }
    }
}
