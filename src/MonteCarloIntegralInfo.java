import java.io.Serializable;

public class MonteCarloIntegralInfo implements Serializable {
    private int numPoints;
    private double leftBound;
    private double rightBound;

    public MonteCarloIntegralInfo(int numPoints, double leftBound, double rightBound)
    {
        this.numPoints = numPoints;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    int getNumPoints() {
        return numPoints;
    }

    double getLeftBound() {
        return leftBound;
    }

    double getRightBound() {
        return rightBound;
    }
}
