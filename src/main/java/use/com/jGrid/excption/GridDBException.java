package use.com.jGrid.excption;

public class GridDBException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GridDBException() {
    	super();
    }
       
    public GridDBException(String message) {
    	super(message);
    }
    
    public GridDBException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public GridDBException(Throwable cause) {
        super(cause);
    }
}
