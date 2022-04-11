package com.progressoft.tools;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadFiles {
    ArrayList < String > myRows = new ArrayList < > ();
    ArrayList < BigDecimal > selectedCol = new ArrayList < > ();
    int index = 0;

    public static int findIndex(String[] arr, String colName) {
        ArrayList < String > titles = new ArrayList < > ();

        titles.addAll(Arrays.asList(arr));

        if (!titles.contains(colName)) {
            throw new IllegalArgumentException("column " + colName + " not found");
        }

        return titles.indexOf(colName);
    }

    public ArrayList < String > getRows() {
        return this.myRows;
    }

    public ArrayList < BigDecimal > getCol() {
        return this.selectedCol;
    }

    public int getIndex() {
        return this.index;
    }

    public void read(Path filePath, String colName) throws IOException {

        String[] splitedRows;
        boolean isTitle = true;
        BufferedReader reader;

        try {
            File file = new File(filePath.toString());
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = reader.readLine();

            while (line != null) {

                splitedRows = line.split("[,]");
                this.myRows.add(line);

                if (isTitle) {

                    this.index = findIndex(splitedRows, colName);
                    line = reader.readLine();
                    isTitle = false;
                    continue;
                }
                this.selectedCol.add(BigDecimal.valueOf(Integer.parseInt(splitedRows[index])));
                line = reader.readLine();

            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("source file not found", exception);
        }
    }

}