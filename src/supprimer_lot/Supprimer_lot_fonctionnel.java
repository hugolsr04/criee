package supprimer_lot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import criee_marc.Bac;
import criee_marc.Lot;
import database.DatabaseConnection;

public class Supprimer_lot_fonctionnel {
	
	
	public static void Supprimer_lot(DatabaseConnection databaseConnection, String idlot) {
		Connection con = databaseConnection.getConnection();
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("DELETE FROM bac WHERE id="+idlot+";");
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
