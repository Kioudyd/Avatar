package machine
import org.junit.Test
import org.junit.Assert._
import avatar_PART1.analyse_phrase_impl

class test_TDD_analyse_phrase extends analyse_phrase_impl{

  // tTe de la fonction interrogation
  @Test
  def testinterrogation{
    assertEquals(interrogation(List("Mairie", "de", "Rennes")), ("Mairie de Rennes","Place de la Mairie"))
    assertEquals(interrogation(List("Mairie")), ("Mairie de Rennes","Place de la Mairie"))
    assertEquals(interrogation(List("blabla", "Mairie", "de", "Rennes")), ("Mairie de Rennes","Place de la Mairie"))
    assertEquals(interrogation(List("Mairie", "de", "Rennes", "blabla")), ("Mairie de Rennes","Place de la Mairie"))
    assertEquals(interrogation(List("blabla", "Mairie", "de", "Rennes", "blabla")), ("Mairie de Rennes","Place de la Mairie"))
    assertEquals(interrogation(List("blabla")), null)
  }
  
  @Test
  def test1_2{  
    assertEquals(("L'adresse du Théâtre National de Bretagne est : 1, Rue Saint-Hélier"),phrase_de_sortie("Théâtre National de Bretagne","1, Rue Saint-Hélier"))
  }
  @Test 
  def test2_2{  // espace enlevé après le est
    assertEquals(("L'adresse de la Mairie de Rennes est: Place de la Mairie"),phrase_de_sortie("Mairie de Rennes","Place de la Mairie"))
  }
  @Test
  def test2_3{  // Un espace après le Mairie
    assertEquals(("L'adresse de la Mairie de Rennes est : Place de la Mairie"),phrase_de_sortie("Mairie de Rennes ","Place de la Mairie"))
  }
    @Test
  def test2_4{  // tuple de string vide
    assertEquals(("L'adresse de la Mairie de Rennes est : Place de la Mairie"),phrase_de_sortie("",""))
  }
    @Test
     def test2_5{  // tuple de string vide
    assertEquals(("L'adresse de la Mairie de Rennes est : "),phrase_de_sortie("Mairie de Rennes ",""))
  }
    
 @Test
  def test1{
    assertEquals(List(),phrase_transforme(""))
    assertEquals(List("l","l","l"),phrase_transforme("l l l"))
    assertEquals(List(),phrase_transforme(" "))
    assertEquals(List("l"),phrase_transforme(" l"))
    assertEquals(List("l'hotel", "de", "ville"),phrase_transforme("l'hotel de ville"))
  }

}

