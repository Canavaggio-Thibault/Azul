package azul;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ThreadPrintStreamTest implements Runnable{

    @Override
    public void run() {
        try {
            ThreadPrintStream.createDir();
            String repo = "Files/";
            String name = repo + Thread.currentThread().getName();
            FileOutputStream fos = new FileOutputStream(name + ".txt");

            PrintStream stream = new PrintStream(new BufferedOutputStream(fos));

            ((ThreadPrintStream)System.out).setThreadOut(stream);

            System.out.println(name + ": first message");
            System.out.println("This is the second message from " + name);
            System.out.println(name + ": 3rd message");

            System.out.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void runnableTest(){
        ThreadPrintStream.replaceSystemOut();

        // Créez et démarrez 10 fils différents. Chaque thread
        // créera son propre PrintStream et l'installera dans le ThreadPrintStream.
        // le ThreadPrintStream, puis écrira trois messages
        // à System.out.
        for (int i = 0;  i < 10;  i++) {
            Thread thread = new Thread(new ThreadPrintStreamTest());
            thread.start();

        }
    }
}
