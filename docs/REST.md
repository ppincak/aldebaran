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
-X POST http://localhost:8080/aldebaran-order/customer
```

#### Read customer 

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-order/customer/:customerId
```

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
-X PUT http://localhost:8080/aldebaran-order/customer/:customerId
```

#### Delete customer

```bash
curl \
-H "Authorization: Bearer :jwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X DELETE http://localhost:8080/aldebaran-order/customer/:customerId
```
