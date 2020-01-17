package machine
import org.junit.Test

import org.junit.Assert._
import avatar_PART1.ToleranceFautes

class testToleranceFautes extends ToleranceFautes {

  val s: String = "opera de rennes"
  val s2: String = "opera de rennes"
  val s3: String = "opera de rinnes"
  val s4: String = "oprra de ronnes"
  val s5: String = "opero do rinees"
  val s6: String = "opera"
  val s7: String = "opero"
  val s8: String = "oper"
  val s9: String = "ope"
  val s10: String = "l'hotel de ville"
  val s11: String = "l'htel de ville"
  val s12: String = "l'htel do ville"
  val s13: String = "operor"
  val s14: String = "lhotel de ville"
  val s15: String = "l'''opërÂ"
  val s16: String = "l'''0PëRâ"
  val s17: String = "''''0PêrA,?'_-()'''"



  //Test fonction Bigger

  @Test
  def test1_bigger {
    assertEquals(s2, biggestString(s, s2))
  }
  @Test
  def test2_bigger {
    assertEquals(s, biggestString(s, s6))
  }

  //Test fonction smallerString
  @Test
  def test3_smallerString {
    assertEquals(s, smallestString(s, s2))
  }
  @Test
  def test4_smallerString {
    assertEquals(s6, smallestString(s, s6))
  }

  //Test fonction distanceDeHamming
  @Test
  def test5_distanceDeHamming {
    assertEquals(0, distanceDeHamming(s, s2))
  }
  @Test
  def test6_distanceDeHamming {
    assertEquals(1, distanceDeHamming(s, s3))
  }
  @Test
  def test7_distanceDeHamming {
    assertEquals(2, distanceDeHamming(s, s4))
  }

  //Test fonction insertString
  @Test
  def test8_insertString {
    assertEquals(true, insertSharp(s6, s8))
  }
  @Test
  def test9_insertString {
    assertEquals(false, insertSharp(s6, s9))
  }

  //Test fonction StringEgal
  @Test
  def test10_stringEgal {
    assertEquals(true, motRecevable(s, s3))
  }
  @Test
  def test11_stringEgal {
    assertEquals(false, motRecevable(s, s4))
  }

  //Test fonction finale
  @Test
  def test12_correctionFautes {
    assertEquals(true, estSansFaute(s6, s8))
  }
  @Test
  def test13_correctionFautes {
    assertEquals(false, estSansFaute(s6, s9))
  }
  @Test
  def test14_correctionFautes {
    assertEquals(true, estSansFaute(s, s3))
  }
  @Test
  def test15_correctionFautes {
    assertEquals(false, estSansFaute(s, s4))
  }

  @Test
  def test16_correctionFautes {
    assertEquals(true, estSansFaute(s10, s11))

  }
  @Test
  def test17_correctionFautes {
    assertEquals(true, estSansFaute(s10, s14))

  }
  @Test
  def test18_correctionFautes {
    assertEquals(false, estSansFaute(s6, s16))
    println(estSansFaute(s6, s16))
  }
  @Test
  def test19_correctionFautes {
    assertEquals(true, estSansFaute(s6, s17))
  }
}

