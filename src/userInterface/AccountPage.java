package userInterface;

import hashTable.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AccountPage extends JFrame implements ActionListener {

    JPanel left, right;
    Node user;
    JLabel icon, title, error;
    JButton home, calculate, view;
    AccountPage(Node user){

        this.user=user;

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\icon orange.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo orange.png");
        ImageIcon homeIcon = new ImageIcon(file.getAbsolutePath()+"\\Images\\home.png");
        ImageIcon Title = new ImageIcon(file.getAbsolutePath()+"\\Images\\title orange.png");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Account Page");
        this.setSize(1140,690);
        this.setLocation(200,100);

        left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(450,0));
        left.setBackground(new Color(255, 147, 88));
        this.add(left,BorderLayout.WEST);

        right = new JPanel(null);
        right.setBackground(new Color(0xFFFFFF));
        right.setPreferredSize(new Dimension(675,0));
        this.add(right,BorderLayout.EAST);

        icon = new JLabel(img,JLabel.CENTER);
        icon.setOpaque(false);
        left.add(icon);

        calculate = new JButton("Calculate Scores");
        calculate.setFocusable(false);
        calculate.addActionListener(this);
        calculate.setBackground(new Color(255, 147, 88));
        calculate.setFont(new Font("Segoe UI", Font.BOLD,16));
        calculate.setBounds(215,430,140,40);
        calculate.setMargin(new Insets(-1,0,0,-1));
        right.add(calculate);

        view = new JButton("View Scores");
        view.setFont(new Font("Segoe UI", Font.BOLD,16));
        view.setBackground(new Color(255, 147, 88));
        view.setFocusable(false);
        view.addActionListener(this);
        view.setBounds(360,430,130,40);
        view.setMargin(new Insets(-1,0,0,0));
        right.add(view);

        title = new JLabel(Title);
        title.setBounds(0,55,690,270);
        title.setOpaque(true);
        title.setBackground(new Color(0xFFFFFF));
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        right.add(title);

        error = new JLabel("The scores are not calculated yet!");
        error.setBounds(250,470,200,30);
        error.setForeground(Color.red);
        error.setVisible(false);
        right.add(error);

        home = new JButton(homeIcon);
        home.addActionListener(this);
        home.setBackground(new Color(255, 147, 88));
        home.setFocusable(false);
        home.setMargin(new Insets(-2,0,0,0));
        home.setBounds(5,5,50,50);
        home.setOpaque(true);
        right.add(home);

        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==calculate){
            new CalculatePage(user);
            this.dispose();
        }
        if(e.getSource()==view){
            if(user.Zopt==0){
                error.setVisible(true);
                return;
            }
            new ViewPage(user);
            this.dispose();
        }
        if(e.getSource()==home){
            new HomePage();
            this.dispose();
        }
    }
}
