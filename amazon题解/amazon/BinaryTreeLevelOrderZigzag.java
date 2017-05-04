/**
* Problem: Zigzag level order traversal of a binary tree
*
* Solution: Use two stacks, one to store node in current level and another one to store nodes
* in next level. Use a boolean to flag if we will visit from right next time, if so
* push left child first and then right child. Alternate order after visiting each level.
*/
import java.util.*;
public class BinaryTreeLevelorderZigzag{
	public LinkedList<LinkedList<Integer>> zigzagLevelOrder(TreeNode root){
		LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();
		if(root == null){
			return result;
		}
		Stack<TreeNode> currLevel = new Stack<TreeNode>();
		currLevel.push(root);
		Stack<TreeNode> nextLevel = new Stack<TreeNode>();
		boolean nextFromRight = true;
          //traverse each level
		while(!currLevel.isEmpty()){
			LinkedList<Integer> levelResult = new LinkedList<Integer>();
            //for each node in current level, visit node and store child
			while(!currLevel.isEmpty()){
				TreeNode r = currLevel.pop();
				levelResult.add(r.val);
                 //if next time visit from right to left
                   //push left child first and then right child
				if(nextFromRight){
					if(r.left != null){
						nextLevel.push(r.left);
					}
					if(r.right != null){
						nextLevel.push(r.right);
					}
				}else{
//next time, visit from left to right
					if(r.right != null){
						nextLeve.push(r.right);
					}
					if(r.left != null){
						nextLevel.push(r.left);
					}
				}
			}
			result.add(levelResult);
//prepare for next level
			Stack<TreeNode> temp = currLevel;
			currLevel = nextLevel;
			nextLevel = temp;
//alternate order
			nextFromRight = !nextFromRight;
		}
		return result;
	}
}