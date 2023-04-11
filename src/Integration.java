import java.util.Random;
import parcs.*;

public class Integration implements AM {

    public void run(AMInfo info) {
        MonteCarloIntegral integral = (MonteCarloIntegral)info.parent.readObject();

        int numPoints = integral.getNumPoints();
        double leftBound = integral.getLeftBound();
        double rightBound = integral.getRightBound();

        Random rand = new Random();
                
        double sum = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = leftBound + rand.nextDouble() * (rightBound - leftBound);

            try {
                double y = integral.calculateFunction(x);
                sum += y;
            } catch (Exception e) {                
                System.out.println("Exception caught during function calculation.");
                System.out.println(e);
                info.parent.write(Double.NaN);
                return;
            }            
        }
        
        double avg = sum / numPoints;
        double res = avg * (rightBound - leftBound);

        info.parent.write(res);
    }
}
