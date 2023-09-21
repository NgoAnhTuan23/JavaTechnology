// javac -cp ".;./lib.jar" Program.java  (compile)
// java -cp ".;./lib.jar" Program        (run)
import vn.tdtu.edu.*;
public class Program {
    public static void main(String[] args) {
        int[] a, b, c;
        a = new int[]{23, 1, 2003, 28, 11, 2002};
        b = new int[]{15,3,14,6,23,1};

        ArrayOutput.print(a);
        ArrayOutput.print(b);

        c = ArrayHandler.merge(a, b);

        ArrayOutput.print(c);
        ArrayHandler.sort(c);
        ArrayOutput.print(c);

        ArrayOutput.writeToFile(c, "output.txt");
    }
}