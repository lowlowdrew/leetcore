import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    int  max = 0;

    public int getMaxNumber(int[] nums, int n) {
          max = 0;
          Arrays.sort(nums);
          dfs(max,nums,n);

        return max;
    }

    private void dfs(int current ,int[]nums, int n){
        if(max>n){
            return;
        }
        max=Math.max(max,current);
        for(int i=nums.length-1;i>=0;i--){
            int next=current*10+nums[i];
            if(next<=n){
                dfs(next,nums,n);
            }
        }

    }

    /**
     * 最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int left=0;
        int right=0;
        int length=1;
        char [] charArray=s.toCharArray();
        Map<Character,Integer> charMap=new HashMap<>();
        while (right<s.length()){
            if(charMap.get(charArray[right])==null){
                charMap.put(charArray[right],1);
                length=Math.max(length,right-left+1);
                right++;
            }else {
            //abca
                //dvdf
                charMap.clear();
                left++;
                right=left;
            }
        }
        return length;
    }
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dumpNode = new ListNode(-1);
        dumpNode.next=head;
        ListNode pre = dumpNode;
        ListNode end= dumpNode;
        while (end!=null){
            for (int i = 0; i <k&&end.next!=null ; i++) {
                end=end.next;
            }
            ListNode startNode=pre.next;
            ListNode nextNode=end.next;
            end.next=null;
            pre.next=reverse(startNode);
            startNode.next=nextNode;
            pre=startNode;
            end=pre;
        }
        return dumpNode.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public String longestPalindrome(String s) {
        int i=0;
        int max=0;
        String longestString="";
        while (i<s.length()){
            int maxlen=getLenBymid(s,i,i);
            int maxle2=getLenBymid(s,i,i+1);
            max=Math.max(maxlen,maxle2);

            longestString=longestString.length()<s.substring(i-(max-1)/2,i+max/2+1).length()?s.substring(i-(max-1)/2,i+max/2+1):longestString;
            i++;
        }
        return longestString;
    }

    private int getLenBymid(String s,int mid,int mid2) {
        if(s.length()==0){
            return 0;
        }
        if(s.length()==1){
            return 1;
        }
        int i=mid;
        int j=mid2;
        while (i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
        return j-i-1;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(k);
        for (int i = 0; i <nums.length ; i++) {
            priorityQueue.add(nums[i]);
            if(priorityQueue.size()>k){
               priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper(nums, 0);
        return result;
    }

    private void subsetsHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }

    public int trap(int[] height) {
        if (height.length == 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int res = 0;
        while (i < height.length) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int topIndex = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                int leftIndex = stack.peek();
                int wide = i - leftIndex - 1;
                int hight = Math.min(height[leftIndex], height[i])-height[topIndex];
                res += wide * hight;
            }
            stack.push(i);
            i++;
        }
        return res;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            if(nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length -1;
            while (left < nums.length && right > 0&&left < right) {
                if (target == nums[left] + nums[right]) {
                    res.add(Arrays.asList(nums[left], nums[right], -target));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (target > nums[left] + nums[right]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public int search(int[] nums, int target) {
        if(nums.length==1&&nums[0]==target){
            return 0;
        }
        if(nums.length==0){
            return -1;
        }
        int left =0;
        int right= nums.length-1;
        int mid=(left+right)/2;

        while (left<=right){
            if(nums[mid]==target){
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            mid=(left+right)/2;
        }

        return -1;
    }



    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

    int res=0;
    List<String> strings= new ArrayList<>();
    public int sumNumbers(TreeNode root) {

        travel(root,new StringBuffer());
        return res;

    }
    public void travel(TreeNode root,StringBuffer s){
        if(root==null){
            return;
        }
        s.append(root.val);
        if(root.left==null&&root.right==null){
            strings.add(s.toString());
            res+=Integer.valueOf(s.toString());
            s.deleteCharAt(s.length()-1);
        }
        if(root.left!=null){
            travel(root.left,s);
        }
        if(root.right!=null){
            travel(root.right,s);
        }
    }

    int islandNum=0;
    public int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]=='1'){
                    islandNum++;
                }
                dfs(grid,i,j);
            }
        }

        return islandNum;
    }
    public void  dfs(char[][] grid,int i,int j){
        if(i>=grid.length||j>=grid[0].length||i<0||j<0){
            return;
        }
        if(grid[i][j]!='1'){
            return;
        }
        grid[i][j]='2';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    /**
     * 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2];
    }

    /**
     * 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue=new PriorityQueue<>((a,b)->{
            return a[0]==b[0]?b[1]-a[1]:b[0]-a[0];
        });
        for (int i = 0; i <k ; i++) {
            priorityQueue.offer(new int[]{nums[i],i});

        }
        int []res=new int [nums.length-k+1];
        res[0]=priorityQueue.peek()[0];
        for(int i=k;i<nums.length;i++){
            priorityQueue.offer(new int []{nums[i],i});
            while(priorityQueue.peek()[1]<=i-k){
                priorityQueue.poll();
            }
            res[i-k+1]=priorityQueue.peek()[0];
        }
        return res;
    }

    /**
     * 最小栈
     */
    class MinStack {

        Integer min = null;
        Stack<Long> data = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (data.isEmpty()) {
                data.push(0L);
                min = x;
            } else {
                //如果x是最小的数，这里可能越界，所以用Long来保存
                data.push(Long.valueOf(x) - min);
                min = Math.min(x, min);
            }
        }

        public void pop() {
            Long diff = data.pop();
            if (diff >= 0) {
                //return min + diff;
            } else {
                int res = min;
                min = (int) (min - diff);
                //return res;
            }
        }

        public int top() {
            Long diff = data.peek();
            if (diff >= 0) {
                return (int) (min + diff);
            } else {
                return min;
            }
        }

        public int getMin() {
            return min;
        }
    }

    int findKthLargestByHeap(int []nums, int k) {
        for (int i = 0; i <k ; i++) {
            swim(nums,i);
        }
        for (int i = k; i <nums.length ; i++) {
            if(nums[i]>nums[0]){
                swap(nums,0,i);
                sink(nums,0,k-1);

            }
        }
        return nums[0];
    }
    private  boolean priorityThan(int v1,int v2 ){
        return v1<v2;
    }
    private void swap(int [] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }
    public void swim(int [] nums,int i){
        while(i>0&&priorityThan(nums[i],nums[(i-1)/2])){
            swap(nums,i,(i-1)/2);
            i=(i-1)/2;
        }
    }
    public void sink(int heap[] ,int i,int N){
        while(2*i+1<=N){
            int j=2*i+1;
            if(j+1<=N&&priorityThan(heap[j+1],heap[j])){
                j++;
            }
            if(priorityThan(heap[i],heap[j])){
                break;
            }
            swap(heap,i,j);
            i=j;
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new LinkedList<>();
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> path=new LinkedList<>();
        boolean []v=new boolean[nums.length];
        permute(nums,res,0,path,v);
        return  res;
    }
    public void permute(int[] nums,List<List<Integer>> res,int index,Deque<Integer> path,boolean v[]) {
        if(index==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if (!v[i]) {
                path.addLast(nums[i]);
                v[i]=true;
                permute(nums, res, index + 1, path,v);
                v[i]=false;
                path.pollLast();
            }
        }
    }

    /**
     * 最近祖先
     */
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }




}
