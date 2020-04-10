package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner; 
import java.util.*;
import java.io.FileWriter;
import java.io.*; 
import java.lang.Math; 


public class WarehouseBook {
  
  protected static class WarehouseNode {
    private ProductRecord record;
    private WarehouseNode left, right;
    private int balance; 
    
    
    public ProductRecord getRecord() {
      return record;
    }
    public void setRecord(ProductRecord record) {
      this.record = record;
    }
    public WarehouseNode getLeft() {
      return left;
    }
    public void setLeft(WarehouseNode left) {
      this.left = left;
    }
    public WarehouseNode getRight() {
      return right;
    }
    public void setRight(WarehouseNode right) {
      this.right = right;
    }
    public int getBalance() {
      return balance;
    }
    public void setBalance(int balance) {
      this.balance = balance;
    }
  }
  
  private WarehouseNode root;
  private int size;


  private int height(WarehouseNode x){
    if (x==null)
      return 0;
    return 1 + Math.max(height(x.left), height(x.right));
    }
  
  public int checkBalance(WarehouseNode root){
    return  height(root.right) - height(root.left) ;
  }

  public int getSize(){
    return size;
  }
  
  public WarehouseBook(){
    root = null;
    size = 0;
  }



   private Stack<String> stack = new Stack<String>();             
   public int getStackSize(){                     
      return stack.size();                          
   }                                  
  

    void deleteId(int id) 
    { 
        root = deleteRec(root, id); 
    } 
  

    WarehouseNode deleteRec(WarehouseNode root, int id) 
    { 
      
        if (root == null)  return root; 
  
    
        if (id < root.record.getProductID()) 
            root.left = deleteRec(root.left, id); 
        else if (id > root.record.getProductID()) 
            root.right = deleteRec(root.right, id); 
  
    
        else
        { 
            
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
  
           
            root.record.setQuantity(minValue(root.right)); 
       
            root.right = deleteRec(root.right, root.record.getProductID()); 
        } 
  
        return root; 
    } 
  
    int minValue(WarehouseNode root) 
    { 
        int minv = root.record.getProductID(); 
        while (root.left != null) 
        { 
            minv = root.left.record.getProductID(); 
            root = root.left; 
        } 
        return minv; 
    } 

    private WarehouseNode rotateLeft(WarehouseNode x)
    {
        WarehouseNode y = x.right;
        x.right = y.left;
        y.left = x;
       
       
        return y;

    }
     private WarehouseNode rotateRight(WarehouseNode x)
    {
        WarehouseNode y = x.left;
        x.left = y.right;
        y.right = x;
   
        return y;
    }

    public WarehouseNode makeBalance(WarehouseNode root, int id){ // right - left
      if (checkBalance(root) > 1 && id > root.record.getProductID())
        return rotateLeft(root);

      if (checkBalance(root) < -1 && id < root.record.getProductID())
        return rotateRight(root);

      if (checkBalance(root) > 1 && id < root.record.getProductID()){
        root.right = rotateRight(root.right);
        return rotateLeft(root);
      }

      if (checkBalance(root) < -1 && id > root.record.getProductID()){
        root.left = rotateLeft(root.left);
        return rotateRight(root);
      }
      return root;
    }


  private void insert(int id, int quantity){
    root = insertRec(root, id, quantity);
  }
  WarehouseNode insertRec(WarehouseNode root, int id, int quantity) 
    { 
 
       /* if empty node, return new node*/
       if (root == null) 
       { 
           root = new WarehouseNode();
           root.record = new ProductRecord(id,quantity);
           return root; 
       } 
 
       /* read bst */
       if (id < root.record.getProductID()) 
           root.left = insertRec(root.left,id,quantity); 
       else if (id > root.record.getProductID()) 
           root.right = insertRec(root.right,id,quantity); 
       else
          return root;

       if (toggle){
          return makeBalance(root,id);
       }
 
       
       return root; 
   } 

   

                                          //
   void lnr()                                 //
   {                                      //
       readLNR(root);                             //
   }    
   void nrl()
   {
      readNRL(root);
   }     
   
   void rln(){
   	readRLN(root);
   }                             //
  private String readNode = "";                                     //
      //
   void readLNR(WarehouseNode root)                     //
   {                                      //    Read node throught left - node- right method.
       if (root != null)                            //
       {                                    //
                                            //
        readLNR(root.left);  
        readNode = readNode + root.record.getProductID() + root.record.getQuantity() + " "; 
        readLNR(root.right);                        //
       }                                    //
   }                                      //
                                          //  
   void readNRL(WarehouseNode root)                     //
   {                                      //    Read node throught Node - right- left method.
       if (root != null)                            //
       {                                    //
         readNode = readNode + root.record.getProductID() + root.record.getQuantity() + " ";                          
         readNRL(root.right);  
         readNRL(root.left); 
                              //
       }                                    //
   }        

   void readRLN(WarehouseNode root)                     //
   {                                      //    Read node throught left - node- right method.
       if (root != null)                            //
       {                                    //
      
        readRLN(root.right);                                    //
        readRLN(root.left);
        readNode = readNode + root.record.getProductID() + root.record.getQuantity() + " ";  
        
                              //
       }                                    //
   }  
   public void print(){
    
    System.out.println(readNode); 
    
  }
  

  public String[] getMid(String[] tempNode, int mid){   // this function was created to make getMid array at mid point to begin point.
    String[] temp = new String[tempNode.length];
    mid --;
    temp[0] = tempNode[mid];
    int j = 1;
    for( int i = 0; i < tempNode.length ; i++){
      if (tempNode[i] == temp[0]){
        j--;        
      }else if(j == tempNode.length){
        break;
      }else{
        temp[j] = tempNode[i];
      }

      j ++;
        
    
    }
    return temp;
  }

  public void eventsThird(){
    lnr();
    String[] tempNode = readNode.split(" ");
    double value =(double)tempNode.length / 2;
    int mid = (int)Math.ceil(value); // example: len of arr is 9, -> mid = 5. len = 10 -> mid = 5.
    
    root = null;
    // getMid at mid to begin point
    tempNode = getMid(tempNode,mid);

    for(String product : tempNode){
    
        String[] IdAndValue = getIdAndValue(product);
                
      insert(Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));
      }
    

  }
  
  


  private boolean toggle; // switch the makeBalance function is trigged in insert function.
  public void eventsFourth(){
  	nrl();
    String[] tempNode = readNode.split(" ");
    root = null;
    toggle = true;

    // do insert and toggle switch 
    // do
    for(String product : tempNode){
    
       		 // System.out.print(product + "-");

            String[] IdAndValue = getIdAndValue(product);
                
     		  insert(Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));
            


    }

    toggle = false;

  }

  public void eventsFifth(){
  	rln();
  	String[] tempNode = readNode.split(" ");
    root = null;

    double value =(double)tempNode.length / 2;
    int mid = (int)Math.ceil(value); // example: len of arr is 9, -> mid = 5. len = 10 -> mid = 5.
    tempNode = getMid(tempNode,mid);
    for (String product : tempNode){
    	System.out.print(product + "-");
    	String[] IdAndValue = getIdAndValue(product);
    	insert(Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));

    }


  }
  public void eventsSixth(int unit){
  	root = makeEventsSixth(root,unit);
  }
  
   private int i = 0;
  public WarehouseNode makeEventsSixth(WarehouseNode root ,int unit){
  		if (root == null)  return root; 
  		// i++;
  		// System.out.println("day la i" + i);
    
        if (height(root) == unit){
        	// System.out.println(height(root) + "root");
        	root = null;
        	return root;
        	
            
        }else{
        	root.left = makeEventsSixth(root.left, unit); 
            root.right = makeEventsSixth(root.right, unit); 
        }
        	
  		return root;
 
        





  }



  private String[] getIdAndValue(String product){
      String str = product.replaceAll("[^0-9.]", "");  
    String[] arr = str.split("");
    String id = "", quantity="";
    for (int i = 0; i < arr.length;i++){
  
      if (i < 3){
        id = id + arr[i];
      }else{
        quantity = quantity + arr[i];
      }
    }
    
    String[] result = {id,quantity}; // or return new String[]{id, quantity};
    return result;
   }




   private String book = "";                        
   
  
  
   void bookFormat(WarehouseNode root, int i){
      int bit = i;
      if (root != null)
      {
        
        if (bit == 2){
          if(root.left != null || root.right != null){
            book = book + "" + root.record.getProductID() + root.record.getQuantity() + " ";
          }else{
            book = book + "" + root.record.getProductID() + root.record.getQuantity() + ")"; 
          }
          
        }else{
          book = book +"("+ root.record.getProductID()+ root.record.getQuantity() + " ";
        }

        //---------------------------------------------------
        
        if (root.left == null && root.right == null){
          // nothing
        }else{
          if (root.left == null){
          
            book = book + "(N "; 
            stack.push(")");

          }else if (root.right == null){
            stack.push("N)");
          }else{
             stack.push(")");
          }

   
          

        }

        

        bookFormat(root.left,0);                // bookFormat left
        //---------------------------------------------------
      
        if (root.left == null && root.right == null){         //
          bookFormat(root.right,0);             //
        }else if(root.left != null && root.right == null){    //
          bookFormat(root.right,1);                         //  bookFormat right
        }else{                                        //
          bookFormat(root.right,2);             //
        }                           //
        
        
        
      }

      if (root == null &&  bit == 1){
        for(int j = 0; j < getStackSize() - 1; j++){  // Sau khi root.right chay den null thi push ra het  until the last one ")" then stop.
          if (stack.peek().equals("N)")){
            book = book + stack.pop();
          }else{
            book = book + " " + stack.pop();    // and except case that it bookFormat(root.right,1)
          }

          
        }
        
      }
      // System.out.println(stack.size());
   }

    public void bookFormatComplete(){
        // stack.push(")");
        //IF TOGGLE NULL THEN WRITE HERE!
        bookFormat(root,0);
        int countClose = 0;
        int countOpen = 0;
        for (int i = 0; i < book.length(); i++){
          if(book.charAt(i) == '(')
            countOpen ++;
          if(book.charAt(i) == ')')
            countClose++;
        }
        int cmp = countOpen - countClose;
        if (cmp > 0){
          for (int i = 0 ; i < cmp; i ++)
            book = book + ")";
        }
  
        
      
        
    }



  private String addZeroInQuantity(String product){
      String str = product.replaceAll("[^0-9.]", "");   
    String[] arr = str.split("");
    String id = "", quantity="";
    for (int i = 0; i < arr.length;i++){
  
      if (i < 3){
        id = id + arr[i];
      }else if(arr.length == 4){
        quantity = quantity + "0" + arr[i];
      }else{
        quantity = quantity + arr[i];
      }
    }
    
    String result = id + quantity; // or return new String[]{id, quantity};
    return result;
   }

    public ArrayList<Integer> sortId(String[] tempArray){
      // int[] sort = new int[tempArray.length];
      ArrayList<Integer> sort = new ArrayList<Integer>();
      // int i = 0;
      for (String element : tempArray){
        
        String product  = addZeroInQuantity(element);
      
        if(product.length() > 3){
          sort.add(Integer.parseInt(product));
          // System.out.print(sort[i] + "-");
          // i++;
        }

      }
      //sort here
      Collections.sort(sort);
      return sort;
    }
      
   
  private boolean isNull;
  
  public WarehouseBook(File file) throws IOException  {  // throws IOException 
    //sinh vien viet ma tai day
 
        Scanner sc = new Scanner(file); 

     if (root == null)  // use toggle true false in bookFormatComplete()
       { 
            root = new WarehouseNode();
            root.record = new ProductRecord(0,0);
        
           isNull = true;
            
       } 
        
        while (sc.hasNextLine()){
          System.out.println("read lie");
        
          
          String[] tempArray = sc.nextLine().split(" ");
         
          for(String element : tempArray){
            // if (element != 0)
            System.out.println(element + "+");
            if(element.length() > 3){
              element = element.replaceAll("[^0-9.]", ""); 
              if (!element.equals("")){
                 System.out.println(element + " lemm");
               String[] IdAndValue = getIdAndValue(element);
              insert(Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));
              } 
             
            }
              
             
          }
        }

          
        sc.close();

    
  } 
      
          
  

  
  
  public void save(File file) throws IOException{
    //sinh vien viet ma tai day
    FileWriter fileWriter = new FileWriter(file);

    bookFormatComplete();
        fileWriter.write(book); //book
      fileWriter.close();
    

  }


  public WarehouseNode addSearch (WarehouseNode root, int id, int quantity){
 
     if (root == null) 
       { 
           root = new WarehouseNode();
           root.record = new ProductRecord(id,quantity);
           return root; 
       } 
 
       /* read bst */
       if (id < root.record.getProductID()) 
           root.left = addSearch(root.left,id,quantity); 
       else if (id > root.record.getProductID()) 
           root.right = addSearch(root.right,id,quantity); 
       else{
       	  int newQuantity =  root.record.getQuantity() + quantity;
          root.record.setQuantity(newQuantity);
          return root;
       }
       return root;

  }

  private ArrayList<Integer> getId(ArrayList<Integer> tempInt){  // get id in element and publish to arraylist
  	 ArrayList<Integer> tempIntId = new ArrayList<Integer>();
  		for (int element : tempInt){
  			String product = String.valueOf(element);
  			String add = "";
  			for (int i = 0 ; i < product.length(); i++){
  				if (i <= 2)
  					add = add + product.charAt(i);
  			}
  			tempIntId.add(Integer.parseInt(add));
  		}
  		return tempIntId;
  }

  public int findIdClose(WarehouseNode root, int id){ // id as parameter is id of event
  		nrl();
  		String[] tempNode = readNode.split(" ");
          ArrayList<Integer> tempInt = new ArrayList<Integer>();
          ArrayList<Integer> tempIntId = new ArrayList<Integer>();
          tempInt = sortId(tempNode); //[33244,....,324324]; // sort id
          							// id = 332


          tempIntId = getId(tempInt);

          if (tempIntId.get(tempIntId.size() - 1) < id )
          		return tempIntId.get(tempIntId.size() - 1);
          if (tempIntId.get(0) > id)
          		return tempIntId.get(0);
         	
          	// String[] IdAndValue = getIdAndValue(String.valueOf(tempInt.get(i)));
          for (int i = 0; i < tempInt.size(); i++){

          		if(  (tempIntId.get(i) < id) && (tempIntId.get(i+1) > id) ){
          			int top = tempIntId.get(i + 1);
          			int bot = tempIntId.get(i);

          			int subtractTop = top - id;
          			int subtractBot = id - bot;
          			return (subtractBot <= subtractTop) ? bot : top;
          			
          		}
              			
          			
          	}
          return -1;
  
        
  }

  public int getIdClose(int id){  //get close id of id as parameter
  	return findIdClose(root,id);	
  }

  public WarehouseNode subtractId(WarehouseNode root, int id, int quantity){
  	if (id < root.record.getProductID()) 
           return subtractId(root.left,id,quantity); 
       else if (id > root.record.getProductID()) 
           return subtractId(root.right,id,quantity); 
       else{
       		int newQuantity =  root.record.getQuantity() - quantity;
		    if (newQuantity <= 0){
		       deleteId(id);
	        }else{
		        root.record.setQuantity(newQuantity);
	        }	   
       }
  	return root;
  }

  public void subtractIdClose(int id, int quantity){
  		 subtractId(root,id,quantity);
  }

  public WarehouseNode subtractSearch (WarehouseNode root, int id, int quantity, int closeId){

  	if (root != null){
	  		if (root.record.getProductID() == id){
	      int newQuantity =  root.record.getQuantity() - quantity;
        System.out.println(newQuantity + " this is quantity");
        System.out.println(id + " this is id");
	      if (newQuantity <= 0){
	        deleteId(id);
          System.out.println(newQuantity + " this sfsfis id");

	      }else{
	        root.record.setQuantity(newQuantity);
	      }
	      
	      return root;
	    } 


		    if (root.record.getProductID() > id) 
		        return subtractSearch(root.left, id,quantity,closeId); 
		    return subtractSearch(root.right, id, quantity,closeId);
	     
		
  	}
    if (root == null){
			subtractIdClose(closeId,quantity);
	        return null; 
		 	
    }
    return root;
 
     
    
  }

  private String[] lineContent;
  private int index;
  private String parcel;

  private int unit;
  
  public void process(File file) throws IOException{
    //sinh vien viet ma tai day
    Scanner sc = new Scanner(file); 
    System.out.println("abcdd");
      while (sc.hasNextLine()){         

          lineContent = sc.nextLine().split(" ");

          List<String> events = new ArrayList<>();
            for(String element : lineContent){
                ;
              if(element.length() > 1){
                events.add(Character.toString(element.charAt(0)));   // get event in one by one of string
              }else{       
                events.add(element);                // get event if not is > 2  
              }
            }

            index = 0;
            for(String element : lineContent){
              if (element.length() > 2){ // xet dieu kien isparcel ?
                parcel = element;
                process(events);
                 System.out.println(events);
                index ++;
              }
              else if(element.length() == 2){
        
              	 unit = Integer.parseInt(Character.toString(element.charAt(1)));
              	 System.out.println(unit + " unit");
              	 process(events);
                  
                 index ++;
              }else{
                if (events.get(index).equals("0")){  // case = 0
                  System.out.println(events.get(i) + " 2234");
                  break;
                }
                process(events);
                 
                index ++;
              }
            }
              
          
        }

        nrl();
        System.out.println(readNode);

  }
  
  public void process(List<String> events){
    //sinh vien viet ma tai day
      String getEvents = events.get(index);

      switch (Integer.parseInt(getEvents)){
      	case 0:
        {
      		break;
        }
        case 1:
          {
            String product = "";
                for (int i = 1; i < parcel.length(); i++){
                  product += parcel.charAt(i);
                }
                
                String[] IdAndValue = getIdAndValue(product);
                 // process
            addSearch(root,Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));  // the action refer doing add quantity   
            if(isNull){
              deleteId(0);
              isNull = false;
            }             
                  
            break;
          }
        case 2:
          { 
            String product = "";
                for (int i = 1; i < parcel.length(); i++){
                  product += parcel.charAt(i);
                }
                System.out.println(parcel + " day la parcle");
                
 				               
                String[] IdAndValue = getIdAndValue(product);
                int closeid = getIdClose(Integer.parseInt(IdAndValue[0]));
                System.out.println(closeid + "closeid");
                 // process
            subtractSearch(root,Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]), closeid); // the action refer doing subtract quantity  
            if(isNull){
              deleteId(0);
              isNull = false;
            }     
            break;
          }
        case 3:
          {
            eventsThird();
            if(isNull){
              deleteId(0);
              isNull = false;
            }     
            break;
          }
        case 4:
          {
            eventsFourth();
            if(isNull){
              deleteId(0);
              isNull = false;
            }     
            break;
          }
         case 5:
         {
         	String product = "";
                for (int i = 1; i < parcel.length(); i++){
                  product += parcel.charAt(i);
                }
                
            String[] IdAndValue = getIdAndValue(product);
                 // process
            addSearch(root,Integer.parseInt(IdAndValue[0]),Integer.parseInt(IdAndValue[1]));

            eventsFifth();
            if(isNull){
              deleteId(0);
              isNull = false;
            }     
         	break;
         }
         case 6:
         {
         	System.out.println("day la height" + height(root));
         	eventsSixth( height(root) - unit + 1); // assume unit = 1, height = 1 is from bot to top, at the top to bot, its height is decrease. 
         									// but in metion of assignment required, delete from top top last bot, so height of root minus unit add 1
   											  //	and root has max height.
          if(isNull){
              deleteId(0);
              isNull = false;
            }     
         	break;
         }
        default:
          break;

      }
    }

  
  /*
  @Override
  public String toString(){
    String res = "";
    //sinh vien viet ma tai 
    return res;
  } */
  
  public static void main(String[] args){
    //vi du ham main de chay
    try{
      WarehouseBook wb = new WarehouseBook(new File("warehouse.txt"));
      // System.out.println("height: " + wb.checkBalance());
      wb.process(new File("events.txt"));
      
        wb.save(new File("warehouse.txt"));
    }catch(Exception ex){
      ex.printStackTrace();
    }
    
  }

}
