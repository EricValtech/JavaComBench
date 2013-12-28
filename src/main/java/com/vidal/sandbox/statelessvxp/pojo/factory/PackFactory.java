package com.vidal.sandbox.statelessvxp.pojo.factory;

import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;
import com.vidal.sandbox.statelessvxp.pojo.protbufgen.PackcontainerProt;
import com.vidal.sandbox.statelessvxp.pojo.vidal.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PackFactory  extends  PojoFactory<PackContainer, PackcontainerProt.Pack>{
    private final PackcontainerProt.Pack.Builder pBuilder= PackcontainerProt.Pack.newBuilder();


    @Override
    public Collection<PackContainer> create(int nbPojo) {
        Collection<PackContainer> res = new ArrayList<PackContainer>();
        PackContainer pc = new PackContainer();
        pc.getList().addAll(createPackList(nbPojo,100));
        res.add(pc);
        return res;
    }

    @Override
    public Collection<PackcontainerProt.Pack> createProtBuff(int nbPojo) {
        Collection<Pack> packList= PackFactory.createPackList(nbPojo,100);
        List<PackcontainerProt.Pack> res = new ArrayList<PackcontainerProt.Pack>();
        for (Pack currPack : packList) {
            pBuilder.clear();
            pBuilder.setId(currPack.getId());
            pBuilder.setName(currPack.getName());
            pBuilder.setActCode(PackcontainerProt.Pack.ActCode.valueOf(currPack.getActCode().ordinal()));
            pBuilder.setCip(currPack.getCip());
            pBuilder.setCip13(currPack.getCip13());
            pBuilder.setCis(currPack.getCis());
            pBuilder.setCommunityAgrement(currPack.getCommunityAgrement());
            pBuilder.setDispensationPlace(PackcontainerProt.Pack.DispensationPlace.valueOf(currPack.getDispensationPlace().ordinal()));
            pBuilder.setDose(currPack.getDose());
            pBuilder.setDoseUnit(currPack.getDoseUnit());
            pBuilder.setDrugInSport(currPack.getDrugInSport());
            pBuilder.setEan(currPack.getEan());
            pBuilder.setGenericType(PackcontainerProt.Pack.GenericType.valueOf(currPack.getGenericType().ordinal()));
            pBuilder.setList(PackcontainerProt.Pack.ListType.valueOf(currPack.getList().ordinal()));
            pBuilder.setManufacturerPrice(currPack.getManufacturerPrice());
            pBuilder.setMarketStatus(PackcontainerProt.Pack.MarketStatus.valueOf(currPack.getMarketStatus().ordinal()));
            pBuilder.setMaxPrescriptionDuration(PackcontainerProt.Pack.PrescriptionDuration.valueOf(currPack.getMaxPrescriptionDuration().ordinal()));
            pBuilder.setNarcoticPrescription( currPack.isNarcoticPrescription()) ;
            pBuilder.setOffMarketDate( currPack.getOffMarketDate().getTime());
            pBuilder.setOnMarketDate(currPack.getOnMarketDate().getTime());
            pBuilder.setOnFreeAccess(currPack.isOnFreeAccess());
            pBuilder.setOtc(currPack.isOtc());
            pBuilder.setPharmacistPrice( currPack.getPharmacistPrice());
            pBuilder.setPricePerDose( currPack.getPricePerDose());
            pBuilder.setProductId(currPack.getProductId());
            pBuilder.setPublicPrice(currPack.getPublicPrice());
            pBuilder.setRefundingBase(currPack.getRefundingBase());
            pBuilder.setRefundingRate((PackcontainerProt.Pack.RefundRate.valueOf(currPack.getRefundingRate().ordinal())));
            pBuilder.setSafetyAlert(currPack.getSafetyAlert());
            pBuilder.setShortName(currPack.getShortName());
            pBuilder.setTfr(currPack.getTfr());
            pBuilder.setType(PackcontainerProt.Pack.ProductType.valueOf(currPack.getType().ordinal()));
            pBuilder.setUcdPrice(currPack.getUcdPrice());
            pBuilder.setVatRate(PackcontainerProt.Pack.VatRate.valueOf(currPack.getVatRate().ordinal()));
            pBuilder.setVatRateValue(currPack.getVatRateValue());
            pBuilder.setWithoutPrescr( currPack.isWithoutPrescr());
            res.add(pBuilder.build());
        }

        return res;
    }

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
	
	public static Collection<Pack> createPackList(int nbPack,int startIndex) {
		PackContainer pc = new PackContainer();
    	for (int i = 0 ; i < nbPack; i++) {
    		pc.getList().add(PackFactory.createPack(startIndex + i));
    	}
    	return pc.getList();
		
	}


}
