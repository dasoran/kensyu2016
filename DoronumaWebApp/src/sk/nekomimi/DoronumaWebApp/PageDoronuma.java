package sk.nekomimi.DoronumaWebApp;

public class PageDoronuma {

	protected void doGet(HttpDoronumaRequest req, HttpDoronumaResponse res) {
		res.setContentType("text/html; charset=UTF-8"); 
		DoronumaPrintWriter out = res.getWriter();
	    out.println("Hello, World");

	    out.close();
	}
}
