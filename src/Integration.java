import java.util.Random;
import java.lang.reflect.Method;
import parcs.*;

public class Integration implements AM {

    public void run(AMInfo info) {
        MonteCarloIntegral integral = (MonteCarloIntegral)info.parent.readObject();

        int numPoints = integral.getNumPoints();
        double leftBound = integral.getLeftBound();
        double rightBound = integral.getRightBound();

        Class<?> classFunctor = null;
        Method evaluateMethod = null;
        Object functor = null;
        try {
            classFunctor = integral.getLoadedFunction();
            evaluateMethod = classFunctor.getDeclaredMethod("evaluate", double.class);
            functor = classFunctor.newInstance();
        } catch (Exception e) {                
            System.out.println("Exception caught during functor instantiation.");
            System.out.println(e);
            info.parent.write(Double.NaN);
            return;
        }

        Random rand = new Random();

        double sum = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = leftBound + rand.nextDouble() * (rightBound - leftBound);

            try {
                double y = (double)evaluateMethod.invoke(functor, x);;
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
