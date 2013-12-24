package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;



/**
 * values available as enum java type related to refund rate
 * 
 * @author Vidal Software
 */
@MessagePackOrdinalEnum
public enum RefundRate {
   /*
    * // Rembours� selon l'indication IND(null, "IND"), // TIPS TIPS(null, "TIPS"),
    */
   // LPPR
   LPPR('T', "LPPR"),
   // Non rembours�
   NR('N', "NR"),
   // Rembours� S�c Soc � 15%
   _15('2', "15%"),
   // Rembours� S�c Soc � 35%
   _35('4', "30%"),
   // Rembours� S�c Soc � 65%
   _65('7', "65%"),
   // Rembours� S�c Soc � 100%
   _100('1', "100%"),
   /**
    * Used only on API side for compatibility with languages which cannot handle <code>null</code> value for enum type.
    */
   UNSPECIFIED('_', null),
   /**
    * Used only for product which have different packs'refundRates
    */
   NOT_HOMOGENEOUS('P', "partiel");

   private final char code;
   private final String label;

   RefundRate(char code, String label) {
      this.code = code;
      this.label = label;
   }

   public char getCode() {
      return code;
   }

   public String getLabel() {
      return label;
   }
}
