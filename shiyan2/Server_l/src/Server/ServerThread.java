package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket s;
    private BufferedInputStream is;
    //private PrintWriter os;
    private BufferedOutputStream bos;
    public ServerThread(Socket socket) throws IOException {
        s = socket;
        is = new BufferedInputStream(s.getInputStream());
        //os = new PrintWriter(s.getOutputStream());
        bos = new BufferedOutputStream(s.getOutputStream());
        start();
    }

    private void sendEnd() throws IOException {
        bos.write("end".getBytes());
        bos.flush();
    }

    @Override
    public void run() {
        try{
            File f = new File("");
            FilesManage fm = new FilesManage(is,bos);
            String path = f.getAbsolutePath();
            bos.write(("当前所在目录：" + path).getBytes());
            bos.flush();
            System.out.println(path);
            //String line;
            //BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
            boolean flag = true;
            while(flag){
                byte[] b = new byte[1024*5];
                int read = is.read(b);
                String line = new String(b,0,read);
                System.out.println(line);
                if (line.equals("bye")) {
                    System.out.println("Client:"+line);
                    flag = false;
                }
                else {
                    String[] sp = line.split(" ");
                    switch (sp[0]) {
                        case "dir":
                            fm.dirGet(path);
                            bos.write("end".getBytes());
                            bos.flush();
                            break;
                        case "cd":
                            path = fm.cd(path,line);
                            String a = path + "\\";
                            bos.write(a.getBytes());
                            sendEnd();
                            break;
                        case "copy":
                            fm.copyFile(sp[1],sp[2]);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "cut":
                            fm.cut(sp[1],sp[2]);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "md":
                            fm.mkdir(sp[1],path);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "delF":
                            fm.deleteFile(sp[1],path);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "delD":
                            fm.deleteDirectory(sp[1]);
                            sendEnd();
                            //System.out.print(path + ">");
                            break;
                        case "creatF":
                            fm.creatFile(sp[1],path);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "encode":
                            fm.Code(line);
                            sendEnd();
                            //System.out.print(pathNow + ">");
                            break;
                        case "decode":
                            fm.Code(line);
                            sendEnd();
                            break;
                            //System.out.print(pathNow + ">");
                        case "download":
                            fm.Download(sp[1]);
                            break;
                        case "upload":
                            fm.Upload(sp[1]);
                            break;
                        default:
                            System.out.println("Client:"+line);
                            bos.write("end".getBytes());
                            bos.flush();
                            break;
                    }
                }
            }
            bos.close(); //关闭Socket输出流
            is.close(); //关闭Socket输入流
            s.close(); //关闭Socket

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
