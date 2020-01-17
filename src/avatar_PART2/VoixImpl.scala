package avatar_PART2

import javax.sound.sampled.AudioInputStream
import marytts.LocalMaryInterface
import marytts.MaryInterface
import marytts.util.data.audio.AudioPlayer


class VoixImpl extends Voix
{
	
	private val marytts: MaryInterface = new LocalMaryInterface()
	
	def DonneVoix(nomLocale: String){
	  var nomVoix: String = ""
	  if (nomLocale == "fr") nomVoix = "upmc-pierre-hsmm"
	  else if (nomLocale == "de") nomVoix = "dfki-pavoque-neutral-hsmm"
	  else if (nomLocale == "en") nomVoix = "cmu-slt-hsmm"
	  else if (nomLocale == "it") nomVoix = "istc-lucia-hsmm"
	  else marytts.setAudioEffects("Volume(amount:0.0)");
	  if (nomVoix != "" && nomLocale != ""){
		  try{
			  marytts.setVoice(nomVoix)
			  marytts.setAudioEffects("Volume(amount:1.0)");
		  }
		  catch {
		    case e: Throwable => e.printStackTrace()
		  }
	  }
	  else {
	    //println("Pas de voix")
	  }
	}

	def parler(s: String){
		 try{
	     val ap: AudioPlayer = new AudioPlayer()
			 val audio: AudioInputStream = marytts.generateAudio(s)
			 ap.setAudio(audio)
			 ap.start()
		 }		
		 catch {
		   case e: Throwable => System.err.println("Error saying phrase.")
		 }
	}
	
	def parler(ls: List[String]){
	  var reponse = ""
	  for (s <- ls){
	    reponse += s + ". "
	  }
	  parler(reponse)
	}
	
	//println("Voix disponibles: " +marytts.getAvailableVoices())
}