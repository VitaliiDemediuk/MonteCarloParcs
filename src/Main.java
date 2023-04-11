import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import parcs.*;

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Integration.jar");
        MonteCarloIntegral integral = fromFile(curtask.findFile("input"), "Function");

        AMInfo info = new AMInfo(curtask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("Integration");

        c.write(integral);

        System.out.println("Waiting for result...");
        System.out.println("Result: " + c.readDouble());
        curtask.end();
    }

    public static MonteCarloIntegral fromFile(String infoFile, String functorClassName) throws Exception {        
        Scanner sc = new Scanner(new File(infoFile));

        double leftBound = sc.nextDouble();
        double rightBound = sc.nextDouble();
        int numPoints = sc.nextInt();

        String currentPath = new java.io.File("..").getCanonicalPath();
        byte[] classBytes = Files.readAllBytes(Path.of(currentPath + "/function/Function.class"));

        return new MonteCarloIntegral(numPoints, leftBound, rightBound, classBytes);
    }
}
