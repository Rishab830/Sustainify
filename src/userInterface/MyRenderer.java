package userInterface;

import hashTable.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.Serial;

public class MyRenderer extends DefaultTableCellRenderer {

    Node user;
    MyRenderer(Node user){
        this.user = user;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if(col==0){
            c.setBackground(new Color(0xFFFFFF));
            return c;
        }
        if(user.Xopt[col-1][row]==1){
            c.setBackground(new Color(191, 216, 128));
        } else {
            c.setBackground(new Color(0xFFFFFF));
        }
        return c;
    }
}
