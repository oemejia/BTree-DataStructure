/**********************************************
** Simple Program to implement insertions    **
** and traversal on B-trees                  **
** This file includes the basic B-tree class **
** Programmed by Olac Fuentes                **
** Last modified  February 23, 2015          **
** Report bugs to me                         **
**********************************************/

public class BTree{
	private BTreeNode root;
	private int T; //2T is the maximum number of children a node can have
	private int height;
	private int updateKeys;
	private int numberNodes;
	public BTree(int t){
		root = new BTreeNode(t);
		T = t;
		height = 0;
		updateKeys = 0;
		numberNodes = 0;
	}

	public void printHeight(BTreeNode T){
		System.out.println("Tree height is "+height);
	}

	public void insert(int newKey){
		if (root.isFull()){//Split root;
			split();
			height++;
		}
		root.insert(newKey);
	}


	/** Part 4b **/
	public void descending(){
		root.descending();
	}
	
	

	public void printNodes(){
		// Wrapper for node print method
		root.printNodes();
	}

	/** Part 4c **/
	public boolean isMember(int k){
		return root.isMember(k);
	}

	/** Part 4d **/
	public int minimumElement(){
		return root.minimumElement();
	}


	/** Part 4e **/
	public int maximumElement(){
		//Wrapper for maximumElement
		return root.maximumElement();
	}

	/** Part 4f **/
	public int numNodes(int tmp){
		//Wrapper for number of nodes
		return (root.numNodes(tmp) + 1);
	}

	/** Part 4g **/
	public int numKeys(){
		//wrapper for numKeys method
		return root.numKeys();
	}

	/** Part 4h **/
	public void numLeaves(){
		//Wrapper for numLeaves method
		root.numLeaves();
	}


	public void split(){
		// Splits the root into three nodes.
		// The median element becomes the only element in the root
		// The left subtree contains the elements that are less than the median
		// The right subtree contains the elements that are larger than the median
		// The height of the tree is increased by one

		//System.out.println("Before splitting root");
		//root.printNodes(); // Code used for debugging
		BTreeNode leftChild = new BTreeNode(T);
		BTreeNode rightChild = new BTreeNode(T);
		leftChild.isLeaf = root.isLeaf;
		rightChild.isLeaf = root.isLeaf;
		leftChild.n = T-1;
		rightChild.n = T-1;
		int median = T-1;
		for (int i = 0;i<T-1;i++){
			leftChild.c[i] = root.c[i];
			leftChild.key[i] = root.key[i];
		}
		leftChild.c[median]= root.c[median];
		for (int i = median+1;i<root.n;i++){
			rightChild.c[i-median-1] = root.c[i];
			rightChild.key[i-median-1] = root.key[i];
		}
		rightChild.c[median]=root.c[root.n];
		root.key[0]=root.key[median];
		root.n = 1;
		root.c[0]=leftChild;
		root.c[1]=rightChild;
		root.isLeaf = false;
		//System.out.println("After splitting root");
		//root.printNodes();
	}
}

