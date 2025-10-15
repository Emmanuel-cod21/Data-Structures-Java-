// RecursiveAssignment.java
public class RecursiveAssignment {

    // Part 1: Recursive multiplication using repeated addition
    public static int multiply(int x, int y) {
        // Base case: if y is 0, the result is 0
        if (y == 0) {
            return 0;
        }
        // Recursive case: x * y = x + (x * (y - 1))
        return x + multiply(x, y - 1);
    }

    // Part 2: Recursive power function (x raised to y)
    public static int power(int base, int exponent) {
        // Base case: anything raised to 0 is 1
        if (exponent == 0) {
            return 1;
        }
        // Recursive case: base^exponent = base * base^(exponent - 1)
        return base * power(base, exponent - 1);
    }

    // Demonstration
    public static void main(String[] args) {
        // Part 1 demo
        int x = 7, y = 4;
        int resultMultiply = multiply(x, y);
        System.out.println(x + " * " + y + " = " + resultMultiply);

        // Part 2 demo
        int base = 3, exponent = 4;
        int resultPower = power(base, exponent);
        System.out.println(base + " ^ " + exponent + " = " + resultPower);
    }
}
