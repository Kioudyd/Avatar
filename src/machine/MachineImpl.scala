package machine
import java.text.Normalizer

import avatar_PART1.analyse_phrase_impl
import avatar_PART2._

object MachineImpl extends MachineDialogue {
  //Init
  var changeLangue: Boolean = false
  var choixMultiples = false // pour pas bloquer en changement de langue pour "je choisi la 1" par exemple
  var num: List[Int] = List() // Choix de numéro
  var mapLangues: Map[String, Object] = langues.languecurr // Map de la langue courante
  var derniereLangue: Map[String, Object] = langues.langueChange // Map de la langue d'avant
  var res: List[String] = List() // resultat de requête
  var tolFaute: avatar_PART1.ToleranceFautes = new avatar_PART1.ToleranceFautes()

  def ask(s: String): List[String] = {

    println(s) // Afficher dans les tests clients

    res = List()
    var requete: String = s.toLowerCase() // Enleve les maj
    requete = Normalizer.normalize(requete, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // Retire tous les accens
    requete = requete.replaceAll("""[\p{Punct}&&[^.]]""", "") // Retire toute forme de ponctuation

    var analyse: analyse_phrase_impl = new analyse_phrase_impl()
    var requeteList = analyse.phrase_transforme(requete) // requete transformÃ©e en liste
    var requeteList2 = analyse.phrase_transforme(requete) // requete transformÃ©e en liste
    val langueDetectee = langues.quelleLangue2(requeteList)
    val xml: avatar_PART2.RechercheXML = new avatar_PART2.RechercheXML()
    var politesse = false

    if (choixMultiples == false && (changeLangue || (derniereLangue != langueDetectee && langueDetectee != null))) {
      // On gère le changement de langue.

      if (!changeLangue) { // Parlez vous Francais etc ..
        changeLangue = true
        derniereLangue = langueDetectee
        langues.langueChange = langueDetectee
        langues.changeVoix(derniereLangue)
        List(derniereLangue.get("RequeteLangue").get.toString())
      } else if (changeLangue) { // si on change la langue
        if (requeteList.head.equals(derniereLangue.get("Oui").get.toString().toLowerCase())) { //Si la réponse est oui
          changeLangue = false // on lock la langue
          mapLangues = derniereLangue
          langues.languecurr = derniereLangue
          langues.langueChange = derniereLangue
          List(mapLangues.get("Question").get.toString()) // Poser la question de la requête
        } else { // SI c'est non
          langues.nextlangue() // On passe à la langue suivante
          derniereLangue = langues.langueChange
          langues.changeVoix(derniereLangue)
          List(derniereLangue.get("RequeteLangue").get.toString())
        }
      } else if (changeLangue && langueDetectee == null) {
        langues.nextlangue() // On passe a la langue suivante
        derniereLangue = langues.langueChange
        langues.changeVoix(derniereLangue)
        List(derniereLangue.get("RequeteLangue").get.toString())
      } else {
        List(mapLangues.get("poCompris").get.toString()) // On réponds "je ne comprends pas"
      }
    } else {
      if (langues.politessePhrase(requeteList)) { // Si on detecte une politesse
        politesse = true
        val pol: List[String] = (langues.languecurr.get("salutation").get).asInstanceOf[List[String]]
        res = List(pol(0)) ++ res // Renvoie la première salutation ex:"Bonjour"
      }

      val askpart1 = (avatar_PART1.askpart1.resultat(requete)) // Traitement de F1 F2 F3
      if (askpart1 != List("Je ne comprends pas votre demande")) {
        res ++ askpart1
      } else {

        if (choixMultiples) { // Si on a pls choix
          num = (1 to F6Impl.compteur).toList // Le nombre de choix possible
          choixMultiples = false
          if (langues.numero(requete)) { // si on tape on tape un num existant
            var nom: String = F6Impl.map.get(requete.toInt).get._1
            var plusieurs: List[(String, String)] = xml.recherche(nom)
            res ++ List(langues.languecurr.get("Adresse").get.toString() + " " + plusieurs.head._1 + " " + langues.languecurr.get("Est").get.toString() + " " + plusieurs.head._2) // affichage de l'adresse

          } else {
            res ++ List(langues.languecurr.get("poCompris").get.toString()) // Si le numéro n'est pas compris dans les choix
          }

        } else { // Recherche internaute.
          /* Ici, on sépare la requete en deux parties. Partie avant avant et apres restaurant ou pizzeria..
  * et on retire les mots non pertinants de manière à ce que la recherche fonctionne si l'on tape
  * "je cherche le restaurant la tomate" */
          var gauche: List[String] = List()
          var droite: List[String] = List()
          var compteurRestaurant: Int = -1
          var compteurCreperie: Int = -1
          var compteurPizzeria: Int = -1
          val ls: List[String] = (langues.languecurr.get("Endroits").get).asInstanceOf[List[String]]
          ////println(List(langues.languecurr.get("Endroits").get.toString()))

          compteurRestaurant = requeteList.indexOf(ls(0))
          compteurCreperie = requeteList.indexOf(ls(1))
          compteurPizzeria = requeteList.indexOf(ls(2))
          if (compteurRestaurant != -1) {
            gauche = requeteList.drop(compteurRestaurant)
            droite = requeteList.dropRight(requeteList.length - compteurRestaurant)
            droite = F7.removeUseless(droite)

          } else if (compteurCreperie != -1) {
            gauche = requeteList.drop(compteurCreperie)
            droite = requeteList.dropRight(requeteList.length - compteurCreperie)
            droite = F7.removeUseless(droite)
          } else if (compteurPizzeria != -1) {
            gauche = requeteList.drop(compteurPizzeria)
            droite = requeteList.dropRight(requeteList.length - compteurPizzeria)
            droite = F7.removeUseless(droite)
          }
          var nouvelleRequeteInternaute: List[String] = /*droite ++ */ gauche
          val internaute = F7.resultat3(nouvelleRequeteInternaute)
          if (internaute != null) { // Appel chez LÃ©o et Melissa
            res ++ List(internaute)

          } else { // On transforme la liste en String sans les mots inutiles
            num = (1 to F6Impl.compteur).toList
            requeteList = F7.removeUseless(requeteList)
            var listToString: String = ""
            for (e <- requeteList) {
              listToString = listToString + e + " "
            }
            var listxml: List[(String, String)] = xml.recherche(listToString) // resultat de la recherche XML

            if (F6Impl.unChoix(xml.recherche(listToString))) { // SI un seul choix
              res ++ List(langues.languecurr.get("Adresse").get.toString() + " " + listxml.head._1 + " " + langues.languecurr.get("Est").get.toString() + " " + listxml.head._2)
            } else if (!choixMultiples) {
              F6Impl.initMap() // On initialise la Map
              F6Impl.plusieurs_choix(requeteList) // On la rempli avec notre requete
              if (F6Impl.map.equals(Map()) && politesse) { // Si la map est vide et qu'il y a politesse on renvoie la politesse et je ne comprend pas votre demande
                langues.comprendsPas(requeteList2)
              } else if (F6Impl.map.equals(Map()) && !politesse) { // Sinon on renvoie je ne comprends pas
                res ++ List(langues.languecurr.get("poCompris").get.toString())
              } else { // S'il y a pls choix on passe choixMultple à true et on renvoie quel est votre demande
                var F6: List[String] = F6Impl.f6ToString(F6Impl.map)
                choixMultiples = true
                res ++ F6 ++ List(langues.languecurr.get("Choix").get.toString())

              }
            } else { // Sinon on renvoie pas compris.
              res ++ List(langues.languecurr.get("poCompris").get.toString())
            }
          }
        }
      }

    }

  }


  def reinit: Unit = {
    changeLangue = false
    choixMultiples = false
    num = List()
    langues.languecurr = langues.bddFr
    langues.langueChange = langues.bddFr
    mapLangues = langues.languecurr
    derniereLangue = langues.langueChange
    res = List()
  }

  def test(l: List[String]): List[String] = {
    var l2: List[String] = List()
    l match {
      case Nil          => Nil
      case head :: tail => ask(head) ::: test(tail) //concatÃ©nation de deux lists
    }
  }

}

