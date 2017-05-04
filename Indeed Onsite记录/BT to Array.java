/* =============================================================================
Question Description
=============================================================================*/
Given a tree, try to implemnt it using other data structures to reduce the overhead.
/* =============================================================================
code
=============================================================================*/
class TreeNode{
    int val;
    TreeNode left, right;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class BT_to_Array {
    public int[] compressDenseTree(TreeNode root){
        int height = getHeight(root);
        if (height == 0){
            return new int[0];
        }
        //dense tree的情况下,默认null node位置放0。(假设原来的tree里面没有0)
        int len = (int) Math.pow(2, height);
        int[] heap = new int[len];
        //BFS一下就压缩好了
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Queue<Integer> idxQueue = new LinkedList<>();
        //这里如果是1开头,那么就是(2i, 2i+1),如果是0开头,就是(2i+1,2i+2),其实1,2,3一下就看出来了。
        idxQueue.offer(1);

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            Integer curI = idxQueue.poll();
            heap[curI] = cur.val;
            if (cur.left != null){
                queue.offer(cur.left);
                idxQueue.offer(2*curI);
            }
            if (cur.right != null){
                queue.offer(cur.right);
                idxQueue.offer(2*curI+1);
            }
        }

        return heap;
    }
    public Map<Integer, Integer> compressSparseTree(TreeNode root){
        //前提假设是sparse tree,用map来记录,key是index,value是root的value
        Map<Integer, Integer> record = new HashMap<>();
        if (root == null) {
            return record;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> idxQueue = new LinkedList<>();
        queue.offer(root);
        idxQueue.offer(1);

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int idx = idxQueue.poll();
            record.put(idx, cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(2*idx);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(2*idx+1);
            }
        }
        return record;
    }
    public int getHeight(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right)+1;
    }

    public static void main(String[] args) {
        BT_to_Array test = new BT_to_Array();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        int[] res = test.compressDenseTree(t1);
        System.out.println(Arrays.toString(res));
        Map<Integer, Integer> resMap = test.compressSparseTree(t1);
        Iterator<Integer> ite = resMap.keySet().iterator();
        while (ite.hasNext()){
            int num = ite.next();
            System.out.println("root index is "+num + " root value is "+resMap.get(num));
        }
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
要求就是压缩压缩再压缩，这帮人是属鸭子的么就知道压。
已知的就是根据树的dense/sparse选择压缩方法。 <- 必须要和面试官去讨论树的形状。（就这两种）
直接压缩成数组感觉已经是极致了，至少是目前能想出来的最佳办法。
/* =============================================================================
Follow Up code
=============================================================================*/
目前已知想法就是提出不同的假设，比如上面只默认是dense tree，那么follow up的时候就可以写
sparse tree的压缩方法了。
另外假设没有duplicate，那么preorder和inorder组合起来也是个办法。
用map存的话，一对儿值分别是index和treeNode.val，也是8个byte。

还有一点在于每提出一个压缩方法，把消耗的内存讲清楚。
已经想不出其他什么办法了，到时候就靠讲简历和嘿嘿嘿拖时间吧，实在不行就忽悠。
/* =============================================================================
题目内容：
=============================================================================*/
现在有个树，用其他结构去存，尽可能的减少内存支出。因为每个node要存的话需要占12Byte。

先求深度，然后用2的深度次方获得数组的长度。BFS把每个node都塞到数组里面。

用一个数组去存，就是heap的实现方法。
和LC 297，但是这题重在压缩，不太看重恢复回来，所以297里面的很多和String相关的方法都没法用。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> 如何把binary tree放在array里表示
<B> 如何用ARRAY表示BT，参考HEAP
<C>  一个美国小哥，问如何把tree储存成array，楼主先用heap的结构存，然后写了代码，有个小小的bug，
    被面试官找到指出，愣是没发现。 然后follow up要求继续压缩，楼主又给了个inorder的形式，
    然后又尝试了各种hashmap，双数组的形式。然后还有五分钟，小哥说，I want challagen you more. 要求继续压缩。。实在想不出来。
<D> 面试官特别面善，一过来就一直对我笑。。
    稍微问了问resume，就做题了。题是把一个binary tree用一个好一点的data structure implement出来。
<E> 树 转成 数组 节约空间， 参考：考虑类似heap的构造方法
<F> 存树。用什么办法可以节省空间，如果比较full的tree，用heap的实现方式。比较sparse的tree就用tree本身。
    介于中间的可以用两个数组，一个表示value，一个表示这个节点在第一种表示方式下的index。
<G> 给了一个树，如果储存为struct的形式需要占用12byte的内存，那如果内存不够，你应该把这个树的结构转化成什么储存起来呢？
    2*i+1; 2*i+2
详细讨论 http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171444&extra=page%3D1%26filter%3Dsortid%2
6sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dc
heckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D26%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
（链接真长）

（文件里面的13题和这个一样。）
给定一个BST的node class，里面有左子树，右子树还有一个int的信息，问如何使用一种方式减少这个Node的Overhead
overhead是经费的意思，所以这题也是这道题。
这题没get到点，啥玩意。这道题是2016年11月18号的电面题。
出处 http://www.1point3acres.com/bbs/thread-212498-1-1.html
后来这人说，就是BT to Array的题目