package avatar_PART1;

object BdImpl extends Bd{

val adresse = Map("Mairie de Rennes"->"Place de la Mairie",
                    "Théâtre La Paillette"->"2, Rue du Pré de Bris",                   
                    "Théâtre National de Bretagne"->"1, Rue Saint-Hélier",                   
                    "Gare SNCF"->"19, Place de la Gare" )
  
  val redirection = Map("Mairie"->"Mairie de Rennes",
      "Hotel de ville"->"Mairie de Rennes",
      "l'Hotel de ville"->"Mairie de Rennes",
      "l'Hotel ville"->"Mairie de Rennes",
      "Hotel ville"->"Mairie de Rennes",
      "La Paillette"->"Théâtre La Paillette",
      "Paillette"->"Théâtre La Paillette",
      "Théâtre de La Paillette"->"Théâtre La Paillette",
      "TNB"->"Théâtre National de Bretagne",
      "Théâtre de Bretagne"->"Théâtre National de Bretagne",
      "Gare"->"Gare SNCF",
      "Mairie de Rennes"->"Mairie de Rennes",
      "Théâtre La Paillette"->"Théâtre La Paillette",                   
      "Théâtre National de Bretagne"->"Théâtre National de Bretagne",                   
      "Gare SNCF"->"Gare SNCF")
                    
  val politesse = Map(
        "bonjour" -> "bonjour",
        "merci" -> "bonjour",
        "salut" -> "bonjour",
        "bonsoir" -> "bonjour")
   
  
  def resultat(requete:List[String]):(String,String) = {

    for(k <- redirection.keys) {
      if(compare(requete,k.split(" ").toList)){            
        var value:Option[String] = redirection.get(k)   
        var adr:Option[String] = adresse.get(value.get)
        return (value.get,adr.get)
      }
    }
    return null
   
  }
  
  def resultat2(requete:List[String]):Boolean = {
   
    var a = false
    for(k <- politesse.keys) {
      if(compare(requete,k.split(" ").toList)){             
        a = true       
      }
    }
    return a     
  }
  
  def compare(requete:List[String],cle:List[String]):Boolean={
    
    var compare = true
    // crée une liste de tuple des valeurs de requete(i) et cle(i) 
    var listeTuple = requete.zip(cle)     
    for (e <- listeTuple){
      var a = new ToleranceFautes() 
      if (!a.estSansFaute(e._1,e._2)){
        compare = false
      }
    }
     compare
  }

}