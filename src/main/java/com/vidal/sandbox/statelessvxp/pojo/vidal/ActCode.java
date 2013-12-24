package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;


/**
 * Code Acte
 */
@MessagePackOrdinalEnum
public enum ActCode {
   AAD("autres accessoires traitement � domicile"),
   AAR("appareillage assistance respiratoire"),
   MAC("materiels et appareils de contention"),
   MAD("mat�riels et appareils de traitements divers"),
   OPT("optique"),
   ORC("accessoires de proth�ses et d'orthop�die"),
   PA("orth�ses"),
   PAN("pansements"),
   PEX("proth�se externe non orthop�dique"),
   PH1("pharmacie 100%"),
   PH4("pharmacie 35%"),
   PH7("pharmacie 65%"),
   PHN("pharmacie non remboursable"),
   PII("proth�se interne inerte"),
   VEH("v�hicules pour handicap�s physiques"),
   PMR("pr�paration magistrale remboursable"),
   ARO("a�rosols"),
   GLU("aliments sans gluten"),
   PAU("audioproth�ses"),
   PH2("pharmacie 15%"),
   PME("PME Implant Mu par Electricite"),
   TNS("traitement nicotinique de substitution"),
   VER("Verres optiques"),
   LEN("Lentilles optiques"),
   LUN("Monture optique"),
   POC("Proth�se Oculaire et faciale"),
   PG7("m�dicament g�n�rique 65%"),
   PDM("Dispositifs m�dicaux ? Prise en charge exceptionnelle"),
   PMH("pr�paration magistrale hom�opathique"),
   DVO("Orth�ses diverses");

   private final String label;

   ActCode(String label) {
      this.label = label;
   }

   public String getLabel() {
      return label;
   }
}
