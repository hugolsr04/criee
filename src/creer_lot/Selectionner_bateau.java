package creer_lot;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import criee_marc.Lot;
import criee_marc.creer_bac_fonctionnel;
import database.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  


public class Selectionner_bateau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String Bateau;
	private static DatabaseConnection databaseConnection;
	public static Lot lot_cree = new Lot("","","","","","","","");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					databaseConnection = new DatabaseConnection("jdbc:mysql://localhost","root","","bdd_criee");
					Selectionner_bateau frame = new Selectionner_bateau();
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
	public Selectionner_bateau() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titre de la fenetre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		contentPane.add(lblNewLabel);
		
		//Liste des bateaux dans l'interface
		DefaultListModel JlistModel = creer_bac_fonctionnel.Jliste_Bateau(databaseConnection);
		JList<String> list = new JList(JlistModel);
		
		list.setBounds(436, 105, 391, 471);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		contentPane.add(list);
		
		//On affiche la peche du jour
		//En recuperant la date systeme
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String strDate = dateFormat.format(date);  
		//Puis en l'affichant dans dans label 
		JLabel lblNewLabel_2 = new JLabel("Pêche du jour : "+strDate);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(446, 78, 188, 14);
		contentPane.add(lblNewLabel_2);
		
		//Bouton de validation d'ajout d'un bateau
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On recupère la valeur selectionnée
				String selectedListValue = list.getSelectedValue();
				lot_cree.setBateau(selectedListValue);
				lot_cree.setDatePeche(strDate);
				//Application.pannelsuivant();
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
		JLabel lblNewLabel_1 = new JLabel("SELECTIONNEZ LA PECHE DU JOUR D'UN BATEAU");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_1.setBounds(436, 11, 391, 56);
		contentPane.add(lblNewLabel_1);
		
		//On definit une taille de fenetre non changeable par l'utilisateur
		setSize(new Dimension(1280,720));
		setResizable(false);
		
	}
}
