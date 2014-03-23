package org.eslion.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistogramBuilder {

    static final String[][] data = {
            {"RUSH", "AQUA", "ACDC", "KORN", "ACDC", "ACDC", "BECK", "KMAG", "RATT", "JAYZ", "ACDC"},
            {"RATT", "DEVO", "AQUA", "KMAG", "CARS", "AQUA", "RATT", "MIMS", "PINK", "KMAG", "AQUA"},
            {"PINK", "CHER", "BECK", "JAYZ", "JAYZ", "CARS", "PINK", "TSOL", "MIMS", "KORN", "BECK"},
            {"MIMS", "ACDC", "CAKE", "FIXX", "KMAG", "FIXX", "MIMS", "ACDC", "KMAG", "MIMS", "CAKE"},
            {"KMAG", "CARS", "CARS", "DEVO", "KORN", "JAYZ", "KMAG", "CARS", "CHER", "PINK", "CARS"},
            {"TOOL", "EVE6", "CHER", "CHER", "MIMS", "KMAG", "CHER", "RUSH", "JAYZ", "RATT", "CHER"},
            {"JAYZ", "FIXX", "DEVO", "BECK", "PINK", "KORN", "JAYZ", "KORN", "KORN", "RUSH", "DEVO"},
            {"KORN", "BECK", "EVE6", "AQUA", "RATT", "MIMS", "KORN", "SEAL", "CARS", "TOOL", "EVE6"},
            {"CARS", "KMAG", "FIXX", "EVE6", "RUSH", "PINK", "CARS", "RATT", "EVE6", "ACDC", "FIXX"},
            {"SEAL", "MUSE", "JAYZ", "CARS", "SEAL", "RATT", "EVE6", "TOOL", "MUSE", "AQUA", "JAYZ"},
            {"TSOL", "JAYZ", "KMAG", "ACDC", "TOOL", "RUSH", "MUSE", "JAYZ", "ACDC", "CARS", "KMAG"},
            {"ACDC", "CAKE", "KORN", "CAKE", "TSOL", "SEAL", "ACDC", "PINK", "CAKE", "FIXX", "KORN"},
            {"UB40", "KORN", "UB40", "MIMS", "AQUA", "STYX", "CAKE", "UB40", "AQUA", "SEAL", "MIMS"},
            {"AQUA", "RATT", "RATT", "MUSE", "BECK", "TOOL", "AQUA", "AQUA", "DEVO", "STYX", "MUSE"},
            {"STYX", "SEAL", "STYX", "PINK", "FIXX", "TSOL", "DEVO", "STYX", "FIXX", "TSOL", "PINK"},
            {"FIXX", "PINK", "TSOL", "RATT", "SADE", "UB40", "FIXX", "FIXX", "BECK", "UB40", "RATT"},
            {"BECK", "MIMS", "PINK", "RUSH", "STYX", "BECK", "RUSH", "BECK", "RUSH", "BECK", "RUSH"},
            {"SADE", "RUSH", "SADE", "SADE", "UB40", "SADE", "SADE", "SADE", "SADE", "CAKE", "SADE"},
            {"DEVO", "STYX", "SEAL", "SEAL", "CAKE", "DEVO", "STYX", "DEVO", "WEEN", "CHER", "SEAL"},
            {"WEEN", "TSOL", "WEEN", "STYX", "CHER", "WEEN", "WEEN", "WEEN", "STYX", "DEVO", "STYX"},
            {"CAKE", "UB40", "MIMS", "TOOL", "DEVO", "CAKE", "UB40", "CAKE", "UB40", "EVE6", "TOOL"},
            {"MUSE", "SADE", "MUSE", "TSOL", "EVE6", "MUSE", "TSOL", "MUSE", "TSOL", "MUSE", "TSOL"},
            {"EVE6", "TOOL", "RUSH", "UB40", "MUSE", "EVE6", "SEAL", "EVE6", "SEAL", "SADE", "UB40"},
            {"CHER", "WEEN", "TOOL", "WEEN", "WEEN", "CHER", "TOOL", "CHER", "TOOL", "WEEN", "WEEN"}
    };
    // 0 - initial
    // 1 - quick sort
    // 2 - selection sort
    // 3 - ?
    // 4 - mergesort t-d
    // 5 - insertion
    // 6 - shuffle
    // 7 - original
    // 8 -




    public static void main(String[] args) {
        int rows = data.length;
        int cols = data[0].length;
        System.out.println("Rows = " + rows + "; Cols = " + cols);
        int columnToBuild = 9;
        List<String> dict = new ArrayList<String>();
        for (String[] row : data) {
            dict.add(row[columnToBuild]);
        }
        Collections.sort(dict);

        for (int i = 0, dataLength = data.length; i < dataLength; i++) {
            String[] row = data[i];
            System.out.println(i + ". " + dict.indexOf(row[columnToBuild]));
        }

    }
}
