package mine;

public class MemeChecker {

	public boolean isMeme(String meme) throws ExceptionCreation {

		if (meme.equals("69")) {
			throw new ExceptionCreation("nice");
		}
		if (meme.equals("420")) {
			throw new ExceptionCreation("dank");
		}
		
		return true;
	}
}
