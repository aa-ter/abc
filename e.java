import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.nio.file.Path;

public class e implements ActionListener,ItemListener{
   String[] optionsToChoose={"黑色","黃色","藍色","紅色","綠色"},optionsToChoose2={"粉色","黃色","藍色","白色","綠色"};
   JComboBox<String> jComboBox,jComboBox2;
   JTextArea ta = new JTextArea(100,10) ;
   JButton bopen=new JButton("選擇檔案"),bsave=new JButton("儲存檔案"),bcount=new JButton("計算符合單字數量");
   JFileChooser filechooser = new JFileChooser();
   JScrollPane g = new JScrollPane(ta);
   String voca="",txt="";File file=null;
   FileNameExtensionFilter filter=new FileNameExtensionFilter("Only .txt .csv .java files", "txt","csv","java");
   Boolean change=true;
   JLabel jl=new JLabel("背景顏色"),jl2=new JLabel("文字顏色"),l_voca=new JLabel("按右鍵可以貼上");
   JMenuItem copy = null, paste = null, cut = null;
   JPopupMenu pop = null;
JFrame f = new JFrame("文字檔案編輯器");
   e(){
     jComboBox = new JComboBox<>(optionsToChoose);
     jComboBox2 = new JComboBox<>(optionsToChoose2);
     //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent event){
    	    //public void actionPerformed(ActionEvent e) {
    	    if(!(change)){
    	        int mType=JOptionPane.QUESTION_MESSAGE;
    	        int oType=JOptionPane.YES_NO_CANCEL_OPTION;
    	        String[] options={"儲存","不儲存"};
    	        int opt=JOptionPane.showOptionDialog(f,"要儲存所做的變更嗎?",
    	          "請選擇",oType,mType,null,options,"儲存");
    	        
    	        if (opt==JOptionPane.YES_OPTION){
    	        	System.out.println("您選擇的是 : 儲存 " + opt);
    	        	System.exit(0);
    	        	System.out.println("file="+file);//這行不會顯示
    	            try {
    	                FileOutputStream out = new FileOutputStream(file);
    	                out.write(ta.getText().getBytes());  
    	                out.close();
    	                } catch (Exception ex) {
    	                  ex.printStackTrace();
    	                }
    	          }
    	        if (opt==JOptionPane.NO_OPTION){
    	        	System.exit(0);
    	          }
    	        /*if (opt==JOptionPane.CANCEL_OPTION){
    	          
    	          }*/
    	    }
    	    }});
     bopen.addActionListener(this);
     ta.addMouseListener(new EventListener1());
     ta.setBackground(new Color(0, 0, 0));
     ta.setForeground(Color.pink);
     ta.setCaretColor(Color.green);
     jComboBox.addItemListener(this);
     jComboBox2.addItemListener(this);
     bsave.setVisible(false);
     f.addComponentListener(new ComponentAdapter() {//?窗口??大小改?事件
         @Override
         public void componentResized(ComponentEvent e) {
         	int fraWidth = f.getWidth();
             int fraHeight = f.getHeight();
             System.out.println("fraHeight"+fraHeight);
             System.out.println("fraWidth"+fraWidth);
             g.setSize(f.getWidth()-50,f.getHeight()-100);
         }
     });
     ta.addKeyListener(new KeyListener() {
    @Override
    public void keyTyped(KeyEvent arg0) {
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyPressed(KeyEvent arg0) {
    if(change) {f.setTitle("*"+f.getTitle());
    change=false;}
    }
});
     //ta.setEditable(false);
     ta.setFont(ta.getFont ().deriveFont (17.0f));
    bsave.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      change=true;f.setTitle(file.getName());
       try {
           FileOutputStream out = new FileOutputStream(file);
           out.write(ta.getText().getBytes());       
           out.close();
           } catch (Exception ex) {
             ex.printStackTrace();
           }
    }
    });
     g.setBounds(10,45,1000,800);
     f.setBounds(100,10,g.getWidth()+50,g.getHeight()+100);
     f.setVisible(true);
     f.setLayout (null); //nul表示自訂樣式，不使用預設的樣式         
     bopen.setBounds(1,1,90,40);
     bsave.setBounds(100,1,90,40);
     jComboBox.setBounds(235+50,1,60,40);
     jComboBox2.setBounds(370+50,1,60,40);
     jl.setBounds(160+50,1,60,40);
     jl2.setBounds(300+50,1,60,40);
     bcount.setBounds(30,1,160,40);
     l_voca.setBounds(300+200,1,190,40);
     //f.add(bopen);
     f.add(g);
     f.add(bsave);
     f.add(jComboBox);
     f.add(jComboBox2);
     f.add(jl);
     f.add(jl2);
     f.add(l_voca);
     f.add(bcount);
     bcount.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	main f=new main();
    	    	if(!("").equals(ta.getText())) {
    	    		l_voca.setText("總共符合"+f.get_amount(ta.getText())+"個單字");
    	    	}
    	    	else JOptionPane.showMessageDialog(null, "你沒有輸入文字", "hi", JOptionPane.ERROR_MESSAGE);
    	    }
    	    });
filechooser.addChoosableFileFilter(filter);
//隱藏下拉列表中的“所有檔案”選項
filechooser.setAcceptAllFileFilterUsed(false);
filechooser.setCurrentDirectory(new File(System.getProperty("user.home")+"//Desktop"));
pop = new JPopupMenu();
pop.add(cut = new JMenuItem("剪下"));
pop.add(paste = new JMenuItem("貼上"));
pop.add(copy = new JMenuItem("複製"));
copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
f.add(pop);
copy.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     action(e);
    }
   });
   paste.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     action(e);
    }
   });
   cut.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     action(e);
    }
   });

}
   public void action(ActionEvent e) {
	   String str = e.getActionCommand();
	   if (str.equals(copy.getText())) { // 複製
	    ta.copy();
	   } else if (str.equals(paste.getText())) { // 貼上
	    ta.paste();
	    if(change) {f.setTitle("*"+f.getTitle());
	    change=false;}
	   } else if (str.equals(cut.getText())) { // 剪下
	    ta.cut();
	    if(change) {f.setTitle("*"+f.getTitle());
	    change=false;}
	   }
	  }
   
class EventListener1 extends MouseAdapter{
 public void mouseClicked(MouseEvent e){
  if(e.getButton() == e.BUTTON3&&e.getSource()==ta)
     ta.paste();
}
 public void mousePressed(MouseEvent e) {
	   if (e.getButton() == MouseEvent.BUTTON3) {
	    copy.setEnabled(isCanCopy());
	    paste.setEnabled(isClipboardString());
	    cut.setEnabled(isCanCopy());
	    if(file!=null) pop.show(ta, e.getX(), e.getY());
	   }
	  }
}
public void read(){ta.setEditable(true);
   ta.setText(null);txt="";f.setTitle(null);file=null;
   if (filechooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION){
   bsave.setVisible(true);
   voca=filechooser.getSelectedFile().getPath().replaceAll("\\\\","\\\\\\\\");
   file=new File(filechooser.getSelectedFile().getPath());
   //System.out.println("file="+file);
   System.out.println("path "+voca);
   System.out.println("filename "+file.getName());
   f.setTitle(file.getName());
   try{
     FileReader g = new FileReader(voca);
      while(g.ready()){
       txt=txt+(char)g.read();
       }
       System.out.print(txt);
       ta.setText(txt);
       g.close();
       }catch (Exception e) {
        JOptionPane.showMessageDialog(null, "有錯誤", "警告", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
       }
   }
}
/*
public void mouseRealsed(MouseEvent e) {
//public void mouseEntered(MouseEvent e) {
}
public void mouseClicked(MouseEvent e) {
//public void mouseExited(MouseEvent e) {
}*/
/*public void mousePressed(MouseEvent e) {
	   if (e.getButton() == MouseEvent.BUTTON3) {
	    copy.setEnabled(isCanCopy());
	    paste.setEnabled(isClipboardString());
	    cut.setEnabled(isCanCopy());
	    pop.show(ta, e.getX(), e.getY());
	   }
	  }*/
public boolean isCanCopy() {
	   boolean b = false;
	   int start = ta.getSelectionStart();
	   int end = ta.getSelectionEnd();
	   if (start != end)
	    b = true;
	   return b;
	  }
public boolean isClipboardString() {
	   boolean b = false;
	   Clipboard clipboard = ta.getToolkit().getSystemClipboard();
	   Transferable content = clipboard.getContents(this);
	   try {
	    if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
	     b = true;
	    }
	   } catch (Exception e) {
	   }
	   return b;
	  }
public void actionPerformed(ActionEvent e){
//if (filechooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION)
  if(e.getSource()==bopen) read();
}
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED){
         if(event.getSource()==jComboBox&&jComboBox.getItemAt(jComboBox.getSelectedIndex())==optionsToChoose[0])
           ta.setBackground(Color.black);
         if(event.getSource()==jComboBox&&jComboBox.getItemAt(jComboBox.getSelectedIndex())==optionsToChoose[1])
           ta.setBackground(Color.yellow);
         if(event.getSource()==jComboBox&&jComboBox.getItemAt(jComboBox.getSelectedIndex())==optionsToChoose[2])
           ta.setBackground(Color.blue);
         if(event.getSource()==jComboBox&&jComboBox.getItemAt(jComboBox.getSelectedIndex())==optionsToChoose[3])
           ta.setBackground(Color.red);
         if(event.getSource()==jComboBox&&jComboBox.getItemAt(jComboBox.getSelectedIndex())==optionsToChoose[4])
            ta.setBackground(Color.green);
         if(event.getSource()==jComboBox2&&jComboBox2.getItemAt(jComboBox2.getSelectedIndex())==optionsToChoose2[0])
            ta.setForeground(Color.pink);
         if(event.getSource()==jComboBox2&&jComboBox2.getItemAt(jComboBox2.getSelectedIndex())==optionsToChoose2[1])
            ta.setForeground(Color.yellow);
         if(event.getSource()==jComboBox2&&jComboBox2.getItemAt(jComboBox2.getSelectedIndex())==optionsToChoose2[2])
            ta.setForeground(Color.blue);
         if(event.getSource()==jComboBox2&&jComboBox2.getItemAt(jComboBox2.getSelectedIndex())==optionsToChoose2[3])
            ta.setForeground(Color.white);
         if(event.getSource()==jComboBox2&&jComboBox2.getItemAt(jComboBox2.getSelectedIndex())==optionsToChoose2[4])
            ta.setForeground(Color.green);
                  }
    }

}//Mess
