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
//implementation of Observers that will watch over the subject
public class User implements Observer{
    private String article;
    private Subject blog;
    public boolean AreUser;
    public User(String nm, boolean AreU){
        this.article = nm;
        this.AreUser = AreU;
    }
    public void setSubject(Subject blog){
        this.blog = blog;
    }
    
  
    public void update(){
        String msg = (String) blog.getUpdate(this);
        if (msg == null){
            System.out.println(this.article + ":: No new message");
        }else System.out.println(this.article + ":: consuming Message: "+ msg );
    }
    public String getArticle(){
        return article;
    }
//    public void editArticle(){
//        String msg = (String) blog.getUpdate(this);
//        if (this.AreUser == true){
//            System.out.println(this.article + "Writer is updating message" + msg);
//        }else System.out.println(this.article + "this user has no permission to edit" + msg);
//    }
    
}
