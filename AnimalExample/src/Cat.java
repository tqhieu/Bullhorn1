
public class Cat extends Animal {
	public Cat (){
		System.out.println("Now I am cat");
		}
	@Override
	public String sleep(){
		return "A cat sleep...";
	}
	@Override
	public String eat(){
		return "A cat eats...";
	}
	public String purr(){
		return "A cat purrs....";
	}
	}
