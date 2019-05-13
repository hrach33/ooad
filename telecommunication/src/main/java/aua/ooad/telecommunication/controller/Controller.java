package aua.ooad.telecommunication.controller;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;
import aua.ooad.telecommunication.entities.tariffs.Tariff;
import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.service.Archive;
import aua.ooad.telecommunication.service.MobileService;
import aua.ooad.telecommunication.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class Controller {

    private List<Call> calls;
    private Map<String, Customer> customers;

    @Autowired
    private Archive archive;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private PackageService packageService;


    @PostConstruct
    public void init(){
        customers = new HashMap<>();
        Customer customer = new Customer();
        customer.setId("1");
        customer.setFirstName("John");
        customers.put(customer.getId(), customer);
    }

    private static class CreateCustomerRequest{
        public String firstName;
        public String lastName;
        public String email;
        public String phoneNumber;
        public String passport;
        public String address;
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                createCustomerRequest.firstName,
                createCustomerRequest.lastName,
                createCustomerRequest.email,
                createCustomerRequest.phoneNumber,
                createCustomerRequest.passport,
                createCustomerRequest.address);
        customers.put(customer.getId(), customer);

        return customer.getId();
    }

    @GetMapping("startBuyingMobileTariff/{customerId}")
    public List<MobileTariff> startBuyingMobileTariff(@PathVariable("customerId") String customerId, HttpServletRequest httpServletRequest){
        Customer customer = customers.get(customerId);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("customer", customer);
        System.out.println(session.getAttribute("customer"));
        List<MobileTariff> tariffs = mobileService.getMobileTariffs();
        return tariffs;
    }


    @GetMapping("buyMobileTariff/{tariffId}")
    public List<PhoneNumber> buyMobileTariff(@PathVariable("tariffId") Integer tariffId, HttpServletRequest httpServletRequest){
        Customer customer = (Customer)httpServletRequest.getSession().getAttribute("customer");
        MobileInstance mi = mobileService.buyMobileTariff(tariffId, customer);
        List<PhoneNumber> numbers = mobileService.getMobileNumbers();

        httpServletRequest.getSession().setAttribute("mobileInstance", mi);
        httpServletRequest.getSession().removeAttribute("customer");
        return numbers;
    }


}
