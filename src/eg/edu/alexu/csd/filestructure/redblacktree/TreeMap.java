package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.security.Key;
import java.util.*;
import java.util.Map.Entry;

public class TreeMap <T extends Comparable<T>, V> implements ITreeMap<T,V> {
	RedBlackTree <T,V>tree =new RedBlackTree<T,V>();

	@Override
	public Entry<T, V> ceilingEntry(T key) {
	    if(key ==null)  throw new RuntimeErrorException(null);
	    myNode ans=null;
		myNode found= tree.searching((myNode) tree.getRoot(),key);
		if(found!=null) {
		    ans =found;
        }else {
            myNode m = (myNode) tree.getRoot();
            ans= goToRight(m, key);
            if (ans == null) throw new RuntimeErrorException(null);
        }
        Map<Comparable, Object> maps = new HashMap<>();
        maps.put(ans.getKey(), ans.getValue());
        Set<Entry<Comparable, Object>> k = maps.entrySet();
        Iterator i = k.iterator();
        Map.Entry me = (Map.Entry) i.next();
		return me;
	}

    private myNode goToRight(myNode node,T key) {
        int k = key.compareTo((T) node.getKey());
	    if(node.getRightChild() ==null && node.getLeftChild()==null&& k > 0)return null;
        int t=0;
        if(node.getLeftChild()!=null) t = key.compareTo((T) node.getLeftChild().getKey());

	    if(k<=0 && node.getLeftChild()==null || k<=0 && t>0){
	        return node;
        }
	    if(k>=0) return goToRight((myNode) node.getRightChild(),key);

	    else return goToRight((myNode) node.getLeftChild(),key);
    }

    @Override
	public T ceilingKey(T key) {
        if(key ==null)  throw new RuntimeErrorException(null);
        myNode ans=null;
        myNode found= tree.searching((myNode) tree.getRoot(),key);
        if(found!=null) {
            ans =found;
        }else {
            myNode m = (myNode) tree.getRoot();
            ans= goToRight(m, key);
            if (ans == null) throw new RuntimeErrorException(null);
        }
        return (T) ans.getKey();
	}

	@Override
	public void clear() {
		tree.clear();
	}

	@Override
	public boolean containsKey(T key) {
		if(key ==null)throw new RuntimeErrorException(null);
		return tree.contains(key);
	}

	@Override
	public boolean containsValue(V value) {
		if(value==null)throw new RuntimeErrorException(null);
		ArrayList<V> ans= (ArrayList<V>) this.values();
		return ans.contains(value);
	}

	@Override
	public Set<Entry<T, V>> entrySet() {

		if(tree.getRoot()!=null) {
			ArrayList<T> ans = new ArrayList<T>();
			ArrayList<V> ans2 = new ArrayList<V>();
            Set<Entry<T, V>> hash = new LinkedHashSet<>();
			setOfKey((myNode) tree.getRoot(),ans);
			setOfVal((myNode) tree.getRoot(),ans2);
			for(int i = 0 ;i<ans.size();i++){
                HashMap<Comparable, Object> maps =new HashMap<>();
                maps.put(ans.get(i),ans2.get(i));
                Set<Entry<Comparable, Object>> k = maps.entrySet();
                Iterator j = k.iterator();
                Map.Entry me = (Map.Entry)j.next();
                hash.add(me);
			}

            return hash;
		}
		return null;
	}

	@Override
	public Entry<T, V> firstEntry() {
		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getLeftChild()!=null){
			node = (myNode) node.getLeftChild();
		}

		Map<Comparable, Object> maps =new HashMap<>();
		maps.put(node.getKey(),node.getValue());
		Set<Entry<Comparable, Object>> k = maps.entrySet();
		Iterator i = k.iterator();

		// Display elements
		Map.Entry me = (Map.Entry)i.next();

		return me;

	}

	@Override
	public T firstKey() {
		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getLeftChild()!=null){
			node = (myNode) node.getLeftChild();
		}
		return (T) node.getKey();
	}

	@Override
	public Entry<T, V> floorEntry(T key) {
        myNode ans=null;
        myNode m = (myNode) tree.getRoot();
        ans= floormax(m, key);
        if (ans == null) throw new RuntimeErrorException(null);
        Map<Comparable, Object> maps = new HashMap<>();
        maps.put(ans.getKey(), ans.getValue());
        Set<Entry<Comparable, Object>> k = maps.entrySet();
        Iterator i = k.iterator();
        Map.Entry me = (Map.Entry) i.next();
		return me;

	}
    private myNode floormax(myNode root , T key){
		myNode curr = root, ans = null;
		while (curr != null)
		{
			int k = key.compareTo((T) curr.getKey());
			if (k>=0)
			{
				ans = curr;
				curr = (myNode) curr.getRightChild();
			}
			else
				curr = (myNode) curr.getLeftChild();
		}
		if (ans != null)
			return ans;
		return null;
    }
	@Override
	public T floorKey(T key) {
        myNode ans=null;
        myNode m = (myNode) tree.getRoot();
        ans= floormax(m, key);
        if (ans == null) throw new RuntimeErrorException(null);
        return (T) ans.getKey();
	}

	@Override
	public V get(T key) {
		if(key==null)  throw new RuntimeErrorException(null);
		return tree.search(key);
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey) {
		if(tree.getRoot() ==null) return null;
		ArrayList<Entry<T, V>> ans = new ArrayList<Entry<T, V>>();
		 System.out.println(toKey);

		setOfMinKey((myNode) tree.getRoot(),ans,toKey,false);
		return ans;
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey, boolean inclusive) {
		if(tree.getRoot() ==null) return null;
		ArrayList<Entry<T, V>> ans = new ArrayList<Entry<T, V>>();
		System.out.println(toKey);
		setOfMinKey((myNode) tree.getRoot(),ans,toKey,inclusive);
		return ans;
	}

	@Override
	public Set<T> keySet() {
		SortedSet<T> hash = new TreeSet<>();
		if(tree.getRoot()!=null) {
			ArrayList<T> ans = new ArrayList<T>();
			setOfKey((myNode) tree.getRoot(),ans);
			System.out.println("first" + ans.get(0));

			for(int i = 0 ;i<ans.size();i++){
				hash.add(ans.get(i));
			}

			return hash;
		}
		return null;
	}

	@Override
	public Entry<T, V> lastEntry() {

		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getRightChild()!=null){
			node = (myNode) node.getRightChild();
		}

		HashMap<Comparable, Object> maps =new HashMap<>();
		maps.put(node.getKey(),node.getValue());
		Set<Entry<Comparable, Object>> k = maps.entrySet();
		Iterator i = k.iterator();

		// Display elements
			Map.Entry me = (Map.Entry)i.next();

		return me;
	}

	@Override
	public T lastKey() {
		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getRightChild()!=null){
			node = (myNode) node.getRightChild();
		}
		return (T) node.getKey();
	}

	@Override
	public Entry<T, V> pollFirstEntry() {

		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getLeftChild()!=null){
			node = (myNode) node.getLeftChild();
		}

		Map<Comparable, Object> maps =new HashMap<>();
		maps.put(node.getKey(),node.getValue());
		tree.delete((T) node.getKey());
		Set<Entry<Comparable, Object>> k = maps.entrySet();
		Iterator i = k.iterator();

		// Display elements
		Map.Entry me = (Map.Entry)i.next();

		return me;
	}

	@Override
	public Entry<T, V> pollLastEntry() {
		if(tree.isEmpty()) return null;
		myNode node= (myNode) tree.getRoot();
		while(node.getRightChild()!=null){
			node = (myNode) node.getRightChild();
		}

		Map<Comparable, Object> maps =new HashMap<>();
		maps.put(node.getKey(),node.getValue());
		tree.delete((T) node.getKey());
		Set<Entry<Comparable, Object>> k = maps.entrySet();
		Iterator i = k.iterator();
		Map.Entry me = (Map.Entry)i.next();

		return me;

	}
	@Override
	public void put(T key, V value) {

       tree.insert(key,value);
	}

	@Override
	public void putAll(Map<T, V> map) {
		if(map==null) throw new RuntimeErrorException(null);
		for (Entry<T, V> entry : map.entrySet()) {

			tree.insert(entry.getKey(),entry.getValue());
		}
	}

	@Override
	public boolean remove(T key) {
		// TODO Auto-generated method stub
        if(key == null) throw new RuntimeErrorException(null);
		return tree.delete(key);
	}

	@Override
	public int size() {
		return tree.size;
	}

	@Override
	public Collection<V> values() {
		if(tree.getRoot()!=null){
			ArrayList<V> ans=new ArrayList<V>();
			setOfVal((myNode) tree.getRoot(),ans);
			return ans;
		}
		return null;
	}
	void setOfVal(myNode iNode,ArrayList<V> ans) {
		if (iNode != null) {
			setOfVal((myNode) iNode.getLeftChild(),ans);
			ans.add((V) iNode.getValue());
			setOfVal((myNode) iNode.getRightChild(),ans);
		}
	}
	void setOfKey(myNode iNode,ArrayList<T> ans) {
		if (tree.getRoot() == null)
			return;
		Stack<myNode> s = new Stack<myNode>();
		myNode curr = (myNode) tree.getRoot();
		while (curr != null || s.size() > 0)
		{

			while (curr !=  null)
			{

				s.push(curr);
				curr = (myNode) curr.getLeftChild();
			}

			curr = s.pop();

			ans.add((T) curr.getKey());

			curr = (myNode) curr.getRightChild();
		}
	}
	void setOfMinKey(myNode iNode,ArrayList<Entry<T, V>>ans,T key,Boolean check) {
//	    if(key ==null )return ;
		int k = key.compareTo((T) iNode.getKey());
		if (iNode != null && k < 0 && !check) {
			setOfMinKey((myNode) iNode.getLeftChild(),ans,key,check);
			Map<Comparable, Object> maps =new HashMap<>();
			maps.put(iNode.getKey(),iNode.getValue());
            Set<Entry<Comparable, Object>> g = maps.entrySet();
            Iterator i = g.iterator();
            Map.Entry me = (Map.Entry)i.next();
			ans.add(me);
			setOfMinKey((myNode) iNode.getRightChild(),ans,key,check);
		}else if(check && iNode != null && k < 0){
			setOfMinKey((myNode) iNode.getLeftChild(),ans,key,check);
            Map<Comparable, Object> maps =new HashMap<>();
            maps.put(iNode.getKey(),iNode.getValue());
            Set<Entry<Comparable, Object>> g = maps.entrySet();
            Iterator i = g.iterator();
            Map.Entry me = (Map.Entry)i.next();
            ans.add(me);
			setOfMinKey((myNode) iNode.getRightChild(),ans,key,check);
		}
	}
}

