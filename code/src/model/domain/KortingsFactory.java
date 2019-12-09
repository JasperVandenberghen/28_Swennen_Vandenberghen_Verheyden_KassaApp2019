package model.domain;


import java.lang.reflect.Constructor;

public class KortingsFactory {

    public KortingsStrategy getKorting(String typeKorting){
        KortingEnum kortingEnum = KortingEnum.getKorting(typeKorting);
        String klasseNaam = kortingEnum.getKlasseNaam();
        KortingsStrategy kortingsStrategy = null;
        try {
            Class domainClass = Class.forName(klasseNaam);
            Constructor<?> c = domainClass.getConstructor();
            Object domainObject = c.newInstance();
            kortingsStrategy = (KortingsStrategy) domainObject;
        } catch (Exception e) {
            throw new DomainException("Geen korting met deze naam gevonden.");
        }
        return kortingsStrategy;
    }
}
