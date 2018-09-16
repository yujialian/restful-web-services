# Restful web service
A web service that support customer - supermarket  transactions.

### Environment:
Java: 1.8
Spring boot: 2.0.5
Maven: 3.3.9

### Preparasion
	1. Go to [Java SE - Downloads | Oracle Technology Network | Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html), download Java SE 8u181
	2. Add `export JAVA_HOME=$(/usr/libexec/java_home)` to you `.bash_profile` or `.bashrc`. (This is for Mac user, for windows, please google `Set up environment variable for java 1.8.`
	3. Source your change by `source .bash_profile`.

### Usage
1. Clone the project to your working folder by: `git clone git@github.com:yujialian/restful-web-services.git`
2. Load the project to your IDE (`eclipse` or `intellij`), run the application.
3. Download `Postman` app as REST client.

#### Create a customer:(POST http://localhost:8080/customers)

Request body:

```
{
    "name": "Grant",
    "birthDate": "1991-09-16T20:15:29.724+0000",
    "walletBalance": 10000.1
}
```

	**Note:** `name` and `walletBalance` is the needed field, will throw `MissingArgumentException` if either of them are missing.

#### Retrive all customer: (GET http://localhost:8080/customers)

#### Get customer by id: (GET http://localhost:8080/customers/id)

	**Note:** Will throw `NotFoundException` if no such customer for the id exist.

#### Charge customer: (POST  http://localhost:8080/customers-recharge)

Request body:

```
{
    "name": "Adam",
    "walletBalance": 100100.1
}
```

**Note:** * `name` and `walletBalance` is the needed field, will throw `MissingArgumentException` if either of them are missing. Will throw `NotFoundException` if no such customer for the name exist.

#### Get all products (GET http://localhost:8080/products)

#### Delete product by name (DELETE  http://localhost:8080/products)

Request body:

```
{ "productName": "Iphone" }
```

**Note:** Exception handling same as above.

#### Create product (POST http://localhost:8080/products)

Request body:

```
    {
        "productName": "Iphone",
        "price": 1999,
        "amount": 10
    }
```

#### Create order (POST http://localhost:8080/transactions) 

Request body:

```
{
    "customerName": "Adam",
    "products": {
    	"Iphone": 1,
    	"Macbook Pro": 2
    }
}
```

#### Get order by order id (GET http://localhost:8080/transactions/id)

#### Get all the orders (GET http://localhost:8080/transactions)

#### Get customer all the order by customer id (GET http://localhost:8080/customer-transactions/id)

#### Delete order by order id (DELETE http://localhost:8080/transactions/id)