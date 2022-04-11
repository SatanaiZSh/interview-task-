package com.progressoft.tools;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Write {

    public void write(ArrayList < String > rows, ArrayList < BigDecimal > col, ScoringSummaryImp s, Path destPath, String newColName, String equation, int index) throws IOException {

        ArrayList < String > modifiedRow = new ArrayList < > ();
        final String lineSep = System.getProperty("line.separator");
        String lineToWrite, newCell = null;
        String[] splitedRow;
        MathContext mc = new MathContext(7);
        boolean isTitle = true;

        BufferedWriter writer;

        try {
            File file = new File(destPath.toString());
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            for (int i = 0; i <= col.size(); i++) {

                if (isTitle) {

                    newCell = newColName + "_" + equation;
                    isTitle = false;

                } else if (equation.equals("mm")) {

                    newCell = (col.get(i - 1).subtract(s.min())).divide(s.max().subtract(s.min()), mc).setScale(2, RoundingMode.HALF_EVEN).toString();

                } else if (equation.equals("z")) {

                    newCell = (col.get(i - 1).subtract(s.mean())).divide(s.standardDeviation(), mc).setScale(2, RoundingMode.HALF_EVEN).toString();

                }

                splitedRow = rows.get(i).split("[,]");

                modifiedRow.addAll(Arrays.asList(splitedRow));

                modifiedRow.add(index + 1, newCell);

                lineToWrite = String.join(",", modifiedRow);

                modifiedRow.clear();

                writer.write(lineToWrite + lineSep);

            }

            writer.close();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }
}