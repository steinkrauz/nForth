import org.supremus.nforth.NForth;

public class App {
    public static void main(String[] args) throws Exception {
        NForth forth = new NForth();
        forth.run("40 2 /");
        System.out.println("Result is "+ forth.retVal());
    }
}
