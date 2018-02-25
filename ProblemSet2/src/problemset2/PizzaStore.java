package problemset2;

public class PizzaStore {
    public static void main(String[] args){
        orderPizza("greek");
    }

	public static Pizza orderPizza (String type) {
		Pizza pizza;
		
		
		pizza = choose(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
//refactored: pizza choosing
        public static Pizza choose(String type){
            if (type.equals("cheese")) {
			return new CheesePizza();
		}
		if (type.equals("greek")) {
			return new GreekPizza();
		}
		if (type.equals("pepperoni")) {
			return new PepperoniPizza();
		}else{
                    System.out.println("Please enter valid Pizza name");
                    return null;
                }
        }
}


class Pizza {

	public void prepare() {
	}

	public void box() {
	}

	public void cut() {
	}

	public void bake() {
	}
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}

