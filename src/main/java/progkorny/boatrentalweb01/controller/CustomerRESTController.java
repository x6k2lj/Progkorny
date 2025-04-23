package progkorny.boatrentalweb01.controller;


import progkorny.boatrentalweb01.model.Customer;
import progkorny.boatrentalweb01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/customers")
public class CustomerRESTController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers()
    {return
            customerService
                    .getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }


    @PostMapping()
    public Customer putCustomerIntoDb(@RequestBody Customer cust) {
        Customer answer = customerService.insertOrUpdateCustomer(cust);
        return answer;
    }

    @DeleteMapping("/{id}")
    public String deleteCustomerById( @PathVariable int id) {
        if(customerService.deleteCustomerById(id)) return "customer with id " +id+ " is deleted"; else return "no customer is deleted";
    }

}

