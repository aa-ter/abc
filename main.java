import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class main{
	 static String article="Taiwanese computer maker ASUS announced the launch of a foldable laptop computer.";
 public static void main(String[] args) {

	 //read_voca();
     SwingUtilities.invokeLater(
             new Runnable() {
                public void run() {
                   new e();
                }
             });
     //System.out.println("總共符合"+get_amount(article)+"個單字");
 }
 static int get_amount(String article) {
	 String[] voca= {"laptop","of","the"};
	 voca=read_voca();
	 int y=0;
	 for(String e:voca)
		 if((article).indexOf(e)!=-1)
	       y++;
         System.out.println("符合的單字如下");
	 for(String e:voca)
		 if((article).indexOf(e)!=-1)
			 System.out.println(e);
	 
	 return y;
 }
 static String[] read_voca() {
     String  thisLine = null;
     String[] voca= {};
     ArrayList<String> vocalist=new ArrayList<String>();
     try{
         FileReader fileReader = new FileReader( "C:\\Users\\ater\\Desktop\\o.txt"); 

     // Convert fileReader to 
     // bufferedReader 
     BufferedReader buffReader = new BufferedReader( fileReader); 

     while (buffReader.ready()) { 
    	 vocalist.add(buffReader.readLine()); 
     } 
     voca = (String[]) vocalist.toArray(new String[0]);
     }catch(Exception e){
        e.printStackTrace();
     }
     //for(String e:voca) System.out.println("e"+e);
     return voca;
 }
 
}
