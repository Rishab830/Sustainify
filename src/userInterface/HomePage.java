package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HomePage extends JFrame implements ActionListener {

    JPanel left, right;
    JLabel icon, title;
    JButton signIn, signUp, remove;
    HomePage(){

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\Icon.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo.png");
        ImageIcon Title = new ImageIcon(file.getAbsolutePath()+"\\Images\\title.png");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Home Page");
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

        signIn = new JButton("Sign In");
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        signIn.setBackground(new Color(191, 216, 128));
        signIn.setFont(new Font("Segoe UI", Font.BOLD,16));
        signIn.setBounds(257,430,85,40);
        signIn.setMargin(new Insets(-1,0,0,0));
        right.add(signIn);

        signUp = new JButton("Sign Up");
        signUp.setFont(new Font("Segoe UI", Font.BOLD,16));
        signUp.setBackground(new Color(191, 216, 128));
        signUp.setFocusable(false);
        signUp.addActionListener(this);
        signUp.setBounds(347,430,85,40);
        signUp.setMargin(new Insets(-1,0,0,0));
        right.add(signUp);

        remove = new JButton("Remove Account");
        remove.setFont(new Font("Segoe UI", Font.BOLD,16));
        remove.setBackground(new Color(191, 216, 128));
        remove.setFocusable(false);
        remove.addActionListener(this);
        remove.setBounds(270,475,150,40);
        remove.setMargin(new Insets(-1,0,0,0));
        right.add(remove);

        title = new JLabel(Title);
        title.setBounds(0,0,690,270);
        title.setOpaque(true);
        title.setBackground(new Color(0xFFFFFF));
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        right.add(title);

        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signIn){
            this.dispose();
            new SignInPage();
        }
        else if(e.getSource()==signUp){
            this.dispose();
            new SignUpPage();
        }
        else if(e.getSource()==remove){
            this.dispose();
            new RemoveAccountPage();
        }
    }
}
