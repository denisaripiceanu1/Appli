package controleur;

import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.outils.Sauvegarde;
import modele.Bien;
import modele.dao.DaoBien;
import vue.insertion.Fenetre_InsertionLocation;

public class GestionTableLogementsFenetreLocation implements ListSelectionListener {

	private Fenetre_InsertionLocation fil;
	private DaoBien daoBien;

	public GestionTableLogementsFenetreLocation(Fenetre_InsertionLocation fil) {
		this.fil = fil;
		this.daoBien = new DaoBien();
		Sauvegarde.initializeSave();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {

			int selectedRowLogement = this.fil.getTable_liste_logements().getSelectedRow();

			if (selectedRowLogement > -1) {
				JTable tableLogement = this.fil.getTable_liste_logements();
				Bien bien = null;
				try {
					bien = this.daoBien.findById(tableLogement.getValueAt(selectedRowLogement, 0).toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Sauvegarde.deleteItem("Logement");
				Sauvegarde.addItem("Logement", bien);
			}
		}
	}
}