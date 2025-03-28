package userInterface;

import hashTable.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ViewPage extends JFrame implements ActionListener {

    Node user;
    JPanel left, right;
    JButton home;
    JLabel icon;
    JTable table;
    JScrollPane scroll;
    DefaultCategoryDataset data;
    CategoryDataset dataset;
    JFreeChart chart;
    ChartPanel chartPanel;
    ViewPage(Node user){

        File file = new File("");
        ImageIcon favicon = new ImageIcon(file.getAbsolutePath()+"\\Images\\icon orange.png");
        ImageIcon img = new ImageIcon(file.getAbsolutePath()+"\\Images\\logo orange.png");
        ImageIcon homeIcon = new ImageIcon(file.getAbsolutePath()+"\\Images\\home.png");

        this.user = user;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(favicon.getImage());
        this.setTitle("Data View Page");
        this.setSize(1140,690);
        this.setLocation(200,100);

        left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(450,0));
        left.setBackground(new Color(255, 147, 88));
        this.add(left,BorderLayout.WEST);

        icon = new JLabel(img,JLabel.CENTER);
        icon.setOpaque(false);
        left.add(icon);

        right = new JPanel(null);
        right.setBackground(new Color(0xFFFFFF));
        right.setPreferredSize(new Dimension(675000,100000000));
        this.add(right);

        home = new JButton(homeIcon);
        home.addActionListener(this);
        home.setBackground(new Color(255, 147, 88));
        home.setFocusable(false);
        home.setMargin(new Insets(-2,0,0,0));
        home.setBounds(5,5,50,50);
        home.setOpaque(true);
        right.add(home);

        Object[] modeNo = {"Practices","Mode 1", "Mode 2", "Mode 3", "Mode 4", "Mode 5"};

        Object[][] modes = {{"Training on sustainable practices to employees: ","Yearly","Half yearly","Quarterly","Bimonthly","Monthly"},
                            {"Frequency of environmental practices audit: ","Yearly","Half yearly","Quarterly","Bimonthly","Monthly"},
                            {"Environmental management system: ","Nil","Green Manufacturing","Cleaner production","In campus ETP/STP plants","ISO 14000"},
                            {"Proportion of expenses from turn over to R&D activities: ","<=1%","1-5%","6-15%","16-30%",">=30%"},
                            {"Proportion of employees involved in R&D activities: ", "<=0%","1-5%","6-15%","16-30%","21-50%",">=50%"},
                            {"Quality/management practices: ","Six sigma","Product chart","EOQ","Zero defect","Eco friendly"}};

        DefaultTableModel tableModel = new DefaultTableModel(modes, modeNo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(modes,modeNo);
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setBackground(new Color(255, 147, 88));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,16));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        TableColumnModel col = table.getColumnModel();
        col.getColumn(0).setPreferredWidth(400);
        col.getColumn(1).setPreferredWidth(100);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(200);
        col.getColumn(4).setPreferredWidth(175);
        col.getColumn(5).setPreferredWidth(75);
        table.setRowHeight(25);
        table.setShowGrid(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setDefaultRenderer(table.getColumnClass(0),new MyRenderer(user));

        scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10,60,655,195);
        right.add(scroll,BorderLayout.EAST);

        this.setVisible(true);
        this.setResizable(false);

        data = new DefaultCategoryDataset();
        data.setValue(user.Eoptmin,"min","Emin");
        data.setValue(user.Emin*12,"mid","E");
        data.setValue(user.Eoptmax,"max","Emax");
        data.setValue(user.Soptmin,"min","Smin");
        data.setValue(user.Smin*12,"mid","S");
        data.setValue(user.Soptmax,"max","Smax");
        data.setValue(user.SEoptmin,"min","SEmin");
        data.setValue(user.SEmin*12,"mid","SE");
        data.setValue(user.SEoptmax,"max","SEmax");
        dataset = data;

        chart = ChartFactory.createBarChart("Comparison of expectations and suggestions",
                "",
                "points",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);
        BarRenderer bar = (BarRenderer) chart.getCategoryPlot().getRenderer();
        bar.setMaximumBarWidth(.9);
        bar.setItemMargin(-1.7);

        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setBounds(10,260,655,395);
        right.add(chartPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==home){
            new AccountPage(user);
            this.dispose();
        }
    }
}
