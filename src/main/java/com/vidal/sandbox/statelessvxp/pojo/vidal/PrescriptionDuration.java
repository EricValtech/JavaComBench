package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;


@MessagePackOrdinalEnum
public enum PrescriptionDuration implements Keyed {
   FOURTEEN_DAYS(1, 14),
   TWENTY_EIGHT_DAYS(2, 28),
   SEVEN_DAYS(3, 7),
   EIGHTY_FOUR_DAYS(5, 84),
   /**
    * 28 jours fractionn�s en 7 jours
    */
   TWENTY_EIGHT_SPLITTED_IN_SEVEN_DAYS(7, 28, 7),
   /**
    * 28 jours fractionn�s en 14 jours
    */
   TWENTY_EIGHT_SPLITTED_IN_FOURTEEN_DAYS(8, 28, 14),
   /**
    * 14 jours fractionn�s en 7 jours
    */
   FOURTEEN_SPLITTED_IN_SEVEN_DAYS(9, 14, 7),
   NOT_HOMOGENEOUS(0);

   private final byte key;

   private Integer maxDuration;
   private Integer splitDelivery;

   PrescriptionDuration(int key) {
      this(key, null, null);
   }

   PrescriptionDuration(int key, int maxDuration) {
      this(key, maxDuration, null);
   }

   PrescriptionDuration(int key, Integer maxDuration, Integer splitDelivery) {
      this.key = (byte) key;
      this.maxDuration = maxDuration;
      this.splitDelivery = splitDelivery;
   }


   /**
    * @return duration code, if applicable
    */
   public String getCode() {
      if (this == NOT_HOMOGENEOUS) {
         return "not homogeneous";
      }
      if (null == splitDelivery) {
         return Integer.toString(maxDuration);
      }
      return String.format("%d@%d", maxDuration, splitDelivery);
   }

   /**
    * @return prescription max duration, null if not applicable
    */
   public Integer getMaxDuration() {
      return maxDuration;
   }

   /**
    * @return split delivery, null if not applicable
    */
   public Integer getSplitDelivery() {
      return splitDelivery;
   }

public byte getKey() {
	return key;
}
}
