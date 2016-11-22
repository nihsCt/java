import java.lang.Thread;

public class testThread {
	public static void main(String[] args){
		Subthread sub = new Subthread();
		Subthread thr = new Subthread();
		sub.setName("Sub Thread");
		thr.setName("Third Thread");
		sub.start();
		thr.start();
		for(int i = 0; i < 10; i++){
			System.out.println(Thread.currentThread().getName());
		}
		
		
		
	}
	
	
}
class Subthread extends Thread{
	public void run(){
		for(int i = 0; i < 10; i++)
			System.out.println(Thread.currentThread().getName());
	}
	
	Subthread(){
		super();
	}
}


