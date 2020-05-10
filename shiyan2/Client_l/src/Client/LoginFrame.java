package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel title = new JLabel("欢迎使用RFM系统");
    private JLabel ip=new JLabel("IP ");
    private JTextField tfip=new JTextField("127.0.0.1",9);
    private JLabel port = new JLabel("端口号");
    private  JTextField tfport = new JTextField("8888",4);

    private JLabel lbAccount=new JLabel("账号 ");
    private JTextField tfAccount=new JTextField(14);
    private JLabel lbPassword=new JLabel("密码 ");
    private JPasswordField pfPassword=new JPasswordField(14);
    private JButton btLogin=new JButton("登录");
    private JButton btRegister=new JButton("注册");
    private JButton btExit=new JButton("退出");
    public LoginFrame() {
        /**********************界面初始化*****************************/
        super("登录");
        title.setFont(new Font("宋体",1,24));
        ip.setFont(new Font("黑体",0,16));
        port.setFont(new Font("黑体",0,16));
        lbAccount.setFont(new Font("黑体",0,16));
        lbPassword.setFont(new Font("黑体",0,16));
        this.setLayout(new GridLayout(5,1));
        this.setSize(400,270);
        GUIUtil.toCenter(this);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        p1.add(btLogin);
        p1.add(btRegister);
        p1.add(btExit);

        p2.add(lbAccount);
        p2.add(tfAccount);
        p3.add(lbPassword);
        p3.add(pfPassword);

        p4.add(title);

        p5.add(ip);
        p5.add(tfip);
        p5.add(port);
        p5.add(tfport);

        this.add(p4);
        this.add(p5);
        this.add(p2);
        this.add(p3);
        this.add(p1,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        /*****************************增加监听************************/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btLogin) {
            String ip = tfip.getText();
            String tfort = tfport.getText();

            String account = tfAccount.getText();
            String password = new String(pfPassword.getPassword());
            FileOpe.getInfoByAccount(account);
            if(Conf.account == null || !Conf.pwd.equals(password)) {
                JOptionPane.showMessageDialog(this,"登录失败");
                return;
            }
            JOptionPane.showMessageDialog(this,"登录成功");
            this.dispose();
//            try {
//                Client.start(ip,Integer.parseInt(tfort));
//                new OperationFrame();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
            new OperationFrame(ip,Integer.parseInt(tfort));

        }
        else if(e.getSource()==btRegister) {
            this.dispose();
            new RegisterFrame();
        }
        else {
            JOptionPane.showMessageDialog(this,"欢迎再次使用！");
            System.exit(0);
        }
    }
}
