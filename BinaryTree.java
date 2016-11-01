public class BinaryTree<E> {
    private E root;
    private BinaryTree<E> left, right;


// Constructors
    public BinaryTree(E root, BinaryTree<E> left, BinaryTree<E> right) {
    this.root = root;
    this.left = left;
    this.right = right;
}
    public BinaryTree(E root) {
    this.root = root;
    this.left = null;
    this.right = null;
}




// Methods
      public E getRoot() {
    return this.root;
}
      public BinaryTree<E> getLeft() {
    return this.left;
}
      public BinaryTree<E> getRight() {
    return this.right;
}
      public E setRoot(E element) {
    E tmpElement = this.root;
    this.root = element;
    return tmpElement;
}
    public BinaryTree<E> setLeft(BinaryTree<E> tree) {
    BinaryTree<E> temp = left;
left = tree;
return temp;
}
    public BinaryTree<E> setRight(BinaryTree<E> tree) {
    BinaryTree<E> temp = right;
right = tree;
	return temp;
}
    public String toString() {
	    String test = "Printing tree";
	    return test;
}
      public boolean isLeaf() {
    if (left == null && right == null) {
        return true;
    } else {
        return false;
	}
      }
}
