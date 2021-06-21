public class PowerGridUtils {
	
	// Return the IDs of all elements in the power grid pg in pre-order.


	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
		
		LinkedQueue<Integer> preorder = new LinkedQueue<Integer>();
		if(pg==null)
			return preorder;
		if(pg.empty())
			return preorder;
		pg.findRoot();
		
		
		int number =pg.nbChildren();
		if(number==0) {
			preorder.enqueue(pg.retrieve().getId());
			      return preorder;}
		
		preorder.enqueue(pg.retrieve().getId());
		for(int i=0; i<number; i++) {
			pg.findRoot();
		pg.findChild(i);
	 if(pg.nbChildren()>0)
		allid(pg , preorder);
	 else
		preorder.enqueue(pg.retrieve().getId());
		}
		
		
	
			
			
		
	
		
		
		
		return preorder;
	}
	private static void allid(GT<PGElem> e , LinkedQueue<Integer> preorder) {    /// was return a queue
		
		int a =e.nbChildren();
           
			preorder.enqueue(e.retrieve().getId());
		if(a==0)
			return;
		for(int k=0; k<a; k++) {
			
			e.findChild(k);
			allid(e,preorder);
			e.findParent();
			
		}
		
	}
		
		

	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id) {
	
		if(pg.empty())
			return false;
		if(pg==null)
			return false;
		
		pg.findRoot();
		int number =pg.nbChildren();

		if(number==0) {
		
			if(pg.retrieve().getId()==id)
				
				return true;
				else
					return false;
		}
			
		if(pg.retrieve().getId()==id)
			return true;
		boolean test = false;
		for(int i=0; i<number; i++) {
		
			pg.findRoot();
		pg.findChild(i);
	 if(pg.nbChildren()>0) {
		 
		if( FINDIDTREE(pg , id ,test ))
			return true; }
	 else {
		 if(pg.retrieve().getId()==id)
				return true;
		
		}
		
		}
	
		return false;
		
		
	}
 private static boolean FINDIDTREE(GT<PGElem> e , int id , boolean c) {

		int a =e.nbChildren();
		
			if(e.retrieve().getId()==id) {
				c= true; 
			return true;
			}
			
			
		for(int i=0; i<a; i++) {

			e.findChild(i);
			c=FINDIDTREE(e,id ,c);
			if(c)
				return true;
			e.findParent();

			
		
				
			
		}
		return c;
		
		
		
		
	}

	

	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen) {
		
		if(pg==null)
			return false;
	
		if(gen==null)
			return false;
		
		if(!pg.empty())
			return false;
		if(!gen.getType().equals(ElemType.Generator))
			return false;
		
		pg.insert(gen);
		return true;
		
	}

	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already aelement with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id) {
		
		
		
		if(pg==null)
			return false;
		if(pgn==null){
			return false;
		}
//		if(pgn.getPower()<0) {  /// and id ?? -1 
//			return false;
//		}
		if(pg.empty())
			return false;
		if(pgn.getType().equals(ElemType.Generator))
			return false;
		
	
		
		
	if(find(pg, pgn.getId()))
			return false;
	
	 if(!find(pg, id))
			return false;
	 if(pg.retrieve().getType()==ElemType.Consumer)
		 return false;
		
	
	
	 
		find(pg, id);
		if(pgn.getType()==ElemType.Consumer && pg.retrieve().getType()!=ElemType.Transmitter)//|| pg.retrieve().getType().equals(ElemType.Generator)
		return false;
	
		pg.insert(pgn);
	   
		return true;
	}
	

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id) {
		if(pg==null)
			return false;
		
		if(pg.empty())
			return false;
		if(!find(pg, id))
			return false;
		
		find(pg, id);
		
		pg.remove();
		return true;
		
	}

	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id) {
		
		double power=0;
		if(pg==null)
			return -1;
		
		if(pg.empty())
			return -1;
		
		if(!find(pg, id))
			return -1;
		
		find(pg, id);
		
		if(pg.retrieve().getType().equals(ElemType.Consumer)) {
			  power+=pg.retrieve().getPower();
			  return power; }
		
		int number =pg.nbChildren();
		for(int i=0; i<number; i++) {
			find(pg, id);
			pg.findChild(i);
		 if(pg.nbChildren()>0)
			 power+=total(pg , id);
		 else
			 if(pg.retrieve().getType().equals(ElemType.Consumer))
		   power+=pg.retrieve().getPower();        
		              }
		
		return power;
	}
	private static double total(GT<PGElem> pg , int id ) {
		double tot=0;
		int a =pg.nbChildren();
		
			 if(pg.retrieve().getType().equals(ElemType.Consumer))
				 tot+=pg.retrieve().getPower(); 
			
			
		for(int i=0; i<a; i++) {
			
			pg.findChild(i);
			tot+=total(pg , id);
			pg.findParent();
			
		}
		return tot;
	}

	// Checks if the power grid contains an overload. The method returns the ID of the first element preorder that has an overload and -1 if there is no overload.
	public static int findOverload(GT<PGElem> pg) {
		if(pg==null)
			return -1;
		
		if(pg.empty())
			return -1;
		
            pg.findRoot();
		
		
		int number =pg.nbChildren();
		 if(pg.retrieve().getPower()<totalPower(pg,pg.retrieve().getId())) {
			 pg.findRoot();
			 return pg.retrieve().getId(); }
		
		for(int i=0; i<number; i++) {
			pg.findRoot();
		pg.findChild(i);
		
	 if(pg.nbChildren()>0) {
		 if(-1!=Overload(pg)) {
			return pg.retrieve().getId();  } }
	 else {
		 int thefind=  pg.retrieve().getId();
		 if( pg.retrieve().getPower()<totalPower(pg,pg.retrieve().getId())) {
			 find(pg,thefind);
				
			 int test = pg.retrieve().getId();
			 return test; }
	 }
		
		}
		
	return -1;
	}
	private static int Overload(GT<PGElem> pg) {
	
		int a =pg.nbChildren();
		
		if(pg.retrieve().getType().equals(ElemType.Consumer))
		 {
			
		 return -1;
		 }
		
		int thefind=  pg.retrieve().getId();
		 if(pg.retrieve().getPower()<totalPower(pg,pg.retrieve().getId())) {
			 find(pg,thefind);
			
			 int test = pg.retrieve().getId();
			 return test;  }
		 
		 find(pg,thefind);
		 
	for(int k=0; k<a; k++) {
		
		 
		 
		pg.findChild(k);
		int te=Overload(pg);
		
		
		 if(te!=-1) {
			 
			 return te; }
		pg.findParent();
		
	}
	return -1;
	}
}
