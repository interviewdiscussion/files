#include <queue>
#include <vector>
using namespace std;

// 1. Round Robin
class Process {
public:
	int arrtime;
	int exetime;
	Process(int _arr, int _exe): arrtime(_arr), exetime(_exe) {};
};

double roundRobin(vector<int> &atime, vector<int> &etime, int q) {
	if (atime.size() != etime.size()) {
		return 0;
	}
	queue<Process> processlist;
	processlist.push(Process(atime[0], etime[0]));
	int index = 1, waittime = 0, curtime = 0;
	while (!processlist.empty()) {
		Process cur = processlist.front();
		processlist.pop();
		waittime += (curtime - cur.arrtime);
		curtime += cur.exetime >= q ? q : cur.exetime;
		while (index < atime.size() && curtime >= atime[index]) {
			processlist.push(Process(atime[index], etime[index]));
			index++;
		}
		if (cur.exetime > q) {
			processlist.push(Process(curtime, cur.exetime - q));
		}
	}
	return (double)waittime / atime.size();
}

// 2. Rotate Matrix
/*
	flag == 1:
	1 2 3		7 4 1
	4 5 6	->  8 5 2
	7 8 9		9 6 3
	flag == 0:
	1 2 3		3 6 9
	4 5 6	->  2 5 8
	7 8 9		1 4 7
*/
// in place rotation
void rotate(vector<vector<int> > &matrix, int flag) {
	// write your code here
	if (matrix.empty() || matrix[0].empty()) {
		return;
	}
	int row = matrix.size(), col = matrix[0].size();
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (j > i) {
				swap(matrix[i][j], matrix[j][i]);
			}
		}
	}
	if (flag == 1) {
		for (int i = 0; i < row; i++) {
			reverse(matrix[i].begin(), matrix[i].end());
		}
	}
	if (flag == 0) {
		for (int j = 0; j < col; j++) {
			for (int i = 0; i < row / 2; i++) {
				swap(matrix[i][j], matrix[row-i-1][j]);
			}
		}
	}
}
// 3. Find minimum path sum of a binary tree
class TreeNode {
public:
	int val;
	TreeNode* left;
	TreeNode* right;
	TreeNode(int _val) : val(_val), left(NULL), right(NULL) {};
};
int minPath(TreeNode* root) {
	if (root == NULL) {
		return 0;
	}
	if (!root->left && !root->right) {
		return root->val;
	}
	int minSum = INT_MAX;
	stack<TreeNode*> pathNode;
	stack<int> pathSum;
	pathNode.push(root);
	pathSum.push(root->val);
	while (!pathNode.empty()) {
		TreeNode* curNode = pathNode.top();
		pathNode.pop();
		int curSum = pathSum.top();
		pathSum.pop();
		// when reach the leaves
		if (!curNode->left && !curNode->right) {
			if (curSum < minSum) {
				minSum = curSum;
			}
		}
		if (curNode->right) {
			pathNode.push(curNode->right);
			pathSum.push(curSum + curNode->right->val);
		}
		if (curNode->left) {
			pathNode.push(curNode->left);
			pathSum.push(curSum + curNode->left->val);
		}
	}
	return minSum;
}

int minPath(TreeNode *root) {
	// write your code here
   /* ResultType res = helper(root);
	return res.any2any;*/
	if (!root) {
		return 0;
	}
	if (!root->left && !root->right) {
	 return minPath(root->left) + root->val;
	}
	if (root->left == NULL && root->right != NULL) {
		return minPath(root->right) + root->val;
	}
	return min(minPath(root->left), minPath(root->right)) + root->val;
}

class ListNode {
public:
	int val;
	ListNode* next;
	ListNode(int _val) : next(NULL), val(_val){};
};

// 4. insert value into a circle linked-list
// the list is in ascending order.
// case 1 : pre->val ¡Ü x ¡Ü cur->val:
// Insert between pre and cur.
// case 2 : x is the maximum or minimum value in the list:
// Insert before the head. e.g. the head has the smallest value and its pre->val > head->val.
// case 3 : Traverses back to the starting point:
// case 4 : There is only one node in the list:
// Insert before the starting point.
ListNode* insertInCycle(ListNode* head, int target) {
	// creat the target node
	ListNode* newnode = new ListNode(target);
	// corner case
	if (!head) {
		newnode->next = newnode;
		return newnode;
	}
	// only one node.
	/*if (head->next == head) {
        head->next = newnode;
        newnode->next = head;
        return newnode;
	}*/
	// find the real head which is the smallest node.
	ListNode* realhead = head;
	while (true) {
		realhead = realhead->next;
		if (realhead->next->val < realhead->val) {
			break;
		}
		// run a circle cannot find min and max. 
		// case 3: all the elements are identical.
		// case 4: only one node.
		if (realhead == head) {
            ListNode* tmp =  realhead->next;
            realhead->next = newnode;
            newnode->next = tmp;
            return newnode;
		}
	}
	// save the end node which is the largest node.
	ListNode* pre = realhead;
	realhead = realhead->next;
	// case 2
	if (target < realhead->val || target > pre->val) {
		pre->next = newnode;
		newnode->next = realhead;
		return newnode;
	}
	// case 1
	while (target > realhead->val) {
		pre = realhead;
		realhead = realhead->next;
	}
	pre->next = newnode;
	newnode->next = realhead;
	return newnode;
}

// 5. greatest common divisor
/*
	time complexity: O(n) 
	space complexity: O(1)
*/
int gcd(int a, int b) {
    while (b) {
        int tmp = a % b;
        a = b;
        b = tmp;
    }
    return a;
}
int getGCD(vector<int> arr) {
    if (arr.empty()) {
        return 0;
    }
    int res = arr[0];
    for (int i = 1; i < arr.size(); i++) {
        if (arr[i] == 0) {
            return 0;
        }
        res = gcd(res, arr[i]);
    }
    return res;
}

// 6. Shortest Job First
// O(nlogn)
class cmpshort {
public:
    bool operator()(const Process &p1, const Process &p2) const {
        return p1.exetime > p2.exetime;
    }
};

double shortJobAverage(vector<int> &request, vector<int> &duration) {
    if (request.empty() || duration.empty() || request.size() != duration.size()) {
        return 0;
    }
    // min heap, save duration
    priority_queue<Process, vector<Process>, cmpshort> qduration;
    int curtime = 0, waittime = 0;
    qduration.push(Process(request[0], duration[0]));
    int index = 1;
    while (!qduration.empty()) {
        Process cur = qduration.top();
        qduration.pop();
        waittime += curtime - cur.arrtime;
        curtime += cur.exetime;
        while (index < request.size() && request[index] <= curtime) {
            qduration.push(Process(request[index], duration[index]));
            index++;
        }
    }
    return (double)waittime / request.size();
}

// 7. count miss
bool cacheHit(list<int> &cache, int val) {
    for (list<int>::iterator it = cache.begin(); it != cache.end(); it++) {
        if (*it == val) {
            return true;
        }
    }
    return false;
}
int countMiss(vector<int> &arr, int size) {
    int res = 0;
    if (arr.empty() || size < 1) {
        return res;
    }
    list<int> cache;
    int miss = 0;
    for (int i = 0; i < arr.size(); i++) {
        if (cacheHit(cache, arr[i])) {
            cache.remove(arr[i]);
            cache.push_back(arr[i]);
        } else {
            if (cache.size() == size) {
                cache.pop_front();
            }
            cache.push_back(arr[i]);
            miss++;
        }
    }
    return miss;
}

// 8. day change(cell growth)
vector<int> dayChange(vector<int> nums, int days) {
	vector<int> res;
	if (nums.empty()) {
		return res;
	}
	vector<int> pre = nums;
	int len = nums.size();
	for (int i = 0; i < days; i++) {
		vector<int> cur(len, 0);
		cur[0] = pre[1];
		cur[len - 1] = pre[len - 2];
		for (int j = 1; j < len - 1; j++) {
			cur[j] = pre[j - 1] ^ pre[j + 1];
		}
		pre = cur;
	}
	return pre;
} 

// 9. Find path in maze
bool isPath(int** grid, int i, int j, int row, int col) {
	if (i >= 0 && i < row && j < col && j >= 0 && grid[i][j]) {
		return true;
	}
	return false;
}
bool dfs(int** grid, int i, int j, bool** isvisited, int row, int col) {
	// if (i, j is goal) return true
	if (i >= 0 && i < row && j < col && j >= 0 && grid[i][j] == 9) {
		isvisited[i][j] = true;
		return true;
	}
	if (isPath(grid,i,j,row,col) && !isvisited[i][j]) {
		// mark i, j as part of solution path
		isvisited[i][j] = true;
		if (dfs(grid, i - 1, j, row, col)) {
			return true;
		}
		if (dfs(grid, i + 1, j, row, col)) {
			return true;
		}
		if (dfs(grid, i, j + 1, row, col)) {
			return true;
		}
		if (dfs(grid, i, j - 1, row, col)) {
			return true;
		}
		/* If none of the above movements work then BACKTRACK: 
            unmark i, j as part of solution path */
		visited[i][j] = false;
	}
	return false;
}
bool mazeCheese(int** grid, int row, int col) {
	if (grid == NULL) {
		return false;
	}
	bool** isvisited = new int*[row];
	for (int i = 0; i < row; i++) {
		isvisited[i] = new int[col];
	}
	return dfs(grid, 0, 0, isvisited, row, col);
} 

//10. find out number of arithmetic sequence in array
int arithSeq(vector<int> nums) {
	if (nums.size() < 3) {
		return 0;
	}
	int left = 0, right = 1;
	int diff = nums[right] - nums[left];
	int res = 0;
	while (right < nums.size() - 1) {
		if (diff != nums[right + 1] - nums[right]) {
			diff = nums[right + 1] - nums[right];
			res += (right - left - 1) * (right - left) / 2;
			if (res > 1000000000) {
				return -1;
			}
			left = right;
		}
		right++;
	}
	res += (right - left - 1) * (right - left) / 2;
	if (res > 1000000000) {
		return -1;
	}	
	return res;
}

// 11. Course Schedule II
// input:   4, [[1,0],[2,0],[3,1],[3,2]]
// output:  [0,1,2,3] or  [0,2,1,3]
vector<int> findOrder(int numCourses, vector<pair<int, int> >& prerequisites) {
	vector<int> res;
	vector<vector<int> > graph(numCourses, vector<int>());
	// the number of pre courses for each course.
	vector<int> indegree(numCourses, 0);
	// initialize the graph and indegree.
	for (int i = 0; i < prerequisites.size(); i++) {
		graph[prerequisites[i].second].push_back(prerequisites[i].first);
		indegree[prerequisites[i].first]++;
	}
	queue<int> q;
	for (int i = 0; i < numCourses; i++) {
		if (indegree[i] == 0) {
			q.push(i);
		}
	}
	while (!q.empty()) {
		int top = q.front();
		res.push_back(top);
		q.pop();
		for (int i = 0; i < graph[top].size(); i++) {
			int tmp = graph[top][i]; 
			indegree[tmp]--;
			if (indegree[tmp] == 0) {
				q.push(tmp);
			}
		}
	}
	if (res.size() == numCourses) {
		return res;
	}
	return vector<int>();
}

// 12. minimum spanning tree
class Connection{
public:
    string node1;
    string node2;
    int cost;
    Connection(string a, string b, int c){
		node1 = a;
		node2 = b;
		cost = c;
    }
}
map<string, string> root;
map<string, int> rank;
void unionTwo(string& node1, string& node2) {
	if (rank[node1] > rank[node2]) {
		root[node2] = node1;
	} else if (rank[node1] < rank[node2]) {
		root[node1] = node2;
	} else {
		root[node1] = node2;
		rank[node2]++;
	}
}
string find(string &node1) {
	if (root[node1] == node1) {
		return node1;
	}
	return find(root[node1]);
}
void makeSet(string node) {
	root[node] = node;
	rank[node] = 0;
}
bool cmp (const Connection &c1,const Connection &c2) {
	return c1.cost < c2.cost;
}
vector<Connection> getLowCost(vector<Connection> connections) {
	vector<Connection> res;
	if (connections.empty()) {
		return res;
	}
	for (int i = 0; i < connections.size(); i++) {
		makeSet(connections[i].node1);
		makeSet(connections[i].node2);
	}
	sort(connections.begin(), connections.end(), cmp);
	for (int i = 0; i < connections.size(); i++) {
		if (find[connections[i].node1] != find[connections[i].node2]) {
			res.push_back(connections[i]);
			unionTwo(connections[i].node1, connections[i].node2);
		}
	}
	return res;
}

// 13. High Five
class Result{
public:
    int id;
    int value;
    Result(int id, int value){
        this->id = id;
        this->value = value;
    }
};
bool cmp(const int a, const int b) {
	return a > b;
}
map<int, double> getHighFive(vector<Result> results){
    map<int, double> res;
    map<int, vector<int> > scores;
    for (int i = 0; i < results.size(); i++){
        int id = results[i].id;
        scores[id].push_back(results[i].value);
    }
    for (map<int, vector<int> >::iterator it = scores.begin(); it != scores.end(); it++){
        sort((*it).second.begin(), (*it).second.end(), cmp);
        double value = 0;
        for (int k = 0; k < (*it).second.size() && k < 5; k++){
            value += (*it).second[k];
        }
        value = value / 5.0;
        res[(*it).first] = value;
    }
    return res;
}

// company tree
class Node {  
public:
    int val;
    vector<Node> children;
    Node(int _val): val(_val){};
};
class SumCount{
public:
    int sum;
    int count;
    SumCount(int sum, int count){
        this->sum = sum;
        this->count = count;
    }
};

Node* getHighAve(Node* root){
	if (!root) {
		return null;
	}
	double resAve = DBL_MAX;
	Node result;
	dfs(root, result, resAve);
	return result;
} 
SumCount dfs(Node* root, Node &result, double &resAve){ 
	if (root->children.empty()){
		return SumCount(root->val, 1);
	} 
	int curSum = root->val;
	int curCnt = 1; 
	for (int i = 0; i < root->children; i++) {
		SumCount cSC = dfs(root->children[i], result, resAve); 
		curSum += cSC.sum;
		curCnt += cSC.count;
	}
	double curAve = (double) curSum/curCnt; 
	if (resAve < curAve){
		resAve = curAve;
		result = root;
	}

	return SumCount(curSum, curCnt);
}