package nyp2025Proje_AtaBas;


@SuppressWarnings("serial")
public class IDException extends RuntimeException {
    
	public IDException(String message) {
        super(message);
    }
	
	public void printStackTrace() {
	    System.out.println("ID could not be found");
	}
	
}
