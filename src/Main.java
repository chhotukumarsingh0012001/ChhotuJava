import dao.StudentDAO;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);
        int choice=0;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Course: ");
                    String course = sc.nextLine();

                    if (!name.matches("[a-zA-Z ]+") || !course.matches("[a-zA-Z ]+")) {
                        System.out.println("Name and Course must contain only letters. Student not added.");
                        break;
                    }

                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (!email.contains("@") || !email.contains(".")) {
                        System.out.println("Invalid email format. Student not added.");
                    } else {
                        dao.addStudent(new Student(0, name, course, email));
                    }
                }
                case 2 -> {
                    List<Student> students = dao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found. Table is empty.");
                    } else {
                        System.out.println("\nID | Name | Course | Email");
                        for (Student s : students) System.out.println(s);
                    }
                }
                case 3 -> {
                    System.out.print("Enter ID to update: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                        break;
                    }

                    Student existing = dao.getStudentById(id);

                    if (existing == null) {
                        System.out.println("No student found with that ID.");
                    } else {
                        System.out.print("New Name: ");
                        String name = sc.nextLine();
                        System.out.print("New Course: ");
                        String course = sc.nextLine();

                        if (!name.matches("[a-zA-Z ]+") || !course.matches("[a-zA-Z ]+")) {
                            System.out.println("Name and Course must contain only letters. Student not updated.");
                            break;
                        }
                        System.out.print("New Email: ");
                        String email = sc.nextLine();
                        if (!email.contains("@") || !email.contains(".")) {
                            System.out.println("Invalid email format. Student not updated.");
                        } else {
                            dao.updateStudent(id, name, course, email);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Please enter a number.");
                        break;
                    }
                    dao.deleteStudent(id);
                }
                case 5 -> System.out.println("Exiting... Bye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}