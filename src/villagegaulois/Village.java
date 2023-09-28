package villagegaulois;

import java.awt.desktop.PrintFilesEvent;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nbEtal;
		
		private Marche (int nbEtal) {
			this.nbEtal = nbEtal;
			etals = new Etal[nbEtal];
			for (int i=0; i<nbEtal; i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int i;
			for (i=0; i<nbEtal && etals[i].isEtalOccupe(); i++);
			
			if (i==nbEtal) {
				return -1;
			}
			return i;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalsAvecProduit=0;
			Etal[] etalsAvecProduit;
			int j=0;
			// calcul taille du tableau
			for (int i=0; i<nbEtal && etals[i].isEtalOccupe(); i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalsAvecProduit++;
				}
			}
			etalsAvecProduit = new Etal[nbEtalsAvecProduit];
			for (int i=0; i<nbEtalsAvecProduit; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsAvecProduit[j] = etals[i];
					j++;
				}
				
			}
			return etalsAvecProduit;
		}
		
		private Etal trouverVendeur(Gaulois vendeur) {
			int i;
			for (i=0; i<nbEtal && etals[i].getVendeur()!=vendeur; i++);
			if (i==nbEtal) {
				return null;
			}
			return etals[i];
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide;
			int i;
			for (i=0; i<nbEtal && etals[i].isEtalOccupe(); i++) {
				chaine.append(etals[i].afficherEtal());
			}
			nbEtalVide = nbEtal-i;
			if (i<nbEtal) {
				chaine.append("Il reste "+nbEtalVide+" étals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}
		
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int numeroEtal;
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		numeroEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(numeroEtal, vendeur, produit, nbProduit);
		chaine.append("Le/la vendeur.se "+vendeur.getNom()+" vend des "+produit+ " à l'étal n°"+(numeroEtal+1)+".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsAvecProduit;
		chaine.append("Les vendeurs.es qui proposent des "+produit+" sont:\n");
		etalsAvecProduit = marche.trouverEtals(produit);
		for (int i=0; i<etalsAvecProduit.length; i++) {
			Gaulois vendeurAvecProduit = etalsAvecProduit[i].getVendeur();
			chaine.append("- "+vendeurAvecProduit.getNom()+"\n");
		}
		return chaine.toString();
	}
	
	 public Etal rechercherEtal(Gaulois vendeur) {
		 return marche.trouverVendeur(vendeur);
	 }
	 
	 public String partirVendeur(Gaulois vendeur) {
		 Etal etal = rechercherEtal(vendeur);
		 return etal.libererEtal();
	 }
	 
	 public String afficherMarche() {
		 StringBuilder chaine = new StringBuilder();
		 chaine.append("Le marché du village ''"+getNom());
		 return marche.afficherMarche();
	 }
}