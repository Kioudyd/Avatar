package avatar_PART2
import collection.immutable.SortedMap
object F6Impl {
  var map: Map[Int, (String, String)] = Map[Int, (String, String)]()
  var tab : Array[(String,String)] = new Array[(String,String)](100)
  //La map reste en public pour pouvoir y accéder à tout moment
  var compteur = 0;
  var xml : RechercheXML = new RechercheXML()

  /**
   * @param liste des résultats obtenus de la requete de départ
   */
  def remplir(list: List[(String,String)]):Unit = {
    list match {
      case Nil => 
      case y :: Nil => 
      compteur += 1
      map = map+ (compteur -> y)
      case head :: tail =>
      compteur += 1
      map = map+ (compteur -> head)
      remplir(tail)
      
    }
  }
  /**
   *  @param : liste une list[Strin]
   *  @return : Aucun mais remplit la map en public
   */
  def plusieurs_choix(liste: List[String]): Unit = {
    liste match {
      case Nil => 
      case head2 :: tail2 =>
        remplir(xml.recherche(head2))
        plusieurs_choix(tail2)
    }
  }
    /**
   *  @param : liste une list[String,String]
   *  @return : Un boolean si cette liste est constituée d'un seul élément
   */
  def unChoix(list : List[(String,String)] ): Boolean = {
    list match {
      case head :: Nil => true
      case _ => false
    }
  }
  /**
   * @param : rien 
   * @return : Rien réinit la map
   */
  def initMap():Unit={
        map=map.empty 
        compteur=0
  }


  
  /**
   * @return liste de string composé du numéro du resultat et du nom du résultat
   * 
   */
  
  def f6ToString(map: Map[Int, (String, String)]): List[String] = {
    var list_resultat: List[String] = List()  
    for (key <- map.keys) {
      list_resultat= list_resultat ++ List(key + ") " + map.get(key).get._1) 
    }
    list_resultat.sorted
  }
  
  
}