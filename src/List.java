import javax.swing.*;

public class List {
    String[] farbe;
    String[] farbeCopy;
    String[] größe;
    String[] größeCopy;
    String[] preis;
    String[] preisCopy;
    String handler;
    boolean[] verkauft;
    boolean[] verkauftCopy;
    boolean boolenHandler;
    int size = 0;

    public void addProduct(String t) {
        if (size == 0) {
            size = 1;
            größe = new String[1];
            farbe = new String[1];
            preis = new String[1];
            verkauft = new boolean[1];
            remove(1);
        } else {
            preisCopy = new String[size];
            größeCopy = new String[size];
            farbeCopy = new String[size];
            verkauftCopy = new boolean[size];
            try {
                for (int i = 0; i < size; i++) {
                    preisCopy[i] = handler = preis[i];
                    größeCopy[i] = handler = größe[i];
                    farbeCopy[i] = handler = farbe[i];
                    verkauftCopy[i] = boolenHandler = verkauft[i];
                }
                size++;
                preis = new String[size];
                größe = new String[size];
                farbe = new String[size];
                verkauft = new boolean[size];
                for (int i = 0; i < size - 1; i++) {
                    preis[i] = handler = preisCopy[i];
                    farbe[i] = handler = farbeCopy[i];
                    größe[i] = handler = größeCopy[i];
                    verkauft[i] = boolenHandler = verkauftCopy[i];
                }
            } catch (Exception e) {
                size = 1;
                größe = new String[1];
                farbe = new String[1];
                preis = new String[1];
                verkauft = new boolean[1];
            }
        }
    }

    public void addProduct() {
        if (size == 0) {
            size = 1;
            größe = new String[1];
            farbe = new String[1];
            preis = new String[1];
            verkauft = new boolean[1];
            boolean stop = false;
            while (true) {
                try {
                    größe[0] = JOptionPane.showInputDialog("Gib die Größe ein:");
                    float test = Float.parseFloat(sizeCompiler(größe[0]));
                    break;
                } catch (Exception e) {
                    if (größe[0] == null) {
                        stop = true;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Eingaben sollten die Form \"XX,XX\" haben", "Warnung", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (!stop) {
                farbe[0] = JOptionPane.showInputDialog("Gib die Farbe ein:");
                if (farbe[0] == null)
                    stop = true;
                if (!stop)
                    while (true) {
                        try {
                            preis[0] = JOptionPane.showInputDialog("Gib den Preis ein:");
                            float testFloat = Float.parseFloat(priceCompiler(preis[0]));
                            break;
                        } catch (Exception e) {
                            if (preis[0] != null)
                                JOptionPane.showMessageDialog(new JFrame(), "Eingaben sollten die Form \"XX,XX€\" haben", "Warnung", JOptionPane.ERROR_MESSAGE);
                            else {
                                stop = true;
                                break;
                            }
                        }
                    }
                verkauft[0] = false;
            }
            if (stop == true)
                remove(1);
        } else {
            //inizialisierung mit übergabe
            preisCopy = new String[size];
            größeCopy = new String[size];
            farbeCopy = new String[size];
            verkauftCopy = new boolean[size];
            try {
                for (int i = 0; i < size; i++) {
                    preisCopy[i] = handler = preis[i];
                    größeCopy[i] = handler = größe[i];
                    farbeCopy[i] = handler = farbe[i];
                    verkauftCopy[i] = boolenHandler = verkauft[i];
                }
                size++;
                preis = new String[size];
                größe = new String[size];
                farbe = new String[size];
                verkauft = new boolean[size];
                for (int i = 0; i < size - 1; i++) {
                    preis[i] = handler = preisCopy[i];
                    farbe[i] = handler = farbeCopy[i];
                    größe[i] = handler = größeCopy[i];
                    verkauft[i] = boolenHandler = verkauftCopy[i];
                }
            } catch (Exception e) {
                size = 1;
                größe = new String[1];
                farbe = new String[1];
                preis = new String[1];
                verkauft = new boolean[1];
            }
            //hinzufügen des neuen Produkts
            boolean stop = false;
            while (true) {
                try {
                    größe[size - 1] = JOptionPane.showInputDialog("Gib die Größe ein:");
                    float test = Float.parseFloat(sizeCompiler(größe[size - 1]));
                    break;
                } catch (Exception e) {
                    if (größe[size - 1] == null) {
                        stop = true;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Eingaben sollten die Form \"XX,XX\" haben", "Warnung", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (stop == false) {
                farbe[size - 1] = JOptionPane.showInputDialog("Gib die Farbe ein:");
                if (farbe[size - 1] == null)
                    stop = true;
                if (!stop)
                    while (true) {

                        try {
                            preis[size - 1] = JOptionPane.showInputDialog("Gib den Preis ein:");
                            Float.parseFloat(priceCompiler(preis[size - 1]));
                            break;
                        } catch (Exception e) {
                            if (preis[size - 1] == null) {
                                stop = true;
                                break;
                            }
                            JOptionPane.showMessageDialog(new JFrame(), "Eingaben sollten die Form \"XX,XX€\" haben", "Warnung", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                verkauft[size - 1] = false;
                if (stop == false) {
                    sort();
                }
            }
            if (stop == true)
                remove(size);
        }
    }

    void sort() {
        int pos = size - 1;
        if (pos > 1) {
            pos = size - 1;
            while (Float.parseFloat(sizeCompiler(größe[pos])) < Float.parseFloat(sizeCompiler(größe[pos - 1])) && pos >= 2) {
                handler = größe[pos];
                größe[pos] = größe[pos - 1];
                größe[pos - 1] = handler;
                handler = farbe[pos];
                farbe[pos] = farbe[pos - 1];
                farbe[pos - 1] = handler;
                handler = preis[pos];
                preis[pos] = preis[pos - 1];
                preis[pos - 1] = handler;
                boolenHandler = verkauft[pos];
                verkauft[pos] = verkauft[pos - 1];
                verkauft[pos - 1] = boolenHandler;
                pos--;
            }
            if (Float.parseFloat(sizeCompiler(größe[1])) < Float.parseFloat(sizeCompiler(größe[0]))) {
                handler = größe[1];
                größe[1] = größe[0];
                größe[0] = handler;
                handler = farbe[1];
                farbe[pos] = farbe[0];
                farbe[0] = handler;
                handler = preis[1];
                preis[1] = preis[0];
                preis[0] = handler;
                boolenHandler = verkauft[1];
                verkauft[1] = verkauft[0];
                verkauft[0] = boolenHandler;
            }

        } else {
            if (Float.parseFloat(sizeCompiler(größe[1])) < Float.parseFloat(sizeCompiler(größe[0]))) {
                handler = größe[1];
                größe[1] = größe[0];
                größe[0] = handler;
                handler = preis[1];
                preis[1] = preis[0];
                preis[0] = handler;
                handler = farbe[1];
                farbe[1] = farbe[0];
                farbe[0] = handler;
                boolenHandler = verkauft[1];
                verkauft[1] = verkauft[0];
                verkauft[0] = boolenHandler;

            }
        }

    }

    String sizeCompiler(String test) {
        String compile = "";
        for (int i = 0; i < test.length(); i++)
            compile = (test.charAt(i) == ',' ? compile + '.' : compile + test.charAt(i));
        compile = compile + 'f';
        return compile;
    }

    String priceCompiler(String preis) {
        String compile = "";
        for (int i = 0; i < preis.length(); i++)
            if (preis.charAt(i) == ',') {
                compile = compile + ".";
            } else if (preis.charAt(i) != '€') {
                compile = compile + preis.charAt(i);
            }
        return compile;
    }

    void remove(int number) {
        number--;
        if (size <= 1) {
            größe = new String[0];
            preis = new String[0];
            farbe = new String[0];
            verkauft = new boolean[0];
            size = 0;
        } else {
            größeCopy = new String[size - 1];
            preisCopy = new String[size - 1];
            farbeCopy = new String[size - 1];
            verkauftCopy = new boolean[size - 1];
            for (int i = 0; i < number; i++) {
                größeCopy[i] = größe[i];
                preisCopy[i] = preis[i];
                farbeCopy[i] = farbe[i];
                verkauftCopy[i] = verkauft[i];
            }
            for (int i = number; i < size - 1; i++) {
                größeCopy[i] = größe[i + 1];
                preisCopy[i] = preis[i + 1];
                farbeCopy[i] = farbe[i + 1];
                verkauftCopy[i] = verkauft[i + 1];
            }
            size--;
            größe = new String[size];
            größe = größeCopy;
            farbe = new String[size];
            farbe = farbeCopy;
            preis = new String[size];
            preis = preisCopy;
            verkauft = new boolean[size];
            verkauft = verkauftCopy;
        }
    }
    void loadArrays(int size)
    {
        this.size=size;
        größe=new String[size];
        farbe=new String[size];
        preis=new String[size];
        verkauft=new boolean[size];

    }
    void loadSizes(String [] value)
    {
        for (int i=0;i<size;i++)
        {
            größe[i]=handler=value[i];
        }
    }
    void loadColour(String [] value)
    {
        for (int i=0;i<size;i++)
        {
            farbe[i]=handler=value[i];
        }
    }
    void loadPrice(String [] value)
    {
        for (int i=0;i<size;i++)
        {
            preis[i]=handler=value[i];
        }
    }
    void loadSold(String [] value)
    {
        for (int i=0;i<size;i++)
        {
            verkauft[i]=(value[i].equals("true")?true:false);
        }
    }

    void setVerkauft(int nummer, boolean action) {
        verkauft[nummer - 1] = action;
    }

    public float getPreis(int nummer) {
        return Float.parseFloat(priceCompiler(preis[nummer]));
    }
}