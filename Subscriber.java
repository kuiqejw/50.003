package socialmedia;



/**
 * @author ongajong
 * @version 1.0
 * @created 13-Feb-2018 12:39:19 PM
 */
public abstract class Subscriber implements Observer{

	public Subject subject;
	public Post m_Post;

	public Subscriber(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param Post
	 */
	public String comment(Post Post){
		return "";
	}

	/**
	 * 
	 * @param Post
	 */
	public void createNew(Post Post){

	}

	/**
	 * 
	 * @param Post
	 */
	public void deleteOld(Post Post){

	}

	public void update(){

	}

}