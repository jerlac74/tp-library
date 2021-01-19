package fr.training.spring.library.infra.mapper;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.infra.jpa.AddressJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperAddressJpa implements IMapperJpa<AddressJpa, Address> {
    @Override
    public Address mapToEntity(AddressJpa addressJpa) {
        if(addressJpa!=null) {
            return new Address(addressJpa.getNumber(), addressJpa.getStreet(), addressJpa.getPostalCode(), addressJpa.getCity());
        }
        return null;
    }

    @Override
    public AddressJpa mapToJpa(Address address) {
        if(address!=null){
            return  new AddressJpa(address.getNumber(), address.getStreet(), address.getPostalCode(), address.getCity());
        }
        return null;
    }

    @Override
    public List<Address> mapToEntityList(List<AddressJpa> addressJpas) {
        if(addressJpas!=null){
            List<Address> addresses = new ArrayList<>();
            for (AddressJpa addressJpa : addressJpas) {
                addresses.add(mapToEntity(addressJpa));
            }
            return addresses;
        }
        return null;
    }

    @Override
    public List<AddressJpa> mapToJpaList(List<Address> addresses) {
        if(addresses!=null){
            List<AddressJpa> addressJpas = new ArrayList<>();
            for (Address address : addresses) {
                addressJpas.add(mapToJpa(address));
            }
            return addressJpas;
        }
        return null;
    }
}
