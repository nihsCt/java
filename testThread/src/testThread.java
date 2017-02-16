import java.lang.Thread;
import java.lang.InterruptedException;

public class testThread {
	static int err = 100;
	static int addcounter = 0;
	static int subcounter = 0;
	public static void main(String[] args){
		Onethread one_t = new Onethread();
		Twothread two_t = new Twothread();
		Threethread thr_t = new Threethread();
		one_t.setName("SubOne");
		one_t.start();
		two_t.setName("SubTwo");
		two_t.start();
		thr_t.setName("SubThree");
		thr_t.start();
	}

	static class Onethread extends Thread{
		public void run(){

			for(int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}

		Onethread(){
			super();
		}
	}

	static class Twothread extends Thread{
		public void run(){
			for(int i = 0; i < 15; i++){
				System.out.println(Thread.currentThread().getName());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}


		Twothread(){
			super();
		}
	}

	static class Threethread extends Thread{
		public void run(){
			for(int i = 0; i < 15; i++){
				System.out.println(Thread.currentThread().getName());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}

		Threethread(){
			super();
		}
	}
	
}


