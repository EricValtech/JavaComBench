package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;

import java.util.Comparator;

import static java.util.Arrays.sort;

@MessagePackOrdinalEnum
public enum VatRate implements Keyed {
   NORMAL(2, 19.60f), //
   REDUCED(3, 7.00f), //
   SUPER_REDUCED(5, 2.10f), //
   HIGH(6, 33.33f); //

   public static VatRate[] SORTED_VALUES = VatRate.values();
   
   static {
      sort(SORTED_VALUES, new Comparator<VatRate>() {
         public int compare(VatRate vatRate, VatRate vatRate1) {
            return Float.valueOf(vatRate.getRate()).compareTo(vatRate1.getRate());
         }
      });      
   }
   
   private final byte key;
   private final float rate;

   VatRate(int key, float rate) {
      this.rate = rate;
      this.key = (byte) key;
   }
   
   public static VatRate fromNumericRate(Float rate) {
      if (rate == null)
         return null;

      for (VatRate vat : SORTED_VALUES) {
         if (rate <= vat.getRate()) {
            return vat;
         }
      }

      return null;
   }

   public byte getKey() {
      return key;
   }

   public float getRate() {
      return rate;
   }
}
