package local.mydomain.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee /*implements IEmployee*/ {
        protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w*)(?:,\\s*\\{(?<details>.*)})?\\n";
        public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
        protected final Matcher peopleMat;
        protected String lastName;
        protected String firstName;
        protected LocalDate dob;

        protected Employee() {
                peopleMat = null;
                lastName = "N/A";
                firstName = "N/A";
                dob = null;
        }

        protected Employee(String personText) {
                peopleMat = Employee.PEOPLE_PAT.matcher(personText);
                if (peopleMat.find()) {
                        this.lastName = peopleMat.group("lastName");
                        this.firstName = peopleMat.group("firstName");
                        this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
                }
        }

        public static final IEmployee createEmployee(String employeeText) {
                Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);

                if (peopleMat.find()) {
                        return switch (peopleMat.group("role")) {
                                case "Programmer" -> new Programmer(employeeText);
                                case "Manager" -> new Manager(employeeText);
                                case "Analyst" -> new Analyst(employeeText);
                                case "CEO" -> new CEO(employeeText);
                                default -> () -> 0; //this is a lambdas expression for a method that takes no input and return 0
                        };
                } else {
                        return () -> 0;
                }
        }
        public abstract int getSalary(); //this is a template for a method which will actually implemented in the subclasses

        public double getBonus() {
                return getSalary() * 1.10;
        }

        @Override
        public String toString() {

            return String.format("%s, %s: %s - %s",lastName, firstName,moneyFormat.format(getSalary()),moneyFormat.format(getBonus()));

        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Employee employee)) return false;
                return lastName.equals(employee.lastName) && firstName.equals(employee.firstName) && dob.equals(employee.dob);

        }

        @Override
        public int hashCode() {
                return Objects.hash(lastName, firstName, dob);
        }

        private static final class DummyEmployee extends Employee implements IEmployee {
                @Override
               public int getSalary() {return 0;}
       }

       public record Jumper(String firstName, String lastName) {}
}
