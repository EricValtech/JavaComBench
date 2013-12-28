package com.vidal.sandbox.statelessvxp.pojo.vidal;


import org.msgpack.annotation.MessagePackBeans;

import java.util.Date;





/**
 * The Pack bean represents a drug in form of package.<br>
 * You can use Pack objects in the {@link ProductService} class.<br>
 * Use {@link #getName} method to read the actual pack description.<br>
 *
 * @author Vidal Software
 * @see com.vidal.api.drug.ProductService
 * @see com.vidal.merlin.constant.MarketStatus
 * @see com.vidal.merlin.constant.RefundRate
 */
@MessagePackBeans
public class Pack extends IdedApiBean {
   /**
    * the pack CIS code.
    */
   private String cis;
   /**
    * the pack name.
    */
   private String name;
   /**
    * the pack short name (45 char max).
    */
   private String shortName;
   /**
    * the pack CIP code.
    */
   private String cip;
   /**
    * the market status.
    */
   private MarketStatus marketStatus; // Cannot be null
   /**
    * the public price.
    */
   private Float publicPrice;
   /**
    * the refunding base.
    */
   private Float refundingBase;
   /**
    * the refunding rate.
    */
   private RefundRate refundingRate;
   /**
    * the list type.
    */
   private ListType list;
   /**
    * the TFR flag.
    */
   private boolean tfr;
   /**
    * the dose.
    */
   private String dose;
   /**
    * the dose unit.
    */
   private String doseUnit;
   /**
    * the price per dose.
    */
   private Float pricePerDose;
   /**
    * the community agreement.
    */
   private boolean communityAgrement;
   /**
    * Can have security alert (via IAM etc.)
    */
   private boolean safetyAlert;
   /**
    * the product
    */
   private Integer productId;
   private GenericType genericType;
   private ProductType type;

   /* melusine attribute*/
   private String cip13;
   private String ean;
   private Float manufacturerPrice;
   private Float pharmacistPrice;
   private Float vatRateValue;
   private VatRate vatRate;
   private DispensationPlace dispensationPlace;
   private PrescriptionDuration maxPrescriptionDuration;
   private boolean narcoticPrescription;

   private ActCode actCode;

   private Float ucdPrice;

   private boolean otc;
   private boolean withoutPrescr;
   private boolean onFreeAccess;

   private Date onMarketDate;
   private Date offMarketDate;
   private boolean drugInSport;



   /**
    * Get the Ucd price this Pack.
    *
    * @return the Ucd price of this package.
    */
   public Float getUcdPrice() {
      return ucdPrice;
   }

   public void setUcdPrice(Float ucdPrice) {
      this.ucdPrice = ucdPrice;
   }

   public String getCip13() {
      return cip13;
   }

   public void setCip13(String cip13) {
      this.cip13 = cip13;
   }

   public String getEan() {
      return ean;
   }

   public void setEan(String ean) {
      this.ean = ean;
   }

   public Float getManufacturerPrice() {
      return manufacturerPrice;
   }

   public void setManufacturerPrice(Float manufacturerPrice) {
      this.manufacturerPrice = manufacturerPrice;
   }

   public Float getPharmacistPrice() {
      return pharmacistPrice;
   }

   public void setPharmacistPrice(Float pharmacistPrice) {
      this.pharmacistPrice = pharmacistPrice;
   }

   public Float getVatRateValue() {
      return vatRateValue;
   }

   public void setVatRateValue(Float vatRateValue) {
      this.vatRateValue = vatRateValue;
      this.vatRate = VatRate.fromNumericRate(vatRateValue);
   }

   public VatRate getVatRate() {
      return vatRate;
   }

   public void setVatRate(VatRate vatRate) {
      this.vatRate = vatRate;
   }

   public DispensationPlace getDispensationPlace() {
      return dispensationPlace;
   }

   public void setDispensationPlace(DispensationPlace dispensationPlace) {
      this.dispensationPlace = dispensationPlace;
   }

   public PrescriptionDuration getMaxPrescriptionDuration() {
      return maxPrescriptionDuration;
   }

   public void setMaxPrescriptionDuration(PrescriptionDuration maxPrescriptionDuration) {
      this.maxPrescriptionDuration = maxPrescriptionDuration;
   }

   public boolean isNarcoticPrescription() {
      return narcoticPrescription;
   }

   public void setNarcoticPrescription(boolean narcoticPrescription) {
      this.narcoticPrescription = narcoticPrescription;
   }

   public Pack() {
   }

   public Pack(Integer packId) {
      super(packId);
   }

   /**
    * Get the CIP code of this Pack.<br>
    * The CIP code of a Pack acts as its description.
    *
    * @return the CIP code of this package.
    */
   public String getCip() {
      return cip;
   }

   /**
    * Set the CIP code for this Pack.
    *
    * @param cip the CIP code of this Pack.
    */
   public void setCip(String cip) {
      this.cip = cip;
   }

   /**
    * Get the community agreement of this Pack.<br>
    * The community agreement of a Pack acts as its description.
    *
    * @return the community agreement of this Package.
    */
   public boolean getCommunityAgrement() {
      return communityAgrement;
   }

   /**
    * Set the community agreement code for this Pack.
    *
    * @param communityAgrement the community agreement of this Pack.
    */
   public void setCommunityAgrement(boolean communityAgrement) {
      this.communityAgrement = communityAgrement;
   }

   /**
    * Get the dose of this Pack.<br>
    * The dose of a Pack acts as its description.
    *
    * @return the dose of this Package.
    */
   public String getDose() {
      return dose;
   }

   /**
    * Set the dose for this Pack.
    *
    * @param dose the dose of this Pack.
    */
   public void setDose(String dose) {
      this.dose = dose;
   }

   /**
    * Get the dose unit of this Pack.<br>
    * The dose unit of a Pack acts as its description.
    *
    * @return the dose unit of this Package.
    */
   public String getDoseUnit() {
      return doseUnit;
   }

   /**
    * Set the dose unit for this Pack.
    *
    * @param doseUnit the dose unit of this Pack.
    */
   public void setDoseUnit(String doseUnit) {
      this.doseUnit = doseUnit;
   }

   /**
    * Get the list type of this Pack.<br>
    * The list type of a Pack acts as its description.
    *
    * @return the list type of this package.
    */
   public ListType getList() {
      return null == list ? ListType.NULL : list;
   }

   /**
    * Set the list type for this Pack.
    *
    * @param list the list type of this Pack.
    */
   public void setList(ListType list) {
      this.list = list;
   }

   /**
    * Get the market status of this Pack.<br>
    * The market status of a Pack acts as its description.
    *
    * @return the market status of this package.
    */
   public MarketStatus getMarketStatus() {
      return marketStatus;
   }

   /**
    * Set the market status for this Pack.
    *
    * @param marketStatus the market status of this Pack.
    */
   public void setMarketStatus(MarketStatus marketStatus) {
      this.marketStatus = marketStatus;
   }

   /**
    * Get the price per dose of this Pack.<br>
    * The price per dose of a Pack acts as its description.
    *
    * @return the price per dose of this Package.
    */
   public Float getPricePerDose() {
      return pricePerDose;
   }

   /**
    * Set the price per dose for this Pack.
    *
    * @param pricePerDose the price per dose of this Pack.
    */
   public void setPricePerDose(Float pricePerDose) {
      this.pricePerDose = pricePerDose;
   }

   /**
    * Get the public price of this Pack.<br>
    * The public price of a Pack acts as its description.
    *
    * @return the public price of this package.
    */
   public Float getPublicPrice() {
      return publicPrice;
   }

   /**
    * Set the public price for this Pack.
    *
    * @param publicPrice the public price of this Pack.
    */
   public void setPublicPrice(Float publicPrice) {
      this.publicPrice = publicPrice;
   }

   /**
    * Get the refunding base of this Pack.<br>
    * The refunding base of a <code>Pack</code> acts as its description.
    *
    * @return the refunding base of this package.
    */
   public Float getRefundingBase() {
      return refundingBase;
   }

   /**
    * Set the refunding base for this Pack.
    *
    * @param refundingBase the refunding base of this Pack.
    */
   public void setRefundingBase(Float refundingBase) {
      this.refundingBase = refundingBase;
   }

   /**
    * Get the refunding rate of this Pack.<br>
    * The refunding rate of a Pack acts as its description.
    *
    * @return the refunding rate of this Package.
    */
   public RefundRate getRefundingRate() {
      return null == refundingRate ? RefundRate.UNSPECIFIED : refundingRate;
   }

   /**
    * Set the refunding rate for this Pack.
    *
    * @param refundingRate the refunding rate of this Pack.
    */
   public void setRefundingRate(RefundRate refundingRate) {
      this.refundingRate = refundingRate;
   }

   /**
    * Get the TFR flag of this Pack.<br>
    * The TFR flag of a Pack acts as its description.
    *
    * @return the TFR flag of this package.
    */
   public boolean getTfr() {
      return tfr;
   }

   /**
    * Set the TFR flag for this Pack.
    *
    * @param tfr the TFR flag of this Pack.
    */
   public void setTfr(boolean tfr) {
      this.tfr = tfr;
   }

   /**
    * Get the CIS code of this Pack.<br>
    * The CIS code of a Pack acts as its description.
    *
    * @return the CIS code of this Package.
    */
   public String getCis() {
      return cis;
   }

   /**
    * Set the CIS code for this Pack.
    *
    * @param cis the CIS code of this Pack.
    */
   public void setCis(String cis) {
      this.cis = cis;
   }

   /**
    * Get the name of this Pack.<br>
    * The name of an Pack acts as its description.
    *
    * @return the name of this Package.
    */
   public String getName() {
      return name;
   }

   /**
    * Set the name of this Pack.<br>
    * <i>Setting this value is highly discouraged, since Pack are read only data</i>
    *
    * @param name the name of the Pack to set.
    */
   public void setName(String name) {
      this.name = name;
   }

   public void setProductId(Integer productId) {
      this.productId = productId;
   }

   public Integer getProductId() {
      return productId;
   }

   public GenericType getGenericType() {
      return null == genericType ? GenericType.NULL : genericType;
   }

   public void setGenericType(GenericType genericType) {
      this.genericType = genericType;
   }

   public String getShortName() {
      return shortName;
   }

   public void setShortName(String shortName) {
      this.shortName = shortName;
   }

   public void setType(ProductType type) {
      this.type = type;
   }

   public ProductType getType() {
      return type;
   }


   /*
    * @return true if the pack <b>can have</b> safety alert through other API (like IAM).
    */
   public boolean getSafetyAlert() {
      return safetyAlert;
   }

   public void setSafetyAlert(boolean safetyAlert) {
      this.safetyAlert = safetyAlert;
   }

   public ActCode getActCode() {
      return actCode;
   }


   public void setActCode(ActCode actCode) {
      this.actCode = actCode;
   }


   public boolean isOtc() {
      return otc;
   }

   public void setOtc(boolean otc) {
      this.otc = otc;
   }

   public boolean isWithoutPrescr() {
      return withoutPrescr;
   }

   public void setWithoutPrescr(boolean withoutPrescr) {
      this.withoutPrescr = withoutPrescr;
   }

   public boolean isOnFreeAccess() {
      return onFreeAccess;
   }

   public void setOnFreeAccess(boolean onFreeAccess) {
      this.onFreeAccess = onFreeAccess;
   }

   public Date getOnMarketDate() {
      return onMarketDate;
   }

   public void setOnMarketDate(Date onMarketDate) {
      this.onMarketDate = onMarketDate;
   }

   public Date getOffMarketDate() {
      return offMarketDate;
   }

   public void setOffMarketDate(Date offMarketDate) {
      this.offMarketDate = offMarketDate;
   }
   public boolean getDrugInSport() {
         return drugInSport;
      }

      public void setDrugInSport(boolean drugInSport) {
         this.drugInSport = drugInSport;
      }
}
