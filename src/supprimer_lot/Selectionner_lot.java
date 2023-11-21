package supprimer_lot;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import criee_marc.creer_bac_fonctionnel;
import database.DatabaseConnection;
import supprimer_bac.Supprimer_bac_fonctionnel;

public class Selectionner_lot extends JFrame {

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
					Selectionner_lot frame = new Selectionner_lot();
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
	public Selectionner_lot() {
		
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
		
		//Element de mise en page
				JLabel titre_pannel = new JLabel("SELECTIONNEZ LE LOT ASSOCIE");
				titre_pannel.setFont(new Font("Dialog", Font.BOLD, 15));
				titre_pannel.setForeground(new Color(128, 0, 128));
				titre_pannel.setBounds(456, 11, 391, 56);
				contentPane.add(titre_pannel);
				
				//On definit une taille de fenetre non changeable par l'utilisateur
				setSize(new Dimension(1280,720));
				setResizable(false);
				
				//Informations du lot
				JLabel Id_lot = new JLabel("Identifiant du lot : "+"");
				Id_lot.setFont(new Font("Dialog", Font.PLAIN, 25));
				Id_lot.setBounds(650, 107, 450, 50);
				contentPane.add(Id_lot);
				
				JLabel Bateau_sel = new JLabel("Bateau sélectionné : "+"");
				Bateau_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
				Bateau_sel.setBounds(650, 154, 450, 50);
				contentPane.add(Bateau_sel);
				
				JLabel Peche_jour = new JLabel("Pêche du jour sélectionnée : "+"");
				Peche_jour.setFont(new Font("Dialog", Font.PLAIN, 25));
				Peche_jour.setBounds(650, 215, 507, 50);
				contentPane.add(Peche_jour);
				
				JLabel Espece_poisson = new JLabel("Espèce de Poisson : "+"");
				Espece_poisson.setFont(new Font("Dialog", Font.PLAIN, 25));
				Espece_poisson.setBounds(650, 276, 450, 50);
				contentPane.add(Espece_poisson);
				
				JLabel Taille = new JLabel("Taille du lot : "+"");
				Taille.setFont(new Font("Dialog", Font.PLAIN, 25));
				Taille.setBounds(650, 459, 450, 50);
				contentPane.add(Taille);
				
				JLabel Presentation = new JLabel("Presentation du lot : "+"");
				Presentation.setFont(new Font("Dialog", Font.PLAIN, 25));
				Presentation.setBounds(650, 520, 450, 50);
				contentPane.add(Presentation);
				
				JLabel Qualite = new JLabel("Qualité du lot :"+"");
				Qualite.setFont(new Font("Dialog", Font.PLAIN, 25));
				Qualite.setBounds(650, 398, 450, 50);
				contentPane.add(Qualite);
				
				JLabel Poids = new JLabel("Poids du lot :"+"");
				Poids.setFont(new Font("Dialog", Font.PLAIN, 25));
				Poids.setBounds(650, 337, 450, 50);
				contentPane.add(Poids);
		
		//Liste des lots dans l'interface
		DefaultListModel JlistModel = creer_bac_fonctionnel.Jliste_Lot(databaseConnection);
		JList<String> list = new JList(JlistModel);
		list.setBounds(283, 107, 284, 463);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {
			     public void valueChanged(ListSelectionEvent arg0) {
			    	 Id_lot.setText("Identifiant du lot : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getId());   
			    	 Bateau_sel.setText("Bateau sélectionné : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
					    	 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getBateau()); 
			    	 Peche_jour.setText("Pêche du jour sélectionnée : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getDatePeche());
			    	 Espece_poisson.setText("Espèce de Poisson : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getEspece());
			    	 Presentation.setText("Taille du lot : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getTaille());
			    	 Qualite.setText("Qualité du lot : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getQualite());
			    	 Presentation.setText("Presentation du lot : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getPresentation());
			    	 Poids.setText("Poids du lot : " +creer_bac_fonctionnel.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getPoids());
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
						//Application.pannelsuivant();
						int confirm = JOptionPane.showConfirmDialog(
								null,
								"Etes-vous sûr de vouloir supprimer ce bac ?",
								"Confirmation",
				                JOptionPane.YES_NO_OPTION,
				                JOptionPane.QUESTION_MESSAGE, null );
						if (confirm==0) {
							Supprimer_lot_fonctionnel.Supprimer_lot(databaseConnection, selectedListValue);
						}
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
		
	}

}
