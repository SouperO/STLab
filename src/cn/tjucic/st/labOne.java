package cn.tjucic.st;
import java.util.*;


public class labOne {
	
	public int testMoney(int money) {
		if(money < 0) //输入金额小于0则直接返回0
			return 0;
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();//用map储存口袋里包含的钱的面值和数量
		//本题要求的数据如下，可根据需要修改
		map.put(50, 1);
		map.put(20, 1);
		map.put(5, 2);
		map.put(1, 3);
		
		//对map中的键值进行从大到小的排序
		Set<Integer> tempkeys = map.keySet();
		Set<Integer> keys = sortSet(tempkeys);
		Iterator<Integer> it = keys.iterator();
		
		//从大到小判断金额面值是否满足，不满足则直接返回0，可行则最终返回1
		while(it.hasNext()) {
			int key = it.next();
			int value = map.get(key);
			if(money / key > value) {
				return 0;
			}
			else {
				money = money % key;
			}
		}
		return 1;
	}
	
	//集合元素降序排序
	private Set<Integer> sortSet(Set<Integer> set){
        Set<Integer> sortS = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);//降序排列
            }
        });
        sortS.addAll(set);
        return sortS;
	}
	
}
