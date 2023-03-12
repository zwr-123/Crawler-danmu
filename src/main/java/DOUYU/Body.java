package DOUYU;

public class Body {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://www.douyu.com/topic/DOTA2ZF?rid=1870001";
		barrageHandle bh=message -> System.out.println(message);
		new Connect(url,bh).start();

	}

}
