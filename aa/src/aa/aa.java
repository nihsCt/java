package aa;

public class aa {
	public static void main(String[] args){
		float a = -1.5e38f;
		float b = 1.5e38f;
		float c = 1f;
		
		System.out.println((a+b)+c);
		System.out.println(a+(b+c));
	}

}
