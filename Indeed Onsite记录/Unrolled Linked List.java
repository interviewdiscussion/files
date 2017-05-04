/* =============================================================================
Question
=============================================================================*/
Given a LinkedList, every node contains a array. Every element of the array is char
implement two functions
1. get(int index) find the char at the index
2. insert(char ch, int index) insert the char to the index
/* =============================================================================
code思路
=============================================================================*/
class Node{
    char[] chars = new char[5]; //定长5,反正总要有定长。
    int len; //表示数组里面实际有几个字母
    Node next;
    public Node(){}
}
class LinkedList{
    Node head;
    int totalLen; //这个totalLen代表所有char的个数
    public LinkedList(Node head, int total){
        this.head = head;
        this.totalLen = total;
    //可能totalLen是不给的，要遍历一遍去求。
        int count = 0;
        Node cur = head;
        while (cur != null) {
            count += cur.len;
            cur = cur.next;
        }
        totalLen = count;
    }

    public char get(int index){
        if (index < 0 || index >= totalLen || totalLen == 0) {
            return ' ';
        }
        Node cur = head;
        while(cur != null && index >= 0){
            if (index >= cur.len) {
                index -= cur.len;
            }
            else {
                return cur.chars[index];
            }
            //总忘了往后爬一步cur
            cur = cur.next;
        }

        return ' ';
    }
    //insert需要考虑1.普通插进去。2.插入的node满了，要在后面加个node。
    //3.插入的node是空的，那就要在尾巴上加个新node。
    //还需要考虑每个node的len，以及totalLen的长度变化。
    public void insert(char ch, int index){
        if (index > totalLen) {
            return;
        }
        Node cur = head;
        while(cur != null && index >= 0){
            if (index >= cur.len) {
                index -= cur.len;
            }
            else {
                if (cur.len == 5) {
                    Node newNode = new Node();
                    newNode.chars[0] = cur.chars[4];
                    newNode.len = 1;
                    newNode.next = cur.next;
                    cur.next = newNode;
                    cur.len -= 1;
                }
                cur.len += 1;
                for(int i = cur.len-1; i > index; i--){
                    cur.chars[i] = cur.chars[i-1];
                }
                cur.chars[i] = ch;
                break;
            }
            cur = cur.next;
        }
        if (cur == null) {
            Node newNode = new Node();
            newNode.chars[0] = ch;
            newNode.len = 1;
            Node tail = new Node();
            tail.next = head;
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = newNode;
        }
        totalLen += 1;
    }
}

//链表题到时候画一个下面的小case，就能对准index了。
    public static void main(String[] args) {
        Node n1 = new Node(); //a b
        Node n2 = new Node(); //b
        Node n3 = new Node(); //a b c d e

        n1.chars[0] = 'a';
        n1.chars[1] = 'b';
        n2.chars[0] = 'b';
        n3.chars[0] = 'a';
        n3.chars[1] = 'b';
        n3.chars[2] = 'c';
        n3.chars[3] = 'd';
        n3.chars[4] = 'e';

        n1.next = n2;
        n2.next = n3;
        n1.len = 2;
        n2.len = 1;
        n3.len = 5;
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
删除一个数怎么处理，需要注意的地方也就是如果node空了就删掉吧。
那就需要记录前一个node了，这样比较好删掉当前node。
/* =============================================================================
Follow Up code
=============================================================================*/
    //类似insert，先考虑清楚delete的情况
    //1.普通的去掉一个node里面的点。2.去掉node之后，这个点空了，那就把点删掉。
    //也要考虑每个node里面长度的变化。
public void delete(int index){
    if (index < 0 || index >= totalLen) {
        return;
    }
    Node prev = new Node();
    prev.next = head;
    Node cur = head;
    while(cur != null && index >= 0){
        if (index >= cur.len) {
            index -= cur.len;
        }
        else {
            if (cur.len == 1) {
                prev.next = cur.next;
            }
            else {
                for (int i = index; i < cur.len-1; i++) {
                    cur.chars[i] = cur.chars[i+1];
                }
                cur.len -= 1;
            }
        }

        prev = prev.next;
        cur = cur.next;
    }
    totalLen -= 1;
}
/* =============================================================================
题目内容：
=============================================================================*/
给出一个链表，这个链表里面每个Node都是一个数组，存的是char。
实现两个方法，一个是get，一个是insert。

其实两个类就行，还有就是insert的时候可能有第一个node就是空的情况。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> 给一个链表，每个Node里有定长的数组，存的是char，然后问的就是关于插入删除等等一系列的操作，面经原题，
    关键点在于操作的时候要考虑node为空的或者满了的情况。
<B> 一个链表 每个node有一个array， 让你实现 查找 插入 参考: 这个没啥太复杂的吧，主要就是list的添加/删除节点啥的
<C> 给了一个自定义的数据结构，是一个链表，链表的每个节点是一个array，要求实现插入删除操作，很简单，直接travel就好。
<D> 问的题是implement linked list with arraylist. 他让写两个method，get和insert。get给一个index，
    你就check每个node接的那个array里面有多少个character，数一数，数到index所在的那个node然后就输出了。
    insert给一个index，一个char，这个稍微麻烦一点，先找到你要插入char的那个node，看它是不是满了，
    如果满了就新建一个node，然后接到原来的node后面。
<E> Given a linked list, of which all the nodes have an array of integers,
    as well as the current number of ints in that array, write two functions.
    The first one is get a number of a specific index,
    the second one is to insert a number at certain index. Not a hard problem but be careful with edge cases.
<F> linked list，但是每个节点是一个class，class里有linked list node还有size等attribute。让实现插入删除操作
<G> Manager 面的，然后他自己定义了一个结构类似Hash Table with linked list的结构，（LRU？）
    然后让你写一个function插入一个数，然后继续问删除一个数
<H> linkedelist中的每个节点里存了个固定长度的数组，但是数组未必满。
    进行插入操作的时候，如果要插入的节点的数组满了，可以考虑新建个节点插当前节点的数组的溢出的元素。
<J> Unrolled Linked List，有以下数据结构：
    class Node {
            char[] chars = new char[5]; //固定大小5
            int len;
    }
    class LinkedList {
            Node head;
            int totalLen;
    }
    实现以下成员函数：char get(int index), void insert(char ch, int index)
    get比较容易，就是从head traverse，定位第index个char；insert有点麻烦，有几种情况需要考虑。
    时间有点不太够，所以insert函数没完全实现 T.T
<K> 第一个是设计一个fixed size cache的数据结构。（这句话不知所云）