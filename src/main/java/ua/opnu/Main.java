package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        //Завдання 1
        Predicate prime = number -> {
            if ((int)number == 2)
                return true;
            if ((int)number % 2 == 0)
                return false;
            for (int i = 3; i * i <= (int)number; i += 2)
                if ((int)number % i == 0)
                    return false;
            return true;
        };

        int[] numbers = { 0, 1, 2, 3, 4, 7, 9, 11, 13, 15, 17, 18, 25 };

        for (int i : numbers)
            System.out.println("Is " + i + " prime? " + prime.test(i));

        //Завдання 2
        Student[] pals = {
                new Student("Олена", "Коваленко", "АІ-257", new int[]{92, 85, 96, 88}),
                new Student("Максим", "Бондаренко", "АІ-258", new int[]{74, 81, 59, 78}),
                new Student("Іван", "Ткаченко", "AI-259", new int[]{64, 71, 89, 82})
        };

        System.out.println("\nЗагальний список скудентів:");
        for (Student st : pals)
            System.out.println(" " + st.getInfo());

        Predicate debt = object -> {
            Student student = (Student)object;
            for (int mark : student.getMarks())
                if (mark < 60)
                    return false;
            return true;
        };

        Student[] withNoDebts = filter1(pals, debt);

        System.out.println("\nСтуденти без боргів:");
        for (Student st : withNoDebts)
            System.out.println(" " + st.getInfo());

        //Завдання 3
        Predicate groupAI257 = object -> {
            Student student = (Student)object;
            return student.getGroup().equals("АІ-257");
        };

        Student[] inRange = filter2(pals, debt, groupAI257);

        System.out.println("\nСтуденти без боргів та з групи АІ-257");
        for (Student st : inRange)
            System.out.println(" " + st.getInfo());

        //Завдання 4
        Consumer fullName = object -> {
            Student st = (Student) object;
            System.out.println(st.getFullName());
        };

        System.out.println();
        Arrays.asList(pals).forEach(fullName);

        //Завдання 5
        Predicate multOf3 = value -> (int)value % 3 == 0;

        Consumer print = value -> System.out.print((int)value + " ");

        System.out.print("\nЧисла, які діляться на 3 без остачі: ");
        check(numbers, multOf3, print);

        //Завдання 6
        Function power = value -> Math.pow(2, (int)value);

        System.out.println("\n");
        for (int i = 0; i < numbers.length / 2; i++)
            System.out.println("2^" + numbers[i] + " = " + power.apply(numbers[i]));

        //Завдання 7
        int[] digit = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        String[] digitNames = {
                "нуль", "один", "два", "три", "чотири",
                "п'ять", "шість", "сім", "вісім", "дев'ять"
        };

        Function getDigitName = value -> digitNames[(int)value];

        System.out.println("\n" + Arrays.toString(stringify(digit, getDigitName)));
    }

    static Student[] filter1(Student[] students, Predicate condition) { // task 2
        Student[] result = new Student[students.length];
        int count = 0;
        for (Student student : students)
            if (condition.test(student))
                result[count++] = student;
        return Arrays.copyOf(result, count);
    }

    static Student[] filter2(Student[] students, Predicate condition1, Predicate condition2) { // task 2
        return filter1(students, condition1.and(condition2));
    }

    static void check(int[] array,  Predicate condition, Consumer action) {
        for (int i : array)
            if (condition.test(i))
                action.accept(i);
    }

    static String[] stringify(int[] array, Function func) {
        String[] names = new String[array.length];
        for (int i = 0; i < array.length; i++)
            names[i] = (String) func.apply(i);
        return names;
    }
}
