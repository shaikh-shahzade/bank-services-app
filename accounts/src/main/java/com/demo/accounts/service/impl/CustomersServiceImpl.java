package com.demo.accounts.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.accounts.dto.AccountsDto;
import com.demo.accounts.dto.CardsDto;
import com.demo.accounts.dto.CustomerDetailsDto;
import com.demo.accounts.entity.Accounts;
import com.demo.accounts.entity.Customer;
import com.demo.accounts.exception.ResourceNotFoundException;
import com.demo.accounts.mapper.AccountsMapper;
import com.demo.accounts.mapper.CustomerMapper;
import com.demo.accounts.repository.AccountsRepository;
import com.demo.accounts.repository.CustomerRepository;
import com.demo.accounts.service.ICustomersService;
import com.demo.accounts.service.client.CardsFeignClient;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails( mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }


        return customerDetailsDto;

    }
}