package use.com.jGrid.excption;

public class ExportXlsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExportXlsException() {
    	super();
    }
       
    public ExportXlsException(String message) {
    	super(message);
    }
    
    public ExportXlsException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public ExportXlsException(Throwable cause) {
        super(cause);
    }

}
