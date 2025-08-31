import org.supremus.nforth.NForth;

public class App {
    public static void main(String[] args) throws Exception {
        NForth forth = new NForth();
        forth.run(": HITCH 42 + ; 666 HITCH");
        //forth.dump();        
        System.out.println("Result is "+ forth.retVal());
    }
}
