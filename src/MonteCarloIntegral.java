import java.lang.reflect.Method;
import java.io.Serializable;

public class MonteCarloIntegral implements Serializable {
    private int numPoints;
    private double leftBound;
    private double rightBound;
    private byte[] classBytes;

    public Class<?> getLoadedFunction() throws Exception {
        return new ClassLoader() {
            public Class<?> defineClass(String name, byte[] bytes) {
                return defineClass(name, bytes, 0, bytes.length);
            }
        }.defineClass("Function", classBytes);
    }

    public MonteCarloIntegral(int numPoints, double leftBound,
                              double rightBound, byte[] classBytes)
    {
        this.numPoints = numPoints;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.classBytes = classBytes;
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
