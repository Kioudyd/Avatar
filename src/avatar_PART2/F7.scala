package avatar_PART2

import library.UrlProcessor

import library.Html
import library.Tag
import library.Text

object F7 {

  def removeAllSpChar(str: String): String = {
    var result = str
    result = result.toLowerCase() // pour rendre la chaine en minuscule
    // enelever les charactÃ¨re spÃ©ciaux
    result = result.replace("Ã©", "e")
    result = result.replace("Ã¨", "e")
    result = result.replace("Ãª", "e")
    result = result.replace("Ã ", "a")
    result = result.replace("Ã¢", "a")
    result = result.replace("Ã´", "o")
    result
  }

  def removeAllSpChar2(str: String): String = {
    var result = str
    // result = result.toLowerCase() // pour rendre la chaine en minuscule
    // enelever les charactÃ¨re spÃ©ciaux
    result = result.replace("Ã©", "e")
    result = result.replace("Ã¨", "e")
    result = result.replace("Ãª", "e")
    result = result.replace("Ã ", "a")
    result = result.replace("Ã¢", "a")
    result = result.replace("Ã´", "o")
    result
  }

  /**
   * @param : h de type Html et e un mot de type String
   * @return : Renvoie vrai si le mot e est prÃ©sent dans le documents HTML h
   */
  def filtreHtml1(h: Html, e: String): Boolean = {
    var res: Boolean = false;
    h match {
      case Tag(_, _, t) =>
        for (i <- t) { res = res || filtreHtml1(i, e) }; res
      case Text(x) => x.contains(e)
    }
  }

  //F7.resultat3(List("RestaUrant","lÃ¢","tomate"))
  //newList("restaurant","la","tomate")
  def resultat3(req: List[String]): String = {
    
    var result: String = ""
    var newList: List[String] = List()
    var resFound: String = ""
    for (ident <- req) {
      newList = newList :+ removeAllSpChar(ident)
    }
    if (newList.contains("pizzeria") ||
      newList.contains("restaurant") ||
      newList.contains("creperie")) {
      //new List = List(je, cherche, restaurant, la, tomate)
      newList = removeUselesslm(newList)
      //new List = List(la, tomate)
      //result = separeList(newList)
      val str: String = newList.mkString("+") // fonction de scala
      // str = la+tomate
      var url = "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + str;
      //https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=la+tomate
      //println(url)
      var html: Html = UrlProcessor.fetch(url);

      var l: List[Html] = secondaryFun(html, ("id", "jSearchResults"))
      if (!l.isEmpty) {
        var l2: List[(String, String)] = List()
        var l3: List[(String, String)] = List()
        for (ident <- l) {
          l2 = l2 ++ secondaryFun3(ident, "h2", ("class", "bu_restaurant_title_2"))
        }

        if (!l2.isEmpty) {
          resFound = removeAllSpChar2(l2(0)._2)
          if (removeAllSpChar(resFound) == newList.mkString(" ")) {
            url = "https://www.linternaute.com" + l2(0)._1
            html = UrlProcessor.fetch(url);
            l = secondaryFun(html, ("itemprop", "streetAddress"))
            for (ident <- l) {
              result = result + extraireText(ident)

            }
          }
        }
      }
    }
    if (result == "") {
      null
    } else {
      result = langues.languecurr.get("Adresse").get.toString() + " " + resFound + " " + langues.languecurr.get("Est").get.toString() + " " + result
      result
    }
  }

  def separeList(l: List[String]): String = {
    var m = ""
    for (ident <- l) {
      m = m + ident + " "
    }
    return m

  }

  /**
   * params : html
   * param : (attribut,valeur) ex: (class,container)
   * return List[Html] : les balises qui ont (attribut,valeur)
   */
  def secondaryFun(html: Html, param: (String, String)): List[Html] = {
    var result: List[Html] = List()
    html match {
      case Tag(_, attributes, children) =>
        if (attributes.contains((param._1, param._2))) {
          result = result :+ html
        } else {
          for (ident <- children) {
            result = result ++ secondaryFun(ident, param)
          }
        }
      case Text(_) => List()
    }
    result
  }
  /**
   * params : html
   * return List[String] tous les url <a href="url">
   */
  def secondaryFun2(html: Html): List[String] = {
    var result: List[String] = List()
    html match {
      case Tag("a", attributes, children) =>
        for (ident <- attributes) {
          ident match {
            case ("href", x) => result = result :+ x
            case _           => List()
          }
        }
      case Tag(_, attributes, children) =>
        for (ident <- children) {
          result = result ++ secondaryFun2(ident)
        }
      case Text(_) => List()

    }
    result
  }

  def secondaryFun3(html: Html, tag: String, param: (String, String)): List[(String, String)] = {
    var result: List[(String, String)] = List()
    var i: Int = 0
    html match {
      case Tag(tag, attributes, children) =>
        for (ident <- attributes) {
          ident match {
            case (param._1, param._2) => result = result :+ (secondaryFun2(children(0))(0), extraireText(children(0)))
            case _                    => i = i + 1
          }
        }
        for (ident <- children) {
          result = result ++ secondaryFun3(ident, tag, param)
        }
      case Text(_) => List()
    }
    result
  }

  /**
   * params:html
   * return le text
   */
  def extraireText(html: Html): String = {
    var result: String = ""
    html match {
      case Tag(_, _, children) =>
        for (ident <- children) {
          result = result ++ extraireText(ident)
        }
      case Text(x) => result = result + x
    }
    result
  }

val uselessWord: List[String] = List("hello", "bonjour", "hola","hallo","buongiorno","je", "de","cherche", "restaurant", "creperie", "pizzeria", "veux","le","les", "la","el","un","une","una","i","search", "look", "for","the","of","restaurante","ristorante","busco","buscando","suche", "suchen","cerco", "cercando","io","ich","voglio","want","quiero","will")
val uselessWordlm: List[String] = List("je", "cherche", "restaurant", "creperie", "pizzeria", "veux","i","search","for","restaurante","ristorante","busco","buscando","suche", "suchen","cerco", "cercando","io","ich","voglio","want","quiero","will")

  /**
   * params : list[String] ex : List("je","cherche","restaurant","la","tomate")
   * return List[String] sans les elements qui sont dans uselessWord ex : List("la","tomate")
   */
  def removeUseless(list: List[String]): List[String] = {
    var result: List[String] = List()
    for (ident <- list) {
      if (!uselessWord.contains(ident)) {
        result = result :+ ident
      }
    }
    result
  }

    def removeUselesslm(list: List[String]): List[String] = {
    var result: List[String] = List()
    for (ident <- list) {
      if (!uselessWordlm.contains(ident)) {
        result = result :+ ident
      }
    }
    result
  }
 
  

}

object Test extends App {
  //println(F7.separeList(List("restaurant", "la", "tomate")))
  println(F7.resultat3(List("restaurant", "la", "tomate")))
  println(F7.resultat3(List("je", "cherche", "restaurant", "le", "CARrÃ©")))
  println(F7.resultat3(List("je", "cherche", "pizzeria", "kawazaki")))
  println(F7.resultat3(List("je", "veux", "le", "restaurant", "piano", "blanc")))
}