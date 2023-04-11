import java.lang.reflect.Method;
import java.io.Serializable;

public class MonteCarloIntegral implements Serializable {
    private int numPoints;
    private double leftBound;
    private double rightBound;
    private Method method;
    private Object function;

    public double calculateFunction(double x) throws Exception {
        double result = (double)method.invoke(function, x);
        return result;
    }

    public MonteCarloIntegral(int numPoints, double leftBound,
                              double rightBound, Method method,
                              Object function)
    {
        this.numPoints = numPoints;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.method = method;
        this.function = function;
    }

    int getNumPoints() {
        return numPoints;
    }

    double getLeftBound() {
        return numPoints;
    }

    double getRightBound() {
        return numPoints;
    }
}
