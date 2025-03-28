package userInterface;

import hashTable.HashTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RemoveAccountPage extends JFrame implements ActionListener {

    JPanel left, right;
    JLabel icon, usernameLabel, passwordLabel, error, error1, error2, error3;
    JTextField username;
    JPasswordField password;
    JButton continues, home;
    HashTable hash;

    RemoveAccountPage(){

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\Icon.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo.png");
        ImageIcon homeIcon = new ImageIcon(file.getAbsolutePath()+"\\Images\\home.png");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Remove Account Page");
        this.setSize(1140,690);
        this.setLocation(200,100);

        left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(450,0));
        left.setBackground(new Color(191, 216, 128));
        this.add(left,BorderLayout.WEST);

        right = new JPanel(null);
        right.setPreferredSize(new Dimension(675,0));
        right.setBackground(new Color(0xFFFFFF));
        this.add(right,BorderLayout.EAST);

        icon = new JLabel(img,JLabel.CENTER);
        icon.setOpaque(false);
        left.add(icon);

        usernameLabel= new JLabel("User Name");
        usernameLabel.setBounds(170,220,100,30);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        right.add(usernameLabel);

        username = new JTextField();
        username.setPreferredSize(new Dimension(200,20));
        username.setBounds(175,250,250,20);
        right.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(170,290,100,30);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 16));
        right.add(passwordLabel);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200,20));
        password.setBounds(175,320,250,20);
        right.add(password);

        continues = new JButton("Continue");
        continues.addActionListener(this);
        continues.setBackground(new Color(191, 216, 128));
        continues.setBounds(325,360,100,30);
        continues.setFocusable(false);
        continues.setFont(new Font("Segoe UI", Font.BOLD,16));
        continues.setMargin(new Insets(-2,0,0,0));
        right.add(continues);

        home = new JButton(homeIcon);
        home.addActionListener(this);
        home.setBackground(new Color(191, 216, 128));
        home.setFocusable(false);
        home.setOpaque(true);
        home.setMargin(new Insets(-2,0,0,0));
        home.setBounds(5,5,50,50);
        right.add(home);

        error = new JLabel("Username can't be empty!");
        error.setForeground(Color.red);
        error.setVisible(false);
        error.setBounds(175,270,250,20);
        right.add(error);

        error1 = new JLabel("Password can't be empty!");
        error1.setForeground(Color.red);
        error1.setVisible(false);
        error1.setBounds(175,340,250,20);
        right.add(error1);

        error2 = new JLabel("Username doesn't exist");
        error2.setForeground(Color.red);
        error2.setVisible(false);
        error2.setBounds(175,270,250,20);
        right.add(error2);

        error3 = new JLabel("Password is not correct!");
        error3.setForeground(Color.red);
        error3.setVisible(false);
        error3.setBounds(175,340,250,20);
        right.add(error3);

        this.setVisible(true);
        this.setResizable(false);
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

            if(!hash.exists(username.getText())){
                error.setVisible(false);
                error1.setVisible(false);
                error2.setVisible(true);
                error3.setVisible(false);
                return;
            }

            if(!hash.getPassword(username.getText()).equals(String.valueOf(password.getPassword()))){
                error.setVisible(false);
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(true);
                return;
            }

            int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this account?","Confirmation",JOptionPane.YES_NO_OPTION);

            if(choice==JOptionPane.YES_OPTION){
                hash.delete(username.getText());
                HashTable.setTable(hash);
                JOptionPane.showMessageDialog(this,"Account Successfully deleted","Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(e.getSource()==home){
            new HomePage();
            this.dispose();
        }
    }
}
