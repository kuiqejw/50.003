package problemset2;
import java.util.ArrayList;

public class VisitorPatternOriginal {

    public Professor profSunJun = new Professor("Sun Jun",0);

    AdminStuff adminStacey = new AdminStuff("Stacey",5);

    Student studentAllan = new Student("Allan",3);


    public static void main(String[] args) {

        VisitorPatternOriginal new1 = new VisitorPatternOriginal();
        AdminStuffVisitor adminStuffVisitor = new AdminStuffVisitor();
        new1.adminStacey.accept(adminStuffVisitor);

        ProfessorVisitor professorVisitor = new ProfessorVisitor();
        new1.profSunJun.accept(professorVisitor);

        StudentVisitor studentVisitor = new StudentVisitor();
        new1.studentAllan.accept(studentVisitor);

    }

    class SUTD {
        private ArrayList<Employee> employee;

        public SUTD() {
            employee = new ArrayList<Employee>();
            employee.add(new Professor("Sun Jun", 0));
            employee.add(new AdminStuff("Stacey", 5));
            employee.add(new Student("Allan", 3));
        }

        public ArrayList<Employee> getEmployee() {
            return employee;
        }
    }

    abstract class Employee implements Visitable {
        private Employee[] children;

        public Employee(Employee... children) {
            this.children = children;
        }

        public void accept(Visitor visitor) {
            for (Employee child : children) {
                child.accept(visitor);
            }
        }


    }

    class Professor extends Employee {
        private String name;
        private int no_of_publications;

        public Professor(String name, int no_of_publications) {
            this.name = name;
            this.no_of_publications = no_of_publications;
        }

        public String getName() {
            return name;
        }

        public int getNo_of_publications() {
            return no_of_publications;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitProfessor(this);
            super.accept(visitor);
        }
    }

    class AdminStuff extends Employee {
        private String name;
        private float efficiency;

        public AdminStuff(String name, float efficiency) {
            this.name = name;
            this.efficiency = efficiency;
        }

        public String getName() {
            return name;
        }

        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitAdminStuff(this);
            super.accept(visitor);
        }
    }

    class Student extends Employee {
        private String name;
        private float GPA;

        public Student(String name, float GPA) {
            this.name = name;
            this.GPA = GPA;
        }

        public String getName() {
            return name;
        }

        public float getGPA() {
            return GPA;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitStudent(this);
            super.accept(visitor);
        }
    }

// Visitor Interface

    interface Visitor {

        void visitAdminStuff(AdminStuff adminStuff);

        void visitProfessor(Professor professor);

        void visitStudent(Student student);

    }

    static class AdminStuffVisitor implements Visitor {

        @Override
        public void visitAdminStuff(AdminStuff adminStuff) {
            System.out.println("Admin: " + (adminStuff.getName() + "  " + (adminStuff.getEfficiency())));
        }

        @Override
        public void visitProfessor(Professor professor) {

        }

        @Override
        public void visitStudent(Student student) {

        }
    }

    static class ProfessorVisitor implements Visitor {

        @Override
        public void visitAdminStuff(AdminStuff adminStuff) {

        }

        @Override
        public void visitProfessor(Professor professor) {
            System.out.println("Prof: " + (professor.getName() + "  " + (professor.getNo_of_publications())));
        }

        @Override
        public void visitStudent(Student student) {

        }
    }

    static class StudentVisitor implements Visitor {

        @Override
        public void visitAdminStuff(AdminStuff adminStuff) {

        }

        @Override
        public void visitProfessor(Professor professor) {

        }

        @Override
        public void visitStudent(Student student) {
            System.out.println("Student: " + (student.getName() + "  " + (student.getGPA())));
        }
    }

    interface Visitable {
        public void accept(Visitor visitor);
    }
}