package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurAchatVente;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurAchatVente cav;

	public FenetreAchat(String[] lesProduits,ControleurAchatVente cav) {
		this.cav = cav;
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
								String comboRecupProduit = combo.getSelectedItem().toString();
		//try{
			int qtAchete = Integer.parseInt(txtQuantite.getText());
			if(cav.achatProduit(comboRecupProduit, qtAchete)){
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,
					    "Le nombre demandé n'est pas disponible. ",
					    "Erreur de quantité",
					    JOptionPane.ERROR_MESSAGE);
			}
		//}catch{}
		
		
		
	}

}
