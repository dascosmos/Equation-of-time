package Presentacion;

import Logica.Exporter;
import Logica.Mongo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Dgenerar extends JFrame {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTable table;
    private DefaultTableModel df;
    private JRadioButton rdbtnDecimal;
    private JRadioButton rdbtnHorasMinSeg;
    private Grafica graphic;
    private Mongo mongo;
    private double []dec;

    public Dgenerar(final EcTiempo frame) {
        setResizable(true);
        setTitle("Datos por a\u00f1o");
        setBounds(100, 100, 1080, 768);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblEscribaElAo = new JLabel("Año:");
        lblEscribaElAo.setBounds(24, 48, 100, 14);
        contentPanel.add(lblEscribaElAo);


        textField = new JTextField();
        textField.setBounds(114, 45, 96, 20);
        contentPanel.add(textField);
        textField.setColumns(10);

        Vector ciudades = new Vector();
        frame.cargar.cargarDatos();

        for (int i = 0; i< frame.cargar.getTamanio();i++){
            ciudades.add(frame.cargar.getCiudades(i));
        }

        JLabel loc = new JLabel("Localizacion");
        loc.setBounds(24, 88, 120, 20);
        contentPanel.add(loc);
        JComboBox location = new JComboBox(ciudades);
        location.setBounds(114, 88, 170, 20);
        contentPanel.add(location);

        JLabel date = new JLabel("hora (HH:MM:SS)");
        date.setBounds(550, 88, 170, 20);
        contentPanel.add(date);

        JTextField textHora = new JTextField();
        textHora.setBounds(675, 88, 20, 20);
        contentPanel.add(textHora);

        JLabel doscolons = new JLabel(":");
        doscolons.setBounds(697, 88, 20, 20);
        contentPanel.add(doscolons);

        JTextField textMin = new JTextField();
        textMin.setBounds(719, 88, 20, 20);
        contentPanel.add(textMin);

        JLabel doscolons2 = new JLabel(":");
        doscolons2.setBounds(741, 88, 20, 20);
        contentPanel.add(doscolons2);

        JTextField textSeg = new JTextField();
        textSeg.setBounds(763, 88, 20, 20);
        contentPanel.add(textSeg);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 150, 900, 500);
        contentPanel.add(scrollPane);

        Object[] titles = {"Fecha", "Hora sideral", "Ascensi\u00f3n recta",
                "Declinaci\u00f3n", "Ecuaci\u00f3n del tiempo", "Azimuth", "Altura" };

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

        JRadioButton escogerCiudad = new JRadioButton("Ciudades");
        escogerCiudad.setSelected(true);
        JRadioButton escogerLong = new JRadioButton("Longitud/Latitud");

        ButtonGroup locacion = new ButtonGroup();
        locacion.add(escogerCiudad);
        locacion.add(escogerLong);

        JPanel escogerLocacion = new JPanel();
        escogerLocacion.setBounds(800,20, 300, 50);
        escogerLocacion.setBorder(BorderFactory.createTitledBorder("Localizacion"));
        contentPanel.add(escogerLocacion);

        escogerLocacion.add(escogerCiudad);
        escogerLocacion.add(escogerLong);

        JTextField inputLongitud = new JTextField();
        inputLongitud.setBounds(114, 88, 170, 20);
        contentPanel.add(inputLongitud);

        JLabel labelLatitud = new JLabel("Latitud");
        labelLatitud.setBounds(290, 88, 170, 20);
        contentPanel.add(labelLatitud);
        labelLatitud.setVisible(false);

        JTextField inputLatitud = new JTextField();
        inputLatitud.setBounds(350, 88, 170, 20);
        contentPanel.add(inputLatitud);

        inputLongitud.setVisible(false);
        inputLatitud.setVisible(false);

        mongo = new Mongo();
        mongo.createConnection();
        mongo.createCollection();

        escogerCiudad.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(escogerCiudad.isSelected()){
                    location.setVisible(true);
                    inputLatitud.setVisible(false);
                    inputLongitud.setVisible(false);
                    loc.setText("Localizacion");
                    labelLatitud.setVisible(false);
                }
            }
        });

        escogerLong.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(escogerLong.isSelected()){
                    location.setVisible(false);
                    inputLatitud.setVisible(true);
                    inputLongitud.setVisible(true);
                    loc.setText("Longitud");
                    labelLatitud.setVisible(true);
                }
            }
        });


        JButton btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(arg0 -> {

            try {
                limpiarTabla();

                Object[] info = new Object[7];
                double anio = Double.parseDouble(textField.getText());
                double longitud = 0;
                double latitud = 0;
                double elevacion = 0;
                double hora = Double.parseDouble(textHora.getText());
                double min = Double.parseDouble(textMin.getText());
                double seg = Double.parseDouble(textSeg.getText());
                String year = "";
                if(escogerCiudad.isSelected()) {
                     longitud = frame.cargar.getLongitud(location.getSelectedIndex());
                     latitud = frame.cargar.getLatitud(location.getSelectedIndex());
                     year = String.valueOf(anio)+"_1"+location.getSelectedItem()+hora+min+seg;
                }else if (escogerLong.isSelected()){
                    longitud = Double.parseDouble(inputLongitud.getText());
                    latitud = Double.parseDouble(inputLatitud.getText());
                    year = String.valueOf(anio)+" "+longitud+" "+latitud+hora+min+seg;
                }else{
                    JOptionPane.showMessageDialog(null,"Escoja una de las dos opciones");
                }

                try {
                    if (elevacion == 0) {
                        elevacion = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la elevacion de la ciudad"));
                    }else{

                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Ingrese un campo numerico");
                }
                if (rdbtnHorasMinSeg.isSelected()) {
                    
                    if(mongo.validateDocument(year)) {
                        mongo.createDocument(year, info);
                        for (int i = 12; i >= 1; i--) {
                            switch (i) {
                                case 1:

                                    for (int j = 31; j >= 1; j--) {
                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);
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
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 2:

                                    if ((anio % 4 == 0 && anio % 100 != 0)
                                            || anio % 400 == 0) {
                                        for (int j = 29; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, hora, min, seg);
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
                                            info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                            info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    } else {
                                        for (int j = 28; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, hora, min,seg);
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
                                            info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                            info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    }
                                    break;

                                case 3:
                                    for (int j = 31; j >= 1; j--) {
                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 4:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 5:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 6:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 7:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 8:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 9:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 10:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 11:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 12:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj
                                                .conversionHora(frame.obj.hGA());
                                        info[2] = frame.obj
                                                .conversionHora(frame.obj
                                                        .GSunRA());
                                        info[3] = frame.obj
                                                .conversionGrado(frame.obj
                                                        .GSundec());
                                        info[4] = frame.obj.Conversioneq(frame.obj.eqt());
                                        info[5] = frame.obj.conversionHora(frame.obj.topoAzimuthAngle(elevacion,latitud,longitud));
                                        info[6] = frame.obj.conversionHora(frame.obj.topoElevationAngle(elevacion,latitud,longitud));

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
                        Object[] obj = new Object[7];
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
                            obj[0] = token2.nextToken().replaceAll("\"","");
                            obj[1] = token2.nextToken().replaceAll("\"","");
                            obj[2] = token2.nextToken().replaceAll("\"","");
                            obj[3] = token2.nextToken().replaceAll("\"","");
                            obj[4] = token2.nextToken().replaceAll("\"","");
                            obj[5] = token2.nextToken().replaceAll("\"","");
                            obj[6] = token2.nextToken().replaceAll("\"","");
                            df.insertRow(0,obj);
                        }

                    }
                }//fin if radiobutton
                else if (rdbtnDecimal.isSelected()) {
                    
                    if(mongo.validateDocument(year)) {
                        mongo.createDocument(year, info);
                        for (int i = 12; i >= 1; i--) {
                            switch (i) {
                                case 1:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;
                                case 2:

                                    if ((anio % 4 == 0 && anio % 100 != 0)
                                            || anio % 400 == 0) {

                                        for (int j = 29; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, hora, min, seg);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj.hGA();
                                            info[2] = frame.obj.GSunRA();
                                            info[3] = frame.obj.GSundec();
                                            info[4] = -frame.obj.eqt();
                                            info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                            info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    } else {

                                        for (int j = 28; j >= 1; j--) {

                                            frame.obj.recibirDatos(j, i, anio, hora, min, seg);
                                            info[0] = frame.obj.getFecha();
                                            info[1] = frame.obj.hGA();
                                            info[2] = frame.obj.GSunRA();
                                            info[3] = frame.obj.GSundec();
                                            info[4] = -frame.obj.eqt();
                                            info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                            info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                            df.insertRow(0, info);
                                            mongo.actualizeDocument(year, info);
                                        }

                                    }
                                    break;

                                case 3:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 4:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 5:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 6:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 7:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 8:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 9:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 10:
                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 11:
                                    for (int j = 30; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
                                        df.insertRow(0, info);
                                        mongo.actualizeDocument(year, info);
                                    }
                                    break;

                                case 12:

                                    for (int j = 31; j >= 1; j--) {

                                        frame.obj.recibirDatos(j, i, anio, hora, min, seg);                                        info[0] = frame.obj.getFecha();
                                        info[1] = frame.obj.hGA();
                                        info[2] = frame.obj.GSunRA();
                                        info[3] = frame.obj.GSundec();
                                        info[4] = -frame.obj.eqt();
                                        info[5] = frame.obj.topoAzimuthAngle(elevacion,latitud,longitud);
                                        info[6] = frame.obj.topoElevationAngle(elevacion,latitud,longitud);
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
                        Object[] obj = new Object[7];
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
                            obj[4] = Double.parseDouble(token2.nextToken());
                            obj[5] = Double.parseDouble(token2.nextToken());
                            obj[6] = Double.parseDouble(token2.nextToken());
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
        btnExportarAExcel.addActionListener(arg0 -> {
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
                    JOptionPane.showMessageDialog(null,
                            "Error, No se exportaron los datos");
                }
            }
        });
        btnExportarAExcel.setBounds(362, 675, 140, 23);
        contentPanel.add(btnExportarAExcel);


        JButton btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(arg0 -> {
            try{
                graphic=new Grafica(frame);
                graphic.getAc(getAc());
                graphic.getDec(getDec());
                graphic.getEq(getEq());
                graphic.getAZ(getAZ());
                graphic.getAL(getAl());
                graphic.setVisible(true);

            }catch(ClassCastException e){
                JOptionPane.showMessageDialog(null, "Escoja la opci\u00f3n 'decimal' y vuelva a intentarlo");
                graphic.setVisible(false);
            }

        });
        btnGraficar.setBounds(159, 675, 109, 23);
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

    private double[] getAZ(){
        dec=new double[df.getRowCount()];
        for(int i=0;i<dec.length;i++){
            dec[i]=(double) df.getValueAt(i, 5);
        }
        return dec;
    }

    private double[] getAl(){
        dec=new double[df.getRowCount()];
        for(int i=0;i<dec.length;i++){
            dec[i]=(double) df.getValueAt(i, 6);
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
