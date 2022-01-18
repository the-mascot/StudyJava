
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumber {

	public static void main(String[] args) {
		// �񺹿� ���� -- Set ��ü�� ���� ����
		
		Set<Integer> set=new HashSet<Integer>();
		
		while(set.size()<6) {
			set.add((int)(Math.random()*45)+1);	// 1���� (45+1)�̸��� ��
		}
		
		List<Integer> list=new ArrayList<Integer>(set);
		Collections.sort(list);
//		System.out.println(set);
		
		for(Integer ob:list) {
			System.out.print(ob+" ");
		}
		
	}

}
