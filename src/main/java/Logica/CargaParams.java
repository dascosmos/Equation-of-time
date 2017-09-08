package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by david on 1/07/2017.
 */
public class CargaParams {


    public double L0(double jme) {
        ArrayList<Double> params = new ArrayList();
        double l0 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("L0.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                l0 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(l0);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;

    }

    public double L1(double jme) {
        ArrayList<Double> params = new ArrayList();
        double l1 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("L1.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                l1 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(l1);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }
        return suma;

    }

    public double L2(double jme) {
        ArrayList<Double> params = new ArrayList();
        double l2 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("L2.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                l2 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(l2);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double L3(double jme) {
        ArrayList<Double> params = new ArrayList();
        double l3 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("L3.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                l3 = Double.parseDouble(token.nextToken()) * Math.cos(Double.parseDouble(token.nextToken()) + Double.parseDouble(token.nextToken()) * jme);
                params.add(l3);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double L4(double jme) {
        ArrayList<Double> params = new ArrayList();
        double l3 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("L4.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                l3 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(l3);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double L5(double jme){

        return 1*Math.cos(3.14+0*jme);
    }

    public double B0(double jme) {
        ArrayList<Double> params = new ArrayList();
        double b0 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("B0.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                b0 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(b0);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double B1(double jme) {
        ArrayList<Double> params = new ArrayList();
        double b1 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("B1.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                b1 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(b1);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double R0(double jme) {
        ArrayList<Double> params = new ArrayList();
        double r0 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("R0.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                r0 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(r0);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double R1(double jme) {
        ArrayList<Double> params = new ArrayList();
        double r1 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("R1.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                r1 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(r1);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException a) {
            // TODO Auto-generated catch block
            a.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double R2(double jme) {
        ArrayList<Double> params = new ArrayList();
        double r2 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("R2.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                r2 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(r2);

            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }


    public double R3(double jme) {
        ArrayList<Double> params = new ArrayList();
        double r3 =0;
        double suma=0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("R3.txt"));


            String line = "";
            while ((line = lector.readLine()) != null) {

                StringTokenizer token = new StringTokenizer(line, ";");
                r3 = Double.parseDouble(token.nextToken())*Math.cos(Double.parseDouble(token.nextToken())+Double.parseDouble(token.nextToken())*jme);
                params.add(r3);
            }
            lector.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Double param : params) {
            suma+=param;
        }

        return suma;
    }

    public double R4(double jme){
        System.out.println(4*Math.cos(2.56+6283.08*jme));
        return 1*Math.cos(3.14+0*jme);
    }




}
