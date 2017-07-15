package com.aldebaran.integration.order.model


open class CustomerResponse(firstName: String,
                            lastName: String,
                            phone: String,
                            email: String) :
        CustomerModel(firstName, lastName, phone, email) {

}