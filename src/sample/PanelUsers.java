package sample;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Arc2D;
import java.io.*;
import java.util.ArrayList;

public class PanelUsers extends JPanel{
    int WIDTH;
    int DEFWIDTH = 1600;
    double procWIDTH;
    int HEIGHT;
    int DEFHEIGHT = 1000;
    int graphicWidth = 1000;
    int graphicHeight = 410;
    double procHEIGHT;
    JScrollPane scrollPane;
    ArrayList<ArrayList<String>> usersList = null;
    JPanel tablePanelAU = null;

    public PanelUsers(final int width, final int height) {
        this.setLayout(null);
        WIDTH = width;
        HEIGHT = height;
        procWIDTH = WIDTH / (double) DEFWIDTH;
        procHEIGHT = HEIGHT / (double) DEFHEIGHT;
        if (procWIDTH == 1)
            this.graphicWidth = 1000;

        ////////////////////////////////////////////ПАНЕЛЬ
        int otstup = 10;
        final JLabel activeUsers = new JLabel("Все пользователи программы 'Hockey Betting Helper'.");
        activeUsers.setLocation(5, otstup);
        activeUsers.setFont(new Font("", 0, 20));
        activeUsers.setSize(new Dimension((int) (0.98 * WIDTH) - 30, 25));
        final Font font = new Font("Arial", Font.BOLD, 15);
        this.add(activeUsers);
        otstup += activeUsers.getSize().height + 10;

        usersList = Settings.getUsersList();

        String[] colHeadsAU = {"№" , "Пользователь", "Активен", "Серийный номер ЖД", "Окончание подписки", "Код приглашения", "Собств. реф. код"};
        final Object[][] dataAU = new Object[usersList.size()][colHeadsAU.length];
        for (int i=0; i< usersList.size(); i++){
            dataAU[i][0] = i+1;
            dataAU[i][1] = usersList.get(i).get(0);
            dataAU[i][2] = usersList.get(i).get(1);
            dataAU[i][3] = usersList.get(i).get(2);
            dataAU[i][4] = usersList.get(i).get(3);
            dataAU[i][5] = usersList.get(i).get(4);
            dataAU[i][6] = usersList.get(i).get(5);
        }

        final JTable tableAUsers = getTableAUsers(colHeadsAU, dataAU);

        tablePanelAU = new JPanel();
        tablePanelAU.setLayout(new BorderLayout());
        tablePanelAU.add(new JScrollPane(tableAUsers), BorderLayout.CENTER);
        tablePanelAU.add(tableAUsers.getTableHeader(), BorderLayout.NORTH);

        tablePanelAU.setSize(1328, (int) (HEIGHT*0.78));   //(usersList.size() + 2) * 25
        tablePanelAU.setLocation(5, otstup);
        otstup += (int) (HEIGHT*0.78) + 10;               //(usersList.size() + 2) * 25 + 10;
        this.add(tablePanelAU);

        final JButton buttonSaveChanges = new JButton("Сохранить изменения!");
        buttonSaveChanges.setFont(new Font("", 0, 20));
        buttonSaveChanges.setSize(1328, 40);
        buttonSaveChanges.setLocation(5, otstup);
        this.add(buttonSaveChanges);



        buttonSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("tmp/usersHBH.txt");
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                writer.print("");
                writer.close();

                FileWriter fr = null;
                BufferedWriter br = null;
                for (int i=0; i<dataAU.length; i++){
                    try {
                        //для обновления файла нужно инициализировать FileWriter с помощью этого конструктора
                        fr = new FileWriter(file,true);
                        br = new BufferedWriter(fr);
                        //теперь мы можем использовать метод write или метод append
                        if (i>0)
                            br.newLine();
                        if (i<dataAU.length-1){
                            br.write(tableAUsers.getValueAt(i,1) + "=" + tableAUsers.getValueAt(i,2) + "=" + tableAUsers.getValueAt(i,3) + "=" + tableAUsers.getValueAt(i,4) + "=" + tableAUsers.getValueAt(i,5) + "=" + tableAUsers.getValueAt(i,6));
                        } else {
                            boolean flag = !tableAUsers.getValueAt(i,1).equals("") && !tableAUsers.getValueAt(i,2).equals("") && !tableAUsers.getValueAt(i,6).equals("");
                            if (flag)
                                br.write(tableAUsers.getValueAt(i,1) + "=" + tableAUsers.getValueAt(i,2) + "=" + tableAUsers.getValueAt(i,3) + "=" + tableAUsers.getValueAt(i,4) + "=" + tableAUsers.getValueAt(i,5) + "=" + tableAUsers.getValueAt(i,6));
                        }

                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } finally{
                        try {
                            br.close();
                            fr.close();
                            FTPLoader.uploadFile(Settings.getIp(), Settings.getLogin(), Settings.getPassword(), "/data/usersHBH.txt", "tmp/usersHBH.txt");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                buttonSaveChanges.setText("Данные сохранены!");



                /*if (!tableAUsers.getValueAt(tableAUsers.getRowCount()-1,6).equals("")){

                }*/
            }
        });

        tableAUsers.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                buttonSaveChanges.setText("Сохранить изменения!");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

    }

    public JTable getTableAUsers(String[] colHeadsAU, Object[][] dataAU){
        TableModel model = new DefaultTableModel(dataAU, colHeadsAU) {
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                }  else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        final JTable tableAUsers = new JTable(model);
        tableAUsers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableAUsers.setEnabled(true);
        final Font font = new Font("Arial", Font.BOLD, 15);
        tableAUsers.getTableHeader().setFont(font);
        tableAUsers.setFont(font);
        tableAUsers.setRowHeight(25);
        tableAUsers.getColumnModel().getColumn(0).setPreferredWidth(60);
        tableAUsers.getColumnModel().getColumn(1).setPreferredWidth(250);
        tableAUsers.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableAUsers.getColumnModel().getColumn(3).setPreferredWidth(300);
        tableAUsers.getColumnModel().getColumn(4).setPreferredWidth(200);
        tableAUsers.getColumnModel().getColumn(5).setPreferredWidth(200);
        tableAUsers.getColumnModel().getColumn(6).setPreferredWidth(200);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int r=0; r<colHeadsAU.length; r++)
            tableAUsers.getColumnModel().getColumn(r).setCellRenderer(centerRenderer);

        RowSorter<TableModel> sorter = new TableRowSorter<>(model);
        tableAUsers.setRowSorter(sorter);

        return tableAUsers;

    }

}