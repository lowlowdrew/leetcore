import java.util.*;

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


}
