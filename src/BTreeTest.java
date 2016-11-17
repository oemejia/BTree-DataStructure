/***********************************************
** Simple Program to implement insertions     **
** and traversal on B-trees                   **
** Main program to build and print B-tree     **
** Programmed by Olac Fuentes                 **
** Last modified February 23, 2015            **
** Report bugs to me                          **
************************************************/

import java.util.*;

/*[CS2302 Spring 2015]
 * Lab03
 * Omar Mejia
 * Professor: Dr. Olac Fuentes
 * TA: Jesus Medrano
 * Last modified March 05, 2015
 * In this lab we modified a given BTree code to understand the execution and implementation of different
 * BTree algorithms.  
 */

public class BTreeTest{

	public static void main(String[] args)   {
		Random generator = new Random();
		BTree T = new BTree(3);
		BTree T2 = new BTree(3);

		for (int i=0;i<20;i++){
			T.insert(generator.nextInt(20));
		}

		/** Prints random nodes **/
		System.out.println("Random Nodes:");
		T.printNodes();
		System.out.println();


		/** Part 1 **/
		System.out.println("Checking if value 10 is already in tree");
		if(!T.isMember(10)){
			T.insert(10);
			System.out.println("Inserted 10\n ");//  T.insert(10));
		}
		if(!T.isMember(21)){
			T.insert(21);
			System.out.println("Inserted 20");
		}



		
		/** Part 4a **/
		System.out.println("\nKeys in descending order:");
		T.descending();

		//T.printDescending();
		
		/** Part 4c **/
		System.out.println("\n\nThe minimum element in the tree is " + T.minimumElement() + "\n");

		/** Part 4d **/
		System.out.println("The maximum element in the tree is " + T.maximumElement() + "\n");

		/** Part 4e **/
		System.out.println("The number of nodes in the tree is " + T.numNodes(0) + "\n");

		/** Part 4f **/
		System.out.println("The number of keys in the tree is " + T.numKeys() + "\n");

		/** Part 4h **/
		T.numLeaves();
		System.out.println();

	}
}
