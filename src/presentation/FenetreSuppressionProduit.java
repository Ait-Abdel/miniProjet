package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurNouveauSuppression;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private ControleurNouveauSuppression cns;
	
	public FenetreSuppressionProduit(String lesProduits[],ControleurNouveauSuppression p_cns) {
		cns = p_cns;
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");
		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(combo.getSelectedItem().toString());
		String comboRecup = combo.getSelectedItem().toString();
		cns.supprimerProduit(comboRecup);
		this.dispose();
	}

}
