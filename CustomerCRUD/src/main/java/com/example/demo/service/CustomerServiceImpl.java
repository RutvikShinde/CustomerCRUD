package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicateAddressException;
import com.example.demo.exception.DuplicateIdException;
import com.example.demo.exception.DuplicateMobileException;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository cr;
	
	
	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
//		cr.save(customer);
		
		// Duplicate ID check
		if (cr.existsById(customer.getId())) {
			throw new DuplicateIdException("Duplicate ID Found");
		}

		// Duplicate Mobile check
		if (cr.findByMob(customer.getMob()) != null) {
			throw new DuplicateMobileException("Duplicate Mobile Number Found");
		}

		//  Duplicate Address check
		if (cr.findByAddress(customer.getAddress()) != null) {
			throw new DuplicateAddressException("Duplicate Address Found");
		}
		
		//email Validation
		if (!customer.getEmail().contains("@")) {
			throw new RuntimeException("Invalid Email Format");
		}
		
		//mob no Validation
		String mob = customer.getMob();
		if(mob.length() == 10) {
			
			if(mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3' || mob.charAt(0) == '4' || mob.charAt(0) == '5')
				throw new InvalidMobileNumber("Invalid Mobile Number");
			
			for(int i=0; i< mob.length(); i++) {
				if(!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobileNumber("Invalid Mobile Number");
			}
		} else
			throw new InvalidMobileNumber("Invalid Mobile Number");
		
		cr.save(customer); //insert
	}

	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll();// Select * from Customer
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
		
		//search
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);//delete
			return temp;	
		}
		
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);
	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			return temp;	
		}
		return null;
	}

	@Override
	public void addAll(List<Customer> list) {
		// TODO Auto-generated method stub
		cr.saveAll(list);
	}

	@Override
	public Customer findMob(String mob) {
		// TODO Auto-generated method stub
		return cr.findByMob(mob);
	}
	
	

}

