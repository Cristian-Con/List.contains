package local.mydomain.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone, Fred, 1/1/1900, Programmerzzz, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=2630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=3}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=7}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                """;

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        IEmployee employee = null;
        List<IEmployee> employees = new ArrayList<>(16);
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            employees.add(employee);
         }

        IEmployee myEmp = employees.get(5);
        System.out.println(employees.contains(myEmp));
        System.out.println(myEmp);

        IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}");
        System.out.println(employees.contains(employee1));

        System.out.println(myEmp.equals(employee1));



//        Programmer p1 = new Programmer("");
//        Programmer p2 = new Programmer("");
//        p1.equals(p2); //p1 == p2 --> this == tests for whether two object references point to the same address in memory
                                                //which would mean that they are effectively the same object


//        List<String> undesirables = new ArrayList<>(List.of("Wilma5","Barney4","Fred2"));
//        removeUndesirables(employees, undesirables);
//
//
//        for (IEmployee worker : employees) {
//            System.out.println(worker.toString());
//            totalSalaries+= worker.getSalary();
//        }
//
//        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
//        System.out.printf("The total payout should be %s%n",currencyInstance.format(totalSalaries));
//
//        Weirdo larry = new Weirdo("David","Larry", LocalDate.of(1950,1,1));
//        System.out.printf(larry.firstName());
//        Weirdo jake = new Weirdo("Snake","Jake");
//        jake.sayHello();
    }

    private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
        for(Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            if (worker instanceof Employee tmpWorker) {
                if (removalNames.contains(tmpWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }

} 
