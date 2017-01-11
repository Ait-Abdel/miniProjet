package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurAchatVente;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurAchatVente cav;
	public FenetreVente(String[] lesProduits,ControleurAchatVente cav) {
		this.cav = cav;
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String comboRecupProduit = combo.getSelectedItem().toString();
		int qtAchete = Integer.parseInt(txtQuantite.getText());
		
		if(cav.venteProduit(comboRecupProduit, qtAchete)){
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this,
				    "Le nombre demandé n'est pas disponible. ",
				    "Erreur de quantité",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}

}
