package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;


/**
 * values available as enum java type related to list type
 * 
 * @author Vidal Software
 */
@MessagePackOrdinalEnum
public enum ListType {
   I('1', "Liste 1"), //
   II('2', "Liste 2"), //
   NARCOTIC('S', "Stup�fiant"), //

   /**
    * Used only on API side for compatibility with languages which cannot handle <code>null</code> value for enum type.
    */
   NULL('_', null), //
   /**
    * Used only for product which have diff�rent packs' list
    */
   NOT_HOMOGENEOUS('*', "Selon le conditionnement");
   private final char code;
   private final String label;

   ListType(char code, String label) {
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
