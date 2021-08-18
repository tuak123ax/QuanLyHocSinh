import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import static java.awt.Color.*;

public class jdbc {
    public static void main(String[]args) throws IOException, ClassNotFoundException, SQLException {
        JFrame jFrame=MyFrame();
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label=new JLabel();
        Border border=BorderFactory.createLineBorder(green,3);
        label.setText("Quản Lý Học Sinh");
        label.setForeground(green);
        label.setFont(new Font("Time New Romans", Font.PLAIN,50));
        label.setBorder(border);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label,BorderLayout.NORTH);
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridBagLayout());
        JButton button1=new JButton("Thêm");
        ImageIcon icon1=new ImageIcon("rsz_add.png");
        button1.setIcon(icon1);
        JPanel panelCenter=new JPanel();
        BorderLayout borderLayout=new BorderLayout();
        panelCenter.setLayout(borderLayout);
        JPanel panelCenter1=new JPanel();
        JLabel jlabel=new JLabel();
        jlabel.setText("Sắp xếp theo:");
        JRadioButton radioButton1=new JRadioButton("Mã tăng dần");
        JRadioButton radioButton2=new JRadioButton("Mã giảm dần");
        JRadioButton radioButton3=new JRadioButton("Điểm tăng dần");
        JRadioButton radioButton4=new JRadioButton("Điểm giảm dần");
        ButtonGroup butgrp=new ButtonGroup();
        butgrp.add(radioButton1);
        butgrp.add(radioButton2);
        butgrp.add(radioButton3);
        butgrp.add(radioButton4);
        JButton jButton=new JButton("Xác Nhận");
        JLabel Mode=new JLabel("Mode:");
        JRadioButton radioButton5=new JRadioButton("Light");
        JRadioButton radioButton6=new JRadioButton("Dark");
        ButtonGroup grp=new ButtonGroup();
        grp.add(radioButton5);
        grp.add(radioButton6);
        final Vector[] Row = {new Vector()};
        String[]columnName={"Mã","Tên","Điểm","Địa chỉ","Ghi chú","Hình ảnh"};
        String[][] data={};
        JTable jTable=new JTable(data,columnName);
        JScrollPane jScrollPane=new JScrollPane(jTable);
        JPanel panelCenter2=new JPanel();
        panelCenter2.add(jScrollPane);
        panelCenter.add(panelCenter1,BorderLayout.NORTH);
        panelCenter.add(panelCenter2,BorderLayout.CENTER);
        panel.add(panelCenter,BorderLayout.CENTER);
        Vector Header=new Vector();
        Header.add("Mã");
        Header.add("Tên");
        Header.add("Điểm");
        Header.add("Địa chỉ");
        Header.add("Ghi chú");
        Header.add("Hình ảnh");
        Vector finalRow = Row[0];
        if(FileSize("DuLieu.txt")!=0)
        {
            Row[0] =DocFile(Row[0]);
            jTable.setModel(new DefaultTableModel(finalRow,Header));
        }
        panelCenter1.add(jlabel);
        panelCenter1.add(radioButton1);
        panelCenter1.add(radioButton2);
        panelCenter1.add(radioButton3);
        panelCenter1.add(radioButton4);
        panelCenter1.add(jButton);
        JPanel mode=new JPanel();
        mode.add(Mode);
        mode.add(radioButton5);
        mode.add(radioButton6);
        JButton modeBut=new JButton("Change");
        modeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==modeBut)
                {
                    if(radioButton6.isSelected())
                    {
                        panel.setBackground(black);
                        panelCenter1.setBackground(black);
                        panelCenter2.setBackground(black);
                    }
                    if(radioButton5.isSelected())
                    {
                        panel.setBackground(null);
                        panelCenter1.setBackground(null);
                        panelCenter2.setBackground(null);
                    }
                }
            }
        });
        mode.add(modeBut);
        Border border1=BorderFactory.createLineBorder(red,2);
        mode.setBorder(border1);
        panelCenter1.setBorder(border);
        panelCenter1.add(mode);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButton1.isSelected())
                {
                    Row[0] =MHSTang(Row[0]);
                    jTable.setModel(new DefaultTableModel(Row[0],Header));
                    JOptionPane.showMessageDialog(null,"Đã sắp xếp thành công!");
                }
                if(radioButton2.isSelected())
                {
                    Row[0] =MHSGiam(Row[0]);
                    jTable.setModel(new DefaultTableModel(Row[0],Header));
                    JOptionPane.showMessageDialog(null,"Đã sắp xếp thành công!");
                }
                if(radioButton3.isSelected())
                {
                    Row[0] =DiemTang(Row[0]);
                    jTable.setModel(new DefaultTableModel(Row[0],Header));
                    JOptionPane.showMessageDialog(null,"Đã sắp xếp thành công!");
                }
                if(radioButton4.isSelected())
                {
                    Row[0] =DiemGiam(Row[0]);
                    jTable.setModel(new DefaultTableModel(Row[0],Header));
                    JOptionPane.showMessageDialog(null,"Đã sắp xếp thành công!");
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button1)
                {
                    JFrame jFrame1=new JFrame();
                    jFrame1.setTitle("Thêm");
                    ImageIcon Icon=new ImageIcon("rsz_add.png");
                    jFrame1.setIconImage(Icon.getImage());
                    jFrame1.setSize(520,450);
                    jFrame1.setResizable(false);
                    jFrame1.setVisible(true);
                    JPanel panel=new JPanel();
                    JLabel label1=new JLabel();
                    HocSinh hs=new HocSinh();
                    label1.setText("Mã học sinh:");
                    JTextField textField=new JTextField();
                    textField.setPreferredSize(new Dimension(500,30));
                    panel.add(label1);
                    panel.add(textField);
                    JPanel panel1=new JPanel();
                    JLabel label2=new JLabel();
                    label2.setText("Tên học sinh:");
                    JTextField textField1=new JTextField();
                    textField1.setPreferredSize(new Dimension(500,30));
                    panel1.add(label2);
                    panel1.add(textField1);
                    JPanel panel2=new JPanel();
                    JLabel label3=new JLabel();
                    label3.setText("Điểm học sinh:");
                    JTextField textField2=new JTextField();
                    textField2.setPreferredSize(new Dimension(500,30));
                    panel2.add(label3);
                    panel2.add(textField2);
                    JPanel panel3=new JPanel();
                    JLabel label4=new JLabel();
                    label4.setText("Địa chỉ:");
                    JTextField textField3=new JTextField();
                    textField3.setPreferredSize(new Dimension(500,30));
                    panel3.add(label4);
                    panel3.add(textField3);
                    JPanel panel4=new JPanel();
                    JLabel label5=new JLabel();
                    label5.setText("Ghi chú:");
                    JTextField textField4=new JTextField();
                    textField4.setPreferredSize(new Dimension(500,30));
                    panel4.add(label5);
                    panel4.add(textField4);
                    JPanel panel5=new JPanel();
                    JLabel label6=new JLabel();
                    label6.setText("Hình ảnh:");
                    panel5.add(label6);
                    JButton fileBut=new JButton("Chọn ảnh");
                    panel5.add(fileBut);
                    JTextField textField5=new JTextField();
                    textField5.setPreferredSize(new Dimension(500,30));
                    panel5.add(textField5);
                    fileBut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==fileBut)
                            {
                                JFileChooser fileChooser=new JFileChooser();
                                int response=fileChooser.showOpenDialog(null);
                                if(response==JFileChooser.APPROVE_OPTION)
                                {
                                    File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                                    String path=String.valueOf(file);
                                    textField5.setText(path);
                                }
                            }
                        }
                    });
                    JPanel mainPanel=new JPanel();
                    BoxLayout boxLayout=new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
                    mainPanel.setLayout(boxLayout);
                    mainPanel.add(panel);
                    mainPanel.add(panel1);
                    mainPanel.add(panel2);
                    mainPanel.add(panel3);
                    mainPanel.add(panel4);
                    mainPanel.add(panel5);
                    jFrame1.add(mainPanel);
                    JPanel butPanel=new JPanel();
                    JButton but1=new JButton("Thêm");
                    if(radioButton6.isSelected())
                    {
                        panel.setBackground(black);
                        label1.setForeground(green);
                        panel1.setBackground(black);
                        label2.setForeground(green);
                        panel2.setBackground(black);
                        label3.setForeground(green);
                        panel3.setBackground(black);
                        label4.setForeground(green);
                        panel4.setBackground(black);
                        label5.setForeground(green);
                        butPanel.setBackground(black);
                        but1.setBackground(green);
                        panel5.setBackground(black);
                        label6.setForeground(green);
                        fileBut.setBackground(green);
                    }
                    but1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==but1)
                            {
                                if(textField.getText().length()==0||textField1.getText().length()==0||
                                        textField2.getText().length()==0||textField3.getText().length()==0||
                                        textField4.getText().length()==0||textField5.getText().length()==0)
                                {
                                    JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ tất cả thông tin");
                                }
                                else
                                {
                                    if(checkHS(finalRow,Integer.parseInt(textField.getText())))
                                    {
                                        JOptionPane.showMessageDialog(null,"Học sinh này đã tồn tại!");
                                    }
                                    else
                                    {
                                        hs.setMHS(Integer.parseInt(textField.getText()));
                                        hs.setTenHS(textField1.getText());
                                        hs.setDiem(Float.parseFloat(textField2.getText()));
                                        hs.setDiaChi(textField3.getText());
                                        hs.setGhiChu(textField4.getText());
                                        hs.setHinhanh(textField5.getText());
                                        Vector data=new Vector();
                                        data.add(hs.getMHS());
                                        data.add(hs.getTenHS());
                                        data.add(hs.getDiem());
                                        data.add(hs.getDiaChi());
                                        data.add(hs.getGhiChu());
                                        data.add(hs.getHinhanh());
                                        finalRow.add(data);
                                        jTable.setModel(new DefaultTableModel(finalRow,Header));
                                        try {
                                            addSql(hs);
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        JOptionPane.showMessageDialog(null,"Đã thêm thành công!");
                                    }
                            }
                        }}
                    });
                    FlowLayout flowLayout=new FlowLayout();
                    butPanel.setLayout(flowLayout);
                    butPanel.add(but1);
                    mainPanel.add(butPanel);
                }
            }
        });
        JButton button2=new JButton("Xóa");
        Vector finalRow1 = Row[0];
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button2) {
                    if (jTable.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                    else {
                        Vector a= (Vector) finalRow1.get(jTable.getSelectedRow());
                        finalRow1.remove(jTable.getSelectedRow());
                        jTable.setModel(new DefaultTableModel(finalRow1, Header));
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                        try {
                            deletesql(a);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });
        ImageIcon icon2=new ImageIcon("rsz_delete.png");
        button2.setIcon(icon2);
        JButton button3=new JButton("Sửa");
        Vector finalRow2 = Row[0];
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button3)
                {
                    if(jTable.getSelectedRow()<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần sửa!");
                    else
                    {
                        JFrame jFrame1=new JFrame();
                        jFrame1.setTitle("Sửa");
                        ImageIcon Icon=new ImageIcon("rsz_update-icon.png");
                        jFrame1.setIconImage(Icon.getImage());
                        jFrame1.setSize(520,450);
                        jFrame1.setResizable(false);
                        jFrame1.setVisible(true);
                        JPanel panel=new JPanel();
                        JLabel label1=new JLabel();
                        HocSinh hs=new HocSinh();
                        label1.setText("Mã học sinh:");
                        JTextField textField=new JTextField();
                        textField.setPreferredSize(new Dimension(500,30));
                        panel.add(label1);
                        panel.add(textField);
                        JPanel panel1=new JPanel();
                        JLabel label2=new JLabel();
                        label2.setText("Tên học sinh:");
                        JTextField textField1=new JTextField();
                        textField1.setPreferredSize(new Dimension(500,30));
                        panel1.add(label2);
                        panel1.add(textField1);
                        JPanel panel2=new JPanel();
                        JLabel label3=new JLabel();
                        label3.setText("Điểm học sinh:");
                        JTextField textField2=new JTextField();
                        textField2.setPreferredSize(new Dimension(500,30));
                        panel2.add(label3);
                        panel2.add(textField2);
                        JPanel panel3=new JPanel();
                        JLabel label4=new JLabel();
                        label4.setText("Địa chỉ:");
                        JTextField textField3=new JTextField();
                        textField3.setPreferredSize(new Dimension(500,30));
                        panel3.add(label4);
                        panel3.add(textField3);
                        JPanel panel4=new JPanel();
                        JLabel label5=new JLabel();
                        label5.setText("Ghi chú:");
                        JTextField textField4=new JTextField();
                        textField4.setPreferredSize(new Dimension(500,30));
                        panel4.add(label5);
                        panel4.add(textField4);
                        JPanel panel5=new JPanel();
                        JLabel label6=new JLabel();
                        label6.setText("Hình ảnh:");
                        panel5.add(label6);
                        JButton fileBut=new JButton("Chọn ảnh");
                        panel5.add(fileBut);
                        JTextField textField5=new JTextField();
                        textField5.setPreferredSize(new Dimension(500,30));
                        panel5.add(textField5);
                        fileBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource()==fileBut)
                                {
                                    JFileChooser fileChooser=new JFileChooser();
                                    int response=fileChooser.showOpenDialog(null);
                                    if(response==JFileChooser.APPROVE_OPTION)
                                    {
                                        File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                                        String path=String.valueOf(file);
                                        textField5.setText(path);
                                    }
                                }
                            }
                        });
                        JPanel mainPanel=new JPanel();
                        BoxLayout boxLayout=new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
                        mainPanel.setLayout(boxLayout);
                        mainPanel.add(panel);
                        mainPanel.add(panel1);
                        mainPanel.add(panel2);
                        mainPanel.add(panel3);
                        mainPanel.add(panel4);
                        mainPanel.add(panel5);
                        jFrame1.add(mainPanel);
                        JPanel butPanel=new JPanel();
                        JButton but1=new JButton("Sửa");
                        if(radioButton6.isSelected())
                        {
                            panel.setBackground(black);
                            label1.setForeground(green);
                            panel1.setBackground(black);
                            label2.setForeground(green);
                            panel2.setBackground(black);
                            label3.setForeground(green);
                            panel3.setBackground(black);
                            label4.setForeground(green);
                            panel4.setBackground(black);
                            label5.setForeground(green);
                            butPanel.setBackground(black);
                            but1.setBackground(green);
                            panel5.setBackground(black);
                            label6.setForeground(green);
                            fileBut.setBackground(green);
                        }
                        but1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource()==but1)
                                {
                                    Vector data= (Vector) finalRow2.get(jTable.getSelectedRow());
                                    int old= (int) data.get(0);
                                    if(textField.getText().length()!=0)
                                    {hs.setMHS(Integer.parseInt(textField.getText()));
                                        data.remove(0);
                                        data.add(0,hs.getMHS());
                                    }
                                    if(textField1.getText().length()!=0)
                                    {hs.setTenHS(textField1.getText());
                                        data.remove(1);
                                        data.add(1,hs.getTenHS());
                                    }
                                    if(textField2.getText().length()!=0)
                                    {hs.setDiem(Float.parseFloat(textField2.getText()));
                                        data.remove(2);
                                        data.add(2,hs.getDiem());
                                    }
                                    if(textField3.getText().length()!=0)
                                    {hs.setDiaChi(textField3.getText());
                                        data.remove(3);
                                        data.add(3,hs.getDiaChi());
                                    }
                                    if(textField4.getText().length()!=0)
                                    {hs.setGhiChu(textField4.getText());
                                        data.remove(4);
                                        data.add(4,hs.getGhiChu());
                                    }
                                    if(textField5.getText().length()!=0)
                                    {hs.setHinhanh(textField5.getText());
                                        data.remove(5);
                                        data.add(5,hs.getHinhanh());
                                    }
                                    finalRow2.remove(jTable.getSelectedRow());
                                    finalRow2.add(jTable.getSelectedRow(),data);
                                    jTable.setModel(new DefaultTableModel(finalRow2,Header));
                                    JOptionPane.showMessageDialog(null,"Đã sửa thành công!");
                                    try {
                                        updatesql(old,hs);
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });
                        FlowLayout flowLayout=new FlowLayout();
                        butPanel.setLayout(flowLayout);
                        butPanel.add(but1);
                        mainPanel.add(butPanel);
                    }}
            }
        });
        ImageIcon icon3=new ImageIcon("rsz_update-icon.png");
        button3.setIcon(icon3);
        JButton button6=new JButton("Xem ảnh");
        button6.setIcon(new ImageIcon("rsz_photo.png"));
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button6)
                {
                    if (jTable.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xem ảnh!");
                    else {
                     JFrame frame=new JFrame();
                     frame.setSize(300,300);
                     frame.setVisible(true);
                     frame.setIconImage(new ImageIcon("rsz_photo.png").getImage());
                     frame.setTitle("Xem ảnh");
                     Vector a= (Vector) Row[0].get(jTable.getSelectedRow());
                     ImageIcon photo=new ImageIcon(String.valueOf(a.get(5)));
                     JLabel label1=new JLabel();
                     label1.setIcon(photo);
                     frame.add(label1);
                     frame.setResizable(false);
                     frame.pack();
                    }
                }
            }
        });
        JButton button4=new JButton("Export->csv");
        Vector finalRow3 = Row[0];
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button4)
                {
                    try {
                        LamTrangFile2();
                        Export(finalRow3);
                        JOptionPane.showMessageDialog(null,"Đã export dữ liệu ra file csv thành công!");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        ImageIcon icon4=new ImageIcon("rsz_import.png");
        button4.setIcon(icon4);
        JButton button5=new JButton("Thoát");
        Vector finalRow4 = Row[0];
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button5)
                {
                    try {
                        LamTrangFile1();
                        GhiFile(finalRow4, finalRow4.size());
                        JOptionPane.showMessageDialog(null,"Đã lưu lại dữ liệu vừa nhập!");
                        System.exit(0);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        ImageIcon icon5=new ImageIcon("exit.png");
        button5.setIcon(icon5);
        GridBagConstraints gbc1=new GridBagConstraints();
        gbc1.fill=GridBagConstraints.HORIZONTAL;
        gbc1.gridx=0;
        gbc1.gridy=0;
        panel1.add(button1,gbc1);
        gbc1.gridx=1;
        gbc1.gridy=0;
        panel1.add(button2,gbc1);
        gbc1.gridx=2;
        gbc1.gridy=0;
        panel1.add(button3,gbc1);
        gbc1.gridx=3;
        gbc1.gridy=0;
        panel1.add(button6,gbc1);
        gbc1.gridx=4;
        gbc1.gridy=0;
        panel1.add(button4,gbc1);
        gbc1.gridx=5;
        gbc1.gridy=0;
        panel1.add(button5,gbc1);
        panel.add(panel1,BorderLayout.SOUTH);
        JPanel warningPan=new JPanel();
        BoxLayout boxLayout=new BoxLayout(warningPan,BoxLayout.Y_AXIS);
        warningPan.setLayout(boxLayout);
        JButton warningBut=new JButton("Attention!");
        ImageIcon warning=new ImageIcon("rsz_warning.png");
        warningBut.setIcon(warning);
        warningBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==warningBut)
                {
                    JOptionPane.showMessageDialog(null,"Nhấn nút thoát ở hàng dưới cùng sẽ lưu lại dữ liệu trong bảng để dùng cho lần chạy sau!");
                    JOptionPane.showMessageDialog(null,"Mã học sinh và Điểm là dữ liệu kiểu số. Làm ơn không nhập ký tự chữ cái vào 2 mục này!");
                }
            }
        });
        warningPan.add(Box.createVerticalGlue());
        JPanel panelBot=new JPanel();
        panelBot.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelBot.setLayout(new BoxLayout(panelBot,BoxLayout.X_AXIS));
        panelBot.add(warningBut);
        warningPan.add(panelBot);
        warningPan.add(Box.createRigidArea(new Dimension(0,200)));
        panel.add(warningPan,BorderLayout.EAST);
        JPanel imagePan=new JPanel();
        BoxLayout boxLayout2=new BoxLayout(imagePan,BoxLayout.Y_AXIS);
        imagePan.setLayout(boxLayout2);
        ImageIcon imageIcon=new ImageIcon("fit.png");
        JLabel imagelabel=new JLabel();
        imagelabel.setIcon(imageIcon);
        imagePan.add(Box.createVerticalGlue());
        JPanel panelBot2=new JPanel();
        panelBot2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelBot2.setLayout(new BoxLayout(panelBot2,BoxLayout.X_AXIS));
        panelBot2.add(imagelabel);
        imagePan.add(panelBot2);
        imagePan.add(Box.createRigidArea(new Dimension(0,180)));
        panel.add(imagePan,BorderLayout.WEST);
        jFrame.add(panel);
        modeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==modeBut)
                {
                    if(radioButton6.isSelected())
                    {
                        panel.setBackground(black);
                        panelCenter1.setBackground(black);
                        panelCenter2.setBackground(black);
                        panelCenter.setBackground(black);
                        mode.setBackground(black);
                        Mode.setForeground(green);
                        imagePan.setBackground(black);
                        warningPan.setBackground(black);
                        panel1.setBackground(black);
                        jTable.setBackground(black);
                        jScrollPane.setBackground(black);
                        radioButton1.setBackground(black);
                        radioButton2.setBackground(black);
                        radioButton3.setBackground(black);
                        radioButton4.setBackground(black);
                        radioButton5.setBackground(black);
                        radioButton6.setBackground(black);
                        radioButton1.setForeground(green);
                        radioButton2.setForeground(green);
                        radioButton3.setForeground(green);
                        radioButton4.setForeground(green);
                        radioButton5.setForeground(green);
                        radioButton6.setForeground(green);
                        jlabel.setForeground(green);
                        modeBut.setBackground(green);
                        jButton.setBackground(green);
                        jTable.setForeground(green);
                        warningBut.setBackground(green);
                        button1.setBackground(green);
                        button2.setBackground(green);
                        button3.setBackground(green);
                        button4.setBackground(green);
                        button5.setBackground(green);
                        button6.setBackground(green);
                    }
                    if(radioButton5.isSelected())
                    {
                        panel.setBackground(null);
                        panelCenter1.setBackground(null);
                        panelCenter2.setBackground(null);
                        panelCenter.setBackground(null);
                        mode.setBackground(null);
                        Mode.setForeground(null);
                        imagePan.setBackground(null);
                        warningPan.setBackground(null);
                        panel1.setBackground(null);
                        jTable.setBackground(white);
                        jScrollPane.setBackground(null);
                        radioButton1.setBackground(null);
                        radioButton2.setBackground(null);
                        radioButton3.setBackground(null);
                        radioButton4.setBackground(null);
                        radioButton5.setBackground(null);
                        radioButton6.setBackground(null);
                        radioButton1.setForeground(null);
                        radioButton2.setForeground(null);
                        radioButton3.setForeground(null);
                        radioButton4.setForeground(null);
                        radioButton5.setForeground(null);
                        radioButton6.setForeground(null);
                        jlabel.setForeground(null);
                        modeBut.setBackground(null);
                        jButton.setBackground(null);
                        jTable.setForeground(null);
                        warningBut.setBackground(null);
                        button1.setBackground(null);
                        button2.setBackground(null);
                        button3.setBackground(null);
                        button4.setBackground(null);
                        button5.setBackground(null);
                        button6.setBackground(null);
                    }
                }
            }
        });
        jFrame.pack();
    }
    static JFrame MyFrame()
    {
        ImageIcon imageIcon=new ImageIcon("student.png");
        JFrame jFrame=new JFrame();
        jFrame.setSize(700,700);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("Quản Lý Học Sinh");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        return jFrame;
    }
    static void Export(Vector a) throws IOException {
        FileWriter fileWriter=new FileWriter("Export.csv");
        for(int i=0;i<a.size();i++)
        {
            Vector hs=(Vector)a.get(i);
            fileWriter.write("   Hoc sinh thu "+(i+1)+" :\n");
            fileWriter.write("Ma hoc sinh: "+hs.get(0)+'\n');
            fileWriter.write("Ten hoc sinh: "+hs.get(1)+'\n');
            fileWriter.write("Diem hoc sinh: "+hs.get(2)+'\n');
            fileWriter.write("Dia chi hoc sinh: "+hs.get(3)+'\n');
            fileWriter.write("Ghi chu hoc sinh: "+hs.get(4)+'\n');
        }
        fileWriter.flush();
        fileWriter.close();
    }
    static void GhiFile(Vector a,int count) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieu.txt"));
        out.writeInt(count);
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static long FileSize(String filename)
    {
        File file=new File(filename);
        if(!file.exists()||!file.isFile()) return 0;
        else
        {
            return file.length();
        }
    }
    static Vector DocFile(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieu.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Vector hs= (Vector) in.readObject();
            a.add(hs);
        }
        in.close();
        return a;
    }
    static void LamTrangFile1() throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieu.txt"));
        out.writeBytes("");
        out.flush();
        out.close();
    }
    static void LamTrangFile2() throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Export.csv"));
        out.writeBytes("");
        out.flush();
        out.close();
    }
    static Vector MHSTang(Vector a)
    {
        for(int i=0;i<a.size()-1;i++)
        {
            for(int j=i+1;j<a.size();j++)
            {
                Object ai=a.get(i);
                Object aj=a.get(j);
                Vector hs1= (Vector) ai;
                Vector hs2= (Vector) aj;
                int num1=Integer.parseInt(String.valueOf(hs1.get(0)));
                int num2=Integer.parseInt(String.valueOf(hs2.get(0)));
                if(num1>num2)
                {
                    a.set(i,aj);
                    a.set(j,ai);
                }
            }
        }
        return a;
    }
    static Vector MHSGiam(Vector a)
    {
        for(int i=0;i<a.size()-1;i++)
        {
            for(int j=i+1;j<a.size();j++)
            {
                Object ai=a.get(i);
                Object aj=a.get(j);
                Vector hs1= (Vector) ai;
                Vector hs2= (Vector) aj;
                int num1=Integer.parseInt(String.valueOf(hs1.get(0)));
                int num2=Integer.parseInt(String.valueOf( hs2.get(0)));
                if(num1<num2)
                {
                    a.set(i,aj);
                    a.set(j,ai);
                }
            }
        }
        return a;
    }
    static Vector DiemTang(Vector a)
    {
        for(int i=0;i<a.size()-1;i++)
        {
            for(int j=i+1;j<a.size();j++)
            {
                Object ai=a.get(i);
                Object aj=a.get(j);
                Vector hs1= (Vector) ai;
                Vector hs2= (Vector) aj;
                float num1=Float.parseFloat(String.valueOf( hs1.get(2)));
                float num2=Float.parseFloat(String.valueOf( hs2.get(2)));
                if(num1>num2)
                {
                    a.set(i,aj);
                    a.set(j,ai);
                }
            }
        }
        return a;
    }
    static Vector DiemGiam(Vector a)
    {
        for(int i=0;i<a.size()-1;i++)
        {
            for(int j=i+1;j<a.size();j++)
            {
                Object ai=a.get(i);
                Object aj=a.get(j);
                Vector hs1= (Vector) ai;
                Vector hs2= (Vector) aj;
                float num1=Float.parseFloat(String.valueOf( hs1.get(2)));
                float num2=Float.parseFloat(String.valueOf( hs2.get(2)));
                if(num1<num2)
                {
                    a.set(i,aj);
                    a.set(j,ai);
                }
            }
        }
        return a;
    }
    static void addSql(HocSinh a) throws SQLException {
        String dbURL = "jdbc:sqlserver://DESKTOP-UNNBSU0\\MSSQLSERVER:1433;databaseName=QLHS;user=tuak123ax;password=123456Tu";
        Connection con1 = DriverManager.getConnection(dbURL);
        String onenhay="'";
        String sql="insert into SinhVien(Ma,Ten,Diem,DiaChi,GhiChu,HinhAnh)"+"values("+a.getMHS()+","+onenhay+a.getTenHS()+onenhay+","+
                a.getDiem()+","+onenhay+a.getDiaChi()+onenhay+","+onenhay+a.getGhiChu()+onenhay+","+onenhay+a.getHinhanh()+onenhay+")";
        Statement statement=con1.createStatement();
        int row= statement.executeUpdate(sql);
        con1.close();
        }
        static void deletesql(Vector a) throws SQLException {
            String dbURL = "jdbc:sqlserver://DESKTOP-UNNBSU0\\MSSQLSERVER:1433;databaseName=QLHS;user=tuak123ax;password=123456Tu";
            Connection con1 = DriverManager.getConnection(dbURL);
            String sql="delete SinhVien"+" where Ma="+(int)a.get(0);
            Statement statement=con1.createStatement();
            int row= statement.executeUpdate(sql);
            con1.close();
        }
        static void updatesql(int old,HocSinh a) throws SQLException {
            String dbURL = "jdbc:sqlserver://DESKTOP-UNNBSU0\\MSSQLSERVER:1433;databaseName=QLHS;user=tuak123ax;password=123456Tu";
            Connection con1 = DriverManager.getConnection(dbURL);
            String onenhay="'";
            String sql="update SinhVien" + " set Ma="+a.getMHS()+" where Ma="+old
                    +"update SinhVien" + " set Ten="+onenhay+a.getTenHS()+onenhay+" where Ma="+a.getMHS()
                    +"update SinhVien" + " set Diem="+a.getDiem()+" where Ma="+a.getMHS()
                    +"update SinhVien" + " set DiaChi="+onenhay+a.getDiaChi()+onenhay+" where Ma="+a.getMHS()
                    +"update SinhVien" + " set GhiChu="+onenhay+a.getGhiChu()+onenhay+" where Ma="+a.getMHS()
                    +"update SinhVien" + " set HinhAnh="+onenhay+a.getHinhanh()+onenhay+" where Ma="+a.getMHS();
            Statement statement=con1.createStatement();
            int row= statement.executeUpdate(sql);
            con1.close();
        }
        static boolean checkHS(Vector a,int Ma)
        {
            for(int i=0;i<a.size();i++)
            {
                Vector temp= (Vector) a.get(i);
                if(Ma==(int)temp.get(0))
                {
                    return true;
                }
            }
            return false;
        }
}
