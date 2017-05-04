/**
* Problem: Level order traversal of a binary tree
*
* Solution: for each level, use queuefind all nodes in the next level, then add current level to list
*/
import java.util.*;
public class treeLevelOrder{
	public LinkedList<LinkedList<Integer>> levelOrder(TreeNode root){
		LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();
		if(root == null){
			return result;
		}
		Queue<TreeNode> levelNode = new LinkedList<TreeNode>();
		levelNode.offer(root);
		int level = 0;
          //continue until no node at level
		while(levelNode.size() != 0){
			LinkedList<Integer> levelData = new LinkedList<Integer>();
            //store child nodes into levelNode
			int size = levelNode.size();
			for(int i=0; i<size; i++){
				TreeNode parent = levelNode.poll();
                //retrieve data
				levelData.add(parent.val);
				if(parent.left != null){
					levelNode.offer(parent.left);
				}
				if(parent.right != null){
					levelNode.offer(parent.right);
				}
			}
			//add level data to result list
			result.add(level,levelData);
			level++;
		}
		return result;
	}
}