
public class LinkedGT<T> implements GT<T> {
	
	BTNode<T> root, current;
	
	/** Creates a new instance of BT */
	public LinkedGT() {
		root = current = null;
	}
	
	public boolean empty(){
		return root == null;
	}
	public boolean full(){
		return false;
	}
	public T retrieve() {
		if(current==null)
			return null;
		return current.data;
	}
	

	public void update(T val) {
		current.data = val;
	}
	public void findRoot() {
		if(!empty())
		current=root;
	}
	

	public boolean insert(T val) {
		
			if(empty()) {
				root = current = new BTNode<T>(val);
				
			return true; }
		   
		  
		
			
				BTNode<T> tf =	new BTNode<T>(val ,current );
				
				current.aya.insert(tf);
				

				current=tf;
				 return true;
			}
			
			
		 
		
	
	public boolean findParent() {
		
		 if(current==null) 
			 return false;
		
		if(current==root)
			return false;
		
//		BTNode<T> par =current;
//		current=root;
//		int number = nbChildren();
//		
//			for(int i=0; i<number; i++) {
//				findChild(i);
//				if(current==par)
//					return true;
//				
//					
//				
//			}
//			return true;
//		
		current=current.par;
		return true;
	}
	public void remove(){
		if(current==null)
			return;
		
		if(current == root){
			current = root = null;
			return;
		}
		else {
		
			BTNode<T> p = current;
		
		
			BTNode<T> q =	current.par;
	
			current = current.par;
			current.aya.findfirst();
		
			while(!current.aya.retrieve().equals(p) && !current.aya.last()) {
				
			current.aya.findnext(); }
			
			current.aya.remove();
			current = q;
			
		}
	}
	
	
	public int nbChildren() {
		if(current==null)
			return 0;
		if(current.aya.empty())
			return 0;
		
		int count=1;
		
		current.aya.findfirst();
	while(!current.aya.last()) {
		current.aya.findnext();
			count++;}
	
		return count;
	}
	

	public boolean findChild(int i) { 
		 if(current==null) 
			 return false;
		 
		BTNode<T> a=current;
		int number=nbChildren();
		
//		if(current==null)
//			return false;
//		int number=1;
//		BTNode<T> a=current;
//		current.aya.findfirst();
//		while(!current.aya.last() ) {
//			current.aya.findnext();
//			number++;
//			
//		}
		
		if(number<=i) {
			current=a;
			return false;}
	
		int count=0;
		
		current.aya.findfirst();
while(count<i) {
	current.aya.findnext();	
		count++;
			}

	

  BTNode<T> b =current.aya.retrieve();
  current=b;
	return true;

	
			
	
	
}         
	}



