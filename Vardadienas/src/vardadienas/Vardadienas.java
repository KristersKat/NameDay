/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vardadienas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kkatkevics
 */
public class Vardadienas {

    String[] vardi = new String[5095];
    String[] datumi = new String[5095];
    int n = 5095;
    ArrayList<String> binaraRez = new ArrayList<String>();

    public void noFaila() {

        try {
            Scanner ievads = new Scanner(new File("vardi.txt"));
            for (int i = 0; i < 5095; i++) {
                String rinda = ievads.next();
                String[] tmp = rinda.split(";");
                vardi[i] = tmp[0];

                if (tmp[2].length() == 1) {
                    datumi[i] = '0' + tmp[2];
                } else {
                    datumi[i] = tmp[2];
                }

                if (tmp[1].length() == 1) {
                    datumi[i] = datumi[i] + " " + '0' + tmp[1];
                } else {
                    datumi[i] = datumi[i] + " " + tmp[1];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vardadienas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String linearSearch(String x) {
        boolean found = false;
        int i = 0;
        while (i < 5095 && !found) {
            if (vardi[i].equals(x)) {
                return datumi[i];
            }
            i++;
        }
        return "nav atrasts";
    }

    public ArrayList<String> binarySearch(String datums) {
        binaraRez.clear();
        boolean found = false;
        int s = 0, b = n - 1, v;
        do {
            v = (s + b) / 2;
            if (datumi[v].equals(datums)) {
                found = true;
            } else if (datumi[v].compareTo(datums) > 0) {
                b = v - 1;
            } else {
                s = v + 1;
            }
        } while (s <= b && !found);
        if (found) {
            for (int i = v; i >= 1 && datumi[i].equals(datums); i--) {
                binaraRez.add(vardi[i]);
            }
            while (v+1<n && datumi[v + 1].equals(datums)) {
                v++;
                binaraRez.add(vardi[v]);
            }
        } else {
            binaraRez.add("nav atrasts");
        }
        Collections.sort(binaraRez);
        return binaraRez;
    }

    public static void main(String[] args) {
        Vardadienas vd = new Vardadienas();
        vd.noFaila();
        System.out.println(vd.linearSearch("Vilibalds"));
        System.out.println(vd.binarySearch("02 29"));
    }

}
