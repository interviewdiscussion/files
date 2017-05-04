//Write a function that will take in email lists and 
//return a new email list that contains only the email addresses that existed in all lists


import java.util.*;
public class emailList{
	public static void main(String[] args){
		ArrayList<ArrayList<String>> emails = new ArrayList<ArrayList<String>>();
		ArrayList<String> l1 = new ArrayList<String>();
		l1.add("foo@amazon.com");
		l1.add("bar@amazon.com");

		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("jason@amazon.com");
		l2.add("bar@amazon.com");
		l2.add("foo@amazon.com");

		ArrayList<String> l3 = new ArrayList<String>();
		l3.add("foo@amazon.com");
		l3.add("jason@amazon.com");
		l3.add("bar@amazon.com");

		ArrayList<String> l4 = new ArrayList<String>();
		l4.add("bar@amazon.com");
		l4.add("foo@amazon.com");

		emails.add(l1);
		emails.add(l2);
		emails.add(l3);
		emails.add(l4);

		ArrayList<String> res = commonEmail(emails);
		for(String s: res){
			System.out.println(s);
		}
	}

	public static ArrayList<String> commonEmail(ArrayList<ArrayList<String>> emails){
		ArrayList<String> res = new ArrayList<String>();
		if(emails == null){
			return res;
		}
		int len = emails.size();
		if(len == 1){
			emails.get(0);
		}else if(len == 0){
			return res;
		}
		

		Set<String> intersection = new HashSet<String>(emails.get(0));
		Set<String> storage = new HashSet<String>();

		for(List<String> list: emails){
			storage.addAll(list);
			intersection.retainAll(storage);
			storage.clear();
			if(intersection.size() == 0){
				return res;
			}
		}
		return new ArrayList<String>(intersection);
	}

	public static ArrayList<String> findEmail(ArrayList<ArrayList<String>> in){
		ArrayList<String> res= new ArrayList<String>();
		if(in.size() == 0) return res;
		HashSet<String> set = new HashSet<String>();. 
		for(int i=0;i<in.get(0).size();i++) set.add(in.get(0).get(i));
			for(int i=1;i<in.size();i++){
				HashSet<String> tmp = new HashSet<String>();-google 1point3acres
				ArrayList<String> cur = in.get(i);
				for(int j=0;j<cur.size();j++){
					if(set.contains(cur.get(j))) tmp.add(cur.get(j));
				}
				set = tmp;. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
			}
			for(String s:set) res.add(s);
				return res;
		}


}