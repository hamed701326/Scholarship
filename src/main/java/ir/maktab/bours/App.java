package ir.maktab.bours;

import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.BasicInformation;
import ir.maktab.bours.model.scholarship.Degree;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.impl.*;
import ir.maktab.bours.scholarshipexploringfeature.usecase.*;
import ir.maktab.bours.userfeature.impl.LoginUseCaseImpl;
import ir.maktab.bours.userfeature.usecase.LoginUseCase;

import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("what do you want? ");
            command = scanner.nextLine();
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                User user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println(" Login successful by " + user.getRole());
                    String option=user.getRole();
                    switch (option){

                        case "Manager":
                            int optionManager=1;
                            while(optionManager!=4) {
                                System.out.println("What do you want to do?\n 1. accept scholarship\n 2.reject scholarship \n 3. find scholarship 4.exit");
                                optionManager = scanner.nextInt();
                                switch (optionManager) {
                                    case 1:
                                        //Todo accept scholarship by manager
                                        System.out.println("Accepting Scholarship...");
                                        AcceptScholarshipByManagerUseCase acceptCVByManager=
                                                new AcceptScholarshipByManagerUseCaseImpl();
                                        System.out.println("Enter ID:");

                                        acceptCVByManager.updateScholarship(scanner.nextInt());

                                        break;
                                    case 2:
                                        //Todo reject scholarship by manager
                                        System.out.println("Rejecting Scholarship ...");
                                        System.out.println("Enter ID:");

                                        RejectScholarshipByManagerUseCase rejectCVByManager=
                                                new RejectScholarshipByManagerUseCaseImpl();
                                        rejectCVByManager.updateStatus(scanner.nextInt());

                                        break;
                                    case 3:
                                        //todo find scholarship by manager
                                        FindScholarshipByManagerUseCase findCVByManager=
                                                new FindScholarshipByManagerUseCaseImpl();
                                        findCVByManager.listScholarships().stream()
                                                .forEach(scholarship -> scholarship.toString());
                                        break;
                                    case 4:
                                    default:
                                }
                            }
                            break;
                        case "Superviser":
                            int optionSuperviser=1;
                            while(optionSuperviser!=4) {
                                System.out.println("What do you want to do?\n 1. accept scholarship\n 2.reject scholarship \n 3. find scholarship 4.exit");
                                optionSuperviser = scanner.nextInt();
                                switch (optionSuperviser) {
                                    case 1:
                                        System.out.println("Accepting Scholarship...");
                                        AcceptScholarshipBySuperviserUseCase acceptCVBySuperviser=
                                                new AcceptScholarshipBySuperviserUseCaseImpl();
                                        System.out.println("Enter ID:");
                                        acceptCVBySuperviser.updateScholarship(scanner.nextInt());
                                        break;
                                    case 2:
                                        //Todo writing validation for reject
                                        System.out.println("Rejecting Scholarship ...");
                                        System.out.println("Enter ID:");
                                        RejectScholarshipBySuperviserUseCase rejectCVBySuperviser=
                                                new RejectScholarshipBySuperviserUseCaseImpl();
                                        rejectCVBySuperviser.updateStatus(scanner.nextInt());
                                        break;
                                    case 3:
                                        //todo find scholarship
                                        FindScholarshipBySuperviserUseCase findCVBySuperviser=
                                                new FindScholarshipBySuperviserUseCaseImpl();
                                        findCVBySuperviser.listScholarships().stream()
                                                .forEach(scholarship -> scholarship.toString());

                                        break;
                                    case 4:
                                        System.out.println("Thanks for your time :)");
                                    default:
                                }
                            }
                            break;
                        case "Student":
                            int optionStudent=1;
                            while(optionStudent!=4) {
                                System.out.println("What do you want to do?\n 1. request scholarship\n 2. my scholarship \n 3.exit");
                                optionStudent = scanner.nextInt();
                                switch (optionStudent) {
                                    case 1:
                                        System.out.println("Requesting Scholarship...");
                                        RequestScholarshipByStudentUseCase requestScholarship=
                                                new RequestScholarshipByStudentUseCaseImpl();
                                        requestScholarship.setScholarship(setScholarship());
                                        break;
                                    case 2:
                                        System.out.println("Your Scholarship");
                                        FindScholarshipByStudentUseCase findByStudent=
                                                new FindScholarshipByStudentUseCaseImpl();
                                        findByStudent.listScholarships().stream()
                                                .forEach(scholarship -> scholarship.toString());
                                        //Todo: Find scholarship by Student
                                        break;
                                    case 3:
                                        System.out.println("Thanks for your time :)");
                                        break;
                                    default:System.out.print("Enter the right number !");
                                }
                            }
                            break;
                        default:
                            System.out.println("There isn't this role in this web");

                    }
                }
            }
        }
    }
    public static void showList(List<Scholarship> scholarships) {
        System.out.println("-------------------------------------------------------------------------------------");
        String format = "|%1$-5s|%2$-15s|%3$-20s|%4$-10s|%5$-15s" +
                "|%6$-15s|%7$-15s|%8$-4S|%9$-10s|%10$-15s|%11$-15s|%12$-15s\n";
        System.out.format(format, "Id", "nationalId", "first_name", "last_name",
                "last_Universiy","last_grade","last_field","Gp",
                "de-university");
        scholarships.stream()
                .forEach(sc -> System.out.format(format,
                        String.valueOf(sc.getId()),
                        String.valueOf(sc.getBasicInformation().getNationalId()),
                        sc.getBasicInformation().getName(),
                        sc.getBasicInformation().getLastName(),
                        sc.getLastDegree().getUniversity(),
                        sc.getLastDegree().getGrade(),
                        sc.getLastDegree().getField(),
                        sc.getLastDegree().getGp(),
                        sc.getAcceptDegree().getUniversity(),
                        sc.getAcceptDegree().getGrade(),
                        sc.getAcceptDegree().getField()
                        ,sc.getStatus()));

    }
    public static Scholarship setScholarship(){
        System.out.print("firstName: ");
        String firstName=scanner.next();
        System.out.print("lastName: ");
        String lastName=scanner.next();
        System.out.println("nationalId: ");
        int nationalId=scanner.nextInt();
        System.out.print("lastUniversity: ");
        String lastUniversity=scanner.next();
        System.out.print("lastGrade: ");
        String lastGrade=scanner.next();
        System.out.print("lastField: ");
        String lastField=scanner.next();
        System.out.print("gpa:");
        Double gp=scanner.nextDouble();
        System.out.print("destinationUniversity: ");
        String destinationUniversity=scanner.next();
        System.out.print("destinationGrade:");
        String destinationGrade=scanner.next();
        System.out.print("destinationField:");
        String destinationField=scanner.next();

        return new Scholarship("RequestedByStudent"
        ,new BasicInformation(firstName,lastName,nationalId)
        ,new Degree(lastUniversity,lastGrade,gp,lastField)
        ,new Degree(destinationUniversity,destinationGrade,null,destinationField));
    }
}

