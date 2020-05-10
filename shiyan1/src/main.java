import java.io.*;
import java.util.Scanner;

public class main {



    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Scanner s = new Scanner(System.in);

        init.display_start();
        if (s.next().toLowerCase().equals("init")) {
            init.init(); //初始化
            System.out.println("初始化成功！共生成100名员工，5个部门以及5个经理。");
            init.display();
        }else {
            System.out.println("----未初始化，请自行添加数据----");
            init.display();
        }

        while (s.hasNext()){
            int op = s.nextInt();
            if (op == 0){
                System.out.println("-----------谢谢使用-----------");
                break;
            }
            switch (op) {
                case 1:
                    init.display1();
                    op = s.nextInt();
                    switch (op) {
                        case 1:
                            System.out.println("请输入员工姓名或ID：");
                            String ss1 = s.next();
                            init.Search_by_emp(ss1);
                            break;
                        case 2:
                            System.out.println("请输入要查找的部门名或ID：");
                            String ss2 = s.next();
                            init.Search_dep(ss2);
                            break;
                        case 3:
                            System.out.println("请输入要查找的经理名或ID：");
                            String ss3 = s.next();
                            init.Search_manager(ss3);
                            break;
                        case 4:
                            init.count();
                            break;
                        case 5:
                            init.show_all_emp();
                            break;
                        case 6:
                            init.show_all_dep();
                            break;
                        case 7:
                            init.show_all_man();
                            break;
                        case 8:
                            System.out.println("请输入部门名：");
                            String ss4 = s.next();
                            init.Search_by_dname(ss4);
                        default:
                            break;
                    }
                    init.display();
                    break;

                case 2://删除与添加
                    init.display2();
                    op = s.nextInt();
                    switch (op){
                        case 1:
                            System.out.println("请按照如下格式进行操作，否则将操作失败");
                            System.out.println("例子：delete 员工ID");
                            init.Delete_emp();
                            break;
                        case 2:
                            System.out.println("！！！警告！删除部门后，原部门所在员工以及经理将一起删除！！！");
                            System.out.println("请输入需要删除部门ID：");
                            String s22 = s.next();
                            init.Delete_dep(s22);
                            break;
                        case 3:
                            System.out.println("请按照如下格式操作，否则操作将失败");
                            System.out.println("例：add 001 张三 男 25 3000.00 2019年1月1日 财务部");
                            String information = new Scanner(System.in).nextLine();
                            System.out.println(information);
                            init.Add_emp(information);
                            break;
                        case 4:
                            System.out.println("请按照如下格式操作，否则操作将失败");
                            System.out.println("请输入要添加部门的信息");
                            System.out.println("例：\"部门名 办公室\"");

                            init.Add_dep();
                            break;
                        case 5:
                            init.deleteAll();
                        default:
                            break;
                    }
                    init.display();
                    break;
                case 3://导出
                    init.display3();
                    op = s.nextInt();
                    switch (op) {
                        case 1:init.Export_depdata();
                            break;
                        case 2:init.Export_mdata();
                            break;
                        case 3:init.Export_empdata(op);
                            break;
                        case 4:init.Export_empdata(op);
                            break;
                        default:
                            break;
                    }
                    init.display();
                    break;
                case 4://导入
                    init.display4();
                    op = s.nextInt();
                    switch (op) {
                        case 1:
                            System.out.println("！！！请务必确保导入文件在项目“\\src\\data\\”下，且命名为“employee_encode.txt”！！！");
                            System.out.println("！！！！！！！！！！！！！！！！！！否则将操作失败！！！！！！！！！！！！！！！！！！！");
                            init.import_data(op);
                            break;
                        case 2:
                            System.out.println("！！！请务必确保导入文件在项目“\\src\\data\\”下，且命名为“employee.txt”！！！");
                            System.out.println("！！！！！！！！！！！！！！！！！否则将操作失败！！！！！！！！！！！！！！！！");
                            init.import_data(op);
                            break;
                        default:
                            break;
                    }
                    init.display();
                default:
                    break;
            }
        }
    }
}

