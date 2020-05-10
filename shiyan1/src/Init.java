import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class  Init extends Display{
     private ArrayList<Department> departments = new ArrayList<>();
     private ArrayList<Manager> managers = new ArrayList<>();
     private ArrayList<Employee> employees = new ArrayList<>();

     Date date = new Date();
     Random random = new Random();
     DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

     private int num = 0;
     /**
      * 系统初始化函数
      * 自动生成五个部门，五个经理以及100名员工
      */
     public void init(){
         for (int i = 1; i <6 ; i++) {
            String gender = "";
            if (Math.random()>0.5) gender = "男";
            else gender = "女";
            ++num;
            departments.add(new Department("D00"+i,"部门"+i,"Manager"+i,"D10"+i,format.format(date)));
            managers.add(new Manager("M00"+i, "经理"+i, gender, random.nextInt(20) + 30,
                    random.nextInt(15000)+20000,"201"+i+"年4月2日", "部门"+i,"2077年1月1"));

         }

         for (int i = 1; i <101 ; i++) {
            String gender = "";
            if (Math.random()>0.5) gender = "男";
            else gender = "女";
            if (i<10) {
                employees.add(new Employee("E000" + i, "员工" + i, gender, random.nextInt(20)+20,
                        Math.abs(random.nextInt()%(20000-3000+1)) + 3000,format.format(date), "部门"+ (random.nextInt(5)+1)));
            }else if (i<100){
                employees.add(new Employee("E00" + i, "员工" + i, gender, random.nextInt(20)+20,
                        Math.abs(random.nextInt()%(20000-4000+1)) + 4000,format.format(date), "部门"+ (random.nextInt(5)+1)));
            }
            else employees.add(new Employee("E0" + i, "员工" + i, gender, random.nextInt(20)+20,
                        Math.abs(random.nextInt()%(20000-5000+1)) + 5000, format.format(date), "部门"+ (random.nextInt(5)+1)));;
         }
     }


     /**
      * 各种添加（删除）功能模块
      * 用于添加（删除）员工，部门，经理
      */

     //添加员工
     public void Add_emp(String information){
         String[] inf = information.split(" ");
         boolean equals = false;
         boolean equals1 = false;
         if (inf.length==8 && inf[0].toLowerCase().equals("add")){
             for (Employee employee : employees) {
                 equals = employee.getID().equals(inf[1]);
                 if(equals){
                     System.out.println("该ID已经存在");
                     System.out.println("添加失败");
                     break;
                 }
             }
             for (Department department : departments) {
                equals1 = department.getDname().equals(inf[7]);
                if (!equals&&equals1) {
                    employees.add(new Employee(inf[1],inf[2],inf[3],Integer.parseInt(inf[4]),Double.parseDouble(inf[5]),inf[6],inf[7]));
                    System.out.println("添加成功");
                    break;
                }
             }
             if (!equals1) {
                 System.out.println("所要添加的部门不存在");
                 System.out.println("添加失败");
             }
         }else {
             System.out.println("输入错误！");
         }
     }

     //删除员工
     public void Delete_emp() {
         String s = new Scanner(System.in).nextLine();
         String[] s1 = s.split(" ");
         boolean flag = true;
         if (s1.length == 2 && s1[0].toLowerCase().equals("delete")){
             for (int i = 0; i < employees.size() ; i++) {
                 if (employees.get(i).getID().equals(s1[1])) {
                     employees.remove(i);
                     flag = false;
                     System.out.println("删除成功");
                     break;
                 }
             }
         }else System.out.println("输入格式错误");
         if (flag) System.out.println("删除失败或该员工不存在");

     }

     //添加部门和经理
     public void Add_dep() {
         Scanner scanner = new Scanner(System.in);
         String s = scanner.nextLine();
         System.out.println("请输入该部门的部门经理信息");
         System.out.println("例：\"经理名 性别 22 25555.00 任职期限\"");
         String ss = new Scanner(System.in).nextLine();
         String[] s1 = s.split(" ");
         String[] s2 = ss.split(" ");
         if (s1.length == 2 && s2.length == 5) {
             departments.add(new Department("D00"+(++num),s1[0],s2[0],s1[1],format.format(date)));
             managers.add(new Manager("M00"+(num),s2[0], s2[1], Integer.parseInt(s2[2]), Double.parseDouble(s2[3]), format.format(date), s1[0],s2[4]));
             System.out.println("添加成功");
             System.out.println(departments.get(departments.size()-1));
             System.out.println(managers.get(managers.size()-1) + " " +managers.get(managers.size()-1).getTenure());
         }
         else System.out.println("添加失败，请重新操作");
     }

     //删除部门
     public void Delete_dep(String s) {
         boolean flag = false;
         for (Department department : departments) {
             if (department.getD_id().equals(s)) {
                    flag = true;
                     for (int i = 0; i < employees.size(); i++) {
                         if (employees.get(i).getDepartment().equals(department.getDname())){
                             employees.remove(i);
                             i--;
                        }
                     }

                     for (int i = 0; i < managers.size(); i++) {
                         if (managers.get(i).getDepartment().equals(department.getDname())){
                             managers.remove(i);
                             break;
                         }
                     }
                     for (int i = 0; i < departments.size(); i++) {
                         if (departments.get(i).getD_id().equals(s)) {
                             departments.remove(i);
                             break;
                         }
                     }
                     System.out.println("删除成功");
                     break;
             }
         }

         if (!flag) {
             System.out.println("删除失败！");
         }
     }


    /**
     * 所有数据的导出模块
     *
     */
     //导出员工数据
     public void Export_empdata(int op) throws IOException {

         if (op == 3) {
             FileWriter file = new FileWriter(".\\src\\data\\employee_encode.txt");
             BufferedWriter os = new BufferedWriter(file);
             for (Employee employee : employees) {
                 os.write(encode(employee.toString()));
                 os.newLine();
                 os.flush();
             }
             os.close();
             System.out.println("导出成功!数据位于项目“src\\data\\employee_encode.txt”");
         }else {
             FileWriter file = new FileWriter(".\\src\\data\\employee.txt");
             BufferedWriter os = new BufferedWriter(file);
             for (Employee employee : employees) {
                 os.write(employee.toString());
                 os.newLine();
                 os.flush();
             }
             os.close();
             System.out.println("导出成功!数据位于项目“src\\data\\employee.txt”");
         }

     }

     //导出部门数据
     public void Export_depdata() throws IOException {
         FileWriter file = new FileWriter(".\\src\\data\\department.txt");
         BufferedWriter os = new BufferedWriter(file);
         for (Department department : departments) {
            // os.write(encode(employee.toString()));
             os.write(department.toString());
             os.newLine();
             os.flush();
         }
         os.close();
         System.out.println("导出成功!数据位于项目“src\\data\\department.txt”");
     }

     //导出经理数据
     public void Export_mdata() throws IOException {
         FileWriter file = new FileWriter(".\\src\\data\\manager.txt");
         BufferedWriter os = new BufferedWriter(file);
         for (Manager manager : managers) {
             os.write(manager.toString() + " " + manager.getTenure());
             os.newLine();
             os.flush();
         }
         os.close();
         System.out.println("导出成功!数据位于项目“src\\data\\manager.txt”");
     }

    /**
     *数据导入导出的加解密功能
     */
     //加密
     public String encode(String src) {
         byte[] encodeBytes = Base64.getEncoder().encode(src.getBytes());
         return new String(encodeBytes);
     }

     //解密
     public String decode(String src){
         byte[] decodeBytes = Base64.getDecoder().decode(src.getBytes());
         return new String(decodeBytes);
     }


    /**
     * 员工数据的导入功能
     * @param op
     * @throws IOException
     */
     public void import_data(int op) throws IOException{
         if (op == 1) {
             BufferedReader br = new BufferedReader(new FileReader(".\\src\\data\\employee_encode.txt"));
             String aa = br.readLine();
             while (aa != null) {
                 // String[] s = aa.split(" ");
                 String ss = "add " + decode(aa);
                 Add_emp(ss);
                 aa = br.readLine();
             }
             br.close();
         }else {
             BufferedReader br = new BufferedReader(new FileReader(".\\src\\data\\employee.txt"));
             String aa = br.readLine();
             while (aa != null) {
                 // String[] s = aa.split(" ");
                 String ss = "add " + aa;
                 Add_emp(ss);
                 aa = br.readLine();
             }
             br.close();
         }
         System.out.println("导入成功");
     }
     //解码写入文本
//     public void Decode() throws IOException {
//         BufferedReader br = new BufferedReader(new FileReader(".\\src\\data\\employee.txt"));
//         BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\data\\employees.txt"));
//         String aa = br.readLine();
//         while (aa != null) {
//             bw.write(decode(aa));
//             bw.newLine();
//             bw.flush();
//             aa = br.readLine();
//         }
//         br.close();
//         bw.close();
//     }

    /**
     * 各种查询功能
     */
     //查看所有员工
     public void show_all_emp() {

         for (Employee employee : employees) {
             System.out.println(employee);
         }
         System.out.println("共" + employees.size() +"名员工");
     }

     //查看所有部门
     public void show_all_dep() {

         for (Department department : departments) {
             System.out.println(department);
         }
         System.out.println("共" + departments.size() +"个部门");
     }

     //查看所有经理
     public void show_all_man() {

         for (Manager manager : managers) {
             System.out.println(manager);
         }
         System.out.println("共" + managers.size() +"名经理");
     }

     //查询总的员工数
    public void count() {
        System.out.println("一共有员工：" + employees.size() + "名");
        for (Department department : departments) {
            int nums = 0;
            for (Employee employee : employees) {

                if (employee.getDepartment().equals(department.getDname())) {
                    nums++;
                }
            }
            System.out.println(department.getDname() + ":" + nums +"名");
        }

    }

    //查询
    public void Search_by_dname(String search) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(search)) {
                System.out.println(employee);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("查找失败");
        }else System.out.println("查找完成!\r\n" + search + "总计：" + count + "人");
    }

    public void Search_by_emp(String s) {
        boolean flag = false;
        for (Employee employee : employees) {
            if (employee.getName().equals(s)||employee.getID().equals(s)) {
                System.out.println(employee);
                flag = true;
            }
        }
        if (!flag) System.out.println("查无此人");
    }

    //查看部门详情
    public void Search_dep(String s) {
        boolean flag = false;
        for (Department department : departments) {
            if (department.getD_id().equals(s)||department.getDname().equals(s)) {
                System.out.println(department);
                flag =true;
            }
        }
        if (!flag) System.out.println("该部门不存在");
    }

    //查看经理
    public void Search_manager(String s) {
        boolean flag = false;
        for (Manager manager : managers) {
            if (manager.getID().equals(s)||manager.getName().equals(s)) {
                System.out.println(manager + " " +manager.getTenure());
                flag = true;
            }
        }
        if (!flag) System.out.println("没有所要查找的经理");
    }


    public void deleteAll() {
        employees.clear();
    }
}
