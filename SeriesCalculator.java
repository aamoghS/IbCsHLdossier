import java.io.IOException;
import java.io.RandomAccessFile;

public class SeriesCalculator {

    public double[] readCoefficientsFromFile(String fileName) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line = raf.readLine();
            String[] coefficientStrings = line.split(",");
            double[] coefficients = new double[coefficientStrings.length];

            for (int i = 0; i < coefficientStrings.length; i++) {
                coefficients[i] = Double.parseDouble(coefficientStrings[i]);
            }

            return coefficients;
        }
    }

    public double calculateSeriesSum(double[] coefficients, double x) {
    double seriesSum = calculateSeriesSumHelper(coefficients, x, 0);

    // Check for divergence
    if (Double.isInfinite(seriesSum) || Double.isNaN(seriesSum)) {
        return 0;  
    } else {
        return seriesSum;
    }
}


    private double calculateSeriesSumHelper(double[] coefficients, double x, int index) {
        if (index == coefficients.length) {
            return 0;
        } else {
            return coefficients[index] * Math.pow(x, index) + calculateSeriesSumHelper(coefficients, x, index + 1);
        }
    }

    
}