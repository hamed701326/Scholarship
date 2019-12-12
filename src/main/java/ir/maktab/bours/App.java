package ir.maktab.bours;

import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.BasicInformation;
import ir.maktab.bours.model.scholarship.Degree;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.impl.*;
import ir.maktab.bours.scholarshipexploringfeature.usecase.*;
import ir.maktab.bours.userfeature.impl.LogUpUseCaseImpl;
import ir.maktab.bours.userfeature.impl.LoginUseCaseImpl;
import ir.maktab.bours.userfeature.usecase.LogUpUseCase;
import ir.maktab.bours.userfeature.usecase.LoginUseCase;

import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int command = 1;
            while (command!=3) {
                System.out.println("what do you want? \n 1.Log in\n 2.Log up\n 3.Exit");
                command = scanner.nextInt();
                // Login
                switch (command) {
                    case 1:
                        System.out.println("Username : ");
                        String username = scanner.next();
                        System.out.println("Password : ");
                        String password = scanner.next();
                        LoginUseCase loginUseCase = new LoginUseCaseImpl();
                        User user = loginUseCase.login(username, password);
                        if (user != null) {
                            System.out.println(" Login successful by " + user.getRole());
                            String option = user.getRole();
                            switch (option) {

                                case "Manager":
                                    menuManager();
                                    break;
                                case "Superviser":
                                    menuSuperviser();
                                    break;
                                case "Student":
                                    menuStudent();
                                    break;
                                case "University":
                                    menuUniversity();
                                default:
                                    System.out.println("There isn't this role in this web");

                            }
                        } else System.out.println("Login failed ");
                        break;
                    case 2:
                        // log up
                        LogUpUseCase logUp = new LogUpUseCaseImpl();
                        logUp.createAccount(setUser());
                        break;
                    default:
                        System.out.println("I wish you best thing.");

                }
            }
    }



    public  static User setUser(){
        User user=new User();
        System.out.print("User Name: ");
        user.setUserName(scanner.next());
        System.out.println("Password: ");
        user.setPassWord(scanner.next());
        System.out.print("role");
        user.setRole(scanner.next());
        System.out.print("National_Id:");
        user.setNationalId(scanner.nextInt());
        return user;
    }
    public static void showList(List<Scholarship> scholarships) {
        System.out.println("-------------------------------------------------------------------------------------");
        String format = "|%1$-15s|%2$-15s|%3$-20s|%4$-10s|%5$-15s" +
                "|%6$-15s|%7$-15s|%8$-4S|%9$-15s|%10$-15s|%11$-15s|%12$-15s|\n";
        System.out.format(format, "scholar_id", "nationalId", "first_name", "last_name",
                "last_Universiy","last_grade","last_field","Gp",
                "de_university","de_grade","de_field","status");
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
                        sc.getAcceptDegree().getField(),
                        sc.getStatus()));

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
    public static void menuUniversity() {
        int optionStudent = 1;
        while (optionStudent != 2) {
            System.out.println("What do you want to do?" +
                    "\n 1. find Scholarship" +
                    "\n 2.exit");
            optionStudent = scanner.nextInt();
            switch (optionStudent) {
                case 1:
                   FindScholarshipByUniversityUseCase findByUniversity
                           =new FindScholarshipByUniversityUseCaseImpl();
                   showList(findByUniversity.listScholarships());
                    break;
                case 2:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Enter the right number");
            }
        }
    }
    public static void menuStudent(){
        int optionStudent=1;
        while(optionStudent!=4) {
            System.out.println("What do you want to do?" +
                    "\n 1. request scholarship" +
                    "\n 2. my scholarship " +
                    "\n 3. Dashboard" +
                    "\n 4.exit");
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
                    showList(findByStudent.listScholarships());
                    //Todo: Find scholarship by Student
                    break;
                case 3:
                    DashboardForStudentUseCase dashboardForStudent
                            =new DashboardForStudentUseCaseImpl();
                    dashboardForStudent.show();
                    break;
                case 4:
                    System.out.print("Exit ... ");
                    break;
                default:System.out.print("Enter the right number !");
            }
        }
    }
    public static void menuSuperviser(){
        int optionSuperviser=1;
        while(optionSuperviser!=5) {
            System.out.println("What do you want to do?" +
                    "\n 1. accept scholarship" +
                    "\n 2.reject scholarship " +
                    "\n 3. find scholarship " +
                    "\n 4.dashboard" +
                    "\n 5.exist");
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

                    showList(findCVBySuperviser.listScholarships());

                    break;
                case 4:
                    DashboardForSuperviserUseCase dashboardForSuperviser
                            =new DashboardForSuperviserUseCaseImpl();
                    dashboardForSuperviser.show();
                    break;
                case 5:
                    System.out.println("Exit...)");
                    break;
                default:System.out.println("Enter the right number.");
            }
        }
    }
    public static void menuManager(){
        int optionManager=1;
        while(optionManager!=5) {
            System.out.println("What do you want to do?" +
                    "\n 1. accept scholarship" +
                    "\n 2.reject scholarship " +
                    "\n 3. find scholarship " +
                    "\n 4.dashboard" +
                    "\n 5.exit");
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
                    DashboardForManagerUseCase dashboardForManager=
                            new DashboardForManagerUseCaseImpl();
                    dashboardForManager.show();
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Enter the right number.");
            }
        }
    }
}

