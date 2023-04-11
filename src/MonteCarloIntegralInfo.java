import java.io.Serializable;

public class MonteCarloIntegralInfo implements Serializable {
    private int numPoints;
    private double leftBound;
    private double rightBound;

    public MonteCarloIntegralInfo(int numPoints, double leftBound,
                              double rightBound, byte[] classBytes)
    {
        this.numPoints = numPoints;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
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
