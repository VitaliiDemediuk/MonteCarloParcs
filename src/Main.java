import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Method;

import parcs.*;

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Integration.jar");
        MonteCarloIntegralInfo integral = fromFile(curtask.findFile("input"));

        AMInfo info = new AMInfo(curtask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("Integration");

        c.write(integral);

        System.out.println("Waiting for result...");
        System.out.println("Result: " + c.readDouble());
        curtask.end();
    }

    public static MonteCarloIntegralInfo fromFile(String infoFile) throws Exception {        
        Scanner sc = new Scanner(new File(infoFile));

        double leftBound = sc.nextDouble();
        double rightBound = sc.nextDouble();
        int numPoints = sc.nextInt();

        String currentPath = new java.io.File("..").getCanonicalPath();
        Path p = Path.of(currentPath + "/function/Function.class");
        byte[] classBytes = Files.readAllBytes(p);

        MonteCarloIntegralInfo integral = new MonteCarloIntegralInfo(numPoints, leftBound, rightBound, classBytes);

        return integral;
    }
}
