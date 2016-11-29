
public class Bird extends Animal {
	public Bird (){
		System.out.println("Now I am bird");
		}
	@Override
	public String sleep(){
		return "A Bird sleep...";
	}
	@Override
	public String eat(){
		return "A Bird eats...";
	}
	public String purr(){
		return "A Bird fly....";
	}
	}
