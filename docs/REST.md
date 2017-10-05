# Rest documentation

Almost each request to both of the microservices request header called "X-API-KEY", which should contain current api key version.

__Current key__: aldebaran-2fa93c87d445e3cabc5


## REST API - Auth microservice:

*Some of the parameters in the request server as an example*

#### Create User

*Create new user*

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

*Authenticate user based on his credentials*

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

#### Token info 

*Get info from the given token*

```bash
curl \
-H "Authorization: Bearer someJwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-X GET http://localhost:8080/aldebaran-auth/oauth2/tokenInfo
```

#### Revoke token

*Revoke token by its JTI*

```bash
curl \
-H "Content-Type: application/json" \
-H "Authorization: Bearer someJwt" \
-H "X-API-KEY: aldebaran-2fa93c87d445e3cabc5" \
-d \
" 
{
	\"jtis\": [],	
}" \
-X POST http://localhost:8080/aldebaran-auth/security/token/revoke
```

