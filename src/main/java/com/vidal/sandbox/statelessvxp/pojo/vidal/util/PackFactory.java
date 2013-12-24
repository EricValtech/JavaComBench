package com.vidal.sandbox.statelessvxp.pojo.vidal.util;

import java.util.Date;

import com.vidal.sandbox.statelessvxp.pojo.vidal.ActCode;
import com.vidal.sandbox.statelessvxp.pojo.vidal.DispensationPlace;
import com.vidal.sandbox.statelessvxp.pojo.vidal.GenericType;
import com.vidal.sandbox.statelessvxp.pojo.vidal.ListType;
import com.vidal.sandbox.statelessvxp.pojo.vidal.MarketStatus;
import com.vidal.sandbox.statelessvxp.pojo.vidal.Pack;
import com.vidal.sandbox.statelessvxp.pojo.vidal.PrescriptionDuration;
import com.vidal.sandbox.statelessvxp.pojo.vidal.ProductType;
import com.vidal.sandbox.statelessvxp.pojo.vidal.RefundRate;
import com.vidal.sandbox.statelessvxp.pojo.vidal.VatRate;

public class PackFactory {
	
	public static Pack createPack(int id) {
		Pack dto = new Pack();
		  dto.setId( id);
	      dto.setName("name"+id);
	      dto.setActCode(ActCode.AAD);
	      dto.setCip("Cip"+id);
	      dto.setCip13("Cip13"+id);
	      dto.setCis("CIS"+id);
	      dto.setCommunityAgrement(true);
	      dto.setDispensationPlace(DispensationPlace.HOSPITAL);
	      dto.setDose("DOSE");
	      dto.setDoseUnit("DOSEUNIT"); //10
	      dto.setDrugInSport(true);
	      dto.setEan("EAN");
	      dto.setGenericType(GenericType.GENERIC);
	      dto.setList(ListType.NARCOTIC);
	      dto.setManufacturerPrice(10.2f);
	      dto.setMarketStatus(MarketStatus.AVAILABLE);
	      dto.setMaxPrescriptionDuration(PrescriptionDuration.EIGHTY_FOUR_DAYS);
	      dto.setNarcoticPrescription(true);
	      dto.setOffMarketDate( new Date());
	      dto.setOnMarketDate( new Date()); //20
	      dto.setOnFreeAccess( false);

	       dto.setOtc(true);
	       dto.setPharmacistPrice( 10.3f);
	       dto.setPricePerDose( 10.4f);
	       dto.setProductId( 0);
	       dto.setPublicPrice( 100.1f);
	       dto.setRefundingBase( 30f);
	       dto.setRefundingRate( RefundRate._65);
	       dto.setSafetyAlert( true);
	       dto.setShortName("PACK SHORT NAME");           //30
	       dto.setTfr( false);
	       dto.setType( ProductType.ACCESSORY);
	       dto.setUcdPrice( 5.2f);
	       dto.setVatRate(VatRate.HIGH);
	       dto.setVatRateValue(20f);
	       dto.setWithoutPrescr( false);        //36
	       
	       
	       return dto;
	}
	
	public static PackContainer createPackList(int nbPack,int startIndex) {
		PackContainer pc = new PackContainer();
    	for (int i = 0 ; i < nbPack; i++) {
    		pc.getList().add(PackFactory.createPack(startIndex + i));
    	}
    	return pc;
		
	}

}
