public class Department {
    private String D_id;
    private String Dname;
    private String manager;
    private String office;
    private String foundTime;

    public Department() {
    }

    public Department(String d_id, String dname, String manager, String office, String foundTime) {
        D_id = d_id;
        Dname = dname;
        this.manager = manager;
        this.office = office;
        this.foundTime = foundTime;
    }

    public String getD_id() {
        return D_id;
    }

    public void setD_id(String d_id) {
        D_id = d_id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(String foundTime) {
        this.foundTime = foundTime;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "D_id='" + D_id + '\'' +
                ", Dname='" + Dname + '\'' +
                ", manager='" + manager + '\'' +
                ", office='" + office + '\'' +
                ", foundTime='" + foundTime + '\'' +
                '}';
    }
}
