package com.vidal.sandbox.statelessvxp.pojo.vidal;

import org.msgpack.annotation.MessagePackOrdinalEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



/**
 * values available as enum java type related to market status
 * 
 * @author Vidal Software
 */
@MessagePackOrdinalEnum
public enum MarketStatus implements Keyed {
   /**
    * Currently distributed.
    */
   AVAILABLE(0),
   /**
    * Recently deleted (not distributed anymore for less than one year).
    */
   DELETED(1),
   /**
    * Recently deleted by Pharmacovigilance (less than one year).
    */
   PHARMACO(2),
   /**
    * Newly available.
    */
   NEW(3),
   /**
    * Deleted (not distributed anymore for more than one year).
    */
   DELETED_ONEYEAR(4),
   /**
    * Deleted by Pharmacovigilance (more than one year).
    */
   PHARMACO_ONEYEAR(5);

   private static final List<MarketStatus> REMOVED_STATUSES = Collections.unmodifiableList(Arrays.asList(MarketStatus.DELETED, MarketStatus.DELETED_ONEYEAR, MarketStatus.PHARMACO,
         MarketStatus.PHARMACO_ONEYEAR));
   private static final List<MarketStatus> AVAILABLE_STATUSES = Collections.unmodifiableList(Arrays.asList(AVAILABLE, NEW));

   private static final List<MarketStatus> ALL_STATUSES = Collections.unmodifiableList(Arrays.asList(AVAILABLE, DELETED, PHARMACO, NEW, DELETED_ONEYEAR, PHARMACO_ONEYEAR));

   private final byte key;

   MarketStatus(int key) {
      this.key = (byte) key;
   }

   public byte getKey() {
      return key;
   }

   public static List<MarketStatus> getRemovedDrugs() {
      return REMOVED_STATUSES;
   }

   public static List<MarketStatus> getAvailableDrugs() {
      return AVAILABLE_STATUSES;
   }

   public static List<MarketStatus> getAllDrugs() {
      return ALL_STATUSES;
   }
}
