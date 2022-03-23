package com.turkcell.rentACar.business.constants.messages;

public class BusinessMessages {

    //Additional Service
    public static String SUCCESS_GET_ALL_ADDITIONAL_SERVICE = "Additional services listed successfully.";
    public static String SUCCESS_ADD_ADDITIONAL_SERVICE = "Additional service added successfully.";
    public static String SUCCESS_GET_BY_ID_ADDITIONAL_SERVICE = "Getting additional services by id.";
    public static String SUCCESS_DELETE_ADDITIONAL_SERVICE = "Additional service deleted successfully.";


    //Brand
    public static String SUCCESS_GET_ALL_BRAND = "All brands are listed.";
    public static String SUCCESS_ADD_BRAND = "Brand added successfully.";
    public static String SUCCESS_GET_BY_ID_BRAND = "Getting brand by id";
    public static String SUCCESS_DELETE_BRAND = "Brand deleted successfully.";
    public static String SUCCESS_UPDATE_BRAND = "Brand updated successfully.";
    public static String ERROR_ADD_UPDATE_BRAND_SAME_NAME = "Brand name is exists";


    //Car Damage
    public static String SUCCESS_GET_ALL_CAR_DAMAGE = "Car damages listed successfully.";
    public static String SUCCESS_ADD_CAR_DAMAGE = "Car damage added successfully";
    public static String SUCCESS_GET_BY_CAR_ID_CAR_DAMAGE = "Car damages listed successfully for the car.";
    public static String SUCCESS_DELETE_CAR_DAMAGE = "Car damage deleted successfully.";


    //Car Maintenance
    public static String SUCCESS_GET_ALL_CAR_MAINNTENANCE = "Car maintenances listed successfully.";
    public static String SUCCESS_ADD_CAR_MAINNTENANCE = "Car maintenance added successfully.";
    public static String SUCCESS_GET_BY_CAR_ID_CAR_MAINNTENANCE = "Car maintenances listed successfully for the car.";
    public static String SUCCESS_DELETE_CAR_MAINNTENANCE = "Car maintenance deleted successfully.";
    public static String SUCCESS_GET_BY_ID_CAR_MAINNTENANCE = "Getting car maintenance by id";
    public static String SUCCESS_UPDATE_CAR_MAINNTENANCE = "Car maintenance updated successfully.";
    public static String ERROR_RETURN_DATE_BEFORE_MAINTENANCE_DATE_CAR_MAINNTENANCE = "Return date can not be before maintenance date!";
    public static String ERROR_MAINTENANCE_DATE_IN_RENT_DATES_CAR_MAINNTENANCE = "Return date can not be before maintenance date!";
    public static String ERROR_RETURN_DATE_IN_RENT_DATE_CAR_MAINNTENANCE = "Return date of the maintenance can not be in rent dates!";
    public static String ERROR_RENT_DATE_IN_MAINTENANCE_DATE_CAR_MAINNTENANCE = "Maintenance dates can not include rent dates!";


    //CAR
    public static String SUCCESS_GET_ALL_CAR = "Cars listed successfully.";
    public static String SUCCESS_ADD_CAR = "Car added successfully.";
    public static String SUCCESS_GET_BY_DAILY_PRICES_ID_CAR = "Cars listed successfully by daily price.";
    public static String SUCCESS_DELETE_CAR = "Car deleted successfully.";
    public static String SUCCESS_GET_BY_ID_CAR = "Getting car by id";
    public static String SUCCESS_UPDATE_CAR = "Car updated successfully.";


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


    //CITY
    public static String SUCCESS_GET_ALL_CITY = "All cities are listed.";
    public static String SUCCESS_ADD_CITY = "City added successfully.";
    public static String SUCCESS_GET_BY_ID_CITY = "Getting city by id";
    public static String SUCCESS_DELETE_CITY = "City deleted successfully.";


    //COLOR
    public static String SUCCESS_GET_ALL_COLOR = "All colors are listed.";
    public static String SUCCESS_ADD_COLOR = "Color added successfully.";
    public static String SUCCESS_GET_BY_ID_COLOR = "Getting color by id";
    public static String SUCCESS_DELETE_COLOR = "Color deleted successfully.";
    public static String SUCCESS_UPDATE_COLOR = "Color updated successfully.";
    public static String ERROR_ADD_UPDATE_COLOR_SAME_NAME = "Color name is exists";


    //CORPORATE CUSTOMER
    public static String SUCCESS_GET_ALL_CORPORATE_CUSTOMER = "All corporate customers are listed.";
    public static String SUCCESS_ADD_CORPORATE_CUSTOMER = "Corporate customer added successfully.";
    public static String SUCCESS_GET_BY_ID_CORPORATE_CUSTOMER = "Getting corporate customer by id";
    public static String SUCCESS_DELETE_CORPORATE_CUSTOMER = "Corporate customer deleted successfully.";


    //INDIVIDUAL CUSTOMER
    public static String SUCCESS_GET_ALL_INDIVIDUAL_CUSTOMER = "All individual customers are listed.";
    public static String SUCCESS_ADD_INDIVIDUAL_CUSTOMER = "Individual customer added successfully.";
    public static String SUCCESS_GET_BY_ID_INDIVIDUAL_CUSTOMER = "Getting individual customer by id";
    public static String SUCCESS_DELETE_INDIVIDUAL_CUSTOMER = "Individual customer deleted successfully.";


    //POS
    public static String ERROR_POS_DOES_NOT_EXISTS = "Pos does not exists";


    //INVOICE
    public static String SUCCESS_GET_ALL_INVOICE = "All invoices are listed.";
    public static String SUCCESS_ADD_INVOICE = "Invoice added successfully.";
    public static String SUCCESS_GET_BY_ID_INVOICE = "Getting invoice by id";
    public static String SUCCESS_DELETE_INVOICE = "Invoice deleted successfully.";


    //PAYMENT
    public static String SUCCESS_GET_ALL_PAYMENT = "All payments are listed.";
    public static String SUCCESS_ADD_PAYMENT = "Payment added successfully.";
    public static String SUCCESS_GET_BY_ID_PAYMENT = "Getting payment by id";
    public static String SUCCESS_DELETE_PAYMENT = "Payment deleted successfully.";
}
