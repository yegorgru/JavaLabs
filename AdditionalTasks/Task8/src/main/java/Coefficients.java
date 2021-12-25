public class Coefficients {
    private Double[] alpha;
    private Double[] beta;

    public Coefficients(Double[] alpha, Double[] beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    public Double[] getAlpha() {
        return alpha;
    }

    public Double[] getBeta() {
        return beta;
    }
}