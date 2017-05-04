Given two pre-order traversal arrays of two binary search tree respectively, find first pair of non-matching leaves. 
先建树，然后再找O(n)
public int[] findNotMatching(int[] nums1,int[] nums2){
    TreeNode root1=contructTree(nums1);
    TreeNode root2=contructTree(nums2);
    int[] res=new int[2];
    List<Integer> res1=new ArrayList<>();
    List<Integer> res2=new ArrayList<>();
    helper(root1,res1);
    helper(root2,res2);
    for(int i=0;i<Math.min(res1.size(),res2.size());i++){
        if(res1.get(i)!=res2.get(i)){
            res[0]=res1.get(i);
            res[1]=res2.get(i);
            break;
        }
    }
    return res;
}
private static TreeNode contructTree(int[] nums) {
    TreeNode root = new TreeNode(nums[0]);
    for (int i = 1; i<nums.length; i++) {
        construct(root, nums[i]);
    }
    return root;
}
private static void construct(TreeNode node, int val) {
    if(val < node .val) {
        if(node.left == null) {
            node.left = new TreeNode(val);
        } else construct(node.left, val);
    }
    if (val > node.val) {
        if (node.right == null) {
            node.right = new TreeNode(val);
        } else construct(node.right, val);
    }
}
public void helper(TreeNode root,List<Integer> res){
    if(root==null) return;
    if(root.left==null&&root.right==null) res.add(root.val);
    helper(root.left,res);
    helper(root.right,res);
}


Follow Up: If they are general binary trees instead of BSTs, could you solve it? give out your reason.
    
        int[] res=new int[2];
        String[] str1=string1.split("null,null");
        String[] str2=string2.split("null,null");
        int count=0;
        if(str1.length<=str2.length) count=str1.length;
        else count=str2.length;
        for(int i=0;i<count;i++){
            char c1=str1[i].charAt(str1[i].length()-2);
            char c2=str2[i].charAt(str2[i].length()-2);//逗号
            if(c1!=c2){
                res[0]=c1-'0';
                res[1]=c2-'0';
                break;
            }
        }
