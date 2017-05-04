public class WordLadder{
	public int ladderLength(String start, String end, Set<String> dict) {
//no words in dictionary
		if(dict.size()==0){
			return 0;
		}
//queue to store words and distances from starting word
		Queue<String> words = new LinkedList<String>();
		Queue<Integer> dist = new LinkedList<Integer>();
//initial condition
		words.offer(start);
		dist.offer(1);
		while(!words.isEmpty()){
			String curr = words.poll();
			int currDist = dist.poll();
			int len = curr.length();
//find target
			if(curr.equals(end)){
				return currDist;
			}
//try each position
			for(int i=0; i<len; i++){
				char[] currChar = curr.toCharArray();
//try each character
				for(int j='a'; j<='z'; j++){
					currChar[i] = (char)j;
					String temp = new String(currChar);
//intermediate word is in dictionary
					if(dict.contains(temp)){
						words.offer(temp);
						dist.offer(currDist+1);
						dict.remove(temp);
					}
				}
			}
		}
		return 0;
	}
}