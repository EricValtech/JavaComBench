package com.vidal.sandbox.statelessvxp.pojo.vidal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.msgpack.annotation.MessagePackOrdinalEnum;

@MessagePackOrdinalEnum
public enum DispensationPlace implements Keyed {
   HOSPITAL(8), PHARMACY(14),
   /**
    * Used only for product which have different packs'dispensationPlace
    */
   NOT_HOMOGENEOUS(0);

   private static final List<DispensationPlace> ALL_DISPENSATION_PLACES = Collections.unmodifiableList(Arrays.asList(HOSPITAL, PHARMACY));

   private final byte key;

   DispensationPlace(int aKey) {
      key = (byte) aKey;
   }

   public byte getKey() {
      return key;
   }

   public static List<DispensationPlace> getAllDispensationPlaces() {
      return ALL_DISPENSATION_PLACES;
   }

}
