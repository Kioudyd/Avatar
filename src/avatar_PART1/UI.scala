package avatar_PART1

import scala.swing._
import java.awt.Color
import java.util.Calendar
import javax.swing.ImageIcon
import scala.swing.event._
import machine.MachineImpl
import avatar_PART2.VoixImpl
import java.util.Timer


class UI extends MainFrame {
  
  title = "Avatar UI" 
  preferredSize = new Dimension(700, 300)
  
  var panelConversation = new BoxPanel(Orientation.Vertical) // là où la conversation sera enregistrée
  var panelRequete = new TextField {                         // la zone de texte où l'utilisateur rentre sa demande
    maximumSize = new Dimension(Short.MaxValue, preferredSize.height)
  }
  
  var scroll = new ScrollPane(panelConversation) // permet d'utiliser une barre de défilement
  var barreScroll = scroll.verticalScrollBar     // la barre de défilement qui ne s'affichera que quand on en aura besoin
  
  var iconeRequete = new ImageIcon("icones/interrogation.jpg") // avatars utilisateur = interrogation, machine = exclamation
  var iconeReponse = new ImageIcon("icones/exclamation.jpg")
  
  val mary = new VoixImpl // Voix par MaryTTS
  mary.DonneVoix("fr") // en attendant de pouvoir changer de langue
  
  /**
   * La fonction récupère l'heure au moment de l'envoi de la requête, la requête envoyée par l'utilisateur 
   * dans la zone de texte et la réponse obtenue de la machine. Elle réalise alors l'affichage dans le panneau 
   * de conversation en mettant le tout en scène avec des avatars, des couleurs spécifiques, et en indiquant l'heure des messages.
   */
  private def gestionUI() {
    if (panelRequete.text != "") {
      
      var heure = new Label { // panneau de l'heure
        val h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val m = Calendar.getInstance().get(Calendar.MINUTE)
        text = "à " + h + "h"
        if (m < 10) text += "0" + m
        else text += m
      }
      
      var requete = panelRequete.text
      var requeteConversation = new Label { text = requete }
      var reponse = MachineImpl.ask(requete)
      var compteReponse: Int = 0
      
      panelConversation.contents += new BoxPanel(Orientation.Horizontal) { // panneau de l'utilisateur
        contents += Swing.HGlue                                           // ici le panneau sera collé à droite
        background= Color.decode("#ff8989") 
        contents += requeteConversation
        contents += new Label { 
          icon = iconeRequete 
          border = Swing.EmptyBorder(1, 1, 1, 1)
        }
        border = Swing.EmptyBorder(1, 0, 1, 5)
      }

      panelConversation.contents += new BoxPanel(Orientation.Horizontal) { // panneau de l'heure, aussi collé à droite
        contents += Swing.HGlue
        contents += heure
        border = Swing.EmptyBorder(1, 0, 1, 5)
      }

      panelConversation.contents += new BoxPanel(Orientation.Horizontal) { // panneau de réponse machine, collé à gauche
        background= Color.decode("#a1db99")
        contents += new Label {
          icon = iconeReponse 
          border = Swing.EmptyBorder(1, 1, 1, 1)
        }
        contents += new BoxPanel(Orientation.Vertical){
          for (s <- reponse){
            contents += new BoxPanel(Orientation.Horizontal){
              background= Color.decode("#a1db99")
              contents += new Label (reponse(compteReponse))
              contents += Swing.HGlue
            }
            compteReponse += 1
          }
        }
        contents += Swing.HGlue
        border = Swing.EmptyBorder(1, 5, 1, 0)
      }
      
      panelRequete.text = ""                                              // réinitialise la zone de texte
      panelConversation.revalidate                                        // actualise le panneau de conversation pour voir les modifications
      Swing.onEDT(barreScroll.peer.setValue(barreScroll.peer.getMaximum)) // force le défilement vers le bas
      mary.parler(reponse)
    }
  }
    
  
  contents = new BoxPanel(Orientation.Vertical) { // fenêtre principale
    contents += scroll
    
    contents += new BoxPanel(Orientation.Horizontal){
      contents += panelRequete
      
      contents += Button("OK") {
        gestionUI()
      }
      
      listenTo(panelRequete.keys) // permet d'utiliser la touche Entrée au lieu de cliquer sur le bouton OK
      reactions += {
        case KeyPressed(_, Key.Enter, _, _) =>{
          gestionUI()
        }
      }
    }
  }
}