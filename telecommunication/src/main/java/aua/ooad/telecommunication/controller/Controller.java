package aua.ooad.telecommunication.controller;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.Package;
import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;
import aua.ooad.telecommunication.entities.tariffs.Tariff;
import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.instances.PackageInstance;
import aua.ooad.telecommunication.service.Archive;
import aua.ooad.telecommunication.service.MobileService;
import aua.ooad.telecommunication.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Timestamp;
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
    public void init() {
        calls = new ArrayList<>();
        customers = new HashMap<>();
        Customer customer = new Customer();
        customer.setId("1");
        customer.setFirstName("John");
        customers.put(customer.getId(), customer);
    }

    private static class CreateCustomerRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String phoneNumber;
        public String passport;
        public String address;
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
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

    @GetMapping("/startBuyingMobileTariff/{customerId}")
    public List<MobileTariff> startBuyingMobileTariff(@PathVariable("customerId") String customerId, HttpServletRequest httpServletRequest) {
        Customer customer = customers.get(customerId);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("customer", customer);
        System.out.println(session.getAttribute("customer"));
        List<MobileTariff> tariffs = mobileService.getMobileTariffs();
        return tariffs;
    }


    @GetMapping("/buyMobileTariff/{tariffId}")
    public List<PhoneNumber> buyMobileTariff(@PathVariable("tariffId") Integer tariffId, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        MobileInstance mi = mobileService.buyMobileTariff(tariffId, customer);
        List<PhoneNumber> numbers = mobileService.getMobileNumbers();

        httpServletRequest.getSession().setAttribute("mobileInstance", mi);
        httpServletRequest.getSession().removeAttribute("customer");
        return numbers;
    }

    @GetMapping("/chooseMobileNumber/{number}")
    public double chooseMobileNumber(@PathVariable String number, HttpServletRequest httpServletRequest) {
        MobileInstance mi = (MobileInstance) httpServletRequest.getSession().getAttribute("mobileInstance");
        PhoneNumber phoneNumber = mobileService.getPhoneNumber(number);
        phoneNumber.setFree(false);
        mi.setNumber(phoneNumber);

        return mi.getTotalPrice();
    }

    @GetMapping("/finishBuyingMobileTariff/{bankingData}")
    public String finishBuyingMobileTariff(@PathVariable String bankingData, HttpServletRequest httpServletRequest) {
        MobileInstance mi = (MobileInstance) httpServletRequest.getSession().getAttribute("mobileInstance");
        archive.addPayment(mi);

        mobileService.save(mi);
        httpServletRequest.getSession().removeAttribute("mobileInstance");
        return mi.getId();
    }


    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-------Packages--------------


    @GetMapping("/startBuyingPackage/{customerId}")
    public List<Package> startBuyingPackage(@PathVariable("customerId") String customerId, HttpServletRequest httpServletRequest) {
        Customer customer = customers.get(customerId);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("customer", customer);
        System.out.println(session.getAttribute("customer"));
        List<Package> packages = packageService.getPackages();
        return packages;
    }

    private static class BuyPackageResponse {
        public List<PhoneNumber> numbers;
        public double totalPrice;
    }

    @GetMapping("/buyPackage/{packageId}/{registrationAddress}")
    public BuyPackageResponse buyPackage(@PathVariable("packageId") Integer packageId, @PathVariable("registrationAddress") String registrationAddress, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        PackageInstance pi = packageService.buyPackage(packageId, registrationAddress, customer);

        BuyPackageResponse buyPackageResponse = new BuyPackageResponse();

        if (pi.checkIfHasFixedTariff()) {
            List<PhoneNumber> numbers = packageService.getFixedNumbers();
            buyPackageResponse.numbers = numbers;
        } else {
            buyPackageResponse.totalPrice = pi.getTotalPrice();
        }

        httpServletRequest.getSession().setAttribute("packageInstance", pi);
        httpServletRequest.getSession().removeAttribute("customer");
        return buyPackageResponse;
    }

    @GetMapping("/addNumberToPackage/{number}")
    public double addNumberToPackage(@PathVariable String number, HttpServletRequest httpServletRequest) {
        PackageInstance pi = (PackageInstance) httpServletRequest.getSession().getAttribute("packageInstance");
        PhoneNumber phoneNumber = packageService.getPhoneNumber(number);
        phoneNumber.setFree(false);
        pi.setNumber(phoneNumber);

        return pi.getTotalPrice();
    }

    @GetMapping("/finishBuyingPackage/{bankingData}")
    public String finishBuyingPackage(@PathVariable String bankingData, HttpServletRequest httpServletRequest) {
        PackageInstance pi = (PackageInstance) httpServletRequest.getSession().getAttribute("packageInstance");
        archive.addPayment(pi);

        packageService.save(pi);
        httpServletRequest.getSession().removeAttribute("packageInstance");

        return pi.getId();
    }


    //-----------Calls-------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------
    //-----------------------------


    private static class StartCallRequest {
        public String fromNumber;
        public String toNumber;
        public Timestamp startTimestamp;
    }

    @PostMapping("/startCall")
    public void startCall(@RequestBody StartCallRequest startCallRequest) {
        String numberType = getNumberType(startCallRequest.fromNumber);
        if (numberType.equals("mobile")) {
            MobileInstance mi = mobileService.getMobileInstance(startCallRequest.fromNumber);
            Call call = new Call(mi, startCallRequest.toNumber, startCallRequest.startTimestamp);
            calls.add(call);
        } else if (numberType.equals("fixed")) {
            PackageInstance pi = packageService.getPackageInstance(startCallRequest.fromNumber);
            Call call = new Call(pi, startCallRequest.toNumber, startCallRequest.startTimestamp);
            calls.add(call);
        }
    }

    private static class EndCallRequest {
        public String fromNumber;
        public Timestamp endTimestamp;
    }

    @PostMapping("/endCall")
    public void endCall(@RequestBody EndCallRequest endCallRequest) {
        Call c = null;
        for (Call call : calls) {
            if (call.getFromNumber().equals(endCallRequest.fromNumber)) {
                c = call;
                break;
            }
        }

        c.setEndTimestamp(endCallRequest.endTimestamp);
        if (c.getType().equals("mobile")) {
            MobileInstance mi = mobileService.getMobileInstance(endCallRequest.fromNumber);
            mi.chargeForTalkedMinutes(c);
        } else if (c.getType().equals("fixed")) {
            PackageInstance pi = packageService.getPackageInstance(endCallRequest.fromNumber);
            pi.chargeForTalkedMinutes(c);
        }
    }

    private static class MakePaymentRequest{
        public String instanceId;
        public String instanceType;
        public double amount;
        public String bankingData;
    }

    @PostMapping("/makePayment")
    public void makePayment(@RequestBody MakePaymentRequest makePaymentRequest){
        if(makePaymentRequest.instanceType.equals("mobile")){
           MobileInstance mi = mobileService.findMobileInstance(makePaymentRequest.instanceId);
           archive.addPayment(mi, makePaymentRequest.amount);
           mi.addToBalance(makePaymentRequest.amount);
        }
        else if(makePaymentRequest.instanceType.equals("package")){
            PackageInstance pi = packageService.findPackageInstance(makePaymentRequest.instanceId);
            archive.addPayment(pi, makePaymentRequest.amount);
            pi.addToBalance(makePaymentRequest.amount);
        }
    }

    private static class UseInternetRequest{
        public String number;
        public double amountOfMB;
    }

    @PostMapping("/useInternet")
    public void useInternet(@RequestBody UseInternetRequest useInternetRequest){
        mobileService.useInternet(useInternetRequest.number, useInternetRequest.amountOfMB);

    }


    private static class GetInstancesResponse{
        public List<PackageInstance> packageInstances;
        public List<MobileInstance> mobileInstances;
    }
    @GetMapping("/getInstances/{customerId}/{serviceType}")
    public GetInstancesResponse getInstances(@PathVariable String customerId, @PathVariable("serviceType") String serviceType){
        Customer customer = customers.get(customerId);
        GetInstancesResponse getInstancesResponse = new GetInstancesResponse();

        if(serviceType.equals("mobile")){
            getInstancesResponse.mobileInstances = customer.getMobileInstances();
        } else if(serviceType.equals("package")){
            getInstancesResponse.packageInstances = customer.getPackageInstances();
        }

        return getInstancesResponse;

    }
    private String getNumberType(String number) {
        if (number.startsWith("060") || number.startsWith("010"))
            return "fixed";
        else
            return "mobile";
    }
}
