import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Encryptor extends FilterOutputStream
{
    public Encryptor(OutputStream o) {
        super(o);
    }

    public void write(int w) throws IOException {
        w = (w + 50) % 256;
        super.write(w);
    }
}