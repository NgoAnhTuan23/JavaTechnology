public class Program {
    public static void main(String[] args) {
        String operators = "+ - x / ^";
        if (args.length != 3) {
            System.out.println("Invalid expression!");
        } else if (!operators.contains(args[1])) {
            System.out.println("Unsupported operator!");
        } else {
            Double a = Double.parseDouble(args[0]);
            Double b = Double.parseDouble(args[2]);

            switch (args[1]) {
                case "+":
                    System.out.println(a+b);
                    break;
                case "-":
                    System.out.println(a-b);
                    break;
                case "x":
                    System.out.println(a*b);
                    break;
                case "/":
                    if (b == 0) System.out.println("Cannot divide by zero!");
                    else System.out.println(a/b);
                    break;
                case "^":
                    System.out.println(Math.pow(a,b));
                    break;
            }
        }
    }
}