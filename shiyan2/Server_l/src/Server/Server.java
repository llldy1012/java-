package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(8888);
            while (true) {
                Socket socket=null;
                try{
                    //使用accept()阻塞等待客户请求，有客户
                    socket=server.accept();//请求到来则产生一个Socket对象，并继续执行
                    new ServerThread(socket);
                }catch(Exception e){
                    e.printStackTrace();//出错，打印出错信息
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}