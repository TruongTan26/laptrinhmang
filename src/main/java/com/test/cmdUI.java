package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class cmdUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private String abc = "";
	private JLabel lblNewLabel_2, lblNewLabel_1;

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cmdUI frame = new cmdUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void laymaxai() {
		String rsd = textField.getText();
        new Thread(()->{
            // kiểm tra ping google.com (gửi 5 gói tin)
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", rsd );
            // thực thi command line
            Process p = null;
            try {
                p = builder.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // lấy kết quả trả về trên command line
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                try {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                    
                    setAbc(getAbc()+"\n"+line);
                    
                    textArea.setText(getAbc());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
	}

	public cmdUI() throws UnknownHostException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(23, 102, 324, 28);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Input: ");
		lblNewLabel.setBounds(23, 63, 120, 28);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("RUN");
		btnNewButton.setBounds(357, 103, 76, 28);
		contentPane.add(btnNewButton);

		textArea = new JTextArea();
		textArea.setBounds(20, 101, 383, 357);
		textArea.setEditable(false);
		JScrollPane sc = new JScrollPane(textArea);
		sc.setBounds(20, 141, 499, 360);
		contentPane.add(sc);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(443, 102, 76, 28);
		contentPane.add(btnNewButton_1);

		InetAddress ip = InetAddress.getLocalHost();
		lblNewLabel_1 = new JLabel("Hello " + ip.getHostName() + ", your IP: " + ip.getHostAddress());
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 24, 496, 28);
		contentPane.add(lblNewLabel_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				laymaxai();
			}

		});

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					laymaxai();
				}
			}
		});
	}
}
