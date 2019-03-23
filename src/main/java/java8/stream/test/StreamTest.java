package java8.stream.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.stream.test.Person.Sex;

public class StreamTest {

	public static void main(String[] args) {
		
	  List<Person> personList = new ArrayList<>();
	  personList.add(new Person("Ranjveer",LocalDate.of(2016, 7, 4),Sex.MALE,"rajveer@gmail.com",3,50000));
	  personList.add(new Person("Ranjit",LocalDate.of(1987, 9, 24),Sex.MALE,"ranjit@gmail.com",30,45000));
	  personList.add(new Person("Ashvin",LocalDate.of(1991, 4, 23),Sex.FEMALE,"ashvini@gmail.com",27,70000));
	  personList.add(new Person("Swati",LocalDate.of(1983, 12, 7),Sex.FEMALE,"swati@gmail.com",33,65000));
	  personList.add(new Person("Bhaiya",LocalDate.of(2009, 4, 4),Sex.MALE,"bhaiya@gmail.com",9,35000));
	  personList.add(new Person("Sonu",LocalDate.of(2007, 10, 4),Sex.FEMALE,"sonu@gmail.com",12,25000));
	  personList.add(new Person("siya",LocalDate.of(2000, 3, 30),Sex.FEMALE,"siya@gmail.com",19,20000));
	  personList.add(new Person("shivtej",LocalDate.of(1995, 11, 27),Sex.MALE,"shivtej@gmail.com",24,80000));
	  personList.add(new Person("monu",LocalDate.of(2003, 7, 4),Sex.MALE,"monu@gmail.com",16,50000));
	  
	  //Pipelines and Streams
	  //A pipeline is a sequence of aggregate operations. 
	  //A pipeline contains the following components:
	  //1) A source: This could be a collection, an array, a generator function, or an I/O channel.
      //2) Zero or more intermediate operations. An intermediate operation, such as filter, produces a new stream.
	  //********* A stream is a sequence of elements. Unlike a collection, it is not a data structure that stores elements. Instead, a stream carries values from a source through a pipeline. 
	  //3) A terminal operation. A terminal operation, such as forEach, produces a non-stream result, such as a primitive value (like a double value), a collection, or in the case of forEach, no value at all. 
	  
	  //average age of MALE in the persons list
	  double avgMaleAge = personList
			  			  .stream()
			  			  .filter(person -> person.gender == Sex.MALE)
			  			  .mapToInt(Person::getAge)
			  			  .average()
			  			  .getAsDouble();
	  
	  System.out.println("Male's average age : "+avgMaleAge);

	  //Reduction operations
	  //sum of the female salary
	  double sumFemalSalary = personList
			  			   .stream()
			  			   .filter(person -> person.getGender() == Sex.FEMALE)
			  			   .mapToDouble(Person::getSalary)
			  			   .sum();
	  System.out.println("Sum of Female Salary : "+sumFemalSalary);
	  
	  //The Stream.reduce Method
	  double sumFemalSalaryReduce = personList
 			   .stream()
 			   .filter(person -> person.getGender() == Sex.FEMALE)
 			   .mapToDouble(Person::getSalary)
 			   .reduce(0, (a,b) -> a+b);
	  
	  System.out.println("Reduced female sum of the salary : "+ sumFemalSalaryReduce);
	  
	  //The Stream.collect Method
	  //Unlike the reduce method, which always creates a new value when it processes an element, the collect method modifies, or mutates, an existing value.
	  Averager avgAgeMale = personList
			  			  .stream()
			  			  .filter(person -> person.getGender() == Sex.MALE)
			  			  .map(Person::getAge)
			  			  .collect(Averager::new, Averager::accept, Averager::combine);
	 System.out.println(avgAgeMale.average());	  	
	 
	 //using collectors
	 List<String> nameOfTheMales = personList
			 					   .stream()
			 					   .filter(p -> p.getGender() == Sex.MALE)
			 					   .map(Person::getName)
			 					   .collect(Collectors.toList());
	 System.out.println("Name of the Males : "+nameOfTheMales);
	 
	 //group by gender
	 Map<Person.Sex, List<Person>> byGender = personList
			 								  .stream()
			 								  .collect(Collectors.groupingBy(Person::getGender));
	  
	 System.out.println(byGender);
	 
	 //get name of the person for each gender
	 Map<Person.Sex, List<String>> namesByGender = personList
			 									   .stream()
			 									   .collect(Collectors.groupingBy(Person::getGender, 
			 											   Collectors.mapping(Person::getName, Collectors.toList())));
	 System.out.println("namesByGender : " + namesByGender);
	 
	 //total age of members of each gender
	 Map<Person.Sex, Integer> totalAgeByGender = personList
			 									 .stream()
			 									 .collect(Collectors.groupingBy(
			 											 p -> p.getGender(), 
			 											 Collectors.reducing(0, p -> p.getAge(), Integer::sum)));
	 System.out.println("totalAgeByGender : "+totalAgeByGender);
	 
	 Map<Person.Sex, Double> averageAgeByGender = personList
			    .stream()
			    .collect(
			        Collectors.groupingBy(
			            Person::getGender,                      
			            Collectors.averagingInt(Person::getAge)));
	 System.out.println("averageAgeByGender : "+averageAgeByGender);
	}

}
