package modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Bien;
import modele.Charge;
import modele.Compteur;
import modele.Immeuble;
import modele.dao.requetes.select.RequeteSelectCharge;
import modele.dao.requetes.select.RequeteSelectChargeById;
import modele.dao.requetes.select.RequeteSelectCompteur;
import modele.dao.requetes.select.RequeteSelectCompteurById;

public class DaoCompteur extends DaoModele<Compteur> implements Dao<Compteur> {

	@Override
	public void create(Compteur donnees) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Compteur donnees) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Compteur donnees) {
		delete(donnees);
	}
	
	@Override
	protected Compteur creerInstance(ResultSet curseur) throws SQLException {
		Compteur compteur = null;
		try {
			String idBien = curseur.getString("Id_Bien");
			DaoBien daoBien = new DaoBien();
			Bien bien = daoBien.findById(idBien);
			
			String idImmeuble = curseur.getString("Id_Immeuble");
			DaoImmeuble daoImmeuble = new DaoImmeuble();
			Immeuble immeuble = daoImmeuble.findById(idImmeuble);
			
			compteur = new Compteur(curseur.getString("id_compteur"), 
					curseur.getString("typeComp"),bien,immeuble);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compteur;
	}

	@Override
	public Compteur findById(String... id) throws SQLException {
		List<Compteur> compteurs = find(new RequeteSelectCompteurById(), id);
		if (compteurs.isEmpty()) {
			return null;
		}
		return compteurs.get(0);
	}

	@Override
	public List<Compteur> findAll() throws SQLException {
		return find(new RequeteSelectCompteur());
	}

}