
public class AnimalMain {

	public static void main(String[] args) {
		
		//creat a new animal
		//new will cause the constructor to run which prints
		//the line: A new animal has been created.
		Animal a = new Animal ();
		System.out.println(a.eat());
		System.out.println(a.sleep());
		
		Animal b = new Bird ();
		System.out.println(b.chirp());
		System.out.println(b.sleep());
		System.out.println(b.fly());
		//make it so the cat class print eat, sleep and purr
		
		//make a new class for bird that can also fly
	}
			

}
