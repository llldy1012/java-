package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class OperationFrame extends JFrame implements ActionListener {
    /************************定义各控件******************************/
    private String welcomMsg="选择如下操作:";
    private JLabel lblWelcome=new JLabel(welcomMsg);


    private JButton btQuery=new JButton("显示详细信息");
    private JButton btModify=new JButton("修改个人资料");
    private JButton btExit=new JButton("退出");

    private JLabel cmd = new JLabel("请输入指令：");
    private JTextArea jta = new JTextArea();
    private JButton doing = new JButton("执行");
    private JScrollPane jsp = new JScrollPane(jta);


    private String ip;
    private int port;
    private Socket socket = null;
    private BufferedOutputStream bos = null;
    private BufferedInputStream is = null;


    private JTextField jtf = new JTextField(28);
    public OperationFrame(String ip, int port) {
        /****************************界面初始化************************/
        super("欢迎使用RFM系统");

        this.ip = ip;
        this.port = port;

        this.setSize(600,400);
        GUIUtil.toCenter(this);//dasdsa
        this.setLayout(new BorderLayout());

        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jta.setEditable(false);
        jta.setFont(new Font("宋体",0,15));
        cmd.setFont(new Font("黑体",1,15));
        doing.setFont(new Font("黑体",1,15));

        jtf.setFont(new Font("宋体",1,15));

        JPanel psouth = new JPanel();
        psouth.add(cmd);
        psouth.add(jtf);
        psouth.add(doing);

        this.add(jsp,BorderLayout.CENTER);
        this.add(psouth,BorderLayout.SOUTH);
        this.add(btExit,BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        /**********************增加监听*******************************/
        btExit.addActionListener(this);
        doing.addActionListener(this);

        //连接
        if (connectServer(ip,port)) {
            try {
                firstTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(this, "连接服务器失败，请重新连接", "错误",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose();
            new LoginFrame(); //dsadsad
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doing) {
            String trim = jtf.getText().trim();
            if (trim == null || trim.equals("")){
                JOptionPane.showMessageDialog(this, "指令不能为空！", "错误",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                jta.append("当前指令:" + trim + "\r\n");
                jtf.setText(null);
                try {
                    SendAndGet(trim,jta);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
        else {
            JOptionPane.showMessageDialog(this,"欢迎再次使用!");
            try {
                bos.close(); //关闭Socket输出流
                is.close(); //关闭Socket输入流
                socket.close(); //关闭Socket
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);
        }
    }


    private boolean connectServer(String ip, int port) {
        try {
            socket = new Socket(ip,port);
            bos = new BufferedOutputStream(socket.getOutputStream());
            is = new BufferedInputStream(socket.getInputStream());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void SendAndGet(String cmd, JTextArea jta) throws IOException {
        if(!cmd.equals("quit")) {
            //将从系统标准输入读入的字符串输出到Server
            bos.write(cmd.getBytes());
            //刷新输出流，使Server马上收到该字符串
            bos.flush();
            boolean flag = true;
            while (flag) {
                String[] sp = cmd.split(" ");
                if (sp[0].equals("download")){
                    byte[] b2 = new byte[1024*5];
                    File f2 = new File("Download");
                    f2.mkdirs();
                    File f = new File(f2.getName() +"\\" +new File(sp[1]).getName());
                    FileOutputStream fos = new FileOutputStream(f);
                    //BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                    int len = 0;
                    byte[] b = new byte[1024];
                    boolean t = true;
                    while (t) {
                        len = is.read(b);
                        if (len == 1024) {
                            fos.write(b,0,len);
                        }
                        else {
                            fos.write(b,0,len);
                            t = false;
                            flag = false;
                            jta.append("文件已下载到目录:" + f2.getAbsolutePath() +"\r\n");
                            //System.out.println("文件已下载到目录:" + f2.getAbsolutePath());
                        }
                    }
                    fos.flush();
                    fos.close();
                }else if (sp[0].equals("upload")) {
                    //....
                    File f1 = new File(sp[1]);
                    if (f1.isFile()) {
                        FileInputStream ff = new FileInputStream(f1);
                        int len = 0;
                        byte[] bb = new byte[1024];
                        while ((len = ff.read(bb)) != -1) {
                            bos.write(bb,0,len);
                        }
                        bos.flush();
                        ff.close();
                        jta.append("上传成功，请查看Upload目录。" + "\r\n");
                        //System.out.println("上传成功，请查看Upload目录。");
                        flag = false;
                    }
                }
                else {
                    byte[] bytes = new byte[1024*5];
                    int a = is.read(bytes);
                    String res = new String(bytes,0,a);
                    String[] sp11 = res.split("\\\\");
                    switch (sp[0]) {
                        case "dir":
                            for (String q : sp11) {
                                if (q.equals("end")) {
                                    flag = false;
                                } else jta.append(q + "\r\n");//System.out.println(q);
                            }
                            break;
                        default:
                            for (String q : sp11) {
                                if (q.equals("end")) {
                                    flag = false;
                                } else jta.append(q + "\\");//System.out.print(q + "\\");
                            }
                            jta.append("\r\n");//System.out.println();
                            break;
                    }


                }
            }
        }else {
            bos.close(); //关闭Socket输出流
            is.close(); //关闭Socket输入流
            socket.close(); //关闭Socket
            JOptionPane.showMessageDialog(this,"欢迎再次使用!");
            System.exit(0);
        }
    }
    private void firstTime() throws IOException {
        int len = 0;
        byte[] bytes = new byte[1024];
        len = is.read(bytes);
        //bos.write(bytes,0,len);
        jta.append(new String(bytes,0,len) + "\r\n");
    }
}
