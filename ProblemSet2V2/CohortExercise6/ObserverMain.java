/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmedia;

/**
 *
 * @author ongajong
 */
public class ObserverMain {
    public static void main(String args[]){
        Blog blog = new Blog();
        User user1 = new User("Id: Writer", true);
        User user2 = new User("Id: Reader",false);
        User user3 = new User("Id: Commenter",false);
        blog.registerWriter(user1);
        //adds followers to the blog/post
        blog.registerObserver(user2);
        blog.registerObserver(user3);
        
        user1.setSubject(blog);
        user2.setSubject(blog);
        user3.setSubject(blog);
        blog.postNewArticle("New Article",user1);
        blog.postNewArticle("Comment: Hey there!",user3);
        blog.editArticle("I want to snack", user1);
    }
    
}
