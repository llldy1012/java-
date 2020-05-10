public class Manager extends Employee{

    public String tenure;

    public Manager() {
    }

    public Manager(String ID, String name, String gender, int age,double salary,  String hiredate, String department, String tenure) {
        super(ID, name, gender, age, salary, hiredate, department);
        this.tenure = tenure;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

//    @Override
//    public String toString() {
//        return "Manager{" +
//                "tenure='" + tenure + '\'' +
//                '}';
//    }
}
