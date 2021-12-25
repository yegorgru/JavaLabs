import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Decryptor extends FilterInputStream
{
    protected Decryptor(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int i = super.read();
        if(i == -1) {
            return -1;
        }
        else {
            return (char)(i - 50 < 0 ? 256 + i - 50 : i - 50);
        }
    }
}