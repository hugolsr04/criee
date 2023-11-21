package creer_lot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import criee_marc.Bac;
import criee_marc.Lot;
import supprimer_bac.Supprimer_bac_fonctionnel;
import database.DatabaseConnection;

public class Selectionner_bac extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DatabaseConnection databaseConnection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					databaseConnection = new DatabaseConnection("jdbc:mysql://localhost","root","","bdd_criee");
					Selectionner_bac frame = new Selectionner_bac();
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
	public Selectionner_bac() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Informations du lot
		JLabel Id_bac = new JLabel("Identifiant du bac : "+"");
		Id_bac.setFont(new Font("Dialog", Font.PLAIN, 25));
		Id_bac.setBounds(650, 187, 450, 50);
		contentPane.add(Id_bac);
		
		JLabel Bateau_sel = new JLabel("Bateau sélectionné : "+"");
		Bateau_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
		Bateau_sel.setBounds(650, 248, 450, 50);
		contentPane.add(Bateau_sel);
		
		JLabel Peche_jour = new JLabel("Pêche du jour sélectionnée : "+"");
		Peche_jour.setFont(new Font("Dialog", Font.PLAIN, 25));
		Peche_jour.setBounds(650, 309, 507, 50);
		contentPane.add(Peche_jour);
		
		JLabel Id_Lot = new JLabel("Identifiant du lot associé : "+"");
		Id_Lot.setFont(new Font("Dialog", Font.PLAIN, 25));
		Id_Lot.setBounds(650, 370, 450, 50);
		contentPane.add(Id_Lot);
		
		JLabel IdTypeBac = new JLabel("Type du bac : "+"");
		IdTypeBac.setFont(new Font("Dialog", Font.PLAIN, 25));
		IdTypeBac.setBounds(650, 431, 450, 50);
		contentPane.add(IdTypeBac);
		
		//Titre de la fenetre
		JLabel lblNewLabel = new JLabel("CREER UN LOT");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 260, 50);
		contentPane.add(lblNewLabel);
		
		//Liste des bateaux dans l'interface
		DefaultListModel JlistModel = Supprimer_bac_fonctionnel.Jliste_Bac(databaseConnection);
		JList<String> list = new JList(JlistModel);
		
		list.setBounds(232, 104, 391, 471);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {
		     public void valueChanged(ListSelectionEvent arg0) {
		    	 Id_bac.setText("Identifiant du bac : " +Supprimer_bac_fonctionnel.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getId());   
		    	 Bateau_sel.setText("Bateau : " +Supprimer_bac_fonctionnel.liste_info_bac(databaseConnection,
				    	 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getBateau()); 
		    	 Peche_jour.setText("Pêche du jour : " +Supprimer_bac_fonctionnel.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getDatePeche());
		    	 Id_Lot.setText("Identifiant du lot associé : " +Supprimer_bac_fonctionnel.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getLot());
		    	 IdTypeBac.setText("Type de bac : " +Supprimer_bac_fonctionnel.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getTypeBac());
		     }});
		contentPane.add(list);
		
		//Bouton de validation d'ajout d'un bateau
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On recupère la valeur selectionnée
				String selectedListValue = list.getSelectedValue();
				Selectionner_bateau.lot_cree.setBateau(selectedListValue);
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
		JLabel lblNewLabel_1 = new JLabel("SELECTIONNEZ LE BAC");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_1.setBounds(436, 11, 391, 56);
		contentPane.add(lblNewLabel_1);
		
		setSize(new Dimension(1280,720));
		setResizable(false);
	}

}
