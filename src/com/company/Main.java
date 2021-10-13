package com.company;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int majorityAge = 18;
    private static int ageOfConscription = 20;
    private static int ManRetirementAge = 63;
    private static int WomanRetirementAge = 58;

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        List<String> conscripts = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() < ageOfConscription)
                .filter(x -> x.getAge() >= majorityAge)
                .map(Person::getFamily)
                .collect(Collectors.toList());


        long count = persons.stream()
                .filter(x -> x.getAge() < majorityAge)
                .count();
        System.out.println("\nthe number of minors: " + new DecimalFormat( "##,###,###" ).format(count));


        System.out.println("Number of conscripts: " +
                new DecimalFormat( "##,###,###" ).format(conscripts.size()));

        List<Person> ableBodied = new ArrayList<>();
        ableBodied = persons.stream()
                .filter(x -> x.getAge() >= majorityAge)
                .filter(x -> (x.getSex().equals(Sex.MAN) && x.getAge() < ManRetirementAge ||
                        x.getSex().equals(Sex.WOMAN) && x.getAge() < WomanRetirementAge))
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("The able-bodied list of person is formed and sorted: ");
        System.out.println("the first person in the list: " + ableBodied.get(0).toString());
        System.out.println("the second person in the list: " + ableBodied.get(1).toString());
        System.out.println("the third person in the list: " + ableBodied.get(2).toString());
    }

}
