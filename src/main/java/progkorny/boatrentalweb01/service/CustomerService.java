package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<Customer>(List.of(
            Customer.builder()
                    .id(1)
                    .firstName("Sándor")
                    .lastName("Vályi")
                    .countryCode("hu")
                    .driverLicenseNumber("abc")
                    .email("vs@nye.hu")
                    .phoneNumber("4612")
                    .build()));

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(int id) {
        Customer answer;
        try {answer = customers.get(id);}
        catch (IndexOutOfBoundsException e) {throw new NosuchEntityException("Customer with id " + id + " not found");}
        return answer;
    }

    public Customer insertOrUpdateCustomer(Customer customer) {
        int id = customer.getId();
        if (customers.stream()
                .anyMatch(c -> (c.getId() == id))) {
               customers.set(id, customer);
            } else customers.add(customer);

        return customer;
    }

    public boolean deleteCustomerById (int id) {
        Optional<Customer> customerToDelete =  customers
                .stream()
                .filter(c->(c.getId()==id))
                .findFirst();
        if (customerToDelete.isPresent()) {
            boolean answer = customers.remove(customerToDelete);
            return answer;
        }
        else throw new NosuchEntityException("THere is no customer to delete with id:" + id);

    }


}
