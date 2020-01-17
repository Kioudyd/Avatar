package avatar_PART2

trait Voix {
  /**
	 * Applique la voix venant des librairies de MaryTTS choisie par son nom en paramètre (fr, de, en, it..)
	 * @param nomVoix un String
	 */
	def DonneVoix(nomLocale: String): Unit
	
	/**
	 * Génère la voix en fonction d'un texte donné en paramètre
	 * @param s un String
	 */
	def parler(s: String): Unit
	  
	/**
	 * Idem mais depuis une liste de String
	 * @param ls une liste de String
	 */
	def parler(ls: List[String]): Unit
}