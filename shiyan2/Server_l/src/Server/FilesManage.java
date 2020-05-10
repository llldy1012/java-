package Server;

import java.io.*;
import java.util.Base64;

public class FilesManage {
    BufferedInputStream bis;
    BufferedOutputStream bos;

    public FilesManage(BufferedInputStream bis, BufferedOutputStream bos) {
        this.bis = bis;
        this.bos = bos;
    }

    //创建文件夹
    public void mkdir(String path, String oldPath) throws IOException {
        File f1 = new File(path);
        if (f1.isAbsolute() && !f1.exists()){
            f1.mkdir();
        }
        else if (!f1.isAbsolute() && !f1.exists()) {
            File f2 = new File(oldPath + "\\" + path);
            if (!f2.exists()) {
                f2.mkdir();
            }
        }
        else System.out.println("该目录存在！");
    }

    //创建文件
    public void creatFile(String path, String oldPath) throws IOException {
        File f1 = new File(path);
        if (f1.isAbsolute() && !f1.exists()) {
            //f1.createNewFile();
            if (f1.createNewFile()) {
                System.out.println("创建成功。\\end");
            }
        }
        else if (!f1.isAbsolute() && !f1.exists()) {
            File f2 = new File(oldPath + "\\" + path);
            if (f2.createNewFile()) {
                System.out.println("创建成功。\\end");
//                bos.write("创建成功。\\end".getBytes());
//                bos.flush();
            }
        }
        else {
            System.out.println("创建失败。\\end");
//            bos.write("创建失败。\\end".getBytes());
//            bos.flush();
        }
            //System.out.println(f1.isFile());
    }

    //删除文件
    public void deleteFile(String path, String oldPath) throws IOException {
        File f1 = new File(path);
        if (f1.isAbsolute()  ) {
            if (f1.exists() && f1.delete()) {
//                bos.write(("删除成功。"+"\\"+"end").getBytes());
//                bos.flush();
                System.out.println("删除成功");
            }
            else{
                System.out.println("删除失败");
//                bos.write(("删除成功。"+"\\"+"end").getBytes());
//                bos.flush();
            }
        }
        else {
            File f2 = new File(oldPath + "\\" + path);
            if (f2.exists() && f2.delete()) System.out.println("删除成功");
            else System.out.println("删除失败");
        }

    }

    //删除文件夹（非空和空）
    public void deleteDirectory(String path) {
        File file = new File(path);
        File[] fs = file.listFiles();
        if (fs.length != 0) {
            for (File f : fs) {
                if (f.isFile()) {
                    f.delete();
                } else if (f.isDirectory()) {
                    deleteDirectory(f.getAbsolutePath());
                    f.delete();
                }
            }
        }
        if (file.delete())
        {
            System.out.println("删除成功！");
        }else System.out.println("删除失败！");

    }

    //遍历当前目录
    public void dirGet(String path) throws IOException {
        File file = new File(path);
        File[] fs = file.listFiles();
        if (file.exists()) {
            //System.out.println(file.getParent());
            for (File f : fs) {
                String s = f.getName() + "\\";
                bos.write(s.getBytes());
                //p.flush();
                System.out.println(f.getName());
            }
        }
        else System.out.println("该目录不存在");
    }

    //文件复制
    public void copyFile(String oldPath, String newPath) {
        try{
            File file = new File(oldPath);
            if (file.isFile()){
                FileInputStream fi = new FileInputStream(file);
                FileOutputStream fo = new FileOutputStream(new File(newPath + File.separator + file.getName()));
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = fi.read(b)) != -1){
                    fo.write(b,0,len);
                }
                fo.flush();
                fo.close();
                fi.close();
            }
            if (file.isDirectory()) {
                newPath = newPath + File.separator + file.getName();
                new File(newPath).mkdirs();
                File[] fs = file.listFiles();
                for (File f : fs) {
                    if (f.isFile()) {
                        FileInputStream fi = new FileInputStream(f);
                        FileOutputStream fo = new FileOutputStream(new File(newPath + File.separator + f.getName()));
                        byte[] b = new byte[1024];
                        int len = 0;

                        while ((len = fi.read(b)) != -1) {
                            fo.write(b, 0, len);
                        }
                        fo.flush();
                        fo.close();
                        fi.close();
                    }
                    if (f.isDirectory()) {
                        copyFile(oldPath + File.separator + f.getName(), newPath);
                    }
                }
            }
            System.out.println("复制完成");
        }catch (Exception e) {
            System.out.println("复制失败");
            e.printStackTrace();
        }
    }


    //文件剪切
    public void cut(String oldPath, String newPath) {
        try{
            boolean flag = false;
            File file = new File(oldPath);
            if (file.isFile()){
                File newfile = new File(newPath + File.separator + file.getName());
                file.renameTo(newfile);
                flag = true;
            }
            if (file.isDirectory()) {
                newPath = newPath + File.separator + file.getName();
                new File(newPath).mkdirs();
                File[] fs = file.listFiles();
                for (File f : fs) {
                    if (f.isFile()) {
                        f.renameTo(new File(newPath + File.separator + f.getName()));
                    }
                    if (f.isDirectory()) {
                        copyFile(oldPath + File.separator + f.getName(), newPath);
                    }
                }
                flag = true;
            }
            if (flag) System.out.println("剪切成功");
            else System.out.println("剪切失败");
        }catch (Exception e) {
            System.out.println("剪切失败");
            e.printStackTrace();
        }
    }


    //cd指令实现
    public String cd(String oldPath, String newPath) {
        String temp = oldPath;
            String[] s = newPath.split(" ");//cd.. cd ..
            if (s.length == 2) {
                if (s[0].equals("cd")) {
                    String[] s1 = s[1].split("\\\\");
                    for (String s2 : s1) {
                        File f1 = new File(oldPath);
                        switch (s2) {
                            case "..":
                                oldPath = f1.getParent();
                                break;
                            default:
                                oldPath += File.separator + s2;
                                break;
                        }
                    }
                }
            }
            File ff = new File(oldPath);
            if (ff.exists() && ff.isDirectory()){
                System.out.print(oldPath + ">");
            }else {
                oldPath = temp;
                //System.out.print(oldPath + ">");
                System.out.println("路径不存在");
            }
            return oldPath;
        }


    //加密
    private String encode(String src) {
        byte[] encodeBytes = Base64.getEncoder().encode(src.getBytes());
        return new String(encodeBytes);
    }

    //解密
    private String decode(String src){
        byte[] decodeBytes = Base64.getDecoder().decode(src.getBytes());
        return new String(decodeBytes);
    }


    //加解密
    public void Code(String path) throws IOException {
        String[] s = path.split(" ");
        File file = new File(s[1]);
        System.out.println(file.getParent());
        File file1 = null;
        if (s[0].equals("encode")) {
            file1 = new File( file.getParent() + "\\" + "Encode_" + file.getName());
        }else if (s[0].equals("decode")){
            file1 = new File( file.getParent() + "\\" + "Decode_" + file.getName());
        }
        if (file.exists() && file.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
            String read = null;
            if (s[0].equals("encode")){
                while ((read = br.readLine()) != null) {
                    bw.write(encode(read));
                    bw.newLine();
                    bw.flush();
                }
            }
            if (s[0].equals("decode")) {
                while ((read = br.readLine()) != null) {
                    bw.write(decode(read));
                    bw.newLine();
                    bw.flush();
                }
            }
            bw.close();
            br.close();
        }
        else System.out.println("该文件不存在或输入不是文件！");
    }


    //下载服务端文件
    public void Download(String path) throws IOException {
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            FileInputStream fis = new FileInputStream(f);
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                bos.write(b,0,len);
            }
            bos.flush();
            fis.close();
        }
    }


    //接收客户端上传的文件
    public void Upload(String path) throws IOException {
        File file = new File(path);
        String name = file.getName();
        new File("Upload").mkdirs();
        File f2 = new File("Upload\\" + name);
        FileOutputStream fos = new FileOutputStream(f2);
        int len = 0;
        byte[] b = new byte[1024];
        boolean flag = true;
        while (flag) {
            len = bis.read(b);
            if (len == 1024) {
                fos.write(b);
            }else {
                fos.write(b,0,len);
//                bos.write("end".getBytes());
//                bos.flush();
                flag = false;
            }
        }
        fos.flush();
        fos.close();
    }

}
