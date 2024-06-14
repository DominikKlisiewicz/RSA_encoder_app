import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	private JTextArea publicKeyArea, privateKeyArea;
	private RSA rsa;
	private Font font;
	Color bgColor, fieldsColor1, fieldsColor2, buttonColor , optionsColor, decryptionColor;
	GUI(RSA rsa)
	{
		this.bgColor = new Color(245, 245, 245);
		this.fieldsColor1 = new Color(215, 215, 215);
		this.fieldsColor2 = new Color(249, 249, 249);
		this.buttonColor = new Color(204, 245, 206);
		this.optionsColor = new Color(196, 199, 196);
		this.decryptionColor = new Color(242, 177, 177);
		String fontName = "Franklin Gothic Medium Cond";
		int fontSize = 30;
		this.font = new Font(fontName, Font.PLAIN, fontSize);  
		this.rsa = rsa;
	    JFrame frame = new JFrame("RSA Encoder");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(900,600);
	    
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new GridBagLayout());
	    mainPanel.setBackground(bgColor);
	    GridBagConstraints gbc = new GridBagConstraints();
	    	    
	    JTextArea textArea = new JTextArea(10, 30);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setBackground(fieldsColor1);
	    textArea.setFont(font); 
        textArea.setText("Zostaw wiadomość w tym miejscu");
        textArea.setMargin(new Insets(10, 10, 10, 10));
        
	
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.5; 
	    gbc.weighty = 0.4; 
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets = new Insets(0, 0, 0, 0);
	    mainPanel.add(textArea, gbc);
	    
	    
	    JTextArea cypherTextArea = new JTextArea(10, 30);
	    cypherTextArea.setLineWrap(true);
	    cypherTextArea.setWrapStyleWord(true);
	    cypherTextArea.setBackground(fieldsColor2);
	    cypherTextArea.setFont(font); 
	    cypherTextArea.setText("Tu pojawi się szyfr");
	    cypherTextArea.setMargin(new Insets(10, 10, 10, 10));
	    cypherTextArea.setForeground(new Color(95, 95, 95));
	    
	    gbc.gridy = 1;
	    gbc.weighty = 0.5; 
	    mainPanel.add(cypherTextArea, gbc);
	 
	    JPanel buttons = new JPanel(new GridBagLayout());
	    
	    JButton actionButton = new JButton();
	    actionButton.addActionListener(e -> {
            String textM = textArea.getText(); 
            if (textM.length()!=0)
            {
            	System.out.println("Text from JTextArea: " + textM);
            	cypherTextArea.setText(rsa.encrypt(textM));
            }
        });
	    actionButton.setBackground(buttonColor);
	    actionButton.setBorder(null);
	    actionButton.setFocusPainted(false);
	    gbc.gridy = 0;
	    gbc.weightx = 0.4; 
	    gbc.weighty = 0.2; 
	    buttons.add(actionButton, gbc);
	    
	    JButton decryptionButton = new JButton();
	    decryptionButton.addActionListener(e -> {
            String textC = cypherTextArea.getText(); 
            if (textC.length()!=0)
            {
            	textArea.setText(rsa.decrypt(textC));
            }
        });
	    decryptionButton.setBackground(decryptionColor);
	    decryptionButton.setBorder(null); 
	    decryptionButton.setFocusPainted(false); 
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.weightx = 0.4; 
	    gbc.weighty = 0.2; 
	    buttons.add(decryptionButton, gbc);
	    
	    JButton optionsButton = new JButton();
	    optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewWindow();
            }
        });
	    optionsButton.setBackground(optionsColor);
	    optionsButton.setBorder(null); 
	    optionsButton.setFocusPainted(false);
	    gbc.gridx = 2;
	    gbc.weightx = 0.2; 
	    buttons.add(optionsButton, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.weightx = 0.5; 
	    gbc.weighty = 0.2;
	    mainPanel.add(buttons, gbc);
	    	    
	       
	    frame.add(mainPanel);
	    frame.setVisible(true);
	}
	
    private void openNewWindow() {
    	String fontName = "Franklin Gothic Medium Cond";
		int fontSize = 18;
    	Font settingsFont = new Font(fontName, Font.PLAIN, fontSize);
        JFrame newWindow = new JFrame("Settings");
        newWindow.setSize(500, 450);
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
        
        JTextArea publicKeyField = new JTextArea();
        JTextArea privateKeyField = new JTextArea();
        JTextArea nField = new JTextArea();
        
        String publicKey = rsa.getPublicKey().toString();
        String privateKey = rsa.getPrivateKey().toString();
        String n = rsa.getN().toString();
        
        publicKeyField.setText(publicKey);
        publicKeyField.setBackground(new Color(215, 215, 215));
        publicKeyField.setLineWrap(true);
        publicKeyField.setWrapStyleWord(true);
        publicKeyField.setFont(settingsFont); 
        publicKeyField.setMargin(new Insets(10, 10, 10, 10));
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.5; 
	    gbc.weighty = 0.2;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets = new Insets(0, 0, 0, 0);
	    settingsPanel.add(publicKeyField, gbc);
	    
	    privateKeyField.setText(privateKey);
	    privateKeyField.setBackground(new Color(215, 215, 215));
	    privateKeyField.setLineWrap(true);
	    privateKeyField.setWrapStyleWord(true);
	    privateKeyField.setFont(settingsFont); 
	    privateKeyField.setMargin(new Insets(10, 10, 10, 10));
	    privateKeyField.setBackground(new Color(245, 245, 245));
	    gbc.gridy = 1;
	    gbc.weighty = 0.6; 
        settingsPanel.add(privateKeyField, gbc);
        
        nField.setText(n);
        nField.setBackground(new Color(225, 225, 225));
        nField.setLineWrap(true);
        nField.setWrapStyleWord(true);
        nField.setFont(settingsFont); 
        nField.setMargin(new Insets(10, 10, 10, 10));
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.weighty = 0.2;
	    settingsPanel.add(nField, gbc);
        
        JButton button = new JButton("Change Keys");
        button.addActionListener(e -> {
        	String newPubKey, newPrivKey, newN;
        	newPubKey = publicKeyField.getText();
        	newPrivKey = privateKeyField.getText();
        	newN = nField.getText();
        	if (!newPubKey.equals(publicKey) || !newPrivKey.equals(privateKey) || !newN.equals(n)) {
                rsa.changeKeys(newPubKey, newPrivKey, newN); // Use new key values here
                System.out.println("Parameters have been changed");
                System.out.println("Public Key: " + rsa.getPublicKey().toString());
                System.out.println("Private Key: " + rsa.getPrivateKey().toString());
                System.out.println("N: " + rsa.getN().toString());
            }

        	
        });
        button.setBackground(optionsColor);
        button.setBorder(null);
        button.setFocusPainted(false); 
        gbc.gridy = 3;
        gbc.weighty = 0.2; 
        settingsPanel.add(button, gbc);
        
        newWindow.add(settingsPanel);
        newWindow.setVisible(true);
    }

}
