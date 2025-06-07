package nyp2025Proje_AtaBas;

@SuppressWarnings("serial")
public class DuplicateInfoException extends RuntimeException{


	public DuplicateInfoException(String message) {
        super(message);
    }
    
    
    public void printStackTrace() {
        System.out.println("Same ID Error");
    }
	
}
