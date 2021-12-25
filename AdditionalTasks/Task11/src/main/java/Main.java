import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        System.out.println("Original: ");
        System.out.println(message);
        try {
            OutputStream os = new FileOutputStream("message.txt");
            OutputStream bos = new BufferedOutputStream(os);
            Encryptor eos = new Encryptor(bos);
            for(int i = 0; i < message.length(); i++) {
                eos.write(message.charAt(i));
            }
            eos.close();
            InputStream is = new FileInputStream("message.txt");
            InputStream bis = new BufferedInputStream(is);
            Decryptor dis = new Decryptor(bis);
            scanner.close();
            System.out.println("Decrypted message: ");
            while(true) {
                int ch = dis.read();
                if(ch == -1) {
                    dis.close();
                    break;
                }
                else {
                    System.out.print((char)ch);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

