import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

abstract class DerivativeCalculatorBase {
    public abstract double[] calculateDerivative(double[] coefficients);
}

public class DerivativeCalculator extends DerivativeCalculatorBase {

    @Override
    public double[] calculateDerivative(double[] coefficients) {
        int n = coefficients.length;
        double[] derivativeCoefficients = new double[n - 1];

        Stack<Double> stack = new Stack<>();

        for (int i = n - 1; i > 0; i--) {
            double derivativeTerm = coefficients[i] * i;
            derivativeCoefficients[i - 1] = derivativeTerm;

            // Push the derivative term onto the stack
            stack.push(derivativeTerm);
        }

        // Test for an empty stack before attempting to peek or pop
        if (!stack.isEmpty()) {
            // Print the top element of the stack (for demonstration purposes)
            System.out.println(stack.peek());

            // Pop an element from the stack
            double poppedElement = stack.pop();
        } else {
            System.out.println("The Derivative Stack is empty. Cannot peek or pop.");
        }

        
        System.out.println(isStackFull(stack));

        return derivativeCoefficients;
    }

    // Method to check if the stack is full
    private boolean isStackFull(Stack<?> stack) {
        try {
            stack.push(null); 
            stack.pop(); 
            return false; 
        } catch (Exception e) {
            return true; 
        }
    }

    static class SimpleDerivativeCalculator extends DerivativeCalculatorBase {
        @Override
        public double[] calculateDerivative(double[] coefficients) {
            int n = coefficients.length;
            double[] derivativeCoefficients = new double[n - 1];

            for (int i = n - 1; i > 0; i--) {
                derivativeCoefficients[i - 1] = coefficients[i] * i;
            }

            return derivativeCoefficients;
        }

        @Override
        public String toString() {
            return "Simple Derivative Calculator";
        }
    }

    class AdvancedDerivativeCalculator extends DerivativeCalculatorBase {

        @Override
        public double[] calculateDerivative(double[] coefficients) {
            int n = coefficients.length;
            double[] derivativeCoefficients = new double[n - 1];
    
    
            for (int i = 0; i < n - 1; i++) {
                derivativeCoefficients[i] = (coefficients[i + 1] - coefficients[i]) / 1.0;
            }
    
            return derivativeCoefficients;
        }
    
        @Override
        public String toString() {
            return "Advanced Derivative Calculator";
        }
    }


    public double[] readCoefficientsFromFile(String fileName) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line = raf.readLine();
            String[] terms = line.split("\\s+"); // Split by spaces
            double[] coefficients = new double[terms.length];

            for (int i = 0; i < terms.length; i++) {
                coefficients[i] = parseCoefficient(terms[i]);
            }

            return coefficients;
        }
    }

    private static double parseCoefficient(String term) {
        if (term.isEmpty()) {
            return 0.0;
        }

        if (term.endsWith("x")) {
            return Double.parseDouble(term.substring(0, term.length() - 1));
        } else if (term.endsWith("x^2")) {
            return Double.parseDouble(term.substring(0, term.length() - 3));
        } else {
            try {
                return Double.parseDouble(term);
            } catch (NumberFormatException e) {
                if (term.startsWith("-")) {
                    return Double.parseDouble(term.substring(1));
                } else {
                    System.err.println("Invalid coefficient: " + term);
                    return 0.0;
                }
            }
        }
    }

    public String getPolynomialString(double[] coefficients) {
        StringBuilder polynomialString = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            polynomialString.append(coefficients[i]);
            if (i < coefficients.length - 1) {
                polynomialString.append("x^").append(coefficients.length - i - 1).append(" + ");
            }
        }
        return polynomialString.toString();
    }
}
