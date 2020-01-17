package avatar_PART1

object askpart1 extends App {
 


  def resultat(s:String):List[String]= {
    var Stringf : String = ""
     var a : analyse_phrase_impl = new analyse_phrase_impl()
     var politesse : String = ""
     var res : List[String] = List()
     if(politesse == "Bonjour"){
       res = politesse :: res
     }
    if(politesse == "Bonjour*"){
      "Bonjour"::res
    }else {
     Stringf =  a.phrase_de_sortie(a.interrogation(a.phrase_transforme(s)))
     res = res :+ Stringf
     res
    }
  }
  //println(resultat("Bonjour"))
  //println(resultat("Mairie"))
  //println(resultat("Je cherche mairie"))
  //println(resultat("mairi"))
  //println(resultat("Bonjour je cherche mairie"))
  //println(resultat("Bonjour je cherche piscine"))
  //println(resultat("piscine"))
  //println(resultat("hello"))
 
}
