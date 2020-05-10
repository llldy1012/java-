package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    /**********************定义各控件********************************/
    private JLabel title = new JLabel("欢迎注册RFM系统");

    private JLabel lbAccount = new JLabel("账    号");
    private JTextField tfAccount = new JTextField(14);
    private JLabel lbPassword = new JLabel("密    码");
    private JPasswordField pfPassword = new JPasswordField(14);
    private JLabel lbPassword2 = new JLabel("确认密码");
    private JPasswordField pfPassword2 = new JPasswordField(14);

    private JButton btRegister = new JButton("注册");
    private JButton btLogin = new JButton("登录");
    private JButton btExit = new JButton("退出");

    public RegisterFrame() {
        /******************界面初始化********************/
        super("注册");
        title.setFont(new Font("宋体",1,22));
        lbAccount.setFont(new Font("黑体",0,16));
        lbPassword.setFont(new Font("黑体",0,16));
        lbPassword2.setFont(new Font("黑体",0,16));
        this.setLayout(new GridLayout(5,1));
        this.setSize(400, 270);
        GUIUtil.toCenter(this);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        p1.add(lbAccount);
        p1.add(tfAccount);

        p2.add(lbPassword);
        p2.add(pfPassword);

        p3.add(lbPassword2);
        p3.add(pfPassword2);

        p4.add(btLogin);
        p4.add(btRegister);
        p4.add(btExit);

        p5.add(title);

        this.add(p5);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4,BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /****************************增加监听***********************/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btRegister) {
            String password1 = new String(pfPassword.getPassword());
            String password2 = new String(pfPassword2.getPassword());
            if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(this, "两个密码不相同");
                return;
            }
            String account = tfAccount.getText();
            FileOpe.getInfoByAccount(account);
            if (Conf.account != null) {
                JOptionPane.showMessageDialog(this, "用户已经注册");
                return;
            }
            FileOpe.updateCustomer(account, password1);
            JOptionPane.showMessageDialog(this, "注册成功");
        } else if (e.getSource() == btLogin) {
            this.dispose();
            new LoginFrame();
        } else {
            JOptionPane.showMessageDialog(this, "欢迎再次使用！");
            System.exit(0);
        }
    }
}
