package com.project.FurnLand.Service;

import com.project.FurnLand.Entity.Address;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address makeAddressDefault(Long addressId, Long userId){

        // find address
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new BadRequestException(" address doesn't exist"));

        // check if address belongs to user

        if(!checkUserAddress(addressId, userId))throw new BadRequestException("address is not yours");

        // make old default address not default
        removeDefaultAddress(userId);

        // update  address to default
        address.setId(addressId);
        address.setDefault(Boolean.TRUE);
        Address rs = addressRepository.save(address);
        return  rs;
    }

    public void  removeDefaultAddress(Long userId){
        Address defaultAddress = addressRepository.getUserDefaultAddress(userId);
        defaultAddress.setId(defaultAddress.getId());
        defaultAddress.setDefault(Boolean.FALSE);
        Address exDefault = addressRepository.save(defaultAddress);
    }

    public  Boolean checkUserAddress(Long addressId, Long userId){
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new BadRequestException(" address doesn't exist"));
        Long check = address.getUserId();
        return Objects.equals(check, userId);

    }
}
