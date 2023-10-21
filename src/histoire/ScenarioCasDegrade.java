package histoire;

import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
// 		TEST 1: libererEtal
		
		Etal etal = new Etal();
		System.out.println(etal.libererEtal());
		System.out.println("Fin du test 1");
		
		
//		TEST 2: acheterProduit acheteur null
		
		Gaulois g = new Gaulois("gaulois1", 5);
		etal.occuperEtal(g, "fleurs", 10);
		
		System.out.println(etal.acheterProduit(2, null));
		System.out.println("Fin du test 2");
		
//		TEST 3: acheterProduit negative or null quantity
		
		System.out.println(etal.acheterProduit(-5, g));
		System.out.println("Fin du test 3");
		
// 		TEST 4: acheterProduit etal non occupée
		
		Etal etal2 = new Etal();
		try {
			System.out.println(etal2.acheterProduit(2, g));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test 4");
		
//		TEST 5: Village VillageSansChefException
		
		Village village = new Village("le village des irréductibles", 10, 5);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		try {
			village.afficherVillageois();
		} catch(VillageSansChefException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test 5");
		
		
	}

}
