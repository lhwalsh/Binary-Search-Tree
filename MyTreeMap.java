/* Luke Walsh
 * CS 241
 * 28 October 2016
 * Project 2
 */

/* Purpose of the project is to create a binary search tree by using a tree map.
 * The binary tree was provided, and the methods can be found on the website.
 * Also given pseudocode for the private methods, and a shell for the program.
 */

public class MyTreeMap<K extends Comparable<K>,V> implements MyMap<K,V> {
	private BinaryTree<Element> map;
	java.util.Set<K> keys;
	private int size = 0;

    	// returns a boolean if map contains an element with a matching key
	public boolean containsKey(K key) {
	    // used only as a parameter to the search method we call, because
	    // we can't use the key directly
	    Element temp = new Element(key, 0);
	    if (search(temp, map).compareTo(temp) == 0) {
		return true;
	    } else {
		return false;
	    }
	}
	public V put(K key, V value) {
	    size++;
	    //keys.add(key);
	    Element temp = new Element(key, value);
	    return (V)insert(temp).value;
	}
	public V get(K key) {
	    Element temp = new Element(key, 0);
	    return (V)search(temp, map).value;
	}
	public V remove(K key) {
	    size--;
	    keys.remove(key);
	    Element temp = new Element(key, 0);
	    if (map.getRoot().compareTo(temp) == 0)
		return (V)map.getRoot().value;
	    temp = delete(map, temp, map);
	    return (V)temp.value;
	}
	public String toString() {
		StringBuilder buf = new StringBuilder("" + map.getRoot());
 		if (map.getLeft() != null || map.getRight() != null) {
 			buf.append("(");
 			if (map.getLeft() != null) 
 				buf.append(map.getLeft().toString());
 			if (map.getRight() != null)
 				buf.append("," + map.getRight());
 			buf.append(")");
 		} 
 		return buf + "";
		//return null;
		
	}

	public int size() {
	    return size;
	}

	//calls private method inorder
	public java.util.Set<K> keySet() {
	
		return keys;
		
	}


	private Element search(Element element, BinaryTree<Element> tree) {
	    if (tree == null) {
		return null;
	    }
	    if (tree.getRoot().compareTo(element) == 0) {
		return tree.getRoot();
		    //element < map.getRoot()
	    } else if (element.compareTo(tree.getRoot()) < 0) {
		return search(element, tree.getLeft());
	    } else {
		return search(element, tree.getRight());
	    }
	}
	 private Element insert(Element element) {
	    if (map == null) {
		map = new BinaryTree<Element>(element);
		return map.getRoot();
	    } else {
		return insert(element, map);
	    }
	}
	private Element insert(Element element, BinaryTree<Element> tree) {
	    if (element.compareTo(tree.getRoot()) == 0) {
		return tree.setRoot(element);
	    } else if (element.compareTo(tree.getRoot()) < 0) {
		if (tree.getLeft() == null) {
		    BinaryTree<Element> temp = new BinaryTree<Element>(element);
		    return tree.setLeft(temp).getRoot();
		} else {
		    return insert(element, tree.getLeft());
		}
	    } else {
		if (tree.getRight() == null) {
		    BinaryTree<Element> temp = new BinaryTree<Element>(element);
		    return tree.setRight(temp).getRoot();
		} else {
		    return insert(element, tree.getRight());
		}
	    }
	}

	// element being deleted cannot be the root of the tree
	private Element delete(BinaryTree<Element> tree, Element element, BinaryTree<Element> parent) {
	    if (tree == null) {
		return null;
	    }
	    if (element.compareTo(tree.getRoot()) == -1) {
		return delete(tree.getLeft(), element, tree);
	    } else if (element.compareTo(tree.getRoot()) == 1) {
		return delete(tree.getRight(), element, tree);
	    } else {
		if (tree.isLeaf()) {
		    parent.setRight(null);
		    parent.setLeft(null);
		    return tree.getRoot();
		} else if (tree.getRight() != null && tree.getLeft() != null) {
		    BinaryTree<Element> inorderSuccessor = tree.getRight();
		    while (inorderSuccessor.getLeft() != null && inorderSuccessor.getLeft().getLeft() != null) {
		        inorderSuccessor = inorderSuccessor.getLeft();
		    }
		    if (inorderSuccessor.getRight().getLeft() == null) {
		        inorderSuccessor = inorderSuccessor.setLeft(null);
		    } else {
		        inorderSuccessor = inorderSuccessor.setLeft(inorderSuccessor.getLeft().getRight());
		    }
		    return tree.setRoot(inorderSuccessor.getRoot());
		} else if (tree.getLeft() != null) {
		    BinaryTree<Element> temp = tree;
		    parent.setLeft(null);
		    promote(tree, parent, tree.getLeft());
		    return temp.getRoot();
		} else {
		    BinaryTree<Element> temp = tree;
		    parent.setRight(null);
		    promote(tree, parent, tree.getRight());
		    return temp.getRoot();
		}
	    }
	}
// make newChild the appropriate (left or right) child of parent, if parent exists
	private void promote(BinaryTree<Element> tree, BinaryTree<Element> parent, BinaryTree<Element> newChild) {
	    if (parent.getRoot().compareTo(newChild.getRoot()) == 1) {
		parent.setLeft(newChild);
	    } else {
		parent.setRight(newChild);
	    }
	}

}

	// Element class
class Element <K,V> {
	    K key; V value;
	    public Element(K key, V value) {
		this.key = key;
		this.value = value;
	    }
	    // for x.compareTo(y)
	    // if x == y then returns 0
	    // if x < y then returns -1
	    // if x > y then returns 1
	    public int compareTo(Element that) {
		if (key.equals(that.key)) {
		   return 0;
		} else if (key.compareTo(that.key) < 0) {
		    return -1;
		} else {
		    return 1;
		}
	    }
	    public String toString() {
		return "key: " + key + " value: " + value;
	    }
}
