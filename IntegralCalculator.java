import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;

public class Polynomial {
    private final double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients.clone(); 
    }

    public double[] getCoefficients() {
        return coefficients.clone(); // Cloning to prevent external modification
    }

    public UnivariateFunction getPolynomialFunction() {
        return new PolynomialFunctionNewtonForm(coefficients)::value;
    }

    public Polynomial calculateIntegral() {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        double[] integralCoefficients = new double[coefficients.length + 1];

        for (int i = 0; i < coefficients.length; i++) {
            integralCoefficients[i] = integrator.integrate(i, getPolynomialFunction());
        }

        return new Polynomial(integralCoefficients);
    }

    @Override
    public String toString() {
        return new PolynomialFunction(coefficients).toString();
    }

    public static Polynomial readFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] coefficientsAsString = line.split("\\s+");

            double[] coefficients = new double[coefficientsAsString.length];
            for (int i = 0; i < coefficientsAsString.length; i++) {
                coefficients[i] = Double.parseDouble(coefficientsAsString[i]);
            }

            return new Polynomial(coefficients);
        }
    }
}

public class IntegralCalculator {

    public static void main(String[] args) {
        try {
            Polynomial polynomial = Polynomial.readFromFile("coefficients.txt");

            System.out.println("Input Polynomial: " + polynomial);
            
            Polynomial integralPolynomial = polynomial.calculateIntegral();
            System.out.println("Integral Polynomial: " + integralPolynomial);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
