package com.turkcell.rentACar.business.constants.messages;

public class BusinessMessages {

    //Additional Service
    public static String SUCCESS_GET_ALL_ADDITIONAL_SERVICE = "Additional services listed successfully.";
    public static String SUCCESS_ADD_ADDITIONAL_SERVICE = "Additional service added successfully.";
    public static String SUCCESS_GET_BY_ID_ADDITIONAL_SERVICE = "Getting additional services by id.";
    public static String SUCCESS_DELETE_ADDITIONAL_SERVICE = "Additional service deleted successfully.";
    public static String ERROR_ADDITIONAL_SERVICE_DOES_NOT_EXISTS = "Additional service does not exist";



    public static String ERROR_ORDERED_ADDITIONAL_SERVICE_DOES_NOT_EXISTS = "Ordered additional service does not exist";


    //Brand
    public static String SUCCESS_GET_ALL_BRAND = "All brands are listed.";
    public static String SUCCESS_ADD_BRAND = "Brand added successfully.";
    public static String SUCCESS_GET_BY_ID_BRAND = "Getting brand by id";
    public static String SUCCESS_DELETE_BRAND = "Brand deleted successfully.";
    public static String SUCCESS_UPDATE_BRAND = "Brand updated successfully.";
    public static String ERROR_ADD_UPDATE_BRAND_SAME_NAME = "Brand name is exists";
    public static String ERROR_BRAND_DOES_NOT_EXISTS = "Brand does not exist";


    //Car Damage
    public static String SUCCESS_GET_ALL_CAR_DAMAGE = "Car damages listed successfully.";
    public static String SUCCESS_ADD_CAR_DAMAGE = "Car damage added successfully";
    public static String SUCCESS_GET_BY_CAR_ID_CAR_DAMAGE = "Car damages listed successfully for the car.";
    public static String SUCCESS_DELETE_CAR_DAMAGE = "Car damage deleted successfully.";
    public static String ERROR_CAR_DAMAGE_DOES_NOT_EXISTS = "Car damage does not exist";


    //Car Maintenance
    public static String SUCCESS_GET_ALL_CAR_MAINTENANCE = "Car maintenances listed successfully.";
    public static String SUCCESS_ADD_CAR_MAINTENANCE = "Car maintenance added successfully.";
    public static String SUCCESS_GET_BY_CAR_ID_CAR_MAINTENANCE = "Car maintenances listed successfully for the car.";
    public static String SUCCESS_DELETE_CAR_MAINTENANCE = "Car maintenance deleted successfully.";
    public static String SUCCESS_GET_BY_ID_CAR_MAINTENANCE = "Getting car maintenance by id";
    public static String SUCCESS_UPDATE_CAR_MAINTENANCE = "Car maintenance updated successfully.";
    public static String ERROR_RETURN_DATE_BEFORE_MAINTENANCE_DATE_CAR_MAINTENANCE = "Return date can not be before maintenance date!";
    public static String ERROR_MAINTENANCE_DATE_IN_RENT_DATES_CAR_MAINTENANCE = "Return date can not be before maintenance date!";
    public static String ERROR_RETURN_DATE_IN_RENT_DATE_CAR_MAINTENANCE = "Return date of the maintenance can not be in rent dates!";
    public static String ERROR_RENT_DATE_IN_MAINTENANCE_DATE_CAR_MAINTENANCE = "Maintenance dates can not include rent dates!";
    public static String ERROR_CAR_MAINTENANCE_DOES_NOT_EXISTS = "Car maintenance does not exist";


    //CAR
    public static String SUCCESS_GET_ALL_CAR = "Cars listed successfully.";
    public static String SUCCESS_ADD_CAR = "Car added successfully.";
    public static String SUCCESS_GET_BY_DAILY_PRICES_ID_CAR = "Cars listed successfully by daily price.";
    public static String SUCCESS_DELETE_CAR = "Car deleted successfully.";
    public static String SUCCESS_GET_BY_ID_CAR = "Getting car by id";
    public static String SUCCESS_UPDATE_CAR = "Car updated successfully.";
    public static String ERROR_CAR_DOES_NOT_EXISTS = "Car does not exist";


    //CAR RENT
    public static String SUCCESS_GET_ALL_CAR_RENT = "Car rents listed successfully.";
    public static String SUCCESS_ADD_CAR_RENT = "Car rent added successfully.";
    public static String SUCCESS_GET_BY_CAR_ID_CAR_RENT = "Cars listed successfully for the car.";
    public static String SUCCESS_DELETE_CAR_RENT = "Car rent deleted successfully.";
    public static String SUCCESS_GET_BY_ID_CAR_RENT = "Getting car rent by id";
    public static String SUCCESS_UPDATE_RETURN_CAR_RENT = "Car rent completed successfully.";
    public static String SUCCESS_UPDATE_CAR_RENT = "Car rent updated successfully.";
    public static String ERROR_RETURN_DATE_BEFORE_RENT_DATE_CAR_RENT = "Return date can not be before rent date!";
    public static String ERROR_RENT_DATE_IN_MAINTENANCE_DATES_CAR_RENT = "Rent date can not be in maintenance dates!";
    public static String ERROR_RETURN_DATE_IN_MAINTENANCE_DATE_CAR_RENT = "Return date of the rent can not be in maintenance dates!";
    public static String ERROR_MAINTENANCE_DATE_IN_RENT_DATE_CAR_RENT = "Rent dates can not include maintenance dates!";
    public static String ERROR_CAR_RENT_DOES_NOT_EXISTS = "Car rent does not exist";


    //CITY
    public static String SUCCESS_GET_ALL_CITY = "All cities are listed.";
    public static String SUCCESS_ADD_CITY = "City added successfully.";
    public static String SUCCESS_GET_BY_ID_CITY = "Getting city by id";
    public static String SUCCESS_DELETE_CITY = "City deleted successfully.";
    public static String ERROR_CITY_DOES_NOT_EXISTS = "City does not exist";


    //COLOR
    public static String SUCCESS_GET_ALL_COLOR = "All colors are listed.";
    public static String SUCCESS_ADD_COLOR = "Color added successfully.";
    public static String SUCCESS_GET_BY_ID_COLOR = "Getting color by id";
    public static String SUCCESS_DELETE_COLOR = "Color deleted successfully.";
    public static String SUCCESS_UPDATE_COLOR = "Color updated successfully.";
    public static String ERROR_ADD_UPDATE_COLOR_SAME_NAME = "Color name is exists";
    public static String ERROR_COLOR_DOES_NOT_EXISTS = "Color does not exist";


    //CUSTOMER
    public static String ERROR_CUSTOMER_DOES_NOT_EXISTS = "Corporate customer does not exist";


    //CORPORATE CUSTOMER
    public static String SUCCESS_GET_ALL_CORPORATE_CUSTOMER = "All corporate customers are listed.";
    public static String SUCCESS_ADD_CORPORATE_CUSTOMER = "Corporate customer added successfully.";
    public static String SUCCESS_GET_BY_ID_CORPORATE_CUSTOMER = "Getting corporate customer by id";
    public static String SUCCESS_DELETE_CORPORATE_CUSTOMER = "Corporate customer deleted successfully.";
    public static String ERROR_CORPORATE_CUSTOMER_DOES_NOT_EXISTS = "Corporate customer does not exist";


    //INDIVIDUAL CUSTOMER
    public static String SUCCESS_GET_ALL_INDIVIDUAL_CUSTOMER = "All individual customers are listed.";
    public static String SUCCESS_ADD_INDIVIDUAL_CUSTOMER = "Individual customer added successfully.";
    public static String SUCCESS_GET_BY_ID_INDIVIDUAL_CUSTOMER = "Getting individual customer by id";
    public static String SUCCESS_DELETE_INDIVIDUAL_CUSTOMER = "Individual customer deleted successfully.";
    public static String ERROR_INDIVIDUAL_CUSTOMER_DOES_NOT_EXISTS = "Individual customer does not exist";


    //POS
    public static String ERROR_POS_DOES_NOT_EXISTS = "Pos does not exists";


    //INVOICE
    public static String SUCCESS_GET_ALL_INVOICE = "All invoices are listed.";
    public static String SUCCESS_ADD_INVOICE = "Invoice added successfully.";
    public static String SUCCESS_GET_BY_ID_INVOICE = "Getting invoice by id";
    public static String SUCCESS_DELETE_INVOICE = "Invoice deleted successfully.";
    public static String ERROR_INVOICE_DOES_NOT_EXISTS = "Invoice does not exist";


    //PAYMENT
    public static String SUCCESS_GET_ALL_PAYMENT = "All payments are listed.";
    public static String SUCCESS_ADD_PAYMENT = "Payment added successfully.";
    public static String SUCCESS_PAYMENT = "Payment is successful.";
    public static String SUCCESS_GET_BY_ID_PAYMENT = "Getting payment by id";
    public static String SUCCESS_DELETE_PAYMENT = "Payment deleted successfully.";
    public static String ERROR_PAYMENT_DOES_NOT_EXISTS = "Payment does not exist";


    //CARD
    public static String SUCCESS_GET_ALL_CARD = "All cards are listed.";
    public static String SUCCESS_ADD_CARD = "Card added successfully.";
    public static String SUCCESS_GET_CUSTOMER_CARDS_CARD = "Getting customer cards";
    public static String SUCCESS_DELETE_CARD = "Card deleted successfully.";
    public static String ERROR_CARD_DOES_NOT_EXISTS = "Card does not exist";
}
