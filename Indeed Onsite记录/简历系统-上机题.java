/* =============================================================================
题目内容：
=============================================================================*/
说实现一个简历的系统，3个API
1) update(String profileId, String field, String value); //这时候版本要+1
2) get(String profileId, int version); //找对应版本的field和value
3) getField(String profileId, int version, String field); //找对应的value

一上来没什么想法，只能硬做。写一个类，里面包含四个变量，profileId, field, value还有version
然后建map，key是id，value就是后面的三个东西吧。
value应该也弄个map，key是version，value再来个map，key是field。
真是圈套圈。
目前能想到的就这么多了。
/* =============================================================================
地里面经总结
=============================================================================*/
HackerRank 新题，我的解法不够优，很多大数据点超时了。大概是一个简历的系统，实现三个 API
1) update(String profileId, String field, String value);
2) get(String profileId, int version);
3) getField(String profileId, int version, String field);

每个用户有一个 profile，然后 profile 里有各种 field 和对应的 value，第一次 update 之后的 version 是 1，
再 update version 变成 2，依此类推。
虽然是 online HackerRank，但是是有面试官坐旁边随时解答问题的。我习惯了 HackerRank 就闷头做题，想了两个思路，
没有和面试官交流就直接写了一种（他倒也说了不用和他讲思路，直接做题就好）。