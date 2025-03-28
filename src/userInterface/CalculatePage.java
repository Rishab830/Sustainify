package userInterface;

import hashTable.HashTable;
import hashTable.Node;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import process.Process;

public class CalculatePage extends JFrame implements ActionListener {

    JPanel left, right;
    JLabel icon, enter, bMax, sMin, eMin, seMin, error, error1, lakhs, sMinRight, eMinRight, seMinRight;
    JButton home, submit;
    JTextField Bmax, Smin, Emin, SEmin;
    Node user;
    
    CalculatePage(Node user){

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\icon orange.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo orange.png");
        ImageIcon homeIcon = new ImageIcon(file.getAbsolutePath()+"\\Images\\home.png");

        this.user = user;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Calculate Page");
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

        home = new JButton(homeIcon);
        home.addActionListener(this);
        home.setBackground(new Color(255, 147, 88));
        home.setFocusable(false);
        home.setMargin(new Insets(-2,0,0,0));
        home.setBounds(5,5,50,50);
        home.setOpaque(true);
        right.add(home);

        enter = new JLabel("Enter the following-");
        enter.setBounds(70,230,200,30);
        enter.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(enter);

        bMax = new JLabel("1) Maximum Budget:");
        bMax.setBounds(90,260,158,30);
        bMax.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(bMax);

        lakhs = new JLabel("Lakhs (12-34)");
        lakhs.setBounds(430,237,120,30);
        lakhs.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(lakhs);

        sMin =new JLabel("2) Minimum social performance:");
        sMin.setBounds(90,295,250,30);
        sMin.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(sMin);

        sMinRight = new JLabel("0 to 6");
        sMinRight.setBounds(570,295,250,30);
        sMinRight.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(sMinRight);
        
        eMin = new JLabel("3) Minimum economic performance:");
        eMin.setBounds(90,330,280,30);
        eMin.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(eMin);

        eMinRight = new JLabel("0 to 6");
        eMinRight.setBounds(570,330,250,30);
        eMinRight.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(eMinRight);

        seMin =new JLabel("4) Minimum socio-economic performance:");
        seMin.setBounds(90,365,317,30);
        seMin.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(seMin);

        seMinRight = new JLabel("0 to 8");
        seMinRight.setBounds(570,365,250,30);
        seMinRight.setFont(new Font("Serif", Font.BOLD, 17));
        right.add(seMinRight);

        error = new JLabel("All fields are required!");
        error.setBounds(270,440,200,30);
        error.setForeground(Color.red);
        error.setVisible(false);
        right.add(error);

        error1 = new JLabel("Output not able to be generated!");
        error1.setBounds(270,440,200,30);
        error1.setForeground(Color.red);
        error1.setVisible(false);
        right.add(error1);

        Bmax= new JTextField();
        Bmax.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if((c>'9'||c<'0')&&(c!='\b')){
                    e.consume();
                }
            }
        });
        Bmax.setBounds(410,267,150,20);
        right.add(Bmax);

        Smin= new JTextField();
        Smin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c<'7'&&c>='0')||c=='\b'||c=='.')){
                    e.consume();
                }
                if(c=='.'&&Process.contains(Smin.getText(),'.')){
                    e.consume();
                }
            }
        });
        Smin.setBounds(410,302,150,20);
        right.add(Smin);

        Emin= new JTextField();
        Emin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c<'7'&&c>='0')||c=='\b'||c=='.')){
                    e.consume();
                }
                if(c=='.'&&Process.contains(Emin.getText(),'.')){
                    e.consume();
                }
            }
        });
        Emin.setBounds(410,337,150,20);
        right.add(Emin);

        SEmin= new JTextField();
        SEmin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c<'9'&&c>='0')||c=='\b'||c=='.')){
                    e.consume();
                }
                if(c=='.'&&Process.contains(SEmin.getText(),'.')){
                    e.consume();
                }
            }
        });
        SEmin.setBounds(410,372,150,20);
        right.add(SEmin);

        submit = new JButton("Submit");
        submit.setBackground(new Color(255, 147, 88));
        submit.setBounds(450,402,100,30);
        submit.setFocusable(false);
        submit.addActionListener(this);
        submit.setFont(new Font("Segoe UI", Font.BOLD,16));
        right.add(submit);

        this.setVisible(true);
        this.setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==home){
            new AccountPage(this.user);
            this.dispose();
        }
        
        if(e.getSource()==submit){
            if(Bmax.getText().isBlank()||Smin.getText().isBlank()||Emin.getText().isBlank()||SEmin.getText().isBlank()){
                error.setVisible(true);
                error1.setVisible(false);
                return;
            }
            Object[] result = Process.optimize(Integer.parseInt(Bmax.getText()),Double.parseDouble(Emin.getText()),Double.parseDouble(Smin.getText()),Double.parseDouble(SEmin.getText()));
            if(!(boolean)result[10]){
                error.setVisible(false);
                error1.setVisible(true);
                return;
            }
            error.setVisible(false);
            error1.setVisible(false);
            user.insert(result,Double.parseDouble(Emin.getText()),Double.parseDouble(Smin.getText()),Double.parseDouble(SEmin.getText()));
            HashTable hash = HashTable.getTable();
            hash.delete(user.username);
            hash.insert(user);
            HashTable.setTable(hash);
            this.dispose();
            new ViewPage(user);
        }
    }
}
