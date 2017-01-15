package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurNouveauSuppression;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	private ControleurNouveauSuppression cns;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(ControleurNouveauSuppression p_cns) {	
		cns = p_cns;
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit� en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int qtProduit = Integer.parseInt(txtQte.getText());
		double prixHt =  Double.parseDouble(txtPrixHT.getText());
		cns.ajouterProduit(txtNom.getText(), prixHt , qtProduit );
		this.dispose();
	}

}