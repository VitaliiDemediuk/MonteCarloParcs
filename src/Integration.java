import java.util.Random;
import java.lang.reflect.Method;
import parcs.*;

public class Integration implements AM {

    private double f(double x) {
        return x * 2;
    }

    public void run(AMInfo info) {
        MonteCarloIntegralInfo integral = (MonteCarloIntegralInfo)info.parent.readObject();

        int numPoints = integral.getNumPoints();
        double leftBound = integral.getLeftBound();
        double rightBound = integral.getRightBound();

        Random rand = new Random();

        double sum = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = leftBound + rand.nextDouble() * (rightBound - leftBound);

            double y = f(x);
            sum += y;
        }
        
        double avg = sum / numPoints;
        double res = avg * (rightBound - leftBound);

        info.parent.write(res);
    }
}
