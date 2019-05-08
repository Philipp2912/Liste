import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private List list;
    private TextField moneyField;
    private boolean del=false;

    public static void main(String[] args) {
        new Graphics();
    }

    private Graphics() {
        FileHandler filehandler = new FileHandler();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(2800, 1500);
        this.setResizable(true);
        list = new List();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        JButton button6 = new JButton();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1) {
                    //add
                    list.addProduct();
                    update();

                }
                if (e.getSource() == button2) {
                    //remove
                    if (list.size >= 1) {
                        list.remove(getNumber("e"));
                        update();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Keine Artikel vorhanden!", "Warnung", JOptionPane.ERROR_MESSAGE);

                    }

                }
                if (e.getSource() == button3) {
                    //setSold
                    int numbEr = getNumber("v");
                    list.setVerkauft(numbEr, !list.verkauft[numbEr - 1]);
                    if (del)
                    {
                        del=false;
                        list.remove(list.size);
                    }
                    update();
                }
                if (e.getSource() == button4) {
                    //save
                    filehandler.SaveFile(list.größe, list.farbe, list.preis, list.verkauft);
                }
                if (e.getSource() == button5) {
                    //load

                    filehandler.getFile();
                    if (filehandler.load) {
                        try {
                            list.loadArrays(filehandler.size());
                            list.loadSizes(filehandler.getArray(list.size));
                            list.loadColour((filehandler.getArray(list.size)));
                            list.loadPrice((filehandler.getArray(list.size)));
                            list.loadSold((filehandler.getArray(list.size)));
                            filehandler.exitLoading();
                            update();
                        }
                        catch (Exception e1)
                        {
                            JOptionPane.showMessageDialog(new JFrame(), "Datei konnte nicht geladen werden", "Warnung", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                if (e.getSource() == button6) {
                    //print
                    DefaultTableModel printTableModel=new DefaultTableModel();
                    JTable printTable=new JTable(printTableModel);
                    printTableModel.addColumn("Nr.");
                    printTableModel.addColumn("Größe");
                    printTableModel.addColumn("Farbe");
                    printTableModel.addColumn("Preis");
                    if (JOptionPane.showConfirmDialog(new JFrame(),"Sollen die Verkauftangaben mitgedruckt werden=",null, 0)==1)
                    {
                        for (int i=0; i<list.size; i++)
                            printTableModel.addRow(new Object[]{i + 1, list.größe[i], list.farbe[i], list.preis[i]});

                    }
                    else
                    {
                        printTableModel.addColumn("Verkauft");
                        for (int i=0; i<list.size; i++)
                            printTableModel.addRow(new Object[]{i + 1, list.größe[i], list.farbe[i], list.preis[i], (list.verkauft[i]?"Ja":"nein")});
                    }

                    try {
                        boolean complete = printTable.print();
                    } catch (Exception ignored) {
                    }
                }
            }
        };
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button1.setText("Wahre hinzufügen");
        button2.setText("Wahre entfernen");
        button3.setText("zu Verkauft hinzufügen/entfernen");
        button4.setText("Speichern");
        button5.setText("Laden");
        button6.setText("Drucken");
        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.NORTH);
        panel.setBounds(0, 0, this.getWidth(), 300);
        button1.setBounds(25, 25, 400, 100);
        button2.setBounds(475, 25, 400, 100);
        button3.setBounds(925, 25, 400, 100);
        button4.setBounds(1375, 25, 400, 100);
        button5.setBounds(1825, 25, 400, 100);
        button6.setBounds(2275, 25, 400, 100);

        panel.add(button1, BorderLayout.NORTH);
        panel.add(button2, BorderLayout.NORTH);
        panel.add(button3, BorderLayout.NORTH);
        panel.add(button4, BorderLayout.NORTH);
        panel.add(button5, BorderLayout.NORTH);
        panel.add(button6, BorderLayout.NORTH);
        moneyField = new TextField();
        moneyField.setSize(200, 50);
        JPanel informationPanel = new JPanel();
        informationPanel.add(moneyField, BorderLayout.EAST);
        this.add(informationPanel, BorderLayout.EAST);
        moneyField.setText("Einnahmen:" + einnahmen());
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Nr.");
        tableModel.addColumn("Groeße");
        tableModel.addColumn("Farbe");
        tableModel.addColumn("Preis");
        tableModel.addColumn("Verkauft");
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        JPanel printPanel=new JPanel();
        printPanel.setSize(595,842);

    }

    void update() {
        while (table.getRowCount() > 0)
            tableModel.removeRow(0);
        for (int i = 0; i < list.size; i++)
            try {
                tableModel.addRow(new Object[]{i + 1, list.größe[i], list.farbe[i], list.preis[i], (list.verkauft[i]?"Ja":"nein")});
            } catch (Exception ignored) {
            }
        moneyField.setText("Einnahmen:" + einnahmen());
    }

    int getNumber(String action) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(JOptionPane.showInputDialog((action.equals("e") ? "Geb die zu entfernende Nummer ein" : "Gib die Nummer des verkauften Atrikels ein")));
                if (number <= list.size && number >= 0) {
                    break;
                }
                if (number < 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Die Zahl ist zu klein!", "Warnung", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Die Zahl ist zu groß", "Warnung", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception e) {
                list.addProduct("");
                del=true;
                number=list.size;
                break;
            }
        }
        return number;
    }

    String einnahmen() {
        float einN = 00.00f;
        for (int i = 0; i < list.size; i++) {
            try {
                einN = (list.verkauft[i] ? einN + list.getPreis(i) : einN);
            } catch (Exception ignored) {

            }
        }
        String einn = String.valueOf(einN);
        String returneinnahmen = "";
        for (int i = 0; i < einn.length(); i++) {
            if (einn.charAt(i) == '.') {
                returneinnahmen = returneinnahmen + ",";
            } else if (einn.charAt(i) != 'f') {
                returneinnahmen = returneinnahmen + einn.charAt(i);
            }
        }
        if (returneinnahmen.equals("0,0"))
            returneinnahmen = "00,00";
        String test="";
        int pos=0;
        while (returneinnahmen.charAt(pos)!=',')
            pos++;
        pos++;
        try {
            while (true) {
                test=test+returneinnahmen.charAt(pos);
                pos++;
            }
        }
        catch (Exception ignored)
        {

        }
        returneinnahmen=(test.length()==1?returneinnahmen+"0":returneinnahmen);
        returneinnahmen = returneinnahmen + "€";
        return returneinnahmen;
    }
}