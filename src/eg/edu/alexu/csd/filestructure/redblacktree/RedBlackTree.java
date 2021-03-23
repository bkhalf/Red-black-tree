package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;

public class RedBlackTree <T extends Comparable<T>, V> implements IRedBlackTree<T,V> {

	public int size=0;
	private myNode root=null;
	ArrayList <myNode> arr=new ArrayList <myNode>(4);
	int count=1;

	@Override
	public INode<T, V> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root==null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root=null;
		size = 0;
	}

	@Override
	public V search(T key) {
		if(root == null) return null;
		if(key==null)  throw new RuntimeErrorException(null);
		myNode found = searching(root, key);
		if(found == null )return null;
		return (V) found.getValue();
	}

	@Override
	public boolean contains(T key) {
		if(root == null) return false;
		if(key==null)  throw new RuntimeErrorException(null);
		myNode found = searching(root, key);
		if(found == null )return false;
		return true;
	}

	@Override
	public void insert(T key, V value) {
		if(key==null || value==null)  throw new RuntimeErrorException(null);

		if(root==null) {
			myNode node=new  myNode (key, value);
			root=node;
			root.setColor(false);
			size++;
			return;
		}else {
			myNode t=searching(root, key);
			if(t!=null) {
				t.setValue(value);
				return ;
			}

			myNode k=insertion(root, key, value);
			k=arr.get(0);
			size++;
			if(k.getParent().getColor()) {

				rotate(k);
			}


		}

	}
	@Override
	public boolean delete(T key) {
		if(key == null ) throw new RuntimeErrorException(null);
		if(root ==null) throw new RuntimeErrorException(null);
		myNode theOne =searching(root, key);
		if(theOne == null) return false;

		if(theOne.getRightChild()!=null && theOne.getLeftChild() != null) {   //if the node has two childs
			myNode swaping=predecessor(theOne) ;
			T k=(T) theOne.getKey();
			V v=(V) theOne.getValue();
			theOne.setKey(swaping.getKey());
			theOne.setValue(swaping.getValue());
			swaping.setKey(k);
			swaping.setValue(v);
			delete(key);
		}else if(theOne.getRightChild()!=null) {  //if the node has only Right child
			if(theOne.getColor()) {      //the color is red
				setChild(theOne, theOne.getRightChild());
			}else {                    //the color is black
				setChild(theOne, theOne.getRightChild());
				theOne.getRightChild().setColor(false);
				//*jbkbkbkjbkbkjbbbbbbbbbbbbbbbb
			}
			return true;
		}else if(theOne.getLeftChild()!=null) { //if the node has only Left child
			if(theOne.getColor()) {      //the color is red
				setChild(theOne, theOne.getLeftChild());
			}else {                    //the color is black
				setChild(theOne, theOne.getLeftChild());
				theOne.getLeftChild().setColor(false);
				//bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
			}
			return true;
		}else {                                          //if the node has No childs
			if(theOne.getColor()) {      //the color is red
				setChild(theOne, theOne.getRightChild());
				return true;
			}else {                    //the color is black
				//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
			}
		}


		return false;
	}

	public void setChild(myNode theOne,INode child) {
		if(theOne.getParent()==null) {
			root=(myNode) child;
		}
		else if(theOne.getParent().getLeftChild()==theOne)
			theOne.getParent().setLeftChild(child);
		else {theOne.getParent().setRightChild(child);}
		if(child != null)child.setParent(theOne.getParent());
	}

	public myNode predecessor(myNode node) {
		myNode temp=(myNode) node.getLeftChild();
		while(temp.getRightChild()!=null) {
			temp=(myNode) temp.getRightChild();
		}
		return temp;

	}
	public myNode searching(myNode node, T key) {
		if(node == null) return null;

		if( node.getKey()==key) return node;
		while(true){
			int k = key.compareTo((T) node.getKey());
			if(k==0) return node;
			else if( k > 0 ) {
				if(node.getRightChild()!=null){

					node= (myNode) node.getRightChild();
				}else return null;
			}else {
				if(node.getLeftChild()!=null){
					node= (myNode) node.getLeftChild();
				}else return null;
			}
		}
//		if( k > 0 ) {
//
//			return searching((myNode) node.getRightChild() , key);
//		}
//			return searching((myNode) node.getLeftChild() , key);
	}


	public myNode insertion(myNode node,T key,V value) {
		myNode temp;
		if(node == null) {
			node = new myNode(key,value);
			temp=node;
			if(arr.size()==0) arr.add((myNode) node);
			else arr.set(0,(myNode) node);
			count=1;
//			System.out.println("her is the last node"+arr.get(0).getKey());
			return node;
		}
		int k = key.compareTo((T) node.getKey());
		if( k > 0 ) {
			node.setRightChild(insertion((myNode) node.getRightChild() , key,value));
			node.getRightChild().setParent(node);
		}else {
			node.setLeftChild(insertion((myNode) node.getLeftChild() , key,value));
			node.getLeftChild().setParent(node);
		}
		int c =0;
//		if(node != null) {
//			if(arr.size()<=2) {
//				arr.add((myNode) node);
////				if(arr.size()==1)
////				System.out.print("sdsdsdsdsd"+node.getParent().getKey());
////				arr.get(0).setParent(node);
//			}
//			else arr.set(count, (myNode) node);
//			if(count==2 && arr.get(count-1)!=null && node.getLeftChild()!=null) {
//				if(arr.get(count-1).getKey()!=node.getLeftChild().getKey()) {
//					if(arr.size()==3)arr.add((myNode) node.getLeftChild());
//					else arr.set(3, (myNode) node.getLeftChild());
//				}
//
//
//			}
//			 if(count==2 &&arr.get(count-1)!=null && node.getRightChild()!=null) {
//
//				if(arr.get(count-1).getKey()!=node.getRightChild().getKey()) {
//				if(arr.size()==3)arr.add((myNode) node.getRightChild());
//
//				else arr.set(3,(myNode) node.getRightChild());
//
//				}
//			 }
//			 count++;
//			 if(count == 3) {
//				 count=1;
//			 }
//
//		}
//		System.out.println(count );
//		System.out.println(arr.size());

		return node;
	}
	void inorderRec(myNode iNode) {
		if (iNode != null) {
			inorderRec((myNode) iNode.getLeftChild());
			System.out.print(iNode.getKey());
			inorderRec((myNode) iNode.getRightChild());
		}
	}

	public void rotate(myNode x) {
		boolean right,rightParent=false;
		myNode y = (myNode) x.getParent();
		myNode z= (myNode) y.getParent();
		myNode s;
		if(z==null)s=null;
		else if(z.getLeftChild()==y) {
			s=(myNode) z.getRightChild();
			rightParent=false;
		}
		else {
			s= (myNode) z.getLeftChild();
			rightParent=true;
		}
		if(y.getLeftChild()==x) {
			right=false;
		}else {right=true;}
		if(s==null || !s.getColor()) {
			if(!rightParent) {
				if(!right) {
					if(z.getParent()!=null&&z.getParent().getLeftChild()==z) {
						z.getParent().setLeftChild(y);
					}else if(z.getParent()!=null) z.getParent().setRightChild(y);
					y.setParent(z.getParent());
					if(z.getParent()==null)root=y;
					z.setParent(y);
					z.setLeftChild(y.getRightChild());
					y.setRightChild(z);
					y.setColor(false);
					z.setColor(true);

				}else{
					y.setRightChild(x.getLeftChild());
					z.setLeftChild(x);
					x.setParent(z);
					x.setLeftChild(y);
					y.setParent(x);

					rotate(y);
				}
			}
		}else{
			y.setColor(false);
			s.setColor(false);
			if(z.getParent()!=null) {
				z.setColor(true);
				if(z.getParent().getColor())rotate(z);
			}
		}
	}


	public myNode minNodeGreater(myNode node){
		myNode temp= (myNode) node.getRightChild();

		while(temp.getLeftChild()!=null){
			temp= (myNode) temp.getLeftChild();
		}
		return temp;
	}

}
