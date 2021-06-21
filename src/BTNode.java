public class BTNode <T> {
	 T data;
	BTNode<T> par;
	LinkedList<BTNode<T>> aya ;

	/** Creates a new instance of BTNode */
	public BTNode() {
		data = null;
		aya = new LinkedList<BTNode<T>>() ;
		par=null;
	}
	public BTNode(T val , BTNode<T> a){
		par=a;
		data = val;
		aya = new LinkedList<BTNode<T>>() ;
	}
	public BTNode(T val){
		data = val;
		aya = new LinkedList<BTNode<T>>() ;
		par=null;
	}
}
