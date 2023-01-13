package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.JSchException;
import com.pastdev.jsch.DefaultSessionFactory;
import com.pastdev.jsch.command.CommandRunner;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sshUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField,textField_1,textField_2,textField_3;
	private JTextArea textArea;

	private String abc = "";
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
					sshUI frame = new sshUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws JSchException
	 */

	public void xaitodi() throws JSchException, IOException {

		DefaultSessionFactory ss = new DefaultSessionFactory(textField.getText(), textField_2.getText(), 22);

		Map<String, String> props = new HashMap<String, String>();
		props.put("StrictHostKeyChecking", "no");
		ss.setConfig(props);
		ss.setPassword(textField_1.getText());

		CommandRunner runner = new CommandRunner(ss);

		String command = textField_3.getText();
		CommandRunner.ExecuteResult result = runner.execute(command);

		if (result.getStderr().isEmpty()) {
			System.out.println(result.getStdout());
			setAbc(getAbc() + "\n" + result.getStdout());
		} else {
			System.out.println("done");
			System.out.println(result.getStderr());
		}
		String get = getAbc();
		textArea.setText(get);

		runner.close();
	}

	public sshUI() {
		setTitle("SSH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SSH to:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 123, 23);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(10, 72, 154, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 45, 137, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 113, 137, 23);
		contentPane.add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 140, 154, 30);
		contentPane.add(textField_1);

		JLabel lblNewLabel_1_2 = new JLabel("IP connect:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 181, 137, 23);
		contentPane.add(lblNewLabel_1_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(10, 204, 154, 30);
		contentPane.add(textField_2);

		JButton btnNewButton = new JButton("Connect");

		btnNewButton.setBounds(10, 323, 154, 38);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("done");
				System.exit(0);
			}
		});
		btnCancel.setBounds(10, 372, 154, 38);
		contentPane.add(btnCancel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(192, 11, 408, 399);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("INPUT: ");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(10, 245, 137, 23);
		contentPane.add(lblNewLabel_1_2_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(10, 268, 154, 30);
		contentPane.add(textField_3);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xaitodi();
				} catch (JSchException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
