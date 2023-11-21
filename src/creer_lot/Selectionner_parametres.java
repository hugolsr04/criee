package creer_lot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import database.DatabaseConnection;
import javax.swing.JTextField;

public class Selectionner_parametres extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DatabaseConnection databaseConnection;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					databaseConnection = new DatabaseConnection("jdbc:mysql://localhost","root","","bdd_criee");
					Selectionner_parametres frame = new Selectionner_parametres();
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
	public Selectionner_parametres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
		//Titre de la fenêtre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		contentPane.add(lblNewLabel);
		
		JLabel label_espece = new JLabel("Espèce de poisson :");
		label_espece.setFont(new Font("Dialog", Font.BOLD, 25));
		label_espece.setForeground(UIManager.getColor("Button.darkShadow"));
		label_espece.setBounds(290, 164, 271, 50);
		contentPane.add(label_espece);
		
		//On créé la comboBox qui permettra de choisir le type de bac
		JComboBox cbEspece = new JComboBox();
		cbEspece.setBounds(571, 173, 107, 44);
		for(String espece : Creer_lot_fonctionnel.liste_espece(databaseConnection)) {
			cbEspece.addItem(espece);
		}
		contentPane.add(cbEspece);
		
		JComboBox cbTaille = new JComboBox();
		cbTaille.setBounds(571, 243, 107, 44);
		for(String taille : Creer_lot_fonctionnel.liste_taille(databaseConnection)) {
			cbTaille.addItem(taille);
		}
		contentPane.add(cbTaille);
		
		JComboBox cbQualite = new JComboBox();
		cbQualite.setBounds(571, 311, 107, 44);
		for(String qualite : Creer_lot_fonctionnel.liste_qualite(databaseConnection)) {
			cbQualite.addItem(qualite);
		}
		contentPane.add(cbQualite);
		
		JComboBox cbPresentation = new JComboBox();
		cbPresentation.setBounds(571, 389, 107, 44);
		for(String presentation : Creer_lot_fonctionnel.liste_presentation(databaseConnection)) {
			cbPresentation.addItem(presentation);
		}
		contentPane.add(cbPresentation);
		
		textField = new JTextField();
		textField.setBounds(571, 471, 107, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//Bouton de validation d'ajout d'un type bac
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = cbEspece.getSelectedItem().toString();
				Selectionner_bateau.lot_cree.setEspece(selectedItem);
				selectedItem = cbTaille.getSelectedItem().toString();
				Selectionner_bateau.lot_cree.setTaille(selectedItem);
				selectedItem = cbQualite.getSelectedItem().toString();
				Selectionner_bateau.lot_cree.setQualite(selectedItem);
				selectedItem = cbPresentation.getSelectedItem().toString();
				Selectionner_bateau.lot_cree.setPresentation(selectedItem);
				selectedItem = textField.getText();
				Selectionner_bateau.lot_cree.setPoids(selectedItem);
				//Application.pannelsuivant();
				Creer_lot_fonctionnel.creer_lot(databaseConnection);
			}
		});
		confirmButton.setBounds(502, 600, 260, 50);
		contentPane.add(confirmButton);
				
		//Bouton de retour au menu precedent
		JButton backButton = new JButton("RETOUR");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Application.pannelprecedent();
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(204, 0, 0));
		backButton.setBounds(1111, 17, 127, 44);
		contentPane.add(backButton);
				
		//Element de mise en page
		JLabel titre_pannel = new JLabel("SELECTIONNEZ LE TYPE DE BAC");
		titre_pannel.setFont(new Font("Dialog", Font.BOLD, 15));
		titre_pannel.setForeground(new Color(128, 0, 128));
		titre_pannel.setBounds(496, 12, 391, 56);
		contentPane.add(titre_pannel);
		
		JLabel Tare_1 = new JLabel("Poids :");
		Tare_1.setForeground(UIManager.getColor("Button.darkShadow"));
		Tare_1.setFont(new Font("Dialog", Font.BOLD, 25));
		Tare_1.setBounds(290, 456, 86, 50);
		contentPane.add(Tare_1);
		
		JLabel lblTaille = new JLabel("Taille :");
		lblTaille.setForeground(UIManager.getColor("Button.darkShadow"));
		lblTaille.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTaille.setBounds(290, 243, 271, 50);
		contentPane.add(lblTaille);
		
		JLabel lblQualit = new JLabel("Qualité :");
		lblQualit.setForeground(UIManager.getColor("Button.darkShadow"));
		lblQualit.setFont(new Font("Dialog", Font.BOLD, 25));
		lblQualit.setBounds(290, 311, 271, 50);
		contentPane.add(lblQualit);
		
		JLabel lblPrsentation = new JLabel("Présentation :");
		lblPrsentation.setForeground(UIManager.getColor("Button.darkShadow"));
		lblPrsentation.setFont(new Font("Dialog", Font.BOLD, 25));
		lblPrsentation.setBounds(290, 383, 271, 50);
		contentPane.add(lblPrsentation);
		
				
		//On definit une taille de fenetre non changeable par l'utilisateur
		setSize(new Dimension(1280,720));
		setResizable(false);
	}
}
