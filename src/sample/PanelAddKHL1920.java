package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.*;

public class PanelAddKHL1920 extends JPanel{

    public PanelAddKHL1920(int width, int height) {
        this.setLayout(new GridLayout(1, 3, 5, 5));

        final TextArea textArea1 = new TextArea();
        this.add(textArea1);

        final TextArea textArea2 = new TextArea();
        this.add(textArea2);

        JButton buttonAdd = new JButton("Добавить!");
        this.add(buttonAdd);

        /*buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Match m = new Match(textArea1.getText(), textArea2.getText(), "KHL");

                int tarrs = 0;
            }
        });*/

    }
}
