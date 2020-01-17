package avatar_PART2
import avatar_PART1.ToleranceFautes

object langues {
  // Renommer baseDeDonnees en motsCles
  val motsClesFr = List("bonjour", "salut", "bonsoir", "recherche", "cherche", "ou", "est", "donc", "trouve", "trouver", "français")
  val motsClesAng = List("hi", "hello", "morning", "evening", "afternoon", "hey", "seek", "seeking", "search", "searching", "look", "looking", "where", "find", "english")
  val motsClesEsp = List("hola", "buenos", "dias", "donde", "esta", "busco", "buscando", "español")
  val motsClesAll = List("hallo", "guten", "morgen", "tag", "abend", "wo", "ist", "suche", "suchen", "deutsch")
  val motsClesIta = List("buongiorno", "ciao", "salve", "buon", "pomeriggio", "buonasera", "incantato", "dove", "trova", "cerco", "cercando", "italiano")
  var lieu: String = ""
  var nbreponses: Int = 0
  
  val mary = new VoixImpl // Voix par MaryTTS
  mary.DonneVoix("fr")

  val bddFr = Map(
    "salutation" -> List("bonjour", "salut", "bonsoir"),
    "RequeteLangue" -> "parlez-vous français ?",
    "Oui" -> "oui",
    "Non" -> "non",
    "Question" -> "d'accord, quelle est votre demande?",
    "J'ai" -> "j'ai",
    "réponses possibles" -> "réponses possibles",
    "Choix" -> "quel est votre choix?",
    "Endroits" -> List("restaurant", "creperie", "pizzeria"),
    "Adresse" -> "l'adresse du ",
    "Est" -> "est",
    "poCompris" -> "je ne comprends pas votre demande",
    "cherche" -> List("cherche"," recherche","veux","trouver"))

  var languecurr: Map[String, Object] = bddFr
  var langueChange: Map[String, Object] = bddFr

  val bddEn = Map(
    "salutation" -> List("hello", "hi", "good", "morning", "evening", "afternoon", "hey"),
    "RequeteLangue" -> "do you speak English?",
    "Oui" -> "yes",
    "Non" -> "no",
    "Question" -> "ok, what is your query?",
    "J'ai" -> "i found",
    "réponses possibles" -> "answers",
    "Choix" -> "what is your choice?",
    "Endroits" -> List("restaurant", "creperie", "pizzeria"),
    "Adresse" -> "the adress of ",
    "Est" -> "is",
    "poCompris" -> "i do not understand",
    "cherche" -> List("search"," look","for","want","searching"))

  val bddEsp = Map(
    "salutation" -> List("hola", "buenos", "dias"),
    "RequeteLangue" -> "hablas español?",
    "Oui" -> "si",
    "Non" -> "no",
    "Question" -> "está bien, cuál es tu petición?",
    "J'ai" -> "tengo",
    "réponses possibles" -> "opciones",
    "Choix" -> "está bien, cuál es tu petición?",
    "Endroits" -> List("restaurante", "creperie", "pizzeria"),
    "Adresse" -> "la dirección de ",
    "Est" -> "es",
    "poCompris" -> "no comprendo",
    "cherche" -> List("busco","buscando","quiero"))

  val bddAll = Map(
    "salutation" -> List("hallo", "guten", "morgen", "tag"),
    "RequeteLangue" -> "sprechen sie deutsch?",
    "Oui" -> "ja",
    "Non" -> "nein",
    "Question" -> "okay, was ist ihr wunsch?",
    "J'ai" -> "ich habe",
    "réponses possibles" -> "antworten",
    "Choix" -> "was ist ihre wahl?",
    "Endroits" -> List("restaurant", "creperie", "pizzeria"),
    "Adresse" -> "die adresse von ",
    "Est" -> "ist",
    "poCompris" -> "ich verstehe nicht",
    "cherche" -> List("suche","suchen","mochte","wolle","finden"))

  val bddIta = Map(
    "salutation" -> List("buongiorno", "ciao", "salve"),
    "RequeteLangue" -> "parli italiano?",
    "Oui" -> "si",
    "Non" -> "no",
    "Question" -> "va bene, qual è la tua richiesta?",
    "J'ai" -> "ho",
    "réponses possibles" -> "risposte",
    "Choix" -> "qual è la vostra scelta?",
    "Endroits" -> List("ristorante", "creperie", "pizzeria"),
    "Adresse" -> "indirizzo di ",
    "Est" -> "è",
    "poCompris" -> "no capisco",
    "cherche" -> List("cerco", "cercando","voglia","trova"))

  val fr = "Parlez-vous français?"
  val ang = "Do you speak english?"
  val esp = "Hablas español?"
  val all = "Sprechen Sie Deutsch?"
  val ita = "Parli italiano?"

  def numero (s: String) : Boolean = {
    val Pattern = "(\\d+)".r
    s match {
      case Pattern(txt) => true
      case _ => false
    }
  }
  
  def choixLangue(mot: String): String = {
    var reponse: String = ""
    if (motsClesFr.contains(mot)) {
      println("fr")
      reponse = fr
    } else if (motsClesAng.contains(mot)) {
      println("en")
      reponse = ang
    } else if (motsClesEsp.contains(mot)) {
      println("esp")
      reponse = esp
    } else if (motsClesAll.contains(mot)) {
      println("all")
      reponse = all
    } else if (motsClesIta.contains(mot)) {
      println("it")
      reponse = ita
    } else {
      reponse = null
    }
    reponse
  }

  def charabia(mot2: String): String = {
    var reponse2: String = ""
    val tab: Array[String] = Array(fr, ang, esp, all, ita)
    var i: Int = 0
    var testLangue: String = choixLangue(mot2)
    while (testLangue == null && i < tab.length) {
      reponse2 = tab(i)
      i += 1
    }
    if (i >= tab.length) {
      i = 0
      charabia(mot2)
    }
    reponse2
  }

  def nextlangue(): Unit = {
    if (langueChange == bddFr){
      langueChange = bddEn
    }
    else if (langueChange == bddEn){
      langueChange = bddEsp
    }
    else if (langueChange == bddEsp){
      langueChange = bddAll
    }
    else if (langueChange == bddAll){
      langueChange = bddIta
    }
    else if (langueChange == bddIta){
      langueChange = bddFr
    }
  }
  
  def changeVoix(langue: Map[String, Object]){
         if (langue == bddFr)  mary.DonneVoix("fr")
    else if (langue == bddEn)  mary.DonneVoix("en")
    else if (langue == bddEsp) mary.DonneVoix("esp")
    else if (langue == bddAll) mary.DonneVoix("de")
    else if (langue == bddIta) mary.DonneVoix("it")
  }
  
  def quelleLangue(mot: String): Map[String, Object] = {
    var reponse: Map[String, Object] = null
    if (motsClesFr.contains(mot)) {
      reponse = bddFr
    } else if (motsClesAng.contains(mot)) {
      reponse = bddEn
    } else if (motsClesEsp.contains(mot)) {
      reponse = bddEsp
    } else if (motsClesAll.contains(mot)) {
      reponse = bddAll
    } else if (motsClesIta.contains(mot)) {
      reponse = bddIta
    }
    reponse
  }
  
  def quelleLangue2(phrase: List[String]): Map[String, Object] = {
    for (e <- phrase){
           if (motsClesFr.contains(e))  return bddFr
      else if (motsClesAng.contains(e)) return bddEn
      else if (motsClesEsp.contains(e)) return bddEsp
      else if (motsClesAll.contains(e)) return bddAll
      else if (motsClesIta.contains(e)) return bddIta
    }
    null
  }
  val politesseFr: List[String] = List("bonjour", "salut", "bonsoir")
  val politesseEsp: List[String] = List("hola", "buenos", "dias")
  val politesseAll: List[String] = List("hallo", "guten", "morgen")
  val politesseIta: List[String] = List("buongiorno", "ciao", "salve", "buon")
  val politesseEn: List[String] = List("hi", "hello", "morning")

  def politesse(mot: String): Boolean = {
    var trouve: Boolean = false
    if (politesseFr.contains(mot)) {
      trouve = true
    } else if (politesseEsp.contains(mot)) {
      trouve = true
    } else if (politesseAll.contains(mot)) {
      trouve = true
    } else if (politesseIta.contains(mot)) {
      trouve = true
    } else if (politesseEn.contains(mot)) {
      trouve = true
    }
    trouve
  }
  
  def politessePhrase(requete:List[String]):Boolean = {
    var a = false
    for(k <- languecurr.get("salutation").get.asInstanceOf[List[String]]) {
      if(compare(requete,k.split(" ").toList)){             
        a = true       
      }
    }
    return a     
  }
  def politesseMot(requete:String):Boolean = {
    var a = false
    for(k <- languecurr.get("salutation").get.asInstanceOf[List[String]]) {
      if(compare(List(requete),k.split(" ").toList)){             
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
  
  var listSalutations : List[String] = languecurr.get("salutation").get.asInstanceOf[List[String]]
  var listCherche : List[String] = languecurr.get("cherche").get.asInstanceOf[List[String]]
  def comprendsPas(l: List[String]): List[String] = {
    println(l)
    println(l.exists(listSalutations.contains) && !l.exists(listCherche.contains))
    if (!l.exists(listSalutations.contains) && !l.exists(listCherche.contains)) {
      List(languecurr.get("poCompris").get.toString())
    }
    else if (l.size == 1 && l.exists(listSalutations.contains)){
      List(listSalutations(0)) 
    }
    else  {
      List(listSalutations(0)) ++ List(languecurr.get("poCompris").get.toString())
    }
  }

}