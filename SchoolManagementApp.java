import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Enrollment {
    private Student student;
    private Course course;
    private int grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1; // Initialize grade as -1 (indicating not graded)
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

class SchoolManager {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Enrollment> enrollments;

    public SchoolManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentInCourse(int studentId, String courseName) {
        Student student = findStudentById(studentId);
        Course course = findCourseByName(courseName);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        if (course == null) {
            System.out.println("Course '" + courseName + "' not found.");
            return;
        }

        enrollments.add(new Enrollment(student, course));
        System.out.println("Student enrolled successfully!");
    }

    public void gradeStudent(int studentId, String courseName, int grade) {
        Student student = findStudentById(studentId);
        Course course = findCourseByName(courseName);

        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        if (course == null) {
            System.out.println("Course '" + courseName + "' not found.");
            return;
        }

        boolean found = false;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId() == studentId && enrollment.getCourse().getName().equals(courseName)) {
                enrollment.setGrade(grade);
                found = true;
                System.out.println("Student graded successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Student not enrolled in the course.");
        }
    }

    public void displayEnrollments() {
        System.out.println("Enrollments:");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getStudent().getName() + " - " +
                    enrollment.getCourse().getName() + " - Grade: " + enrollment.getGrade());
        }
    }

    private Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }
}

public class SchoolManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolManager schoolManager = new SchoolManager();

        System.out.println("Welcome to School Management System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Grade Student");
            System.out.println("5. Display Enrollments");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume newline
                continue;
            }
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    schoolManager.addStudent(new Student(studentName, studentID));
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    schoolManager.addCourse(new Course(courseName));
                    System.out.println("Course added successfully!");
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int enrollStudentID;
                    try {
                        enrollStudentID = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Consume newline
                        continue;
                    }
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter course name: ");
                    String enrollCourseName = scanner.nextLine();
                    schoolManager.enrollStudentInCourse(enrollStudentID, enrollCourseName);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int gradeStudentID;
                    try {
                        gradeStudentID = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Consume newline
                        continue;
                    }
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter course name: ");
                    String gradeCourseName = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    int grade;
                    try {
                        grade = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Consume newline
                        continue;
                    }
                    scanner.nextLine(); // Consume newline
                    schoolManager.gradeStudent(gradeStudentID, gradeCourseName, grade);
                    break;
                case 5:
                    schoolManager.displayEnrollments();
                    break;
                case 6:
                    System.out.println("Thank you for using School Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
