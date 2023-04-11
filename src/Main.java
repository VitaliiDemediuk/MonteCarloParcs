import java.util.Scanner;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;

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
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://" + currentPath + "/function/")});
        Class<?> clazz = classLoader.loadClass(functorClassName);

        Object function = clazz.newInstance();

        return new MonteCarloIntegral(numPoints, leftBound, rightBound, "evaluate", function);
    }
}
