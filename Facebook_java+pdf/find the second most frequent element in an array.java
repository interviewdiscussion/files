    public static int FrequentNumber(int[] a, int frequency) {
		HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		int temp;
		for (int i = 0; i < a.length; i++) {
			temp = 1;
			if (frequencyMap.containsKey(a[i])) {
				temp = frequencyMap.get(a[i]) + 1;
				frequencyMap.put(a[i], temp);
			} else {
				frequencyMap.put(a[i], temp);
			}
		}
		ArrayList<Integer> values = new ArrayList<Integer>(
				frequencyMap.values());
		Collections.sort(values);

		int index = values.get(values.size() - frequency);
		for (Entry<Integer, Integer> e : frequencyMap.entrySet()) {
			if (e.getValue().equals(index))
				return (int) e.getKey();
		}
		return 0;
	}