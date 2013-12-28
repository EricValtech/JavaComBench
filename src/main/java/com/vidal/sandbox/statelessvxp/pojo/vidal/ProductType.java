package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



/**
 * values available as enum java type related to product type
 *
 * @author Vidal Software
 */
@MessagePackOrdinalEnum
public enum ProductType implements Keyed {
   VIDAL(0), //
   DIETETIC(2), //
   VETERINARY(3), //
   NON_PHARMACEUTICAL(4), //
   ACCESSORY(5), //
   MISCELLANEOUS(6), //
   HOMEOPATHIC(7), //
   BALNEOLOGY(8); // TODO Validate

   private final byte key;

   public static final List<ProductType> ALL = Collections.unmodifiableList(Arrays.asList(VIDAL, DIETETIC, VETERINARY, NON_PHARMACEUTICAL, ACCESSORY, MISCELLANEOUS, HOMEOPATHIC, BALNEOLOGY));


   ProductType(int key) {
      this.key = (byte) key;
   }

   public byte getKey() {
      return this.key;
   }
}
