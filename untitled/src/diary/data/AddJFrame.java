package diary.data;


import cn.hutool.core.io.IoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddJFrame extends JFrame implements ActionListener {

    //定义标题输入框
    JTextField titleText  = new JTextField();

    //定义内容的输入区域
    JTextArea contentText = new JTextArea();

    //定义保存按钮
    JButton save = new JButton("保存");

    //定义取消按钮
    JButton cancel = new JButton("取消");

    public AddJFrame(){
        //初始化界面
        initFrame();

        //初始化组件
        initView();

        //让界面展示出来
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == save){
            // 如果数组的最后一个日记对象不为空，那就说明二维数组存满了
            // 保存添加的内容到本地文件
            try {
                // 创建日记存储文档的文件夹对象
                File file = new File("untitled\\src\\diary\\data\\data");
                // 得到文件夹下所有的日记存储文档的数组
                File[] files = file.listFiles();

                // 如果文件夹下没有日记文件，说明现在写的是第一个日记
                if (files.length == 0) {
                    // 与日记文档建立连接管道（特别的：第一个新文件名的序号1）
                    ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream("untitled\\src\\diary\\data\\data\\data" + 1 + ".txt"));
                    // 将输入框的标题和内容封装成一个日记对象写到本地文件中（特别的：第一个新日记的编号1）
                    Daily daily = new Daily(1, titleText.getText(), contentText.getText());
                    // 利用糊涂包将序列化的日记对象写入到本地文件中
                    IoUtil.writeObjects(bw, true, daily);
                    // 关闭流
                    bw.close();
                }else {
                    // 将最后一个日记文件的路径转换成字符串
                    String str2 = files[files.length - 1] + "";
                    System.out.println(str2);
                    // 截取最后一个日记文件的序号
                    StringBuffer sb=new StringBuffer(str2);
                    String str = sb.reverse().toString();
                    int ID = str.charAt(4) - '0';
                    // 与日记文档建立连接管道（特别的：新文件名的序号就是最后一个文件名的序号 +1）
                    ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream("untitled\\src\\diary\\data\\data\\data" + (ID + 1) + ".txt"));
                    // 将输入框的标题和内容封装成一个日记对象写到本地文件中（特别的：新日记的编号就是最后一个日记的编号 +1）
                    Daily daily = new Daily(ID + 1, titleText.getText(), contentText.getText());
                    // 利用糊涂包将序列化的日记对象写入到本地文件中
                    IoUtil.writeObjects(bw, true, daily);
                    // 关闭流
                    bw.close();
                }
                // 关闭当前界面回到主界面
                this.setVisible(false);
                new NoteJFrame();
            } catch (IOException ex) { // 捕获异常
                throw new RuntimeException(ex);
            }
        }else if(obj == cancel){
            System.out.println("取消按钮被点击了");
            this.setVisible(false);
            new NoteJFrame();
        }
    }

    private void initView() {
        //定义最上面的每日一记
        JLabel title = new JLabel("每日一记");
        title.setBounds(220, 20, 584, 50);
        title.setFont(new Font("宋体", Font.BOLD, 32));
        this.getContentPane().add(title);

        //定义文字：标题
        JLabel subject = new JLabel("标题");
        subject.setBounds(70,90,100,30);
        subject.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(subject);

        //定义文字：内容
        JLabel content = new JLabel("内容");
        content.setBounds(70,140,100,30);
        content.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(content);


        //设置标题的输入框
        titleText.setBounds(120,90,426,30);
        titleText.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(titleText);

        //设置内容的输入框
        contentText.setBounds(120,140,426,300);
        contentText.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(contentText);

        //设置保存按钮
        save.setBounds(132,466,140,40);
        save.setFont(new Font("宋体",Font.PLAIN,24));
        save.addActionListener(this);
        this.getContentPane().add(save);

        //设置取消按钮
        cancel.setBounds(312,466,140,40);
        cancel.setFont(new Font("宋体",Font.PLAIN,24));
        cancel.addActionListener(this);
        this.getContentPane().add(cancel);

    }


    //对添加界面的一些设置
    private void initFrame() {
        //设置界面的宽高
        this.setSize(600, 600);
        //设置界面的标题
        this.setTitle("每日一记（添加日记）");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置背景颜色
        this.getContentPane().setBackground(new Color(212,212,212));
    }


}
