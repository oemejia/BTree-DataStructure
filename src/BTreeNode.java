/********************************************************
 ** Simple Program to implement insertions              **
 ** and traversal on B-trees                            **
 ** This file includes basic operations on B-tree nodes **
 ** Programmed by Olac Fuentes                          **
 ** Last modified February 23, 2015                     **
 ** Report bugs to me                                   **
 *********************************************************/
import java.util.Random;

public class BTreeNode{
	public int[] key;
	public BTreeNode[] c;
	boolean isLeaf;
	public int n;
	public int numKeys;
	private int T; //Each node has at least T-1 and at most 2T-1 keys
	public int numberNodes;
	public int numberLeaves;

	public  BTreeNode(int t){
		T = t;
		isLeaf = true;
		key = new int[2*T-1];
		c = new BTreeNode[2*T];
		n=0;	
		numKeys = 0;
		numberNodes = 0;
		numberLeaves = 0;
	}

	public boolean isFull(){
		return n==(2*T-1);
	}

	public void insert(int newKey){
		// Instert new key to current node
		// We make sure that the current node is not full by checking and
		// splitting if necessary before descending to node

		//System.out.println("inserting " + newKey); // Debugging code

		int i=n-1;
		if (isLeaf){
			while ((i>=0)&& (newKey<key[i])) {
				key[i+1] = key[i];
				i--;
			}
			n++;
			key[i+1]=newKey;
		}
		else{
			while ((i>=0)&& (newKey<key[i])) {
				i--;
			}
			int insertChild = i+1;  // Subtree where new key must be inserted
			if (c[insertChild].isFull()){
				// The root of the subtree where new key will be inserted has to be split
				// We promote the mediand of that root to the current node and
				// update keys and references accordingly

				//System.out.println("This is the full node we're going to break ");// Debugging code
				//c[insertChild].printNodes();
				//System.out.println("going to promote " + c[insertChild].key[T-1]);
				n++;
				c[n]=c[n-1];
				for(int j = n-1;j>insertChild;j--){
					c[j] =c[j-1];
					key[j] = key[j-1];
				}
				key[insertChild]= c[insertChild].key[T-1];
				c[insertChild].n = T-1;

				BTreeNode newNode = new BTreeNode(T);
				for(int k=0;k<T-1;k++){
					newNode.c[k] = c[insertChild].c[k+T];
					newNode.key[k] = c[insertChild].key[k+T];
				}

				newNode.c[T-1] = c[insertChild].c[2*T-1];
				newNode.n=T-1;
				newNode.isLeaf = c[insertChild].isLeaf;
				c[insertChild+1]=newNode;

				//System.out.println("This is the left side ");
				//c[insertChild].printNodes();	
				//System.out.println("This is the right side ");
				//c[insertChild+1].printNodes();
				//c[insertChild+1].printNodes();

				if (newKey <key[insertChild]){
					c[insertChild].insert(newKey);					}
				else{
					c[insertChild+1].insert(newKey);				}
			}
			else
				c[insertChild].insert(newKey);
		}
	}

	/** Method 4a **/
	public void descending(){
		//Prints all keys in the tree in descending order

		if (isLeaf){
			for(int i =n-1; i>=0 ;i--)
				System.out.print(key[i]+" ");
			System.out.println();
		}
		else{
			c[n].descending();
			for(int i =n-1; i>=0;i--){
				System.out.print(key[i]+" ");
				c[i].descending();
			}
		}
	}



	/** Part 4b **/
	public boolean isMember(int k){
		int ch = 0;// variable ch for child is initiated
		int i = 0;
		while(i < n){
			if(key[i] == k){//if key at index i is == k return true
				System.out.println("Value already in tree, will not proceed with insertion");
				return true;
			}
			if(key[i] < k){//if key at index i is less than k we set ch to i
				ch = i;
			}
			i++;//increment while i<n
		}
		if(isLeaf){//if it's already a leaf return false
			System.out.println("Value not found in tree, will proceed with insertion.");
			return false;
		}
		return c[ch].isMember(k);
	}

	/** Part 4c **/
	public int minimumElement(){
		if(isLeaf){// if true
			return key[0];// we return the value within key at index 0
		}
		else
			return c[0].minimumElement();//otherwise return the method until the first if statement is true
	}

	/** Part 4d **/
	public int maximumElement(){
		if(isLeaf){//if it is a leaf
			return key[n-1];//return key at index n-1
		}
		else
			return c[n].maximumElement();//otherwise recursively call at child[n]
	}


	/** Part 4e **/
	public int numNodes(int tmp){
		int i = 0;
		while(i < n){
			tmp = 1 + tmp;
			i++;
		}
		if(isLeaf){
			int j = 0;
			while(j <= n){
				c[j].numNodes(tmp);
			}
		}
		return tmp + 1;
	}

	/** Part 4f **/
	public int numKeys(){
		numberNodes = n;
		if(isLeaf)
			return n;
		else{
			int i = 0;
			while(i <= n){
				numberNodes += c[i].numKeys();
				i++;
			}
			numberLeaves = numberNodes - n;
			return numberNodes;
		}
	}

	/** Part 4h **/
	public void numLeaves(){
		System.out.println("This is the number of leaves " + numberLeaves);
	}



	public void printNodes(){
		//Prints all keys in the tree, node by node, using preorder
		//It also prints the indicator of whether a node is a leaf
		//Used mostly for debugging purposes
		for(int i =0; i<n;i++)
			System.out.print(key[i]+" ");
		System.out.println(isLeaf);
		if (!isLeaf){
			for(int i =0; i<=n;i++){
				c[i].printNodes();
			}
		}
	}

}
