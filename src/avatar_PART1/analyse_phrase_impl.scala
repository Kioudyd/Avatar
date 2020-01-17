package avatar_PART1
import avatar_PART1.BdImpl

class analyse_phrase_impl extends analyse_phrase {

  /*
   * @param requete String obtenue avec l'interface graphique
   * @return une liste de String où chaque élément est un mot de requete
   */
  def phrase_transforme(requete: String): List[String] = {
    var listreq = requete.split(" ").toList
    listreq.filter(_ != "")
  }
  /**
   * @param resultat_bdd prend un couple de string correspondant au lieu recherché et son adesse ( resultat de la fonction interrogation)
   * @return La phrase qui sera rendue et utiliser par la fonction ask
   */
  def phrase_de_sortie(resultat_bdd: (String, String)): String = {
    resultat_bdd match {

      case null => "Je ne comprends pas votre demande"
      case (y, z)       => "L'adresse de " + y + " est : " + z

    }

  }
  /**
   *@param list_bdd est la liste correspondant à la phrase entrée par l'utilisateur dans l'interface graphique
   * , interrogation de la base de donnée pour savoir si la requête est présente dans la base de donnée
   * @return un tuple qui à pour premier élement le monument cherché et le second est son adresse
   */
  def interrogation(list_bdd: List[String]): (String, String) = {
    list_bdd match {
      case Nil      => null
      case x :: Nil => BdImpl.resultat(List(x))
      case head :: y => if (BdImpl.resultat(head :: y) != null) {
        BdImpl.resultat(head :: y)
      } else {
        interrogation(y)
      }
    }
  }
/**
 * @param list_bdd  est la liste correspondant à la phrase entrée par l'utilisateur dans l'interface graphique
   * , interrogation de la base de donnée pour savoir si la requête est présente dans la base de donnée
 *@return Bonjour , lorsque l'utilisateur à entrée une formule de politesse dans l'interface graphique ( en debut , milieu , fin de phrase ) , 
 * ne retourne pas bonjour dans le cas inverse
 */
  def politesse(list_bdd: List[String]): String = {
    list_bdd match {
      case Nil => null
      case x :: Nil => if (BdImpl.resultat2(List(x))) { "Bonjour*" }
      else { null }
      case head :: y => if (BdImpl.resultat2(head :: y)) { 
        "Bonjour"
      } else {
        politesse(y)
      }
    }
  }
}