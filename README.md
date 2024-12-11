# E-Commerce Cart Flow Service 

## Description 🛒

This project is a backend service designed for managing shopping carts within an e-commerce platform. The service includes functionalities such as creating carts (with automatic ID generation), deleting carts manually, adding and editing items within a cart, retrieving detailed cart information via GET requests, and automatically removing inactive carts after 10 minutes.

The application follows a hexagonal architecture, reflected in a well-organized folder structure:

- **Infra**: contains controllers and repositories.
- **Application**: includes request and response as well as the use case implementations.
- **Domain**: contains business logic, such as error handling, domain models, and the storage interface.

## Tech Stack & Tools 💻

- **Java 17**: Make sure your system is equipped with Java 17 to run the application.
- **Maven**: The project relies on Maven for building and managing dependencies.
- **Postman**:  Used to test and interact with the API endpoints.

## Project Setup 📍

### Clone the Repository

- **Clone or fork this repository to your local machine**: `https://github.com/SolTuripe/cart-flow-service-onebox.git`

### Build the Project

Utilize Maven to build the project `mvn clean install`

### Run the Application

When the application runs, it starts on port 8080.

## Technical Implementation ⚙️

### ID Creation

Cart IDs are generated using a method that produces randomly unique values.

### Cart Expiration

Carts are removed automatically if they remain inactive for 10 minutes. An internal scheduler handles the process by identifying and clearing out inactive carts.

After the 10-minute threshold is reached, the cart is deleted, and an error message is logged to the console:

`Delete Cart After TTL Response: {message = 'Cart with id: 8ea1a9b7-5e55-4209-aff8-ac8744968700 deleted after: 600 sec', ttl in milliseconds = 600000}`

### Data Storage

Carts are maintained in memory using a thread-safe data structure, specifically a `ConcurrentHashMap`. Since no database engine is utilized, all data is erased when the application restarts

## Test with Postman ✅

### Endpoints

- **Create a cart**: `POST /carts/`
- **Delete a cart**: `DELETE /carts/{id}`
- **Get cart**: `GET /carts/{id}`
- **Update cart with new products**: `PUT /carts/{id}`

1. **Create a Cart**
    - POST: `http://localhost:8080/carts/` \
   Request:
      ```json
      [
          {
              "id": 1,
              "description": "Party dress",
              "amount": 25.99
          },
          {
              "id": 2,
              "description": "Party shoes",
              "amount": 30.99
          },
          {
              "id": 3,
              "description": "Boots",
              "amount": 40.99
          }
      ]
      ```
    Response:
    ````
   {
    "code": 200,
    "cartMaskId": "fc94a339-c1ec-4ef2-a1f8-a13ba9efa4a9",
    "message": "Cart created successfully",
    "status": "OK"
   }
   ````
       
2. **Delete a Cart**
   - DELETE: `http://localhost:8080/carts/{id}`
   
     Response
    ````
   {
    "code": 200,
    "message": "Cart with maskId = fc94a339-c1ec-4ef2-a1f8-a13ba9efa4a9 was successfully deleted",
    "status": "OK"
   }
   ````
        
3. **Get Cart Information**
   - GET: `http://localhost:8080/carts/{id}`
     
   Response
    ````
   {
    "cartId": "c08326b5-9c69-4e9d-a494-29bf77fe4cb5",
    "items": [
        {
            "id": 1,
            "description": "Party dress",
            "amount": 25.99
        },
        {
            "id": 2,
            "description": "Party shoes",
            "amount": 30.99
        },
        {
            "id": 3,
            "description": "Boots",
            "amount": 40.99
        }
    ]
    ````
   
4. **Update a Cart**

    - PUT: `http://localhost:8080/carts/{id}` \
   Request:

      ```json
      [
          {
              "id": 4,
              "description": "Another party dress",
              "amount": 44.99
          }
      ]
      ```
      
   Response:
 ````
   {
    "cartId": "7dbe1293-0d3d-41b3-80dc-abf8f7900541",
    "items": [
        {
            "id": 1,
            "description": "Party dress",
            "amount": 25.99
        },
        {
            "id": 2,
            "description": "Party shoes",
            "amount": 30.99
        },
        {
            "id": 3,
            "description": "Boots",
            "amount": 40.99
        },
        {
            "id": 4,
            "description": "Another party dress",
            "amount": 44.99
        }
    ]
}
   ````

## Author 👩‍💻

In the project, two accounts appear as contributors, but both belong to me. While working on the project from my laptop, it seems that the Git configuration was linked to the account [Sol Merari](https://github.com/SolTuripe), and I didn't realize it at the time. However, I want to clarify that all the work has been done entirely by [Sol Turipe](https://github.com/SolTuripe)


