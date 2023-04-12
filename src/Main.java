import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Method;

import parcs.*;

public class Main {

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Integration.jar");
        ArrayList<MonteCarloIntegralInfo> infos = fromFile(curtask.findFile("input"));
        AMInfo info = new AMInfo(curtask, null);

        LinkedList<channel> channels = new LinkedList<>();
        for (MonteCarloIntegralInfo integInfo : infos) {
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("Integration");
            c.write(integInfo);
            channels.add(c);
        }

        System.out.println("Waiting for result...");
        double sum = 0;        
        for (var c : channels) {
            sum += c.readDouble();
        }
        
        System.out.println("Result: " + sum);
        curtask.end();
    }

    public static ArrayList<MonteCarloIntegralInfo> fromFile(String infoFile) throws Exception {        
        Scanner sc = new Scanner(new File(infoFile));

        int POINT_COUNT = sc.nextInt();
        double leftBound = sc.nextDouble();
        double rightBound = sc.nextDouble();
        int numPoints = sc.nextInt();

        double intervalSize = (rightBound - leftBound) / POINT_COUNT;

        ArrayList<MonteCarloIntegralInfo> res = new ArrayList<MonteCarloIntegralInfo>();

        for (int i = 0; i < POINT_COUNT; ++i) {
            if (i + 1 < POINT_COUNT) {
                res.add(new MonteCarloIntegralInfo(numPoints / POINT_COUNT, leftBound + intervalSize * i, leftBound + intervalSize * (i + 1)));
            } else {
                res.add(new MonteCarloIntegralInfo(numPoints / POINT_COUNT + numPoints % POINT_COUNT, leftBound + intervalSize * i, leftBound + intervalSize * (i + 1)));
            }
        }

        return res;
    }
}
