package model.domain;



public class KortingsFactory {

    public KortingsStrategy getKorting(String typeKorting){
        KortingEnum kortingEnum = KortingEnum.getKorting(typeKorting);
        String klasseNaam = kortingEnum.getKlasseNaam();
        KortingsStrategy kortingsStrategy = null;
        try {
            Class domainClass = Class.forName(klasseNaam);
            Object domainObject = domainClass.getConstructor();
            kortingsStrategy = (KortingsStrategy) domainObject;
        } catch (Exception e) {
            throw new DomainException("Geen korting met deze naam gevonden.");
        }
        return kortingsStrategy;
    }
}
