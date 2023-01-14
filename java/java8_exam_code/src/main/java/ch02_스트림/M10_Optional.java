package ch02_스트림;

import dto.Product.Job;
import dto.Product.Person;
import dto.Product.Product;

import java.util.Optional;

public class M10_Optional {
    public static void main(String[] args) {
        // 빈 Optional
        Optional<Product> product = Optional.empty();

        // null 이 아닌 Optional
        Product product1 = new Product(0, "Note_red", 1, 100);
        // Product product1 = null;
        Optional<Product> product2 = Optional.of(product1); // product1 이 null 이라면 NullPointerException 발생

        // null Optional
        Product product3 = null;
        Optional<Product> product4 = Optional.ofNullable(product3); // product3 이 null 이면 빈 Optional 객체가 반환된다

        // before
        String test = "";
        if (product1 != null) {
            test = product1.getProductName();
        }

        // after
        Optional<Product> ofProduct = Optional.ofNullable(product1);
        Optional<String> name = ofProduct.map(Product::getProductName); // name 에 값을 넣는다. Optional 이 비어져있으면 아무일도 일어나지 않는다.

        // 여러 get 메소드 연결
        Product product5 = new Product(0, "Note_red", 1, 100);
        Person person = new Person(1, "seohae");
        Job job = new Job(1, "dev");

        Optional<Job> ofJob = Optional.ofNullable(job);
        person.setJob(ofJob);

        Optional<Person> ofPerson = Optional.ofNullable(person);
        product5.setPerson(ofPerson);

        Optional<Product> optProduct = Optional.of(product5);

//        Optional<String> getPersonName = optProduct.map(Product::getPerson) // Optional<Optional<Person>>
//                                                   .map(Person::getJob)
//                                                   .map(Job::getJobName);

        Optional<String> getPersonName = optProduct.flatMap(Product::getPerson)
                                                   .flatMap(Person::getJob)
                                                   .map(Job::getJobName);

        System.out.println(getPersonName);

    }
}
