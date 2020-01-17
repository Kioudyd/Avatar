/*package machine2
import org.junit.Test
import org.junit.Assert._
import avatar_PART2._

class testF6  {
  var map1: Map[Int, (String, String)] = Map(
    1 -> ("piscine de Bréquiny", "viande"),
    2 -> ("chicha", "viande"),
    3 -> ("wc", "allée du pq"),
    4 -> ("eglise Notre-Dame", "rue de Pas-Notre-Dame"),
    5 -> ("danse", "ce soir"))
val tab1:Array[(String,String)]=Array(("piscine de Bréquiny", "viande"), ("chicha", "viande"), ("wc", "allée du pq"), ("eglise Notre-Dame", "rue de Pas-Notre-Dame"), ("danse", "ce soir"))
  

  @Test
  def test1 {
    F6Impl.plusieurs_choix(List(("piscine de Bréquiny", "viande"), ("chicha", "viande"), ("wc", "allée du pq"), ("eglise Notre-Dame", "rue de Pas-Notre-Dame"), ("danse", "ce soir")))
    assertEquals(map1, F6Impl.map)
  }

  @Test
  def test2 {
    F6Impl.initMap()
    assertEquals(Map(), F6Impl.map)
  }

  @Test
  def test3 {
    F6Impl.plusieurs_choix(List(("piscine de Bréquiny", "viande"), ("chicha", "viande"), ("wc", "allée du pq"), ("eglise Notre-Dame", "rue de Pas-Notre-Dame"), ("danse", "ce soir")))
    F6Impl.initMap()
    F6Impl.plusieurs_choix(List(("piscine de Bréquiny", "viande"), ("chicha", "viande"), ("wc", "allée du pq"), ("eglise Notre-Dame", "rue de Pas-Notre-Dame"), ("danse", "ce soir")))
    assertEquals(map1, F6Impl.map)
  }

  @Test
  def test4 {
    F6Impl.initMap()
    val list = List("1) piscine de Bréquiny", "2) chicha", "3) wc", "4) eglise Notre-Dame", "5) danse")
    val list2 = F6Impl.f6ToString(map1)
    assertEquals(list, list2)
    
  }
}
*/