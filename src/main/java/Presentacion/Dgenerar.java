package Presentacion;

import Logica.Exporter;
import Logica.Mongo;
import org.math.plot.utils.Array;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Dgenerar extends JFrame {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTable table;
    private DefaultTableModel df;
    private JRadioButton rdbtnDecimal;
    private JRadioButton rdbtnHorasMinSeg;
    Grafica graphic;
    private Mongo mongo;
    private double []dec;

    public Dgenerar(final EcTiempo frame) {
        setResizable(false);
        setTitle("Datos por a\u00f1o");
        setBounds(100, 100, 716, 538);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblEscribaElAo = new JLabel("Escriba el a\u00f1o:");
            lblEscribaElAo.setBounds(24, 48, 100, 14);
            contentPanel.add(lblEscribaElAo);
        }

        textField = new JTextField();
        textField.setBounds(114, 45, 96, 20);
        contentPanel.add(textField);
        textField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 105, 620, 302);
        contentPanel.add(scrollPane);

        Object[] titles = {"Fecha", "Hora sideral", "Ascensi\u00f3n recta",
                "Declinaci\u00f3n", "Ecuaci\u00f3n del tiempo" };

        df = new DefaultTableModel(titles, 0);
        table = new JTable(df);
        scrollPane.setViewportView(table);

        rdbtnDecimal = new JRadioButton("Decimal");
        rdbtnDecimal.setBounds(360, 44, 74, 23);
        contentPanel.add(rdbtnDecimal);

        rdbtnHorasMinSeg = new JRadioButton("Horas, min, seg");
        rdbtnHorasMinSeg.setBounds(440, 44, 126, 23);
        contentPanel.add(rdbtnHorasMinSeg);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbtnDecimal);
        grupo.add(rdbtnHorasMinSeg);
        mongo = new Mongo();
        mongo.createConnection();
        mongo.createCollection();

        JButton btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(arg0 -> {

            try {
                limpiarTabla();

                Object[] info = new Object[5];
                double anio = Double.parseDouble(textField.getText());

                if (rdbtnHorasMinSeg.isSelected()) {
                    String year = String.valueOf(anio)+"_1";
                    if(mongo.validateDocument(year)) {
                        mongo.createDocument(year, info);
                        for (int i = 12; i >= 1; i--) {
                            switch (i) {
                                case 1:

                                    for (int j = 31; j >= 1; j--) {
                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 2:

                                    if ((anio % 4 == 0 && anio % 100 != 0)
                                            || anio % 400 == 0) {
                                        for (int j = 29; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, 0, 0,
                                                    0);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj
                                                    .conversionHora(frame.obj.hGA());
                                            info[2] = frame.obj
                                                    .conversionHora(frame.obj
                                                            .GSunRA());
                                            info[3] = frame.obj
                                                    .conversionGrado(frame.obj
                                                            .GSundec());
                                            info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    } else {
                                        for (int j = 28; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, 0, 0,
                                                    0);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj
                                                    .conversionHora(frame.obj.hGA());
                                            info[2] = frame.obj
                                                    .conversionHora(frame.obj
                                                            .GSunRA());
                                            info[3] = frame.obj
                                                    .conversionGrado(frame.obj
                                                            .GSundec());
                                            info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    }
                                    break;

                                case 3:
                                    for (int j = 31; j >= 1; j--) {
                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 4:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 5:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 6:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 7:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 8:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 9:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 10:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 11:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 12:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                            }

                        }
                    }else{
                        String auxList =mongo.fetchDocuments(year);
                        System.out.println(auxList);
                        ArrayList<String> lista = new ArrayList<>();
                        Object[] obj = new Object[5];
                        StringTokenizer token = new StringTokenizer(auxList,"][");
                        StringTokenizer token2;
                        while (token.hasMoreElements()) {
                            lista.add(token.nextToken());
                        }

                        lista.remove(0);

                        for (int i = 0; i<lista.size();i++){

                            if(lista.get(i).equals(" , ")) {
                                lista.remove(i);
                            }
                        }

                        for (int i = 0; i<lista.size();i++) {
                            token2 =new StringTokenizer(lista.get(i),",");
                            obj[0] = token2.nextToken();
                            obj[1] = token2.nextToken();
                            obj[2] = token2.nextToken();
                            obj[3] = token2.nextToken();
                            obj[4] = token2.nextToken();
                            df.insertRow(0,obj);
                        }

                    }
                }//fin if radiobutton
                else if (rdbtnDecimal.isSelected()) {
                    String year2 = String.valueOf(anio);
                    if(mongo.validateDocument(year2)) {
                        mongo.createDocument(year2, info);
                        for (int i = 12; i >= 1; i--) {
                            switch (i) {
                                case 1:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;
                                case 2:

                                    if ((anio % 4 == 0 && anio % 100 != 0)
                                            || anio % 400 == 0) {

                                        for (int j = 29; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, 0, 0,
                                                    0);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj.hGA();
                                            info[2] = frame.obj.GSunRA();
                                            info[3] = frame.obj.GSundec();
                                            info[4] = -frame.obj.eqt();
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year2, info);
                                        }

                                    } else {

                                        for (int j = 28; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, 0, 0,
                                                    0);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj.hGA();
                                            info[2] = frame.obj.GSunRA();
                                            info[3] = frame.obj.GSundec();
                                            info[4] = -frame.obj.eqt();
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year2, info);
                                        }

                                    }
                                    break;

                                case 3:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 4:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 5:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 6:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 7:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 8:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 9:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 10:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 11:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }
                                    break;

                                case 12:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, 0, 0, 0);
                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year2, info);
                                    }

                                    break;
                            }
                        }
                    }else{

                        String auxList =mongo.fetchDocuments(year2);
                        System.out.println(auxList);
                        ArrayList<String> lista = new ArrayList<>();
                        Object[] obj = new Object[5];
                        StringTokenizer token = new StringTokenizer(auxList,"][");
                        StringTokenizer token2;
                        while (token.hasMoreElements()) {
                            lista.add(token.nextToken());
                        }

                        lista.remove(0);

                        for (int i = 0; i<lista.size();i++){

                            if(lista.get(i).equals(" , ")) {
                                lista.remove(i);
                            }
                        }

                        for (int i = 0; i<lista.size();i++) {
                            token2 =new StringTokenizer(lista.get(i),",");
                            obj[0] = token2.nextToken();
                            obj[1] = Double.parseDouble(token2.nextToken());
                            obj[2] = Double.parseDouble(token2.nextToken());
                            obj[3] = Double.parseDouble(token2.nextToken());
                            obj[4] = -Double.parseDouble(token2.nextToken());
                            df.insertRow(0,obj);
                        }


                    }


                }//fin if radiobutton2
                else {
                    JOptionPane.showMessageDialog(null,
                            "No ha seleccionado ninguna opci\u00f3n");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un a\u00f1o");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        btnGenerar.setBounds(249, 44, 89, 23);
        contentPanel.add(btnGenerar);


        JButton btnExportarAExcel = new JButton("Exportar a Excel");
        btnExportarAExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "No hay datos para guardar");
                    return;
                }
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Archivos de Excel", "xls");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Guardar archivo");
                chooser.setMultiSelectionEnabled(false);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    List<JTable> tb = new ArrayList<>();
                    List<String> nom = new ArrayList<>();
                    tb.add(table);
                    nom.add("anio");
                    String file = chooser.getSelectedFile().toString()
                            .concat(".xls");
                    try {
                        Exporter a = new Exporter(new File(file), tb, nom);
                        if (a.export()) {
                            JOptionPane.showMessageDialog(null,
                                    "Los datos fueron exportados exitosamente");
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        JOptionPane.showMessageDialog(null,
                                "Error, No se exportaron los datos");
                    }
                }
            }
        });
        btnExportarAExcel.setBounds(362, 442, 140, 23);
        contentPanel.add(btnExportarAExcel);


        JButton btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    graphic=new Grafica(frame);
                    graphic.getAc(getAc());
                    graphic.getDec(getDec());
                    graphic.getEq(getEq());
                    graphic.setVisible(true);

                }catch(ClassCastException e){
                    JOptionPane.showMessageDialog(null, "Escoja la opci\u00f3n 'decimal' y vuelva a intentarlo");
                    graphic.setVisible(false);
                }

            }
        });
        btnGraficar.setBounds(159, 442, 109, 23);
        contentPanel.add(btnGraficar);
    }

    private void limpiarTabla() {
        int a = table.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            df.removeRow(i);
        }
    }

    private double[] getAc(){
        dec=new double[df.getRowCount()];
        for(int i=0;i<dec.length;i++){
            dec[i]=(double) df.getValueAt(i, 2);
        }
        return dec;
    }

    private double[] getDec(){
        dec=new double[df.getRowCount()];
        for(int i=0;i<dec.length;i++){
            dec[i]=(double) df.getValueAt(i, 3);
        }
        return dec;
    }

    private double[] getEq(){
        dec=new double[df.getRowCount()];
        for(int i=0;i<dec.length;i++){
            dec[i]=(double) df.getValueAt(i, 4);
        }
        return dec;
    }
}
