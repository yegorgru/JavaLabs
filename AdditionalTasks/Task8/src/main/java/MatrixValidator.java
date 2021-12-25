import java.util.List;

public class MatrixValidator {
    public boolean isDominant(TridiagonalMatrix matrix){
        List<Double> a = matrix.getA();
        List<Double> b = matrix.getB();
        List<Double> c = matrix.getC();
        a.forEach(d -> Math.abs(d));
        b.forEach(d -> Math.abs(d));
        c.forEach(d -> Math.abs(d));

        if(c.get(0) < b.get(0) || c.get(c.size() - 1) < a.get(a.size() - 1)){
            return false;
        }
        for (int i = 1; i < c.size() - 2; i++){
            if(c.get(i) < a.get(i - 1) + b.get(i + 1)){
                return false;
            }
        }
        return true;
    }
}