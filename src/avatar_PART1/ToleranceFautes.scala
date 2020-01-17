package avatar_PART1
import java.text.Normalizer

class ToleranceFautes {

  /**
   * @param : m1 un String, m2 un String
   * @return : Le plus grand des deux Strings passés en paramètre
   */

  def biggestString(m1: String, m2: String): String = {
    var plusgrand: String = ""
    if (m1.length() > m2.length()) {
      plusgrand = m1
    } else plusgrand = m2
    plusgrand
  }

  /**
   * @param : m1 un String, m2 un String
   * @return : Le plus petit des deux Strings passés en paramètres
   */
  def smallestString(m1: String, m2: String): String = {
    var pluspetit: String = ""
    if (m1.length() > m2.length()) {
      pluspetit = m2
    } else pluspetit = m1
    pluspetit
  }

  /**
   * @param : mot1 et mot2 deux Strings (mot1 est le mot de la requête et mot2 le mot présent dans la base de données 
   * 					mot1 et mot2 sont de la même taille
   * @return : Renvoie la distance de Hamming (nombre de différences) entre mot 1 et mot 2
   */
  def distanceDeHamming(mot1: String, mot2: String): Int = {
    var i = 0
    var compteur: Int = 0
    while (i < mot1.length() && compteur < 2) {
      if (mot1.charAt(i) == mot2.charAt(i)) {
        i = i + 1
      } else {
        compteur = compteur + 1
        i += 1
      }
    }
    compteur
  }

  /**
   * @param : m1 et m2 des Strings de taille différente
   * @return : renvoie vrai si la distance de Hamming entre m1 et m2 est strictement inféreure à 2
   */
  def insertSharp(m1: String, m2: String): Boolean = {
    var enplus: String = "#"
    var i: Int = 0
    var trouve: Boolean = false
    val smallest: String = smallestString(m1, m2)
    val biggest: String = biggestString(m1, m2)
    val entreString: Int = biggest.length() - smallest.length()
    if (entreString > 1) {
      return trouve
    }
    while (trouve == false && i < biggest.length) {
      val smallest2 = smallest.dropRight(smallest.length() - i) + enplus + smallest.drop(i) // On ajoute un # entre deux lettres pour qu'ils aient la même taille
                                                                                            // qu'on décalera à chaque tour de boucle
      if (distanceDeHamming(smallest2, biggest) < 2) {
        trouve = true
      } else {
        i = i + 1

      }
    }
    trouve
  }

    /**
   * @param : m1 et m2 deux Strings
   * @return : renvoie vrai si la distance de Hamming est Strictement inférieur à deux
   */
  def motRecevable(m1: String, m2: String): Boolean = {
    var trouve: Boolean = false
    if (distanceDeHamming(m1, m2) < 2) {
      trouve = true
    }
    trouve
  }

  /**
   * @param : motEnEntree et motVrai deux Strings
   * @return : Renvoie vrai si les deux mots répondent à toutes les fonctions précédentes
   */
  def estSansFaute(motEnEntree: String, motVrai: String): Boolean = {
    var motEntreeSansSpecial: String = motEnEntree.toLowerCase() // Retire les majuscules
    var motVraiSansSpecial: String = motVrai.toLowerCase()
    motEntreeSansSpecial = Normalizer.normalize(motEntreeSansSpecial, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // Retire tous les accens
    motVraiSansSpecial = Normalizer.normalize(motVraiSansSpecial, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
    motVraiSansSpecial = motVraiSansSpecial.replaceAll("""[\p{Punct}&&[^.]]""", "") // Retire toute forme de ponctuation
    motEntreeSansSpecial = motEntreeSansSpecial.replaceAll("""[\p{Punct}&&[^.]]""", "")
    if (motEntreeSansSpecial.length == motVraiSansSpecial.length) {
      motRecevable(motEntreeSansSpecial, motVraiSansSpecial)
    } else insertSharp(motEntreeSansSpecial, motVraiSansSpecial)
  }

}
