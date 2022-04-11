package com.progressoft.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class NormalizerImp implements Normalizer {

    @Override
    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize) throws IOException {

        try {
            return readAndWrite(csvPath, destPath, colToStandardize, "z");
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) throws IOException {

        try {
            return readAndWrite(csvPath, destPath, colToNormalize, "mm");
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    public ScoringSummaryImp readAndWrite(Path csvPath, Path destPath, String colName, String equation) throws IOException {
        ReadFiles readFile = new ReadFiles();

        readFile.read(csvPath, colName);

        Calculations calc = new Calculations(readFile.getCol());
        calc.executeAll();

        Write writeFile = new Write();
        writeFile.write(readFile.getRows(), readFile.getCol(), calc.getScore(), destPath, colName, equation, readFile.getIndex());

        return calc.getScore();

    }
}