package azul;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

/** Un ThreadPrintStream remplace le System.out normal et assure
 * que la sortie vers System.out va vers un PrintStream différent pour
 * chaque thread.  Pour ce faire, il utilise ThreadLocal pour maintenir un  PrintStream pour chaque thread.
 * PrintStream pour chaque thread. */
public class ThreadPrintStream extends PrintStream {

    public static void createDir(){

        String dir = "./Files";

        File file = new File(dir);

        file.mkdirs();
    }
    public static void replaceSystemOut() {

        // Sauvegarde System.out existante
        PrintStream console = System.out;

        // Crée un ThreadPrintStream et l'installe comme un System.out
        ThreadPrintStream threadOut = new ThreadPrintStream();
        System.setOut(threadOut);

        // Utilise le System.out original comme le thread courant System.out
        threadOut.setThreadOut(console);
    }

    /** Stockage spécifique aux threads pour contenir un PrintStream pour chaque thread. */
    private final ThreadLocal<PrintStream> out;

    private ThreadPrintStream() {
        super(new ByteArrayOutputStream(0));
        out = new ThreadLocal<>();
    }

    /** Définit le PrintStream pour le thread en cours d'exécution. */
    public void setThreadOut(PrintStream out) {
        this.out.set(out);
    }

    /** Renvoie le PrintStream pour le thread en cours d'exécution. */
    public PrintStream getThreadOut() {
        return this.out.get();
    }

    @Override public boolean checkError() {
        return getThreadOut().checkError();
    }

    @Override public void write(byte[] buf, int off, int len) {
        getThreadOut().write(buf, off, len);
    }

    @Override public void write(int b) { getThreadOut().write(b); }

    @Override public void flush() { getThreadOut().flush(); }
    @Override public void close() { getThreadOut().close(); }
}
