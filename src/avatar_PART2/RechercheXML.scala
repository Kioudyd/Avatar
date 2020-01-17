package avatar_PART2
import scala.xml._
import sun.security.util.Length

class RechercheXML {
  
  val xml = XML.loadFile("doc/vAr.xml") // on créer une var qui contient le fichier vAr.xml
  val organisation = xml \ "answer" \\ "organization" // on crée une var organisation de type NodeSeq qui rassemble toutes les balises dans organization 

  /**
   * @param : n un noeud
   * @return : Les noms sous forme de string dans le document XML
   * 
   */
  def nom(n: Node): String = {
    (n \ "name").text
  }

    /**
   * @param : n un noeud
   * @return : Les adresses sous forme de string dans le document XML
   * 
   */
  def adresse(n: Node): String = {
    var adresse = ""
    val ville = (n \\ "addresses" \\ "city") // Récupère les villes qui sont fils du noeud n 
    val numero = (n \\ "street" \\ "number") // Récupère les numéros des adresses qui sont fils du noeud n 
    val rue = (n \\ "addresses" \\ "name") // Récupère les rues qui sont fils du noeud n 
    if(ville.text == "Rennes"){ // Si la ville est rennes
      
      if ((numero.text == "")) { //Si il n'y a pas de numéro
        adresse = rue.text //on renvoie la rue 
        
      } else {
        adresse = numero.text + " " + rue.text //Sinon on renvoie le numéro et l'adresse
        
      }
     
    }
    adresse
  }
  /**
   * @param : n un noeud
   * @return : Un boolean qui dit si la langue est rennes ou non 
   */
  
  def nomRennes (n: Node): Boolean = {
    val ville = (n \\ "addresses" \\ "city")
    if(ville.text == "Rennes"){
      true
    }
    else {
      false
    }
  } 
  /**
   * @param : requete la requete tappée par l'utilisateur (à utiliser dans l'ask)
   * @return : La list de couple de String qui est constituée des résulats de la requête dans la ville de rennes
   */

  def recherche(requete: String): List[(String, String)] = {
    var lrequete = requete.split(" ").toList //on split la requete pour avoir une liste avec les espaces
    lrequete = F7.removeUseless(lrequete) // On retire les mots inutiles
    var result: List[(String, String)] = List() // Notre résultat
    for (n <- organisation) {
      val ln = nom(n).split(" ").toList //On récupère les nom dans le fichier XML
      if (avatar_PART1.BdImpl.compare(lrequete, ln) && nomRennes(n)== true) { //On fait une tolérance aux fautes et on utilise la fonction nonRennes pour savoir si le lieu est à rennes
        result = result ++ List((nom(n), adresse(n))) //On construit la liste de String,String
      }
    }
    result
  }
}