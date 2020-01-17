
package machine2
import org.junit.Test
import org.junit.Assert._
import avatar_PART1.ToleranceFautes
import avatar_PART2.RechercheXML

class Test_RechercheXML extends avatar_PART2.RechercheXML {
  
 val t1= List(("Piscine Bréquigny","10 Boulevard Albert 1er"), ("Piscine Gayeulles","16 AVENUE DES GAYEULLES,AVENUE DES GAYEULLES"), ("Piscine Saint-Georges","4 RUE GAMBETTA"), ("Piscine Villejean","1 SQUARE D'ALSACE"))
 
 
 @Test
 def test2{
   assertEquals(List(("20 minutes Rennes","40 RUE DU PRE BOTTE")),(recherche("20 minutes Rennes")))
 }
@Test
 def test3{
   assertEquals(List(("Accroche toi au pinceau","80 Canal Saint-Martin")),(recherche("Accroche toi au pinceau")))
}

@Test
 def test4{
   assertEquals(List(("Accueil à la maison","12 RUE DU GRAND CORDEL,RUE DU GRAND CORDEL")),(recherche("Accuei à l maisn")))
}

 @Test
 def test1 {
    assertEquals(t1, recherche("Piscine"))
  }
 


 
 
}

