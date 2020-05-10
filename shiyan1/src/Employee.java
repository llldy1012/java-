public class Employee {
    private String E_id;
    private String name;
    private String gender;
    private int age;
    private double salary;
    private String hiredate;
    private String department;

    public Employee() {
    }

    public Employee(String ID, String name, String gender, int age, double salary, String hiredate, String department) {
        this.E_id = ID;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.age = age;
        this.hiredate = hiredate;
        this.department = department;
    }

    public String getID() {
        return E_id;
    }

    public void setID(String ID) {
        this.E_id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return
                 E_id + " " +name + " " + gender + " " +
                         age + " " + salary + " " + hiredate + " " + department;
    }
}
