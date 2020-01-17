package avatar_PART1;
/*package machine

class BaseDeDonnees extends  Bd {
  var adresse: List[(String, String)] = List(("Mairie de Rennes", "Place de la Mairie"),
    ("Théâtre La Paillette", "2, Rue du Pré de Bris"),
    ("Théâtre National de Bretagne", "1, Rue Saint-Hélier"),
    ("Gare SNCF", "19, Place de la Gare"))

  val base:Map[String,(String,String)] = Map(
    //"Théâtre" -> "Vous désirez  connaître l'adresse du Théâtre National de Bretagne ou du Théâtre de la paillette", 
    "hotel" -> ("hotel","L'adresse de Mairie de Rennes est : Place de la Mairie"),
    "tnb" -> ("tnb","L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier"),
    "theatre" -> ("base2","base2"),
    "paillette" -> ("pailette","L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris"),
    "gare" -> ("gare","L'adresse de Gare SNCF est : 19, Place de la Gare"),
    "mairie" -> ("Mairie de Rennes","L'adresse de Mairie de Rennes est : Place de la Mairie"))
    
  val base2:Map[String,(String,String)] = Map("national" -> ("theatre national","L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier"),
                 "paillette" -> ("theatre paillette","L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris"))
  
  val base3:Map[String,(String,String)] = Map(
    "bonjour" -> ("bonjour","Bonjour"),
    "merci" -> ("merci","Je vous en prie"),
    "salut" -> ("salut","Bonjour"),
    "bonsoir" -> ("bonsoir","Bonsoir"))
                 // requete = List("je","vous","l'adresse","de","la","mairie")
  // reponse = l'adresses de la mairie est place de la Mairie
  override // redéfinition de la fonction resultat
  //resultat(List("JE","chérche","màirie"))
  // newList = List("je","cherche","mairie")
  def resultat (requete:List[String]):(String,String)={
    var newList:List[String] = List()
    for (ident <- requete){
      newList = newList:+removeAllSpChar(ident)
    }
    var actualBase = base
    if(newList.contains("cherche") || newList.contains("veux") || newList.contains("ou")){// si le client écrit cherche ou veux
      
      for(ident <- newList){
        if(actualBase.contains(ident)){
          var firstResponse = actualBase.get(ident)
          //get(clé) return valeur (Some(réponse)) ou None
          firstResponse match{
            case None => return ("","Je ne comprends pas votre demande") //cette ligne n'est jamais atteint
            //ident = mairie
            //Some("Mairie de Rennes","L'adresse de Mairie de Rennes est : Place de la Mairie")
            case Some((x,y)) => x match{
              case "base2" => println("x ="+x+" ident ="+ident);actualBase = base2
              case _ => return (x,y)
            }
          }
        }
      }
    }else{ //si le client n'écrit pas cherche ou veux
      // newList = List("jsdg","sdfsd","bonjour")
      actualBase = base3
      for(ident <- newList){
        if(actualBase.contains(ident)){
          var firstResponse = actualBase.get(ident)
          firstResponse match{
            case None => return ("","Je ne comprends pas votre demande") //cette ligne n'est jamais atteint
            case Some(a) => return a
            }
          }
        }
    }
    ("","Je ne comprends pas votre demande")
  }
  
  def removeAllSpChar(str:String):String={
    var result = str
    result = result.toLowerCase() // pour rendre la chaine en minuscule
    // enelever les charactère spéciaux
    result = result.replace("é", "e")
    result = result.replace("è", "e")
    result = result.replace("ê", "e")
    result = result.replace("à", "a")
    result = result.replace("â", "a")
    result = result.replace("ô", "o")
    result
  }
}

object Test extends App{
  var bd:BaseDeDonnees = new BaseDeDonnees()
  println(bd.resultat(List("bonsoir")))
  println(bd.resultat(List("je","cherche","mairie")))
  println(bd.resultat(List("je","cherche","théatre","national")))
  println(bd.resultat(List("egrfdg")))
}*/