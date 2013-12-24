package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;


/**
 * values available as enum java type related to generic type
 * 
 * @author Vidal Software
 */

@MessagePackOrdinalEnum
public enum GenericType {
   GENERIC('G'), //
   REFERENT('R'), //
   /**
    * Used only on API side for compatibility with languages which cannot handle <code>null</code> value for enum type.
    */
   NULL('_');

   private final char code;

   GenericType(char code) {
      this.code = code;
   }

   public char getCode() {
      return code;
   }
}
