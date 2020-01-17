package machine2

import avatar_PART2.VoixImpl


object testVoix extends App {
  val mary = new VoixImpl
  
  mary.DonneVoix("fr") // ici voix française
  
  mary.parler("Salut")
  mary.parler("") // catch normal
  mary.parler(List())
  mary.parler(List("Bonjour !", "Je suis très poli.", "Je suis même très très poli.", "Et j'ai la voix d'un reporter de France 3 Bourgogne."))
  
  mary.DonneVoix("it") // voix italienne
  
  mary.parler("Salute")
  mary.parler("") // catch normal
  mary.parler(List())
  mary.parler(List("Non parlo molto bene l'italiano.", "Mi scusi, può indicarmi come si va all'aeroporto ?"))
  
  mary.DonneVoix("de") // voix allemande
  
  mary.parler("Guten Tag")
  mary.parler("") // catch normal
  mary.parler(List())
  mary.parler(List("Achtung.", "Ich weiss nicht mehr, wass ich sagen will.", "Ich mag Kartofen.", "Ein hübscher Papagei."))
  
  mary.DonneVoix("en") // voix anglaise
  
  mary.parler("Hello")
  mary.parler("") // catch normal
  mary.parler(List())
  mary.parler(List("Billie Jean", "is not my lover", "She's just a girl", "who claims that I am the one"))
  
  mary.DonneVoix("djzdhksd")
  mary.DonneVoix("")
  
  
}