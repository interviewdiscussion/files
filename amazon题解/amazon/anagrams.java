public class anagrams {
   public List<String> anagrams(String[] strs){
		List<String> result = new ArrayList<String>();
		if(strs==null || strs.length==0){
			return result;
		}
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		int len = strs.length;
		for(int i=0; i<len; i++){
			//sort the string
			char[] c = strs[i].toCharArray();
			Arrays.sort(c);
			String s = new String(c);
			//store sorted string and its anagrams
			if(!map.containsKey(s)){
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(strs[i]);
				map.put(s,anagrams);
			}else{
				map.get(s).add(strs[i]);
			}
		}

		Iterator iter = map.values().iterator();
		while(iter.hasNext()){
			ArrayList<String> value = (ArrayList<String>)iter.next();
			//store strings with more than one anagrams
			if(value.size() > 1){
				result.addAll(value);
			}
		}
		return result;
	}
}