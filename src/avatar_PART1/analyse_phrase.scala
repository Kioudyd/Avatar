package avatar_PART1

trait analyse_phrase {
  def phrase_transforme(requete : String):List[String]
  def interrogation(list_bdd : List[String]):(String,String)
  def phrase_de_sortie(resultat_bdd : (String,String)):String
  def politesse(list_bdd : List[String]) : String
}