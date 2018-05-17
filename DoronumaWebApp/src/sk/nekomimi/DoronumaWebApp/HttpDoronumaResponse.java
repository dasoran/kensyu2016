package sk.nekomimi.DoronumaWebApp;

public class HttpDoronumaResponse {
	
	DoronumaPrintWriter pw = new DoronumaPrintWriter();
	
	public void setContentType(String contentType) {
		
	}
	
	public DoronumaPrintWriter getWriter() {
		return pw;
	}
	
	public String getString(){
		return pw.getString();
	}
}
