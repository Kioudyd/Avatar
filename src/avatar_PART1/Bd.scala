package avatar_PART1

trait Bd {
  /**
   * @param une phrase sous forme de liste
   * @return un tuple avec comme 1er element le lieu recherche et 2eme element l'adresse du lieu
   */
  def resultat(requete:List[String]):(String,String)
  
  /**
   * @param une phrase sous forme de liste
   * @return un boolean qui vaut vrai si la phrase est une politesse
   */
  def resultat2(requete:List[String]):Boolean
  
  
  /**
   * @param 2 listes
   * @return un boolean qui vaut vrai si les elements des listes sont les memes pour le meme indice i
   */
  def compare(r:List[String],l:List[String]):Boolean
}
