/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset6;

import java.util.Stack;

/**
 *
 * @author ongajong
 */
public class SafeStack<E>  {
    private Stack<E> stack = new Stack<>();
    public void pushIfNotFull(E e){
        synchronized (stack){
            if(stack.size() != stack.capacity()){
                stack.push(e);
            }
        }
    }
    public synchronized E popIfNotEmpty(){
        synchronized (stack) {
            if (!stack.isEmpty()) {
                return stack.pop();
            }

            return null;
        }
    }

}