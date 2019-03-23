package java8.functional.interfaces.study;

import java.util.function.Supplier;

public class SuplierTest {
		
	static String getName(Supplier<String> fistName, Supplier<String> lastName) {
		return fistName.get() + " " + lastName.get();
	}

	public static void main(String[] args) {

		//1. Anonymous class implementation
		String annoName = SuplierTest.getName(new Supplier<String>() {

			@Override
			public String get() {
				return "FirstName";
			}
		}, new Supplier<String>() {

			@Override
			public String get() {
				return "LastName";
			}
		});
		
		System.out.println("Using Annonymous class : " + annoName);

		// Supplier test with lambda expression
		String name = SuplierTest.getName(() -> "FirstName", () -> "LastName");
		System.out.println("Lambda Function for supplier : "+ name);
		
		
		// Supplier is function interface having only one method T get()
		// Lambda function for supplier as variable
		Supplier<String> firstName = () -> {
			return "FirstName";
		};

		// Lambda function for supplier
		Supplier<String> lastName = () -> {
			return "LastName";
		};
		
		//3. Lambda function as parameter
		System.out.println("Lambda as variable : " + SuplierTest.getName(firstName, lastName));
	}

}
