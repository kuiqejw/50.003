package problemset6;

public class MyStackThreadSafe {

    private final int maxSize;
    private long[] stackArray; //guarded by "this"
    private int top; //invariant: top < stackArray.length && top >= -1; guarded by "this"	

    //pre-condition: s > 0
    //post-condition: maxSize == s && top == -1 && stackArray != null
    public MyStackThreadSafe(int s) { //Do we need "synchronized" for the constructor?
        //need synchronized here in line with lock in policy
        if (s < 0) {
        }
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }
    //@requires: pre-condition top < stackArray.length()-1 (myStack is not full)
    //@ensures: post-condition top++, top < stackArray.length()

    // use lock, condition, and wait
    public synchronized void push(long j) {
        while (isFull()) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        stackArray[++top] = j;
        // notify other threads that top and stackArray property have changed
        notifyAll();
    }
    //pre-condition: top >= 0
    //post-condition: the top element is removed
    //use lock, contain, wait
    public synchronized long pop() {
        long toReturn;

        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        toReturn = stackArray[top--];
        notifyAll();
        return toReturn;
    }
    //@requires: pre-condition top >= 0
    //@ensures: post-condition top and stackArray remain unchanged
    //use lock, condition and wait
    public synchronized long peek() {
        while (isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return stackArray[top];
        // does not need to call notify()
    }

    //@requires: pre-condition top >= -1
    //@ensures: post-conditio top and stackArray remain unchanged
    // use a lock, so it will not check while top is being updated
    public synchronized boolean isFull() {
        return (top == maxSize - 1);
    }

    //pre-condition: true
    //post-condition: the elements are un-changed. the return value is true iff the stack is empty.
    public synchronized boolean isEmpty() {
        return (top == -1);
    }
}
