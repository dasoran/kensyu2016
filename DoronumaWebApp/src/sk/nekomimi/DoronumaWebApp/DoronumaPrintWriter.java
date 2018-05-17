package sk.nekomimi.DoronumaWebApp;

public class DoronumaPrintWriter {

	private String output;
	
	public void println(String text) {
		output = text;
	}
	
	public void close() {
		
	}
	
	public String getString() {
		return output;
	}
}
