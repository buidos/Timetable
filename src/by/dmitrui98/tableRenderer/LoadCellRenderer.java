package by.dmitrui98.tableRenderer;

import by.dmitrui98.data.Pair;
import by.dmitrui98.data.Teacher;
import by.dmitrui98.data.WorkingTeacher;
import by.dmitrui98.enums.TypeHour;
import by.dmitrui98.gui.LoadPopupMenu;
import by.dmitrui98.utils.TableFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Администратор on 25.11.2016.
 */
public class LoadCellRenderer extends DefaultTableCellRenderer {
    TableFactory tb;


    public LoadCellRenderer(TableFactory tb) {
        this.tb = tb;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(Color.WHITE);

        int teacherCol;
        if (tb.getTeacherCol() != -1)
            teacherCol = tb.getTeacherCol() / 2;
        else
            teacherCol = -1;

        int groupCol = Integer.parseInt((String) table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue());

        if (value instanceof WorkingTeacher) {
            WorkingTeacher wt = (WorkingTeacher) value;
            if (wt.getPair(tb.defineRow(row), groupCol).getTypeHour() == TypeHour.COMBO)
                c.setBackground(Color.RED);
            else
                c.setBackground(Color.YELLOW);
        } else if (value instanceof ArrayList) {
            c.setBackground(Color.RED);
        } else if (teacherCol == column) {
            c.setBackground(Color.GREEN);

            Teacher selectedTeacher = tb.getSelectedTeacher();
            if (selectedTeacher != null) {
                for (WorkingTeacher wt : tb.getWorkingTeachers())
                    if (wt.getNames().equals(selectedTeacher.getNames())) {

                        boolean fl = false;
                        for (Pair p : wt.getPairs())
                            if (p.getRow() == tb.defineRow(row))
                                if ((p.getTypeHour() == TypeHour.COMBO) || fl) {
                                    c.setBackground(Color.RED);
                                    break;
                                }
                                else {
                                    c.setBackground(Color.YELLOW);
                                    fl = true;
                                }
                    }
            }
        }




        return c;
    }
}