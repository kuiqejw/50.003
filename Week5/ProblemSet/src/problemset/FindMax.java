package problemset;
public class FindMax {
	public static void main (String[] arg) throws Exception {
		System.out.println(max(new int[]{5,6,17,8,2}));
	}
	
	public static int max (int[] list) {
		int max = list[0]; 
		for (int i = 1; i < list.length-1; i++) {//length-1 is where it failed
			if (max < list[i]) {
				max = list[i];
			}
		}
		
		return max;		
	}
}
