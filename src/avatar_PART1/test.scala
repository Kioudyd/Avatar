package avatar_PART1

object oui extends App{
   var requete=List("hotel","de","ville")
   println(BdImpl.resultat(requete))
   
   var requete2=List("salut","theatre","nATional","de","Bretagne","stp","t'es le meilleur")
   println(BdImpl.resultat2(requete2))
}