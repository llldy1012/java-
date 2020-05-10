public class Display {


    public void display_start() {
        System.out.println("                     //");
        System.out.println("         \\\\         //");
        System.out.println("          \\\\       //");
        System.out.println("    ##DDDDDDDDDDDDDDDDDDDDDD##"+"      使用说明：");
        System.out.println("    ## DDDDDDDDDDDDDDDDDDDD ##"+"          1.本系统为员工管理系统；");
        System.out.println("    ## hh                hh ##"+"          2.本系统执行自带初始化功能会随机会生成100名员工，5个部门，5个经理；");
        System.out.println("    ## hh    //    \\\\    hh ##"+"          3.本系统初始化命名格式：");
        System.out.println("    ## hh   //      \\\\   hh ##"+"            1.员工：例：E_ID:E0001 name:“员工+编号(1——100)”");
        System.out.println("    ## hh                hh ##"+"            2.部门：例：D_ID:D001 Dname:“部门+编号(1——5)”");
        System.out.println("    ## hh      wwww      hh ##"+"            3.经理：例：E_ID:M001 name:“经理+编号(1——5)”");
        System.out.println("    ## hh                hh ##"+"          4.对于初始化后使用查询功能，请按照3中命名格式内容进行相应查找；");
        System.out.println("    ## MMMMMMMMMMMMMMMMMMMM ##"+"          5.请输入 'init' 进行初始化操作；");
        System.out.println("    ##MMMMMMMMMMMMMMMMMMMMMM##"+"          6.最后，再次感谢您的使用，祝您使用愉快。");
        System.out.println("         \\/            \\/");

    }
    public void display() {
        System.out.println("------------主页菜单------------");
        System.out.println("--------输入1进行查询操作--------");
        System.out.println("--------输入2进行增删操作--------");
        System.out.println("--------输入3进行导出数据--------");
        System.out.println("--------输入4导入员工数据--------");
        System.out.println("--------输入0进行退出系统--------");
    }


    public void display1() {
        System.out.println("--------输入1查询员工详情--------");
        System.out.println("--------输入2查看部门详情--------");
        System.out.println("--------输入3查看经理详情--------");
        System.out.println("--------输入4查看员工总数--------");
        System.out.println("--------输入5查看全部员工--------");
        System.out.println("--------输入6查看全部部门--------");
        System.out.println("--------输入7查看全部经理--------");
        System.out.println("------输入8查找某部门全部员工------");
    }

    public void display2() {
        System.out.println("--------输入1删除员工--------");
        System.out.println("--------输入2删除部门--------");
        System.out.println("--------输入3添加员工--------");
        System.out.println("--------输入4添加部门--------");
        System.out.println("------输入5删除所有员工-------");
    }

    public void display3() {
        System.out.println("----------输入1导出部门信息-----------");
        System.out.println("----------输入2导出经理信息-----------");
        System.out.println("---------输入3导出加密员工信息---------");
        System.out.println("--------输入4导出未加密员工信息--------");

    }

    public void display4() {
        System.out.println("---------输入1导入加密员工信息---------");
        System.out.println("--------输入2导入未加密员工信息--------");
    }
}
