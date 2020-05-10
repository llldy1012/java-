package Client;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class FileOpe {
    private static String fileName="data.inc";
    private static Properties pps;
    static {
        pps=new Properties();
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader reader=null;
        try {
            reader=new FileReader(fileName);
            pps.load(reader);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"文件操作异常");
            System.exit(0);
        }
        finally {
            try {
                reader.close();
            }
            catch (Exception ex) {}
        }
    }
    private static void listInfo() {
        PrintStream ps=null;
        try {
            ps=new PrintStream(fileName);
            pps.list(ps);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"文件操作异常");
            System.exit(0);
        }
        finally {
            try {
                ps.close();
            }
            catch (Exception ex) {}
        }
    }
    public static void getInfoByAccount(String account) {
        String cusInfo=pps.getProperty(account);
        if(cusInfo!=null) {
            String[] infos=cusInfo.split("#");
            Conf.account=account;
            Conf.pwd=infos[0];

        }
    }
    public static void updateCustomer(String account,String password) {
        pps.setProperty(account,password);
        listInfo();
    }
}
