public class LongestSubstringWithoutRepeatChar{
	public static void main(String[] args){
         System.out.println(longestSubstring("geeksforgeeks"));
	}

	public static int longestSubstring(String s){
		int len = s.length();
		int curr_len = 1;
		int max_len = 1;
		int prev_idx;
		int[] visited = new int[256];

		for(int i=0; i<256; i++){
			visited[i] = -1;
		}

		visited[s.charAt(0)] = 0;

		for(int i=1; i<len; i++){
			prev_idx = visited[s.charAt(i)];

			if(prev_idx==-1 || i-curr_len>prev_idx){
				curr_len ++;
			}else{
				if(curr_len > max_len){
					max_len = curr_len;
				}
				curr_len = i - prev_idx;
			}
			visited[s.charAt(i)] = i;
		}

		if(curr_len > max_len){
			max_len = curr_len;
		}
		return max_len;
	}
}