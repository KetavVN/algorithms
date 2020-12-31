package stack;

/**
 * Cracking the coding interview Ch-3 Problem-6 - AnimalShelter
 * Design a data structure for Animal shelter 
 * - Animal shelter gives out animals in first come first out basis
 *  -- eitehr oldest animal is given out
 *  -- or odest of cat or dog is given out based on ask 
 */
public class AnimalShelter {

	public static class Animal {
		String name;
		boolean isDog;
		public Animal(String n, boolean d) {
			name = n;
			isDog = d;
		}
		@Override
		public String toString() {
			return String.format("name=%s isDog=%b", name, isDog);
		}
	}

	public static class AnimalNode {
		Animal animal;
		AnimalNode next;
		public AnimalNode() {}
		public AnimalNode(Animal a) {
			animal = a;
		}
	}

	private AnimalNode head = new AnimalNode();
	private AnimalNode tail = head;

	public void acceptAnimal(Animal a) {
		tail.next = new AnimalNode(a);
		tail = tail.next;
	}

	public Animal getOldestAnimal() {
		if(head.next == null) {
			return null;
		}
		AnimalNode ret = head.next;
		head.next = ret.next;
		tail = tail == ret ? head : tail;
		ret.next = null;
		return ret.animal;
	}

	public Animal getOldestAnimal(boolean isDog) {
		AnimalNode runner = head;
		while(runner.next != null && runner.next.animal.isDog != isDog) {
			runner = runner.next;
		}
		AnimalNode node = runner.next;
		if(node != null) {
			tail = tail == node ? runner : tail;
			runner.next = node.next;
			node.next = null;
			return node.animal;
		}
		return null;
	}

	public Animal getOldestDog() {
		return getOldestAnimal(true);
	}

	public Animal getOldestCat() {
		return getOldestAnimal(false);
	}

	public static void main(String [] args) {
		AnimalShelter shelter = new AnimalShelter();	//head and tail gets creaated
		shelter.acceptAnimal(new Animal("a", true));
		shelter.acceptAnimal(new Animal("b", true));
		shelter.acceptAnimal(new Animal("c", false));
		shelter.acceptAnimal(new Animal("d", true));
		shelter.acceptAnimal(new Animal("e", false));
		System.out.println(shelter.getOldestAnimal());
		System.out.println(shelter.getOldestCat());
		System.out.println(shelter.getOldestCat());
		System.out.println(shelter.getOldestDog());
		System.out.println(shelter.getOldestAnimal());
		System.out.println(shelter.getOldestAnimal());	//null
	}
}
