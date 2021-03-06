package client.rtsp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.rtsp.controller.ClientController;
import client.rtsp.model.ClientModel;
import client.rtsp.model.ClientModel.Update;


@SuppressWarnings("serial")
public class ClientView extends JFrame implements Observer {

	private JButton setupButton;
	private JButton playButton;
	private JButton pauseButton;
	private JButton tearButton;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JLabel iconLabel;
	private Toolkit toolkit;
	private ImageIcon icon;

	private ClientController controller;
	private ClientModel model;

	public ClientView(ClientController controller) {
		super("Client");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.controller = controller;
		this.model = this.controller.getModel();
		this.model.addObserver(this);
		initisaliseComponents();
		initialiseActionListeners();
	}

	private void initisaliseComponents() {
		toolkit = Toolkit.getDefaultToolkit();

		// Buttons
		setupButton= new JButton("Setup");
		playButton = new JButton("Play");
		pauseButton = new JButton("Pause");
		tearButton = new JButton("Teardown");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		buttonPanel.add(setupButton);
		buttonPanel.add(playButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(tearButton);

		// Image display label
		iconLabel = new JLabel();
		iconLabel.setIcon(null);

		// frame layout
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.add(iconLabel);
		mainPanel.add(buttonPanel);
		iconLabel.setBounds(0, 0, 380, 280);
		buttonPanel.setBounds(0, 280, 380, 50);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.setSize(new Dimension(390, 370));
		this.setVisible(true);
	}

	private void initialiseActionListeners() {

		setupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("Setup Button pressed!\n");
				controller.setup();
			}
		});

		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("Play Button pressed!\n");
				controller.play();
			}
		});

		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("Pause Button pressed!\n");
				controller.pause();
			}
		});

		tearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("Tear Button pressed!\n");
				controller.tear();
			}
		});

	}

	@Override
	public void update(Observable o, Object arg) {
		switch ((Update) arg) {
		case FRAME:
			Image image = toolkit.createImage(model.getFrame(), 0, model.getFrameLength());
			icon = new ImageIcon(image);
			iconLabel.setIcon(icon);
			break;
		}
	}

}