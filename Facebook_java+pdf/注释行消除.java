        boolean commentStringStart = false; 
		boolean commentStringEnd = false; 
		String finalString = "";
		for(int i = 0; i < str.length ; i++) {
			if(str[i] == '/') {
				if(str[i+1] == '*') {
					commentStringStart = true;
				}
			}
			if(str[i] == '*') {
				if(str[i+1] == '/' && commentStringStart) {
					commentStringEnd = true;
					i = i + 1;
					continue;
				}
			}
			if(!commentStringStart || commentStringEnd) {
				finalString = finalString + str[i];
			}
			
		}
		
		return finalString;