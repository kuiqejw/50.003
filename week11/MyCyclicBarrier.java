package Week11;

//this class must be thread-safe. why?
public class MyCyclicBarrier {
	private int count = 0; 
	private Runnable torun;
        private int waiting = 0;
	
	public MyCyclicBarrier (int count, Runnable torun) {
		this.count = count;
		this.torun = torun;
	}

	public MyCyclicBarrier (int count) {
		this.count = count;
	}
	
	//complete the implementation below.
	//hint: use wait(), notifyAll()
	public void await () throws InterruptedException{
            synchronized (this){
                waiting++;
                try{
                    if ( waiting == count){
                        this.notifyAll();
                        torun.run();
                    } else{
                        this.wait();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
           
	}
}
