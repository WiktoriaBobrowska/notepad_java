import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Notepad {
    private JFrame frame = new JFrame("Notepad");
    private JTextArea textArea = new JTextArea();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem itemSave = new JMenuItem("Save");
    private JMenuItem itemOpen = new JMenuItem("Open");

    private void setItemsToMenu(){
        menu.add(itemSave);
        menu.add(itemOpen);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        itemOpen.addActionListener(this::actionPerformed);
        itemSave.addActionListener(this::actionPerformed);
    }
    private void setFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(textArea);
    }
    private void setTextArea(){
        textArea.setSize(550,600);
        textArea.setVisible(true);
        textArea.setLocation(30, 60);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 15));
    }

    private void actionPerformed(ActionEvent e){
            String s = e.getActionCommand();

            JFileChooser F = new JFileChooser("f:");
            int response = F.showOpenDialog(null);
            if (response != JFileChooser.APPROVE_OPTION) return;
            File file = new File(F.getSelectedFile().getAbsolutePath());

            switch (s) {
                case "Save":
                    try {
                        BufferedWriter BW = new BufferedWriter(new FileWriter(file, false));
                        BW.write(textArea.getText());
                        BW.flush();
                        BW.close();
                    } catch (Exception exception) {
                        System.out.println("Błąd!");
                    }
                    break;
                case "Open":
                    try {
                        String line, nextline;
                        BufferedReader BR = new BufferedReader(new FileReader(file));
                        line = BR.readLine();
                        while ((nextline = BR.readLine()) != null) {
                            line = line + "\n" + nextline;
                        }
                        textArea.setText(line);
                    } catch (Exception exception) {
                        System.out.println("Błąd!");
                    }
                    break;

        }
        
    }
    public Notepad(){
        setItemsToMenu();
        setFrame();
        setTextArea();
    }
}
