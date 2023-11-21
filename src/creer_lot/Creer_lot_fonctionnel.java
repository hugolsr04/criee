package creer_lot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import criee_marc.creer_bac;
import database.DatabaseConnection;

public class Creer_lot_fonctionnel {
	public static List<String> liste_espece(DatabaseConnection databaseConnection) {
		Connection con = databaseConnection.getConnection();
		List<String> liste = Arrays.asList();
		List<String> liste_ = new ArrayList<>(liste);
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT nom FROM espece;");	
			while (rs.next()) {
				liste_.add(rs.getString("nom"));
				}
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_;
	}


	public static List<String> liste_taille(DatabaseConnection databaseConnection) {
		Connection con = databaseConnection.getConnection();
		List<String> liste = Arrays.asList();
		List<String> liste_ = new ArrayList<>(liste);
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT specification FROM taille;");	
			while (rs.next()) {
				liste_.add(rs.getString("specification"));
				}
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_;
	}
	
	public static List<String> liste_qualite(DatabaseConnection databaseConnection) {
		Connection con = databaseConnection.getConnection();
		List<String> liste = Arrays.asList();
		List<String> liste_ = new ArrayList<>(liste);
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle FROM qualite;");	
			while (rs.next()) {
				liste_.add(rs.getString("libelle"));
				}
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_;
	}
	public static List<String> liste_presentation(DatabaseConnection databaseConnection) {
		Connection con = databaseConnection.getConnection();
		List<String> liste = Arrays.asList();
		List<String> liste_ = new ArrayList<>(liste);
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT libelle FROM presentation;");	
			while (rs.next()) {
				liste_.add(rs.getString("libelle"));
				}
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_;
	}
	public static void creer_lot(DatabaseConnection databaseConnection) {
			Connection con = databaseConnection.getConnection();
			Date date = Calendar.getInstance().getTime();  
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		    String strDate = dateFormat.format(date);
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("INSERT INTO lot VALUES('"
				+creer_bac.bac_cree.getBateau()+","
				+strDate+","
				+creer_bac.bac_cree.getLot()+","
				+creer_bac.bac_cree.getTypeBac()+"')");
				
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	
}

