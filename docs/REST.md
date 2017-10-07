# Rest documentation

Almost each request to both of the microservices request header called "X-API-KEY", which should contain current api key version.

__Current key__: aldebaran-2fa93c87d445e3cabc5


## REST API - Auth microservice:

*Some of the parameters in the request server as an example*

#### Create User

```bash
curl \
-H "Content-Type: application/json" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"username\": \"sample_user\",
	\"email\": \"sample_user@sample.com\",
	\"password\": \"123456\",
	\"repeatPassword\": \"123456\"
}" \
-X POST http://localhost:8080/aldebaran-auth/users/register
```

#### Authenticate user

```bash
curl \
-H "Content-Type: application/json" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"username\": \"sample_user\",
	\"password\": \"123456\",
	\"grant_type\": \"password\"
}" \
-X POST http://localhost:8080/aldebaran-auth/oauth2/token
```

__Reponse__:
```json
{
  "accessToken" : "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbGRlYmFyYW4tYXV0aCIsImp0aSI6IjllOTM5ZWFjNWYyMTNlY2ViMDI3ZTdjZDJjZDcwMjdjIiwidWlkIjoyLCJhdWlkIjoiY2xpZW50SWQiLCJ1c3JuIjoic2FtcGxlX3VzZXIiLCJlbWwiOiJzYW1wbGVfdXNlckBzYW1wbGUuY29tIiwic2NwcyI6W10sInJscyI6W10sImV4cCI6MTUwNzIyNTI3MywiaWF0IjoxNTA3MjI1MjY2fQ.DOovfT9EgbuVY_rQJg6HS1wxr1yxopLr98FoQ-Kv6ysUdM1MgPZpvY_ho9mZYUDwcbET9llO7kOodH017RQqTA",
  "tokenType" : "BEARER",
  "scope" : [ ]
}
```

#### Token info 

*Get info from the given token*

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-auth/oauth2/tokenInfo
```

__Response__:
```json
{
  "userId" : 2,
  "username" : "sample_user",
  "email" : "sample_user@sample.com",
  "clientId" : "clientId",
  "expiresAt" : 1507225273,
  "roles" : [ ]
}
```

#### Revoke token

*Revoke token by its JTI*

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"jtis\": [],	
}" \
-X POST http://localhost:8080/aldebaran-auth/security/token/revoke
```

## REST API - Order microservice:

#### Create customer

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"firstName\": \"firstName\",
	\"lastName\": \"lastName\",
	\"email\": \"firstName.lastName@sample.com\",
	\"code\": \"9899\"
}" \
-X POST http://localhost:8080/aldebaran-order/customers
```

__Response__:

```json
{
    "id": "1",
    "firstName": "firstName",
    "lastName": "lastName",
    "phone": null,
    "email": "firstName.lastName@sample.com",
    "imageUrl": null,
    "timestamps": {
        "createdAt": "2017-10-06T14:40:39.309",
        "updatedAt": "2017-10-06T14:40:39.309"
    }
}
```

#### Read customer 

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/customers/:customerId
```

__Response__:

sames as [Create customer](#create-customer)

#### Update customer

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"firstName\": \"firstName\",	
	\"code\": \"9899\"
}" \
-X PUT http://localhost:8080/aldebaran-order/customers/:customerId
```

__Response__:

sames as [Create customer](#create-customer)

#### Delete customer

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X DELETE http://localhost:8080/aldebaran-order/customers/:customerId
```

#### Search customers

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/customers?search=email:li:Fir&page=0&limit=10&orderBy=id&orderDirection=asc
```

__Response__:

```json
{
    "totalPages": 1,
    "totalElements": 2,
    "data": [
        {
            "id": "1",
            "firstName": "firstName",
            "lastName": "lastName",
            "email": "firstName.lastName@sample.com",
            "phone": null,
            "imageUrl": null,
            "timestamps": {
                "createdAt": "2017-10-05T18:36:51",
                "updatedAt": "2017-10-05T18:36:51"
            }
        },
        {
            "id": "2",
            "firstName": "someFirstName",
            "lastName": "lastName",
            "email": "someFirstName.lastName@sample.com",
            "phone": null,
            "imageUrl": null,
            "timestamps": {
                "createdAt": "2017-10-06T14:40:39",
                "updatedAt": "2017-10-06T14:40:39"
            }
        }
    ]
}
```

#### Add photo to customer

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X POST http://localhost:8080/aldebaran-order/customers/:customerId/photos/:photoId
```

__Response__:

sames as [Create customer](#create-customer)

#### Create product

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"name\": \"some product\",
	\"description\": \"some product description\",
	\"price\": {
	    \"preTax\": 7.15,
	    \"afterTax\": 18.75
	},
	\"code\": \"9999\"
}" \
-X POST http://localhost:8080/aldebaran-order/products
```

__Response__:

```json
{
    "id": 1,
    "name": "some product",
    "description": "some product description",
    "price": {
        "preTax": "7.15",
        "afterTax": "18.85"
    },
    "code": "9999",
    "timestamps": {
        "createdAt": "2017-10-06T15:17:21.944",
        "updatedAt": "2017-10-06T15:17:21.944"
    },
    "images": []
}
```

#### Read product


```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/products/:productId
```
__Response__:

sames as [Create product](#create-product)

#### Update product 

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"name\": \"sample product name\"
}" \
-X PUT http://localhost:8080/aldebaran-order/products/:productId
```
__Response__:

sames as [Create product](#create-product)

#### Delete product

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X DELETE http://localhost:8080/aldebaran-order/products/:productId
```

#### Create order

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"products\": [
	    {
	        \"productId\": 1,
	        \"quantity\": 5
	    },
	    {
            \"productId\": 1,
            \"quantity\": 9
        }
	]
}" \
-X POST http://localhost:8080/aldebaran-order/customers/:customerId/orders
```

__Response__:

```json
{
    "orderId": 1,
    "products": [
        {
            "productId": 1,
            "name": "some product",
            "price": {
                "preTax": "7.15",
                "afterTax": "18.85"
            },
            "quantity": 14,
            "url": null
        }
    ],
    "timestamps": {
        "createdAt": "2017-10-07T08:07:05.036",
        "updatedAt": "2017-10-07T08:07:05.036"
    },
    "sum": {
        "preTax": "100.10",
        "afterTax": "263.90"
    }
}
```

#### Get orders

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/customers/:customerId/orders
```

__Response__:

```json
{
    "orders": [
        {
            "orderId": 1,
            "products": [
                {
                    "productId": 1,
                    "name": "some product",
                    "price": {
                        "preTax": "7.15",
                        "afterTax": "18.85"
                    },
                    "quantity": 5
                }
            ],
            "timestamps": {
                "createdAt": "2017-10-07T07:53:58",
                "updatedAt": "2017-10-07T07:53:58"
            },
            "sum": {
                "preTax": "35.75",
                "afterTax": "94.25"
            }
        }       
    ]
}
```

#### Update order

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"products\": [
	    {
	        \"productId\": 2,
	        \"quantity\": 5
	    }	   
	],
	\"updateMode\": \"RESET\"
}" \
-X PUT http://localhost:8080/aldebaran-order/customers/:customerId/orders/:orderId
```

#### Delete order

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X DELETE http://localhost:8080/aldebaran-order/customers/:customerId/orders/:orderId
```

#### Upload file

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-F "file=:pathToFile" \
-X POST http://localhost:8080/aldebaran-order/files/:fileName
```

__Response__:

```json
[
    {
        "id": 6,
        "filename": "fileName",
        "url": "/files/1",
        "fileLength": "1019143",
        "fileType": "IMAGE",
        "mediaType": "image/jpeg",
        "timestamps": {
            "createdAt": "2017-10-07T08:33:49.254",
            "updatedAt": "2017-10-07T08:33:49.254"
        }
    }
]
```

#### Download file

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/files/:fileId
```

#### Delete file

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X DELETE http://localhost:8080/aldebaran-order/files/:fileId
```