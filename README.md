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

## Test with Postman ✅

### Endpoints

- **Create a cart**: `POST /carts/`
- **Delete a cart**: `DELETE /carts/{id}`
- **Get cart**: `GET /carts/{id}`
- **Update cart with new products**: `PUT /carts/{id}`

1. **Create a Cart**
    - POST: `http://localhost:8080/carts/`
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
    RESPONSE
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
   
     RESPONSE
    ````
   {
    "code": 200,
    "message": "Cart with maskId = fc94a339-c1ec-4ef2-a1f8-a13ba9efa4a9 was successfully deleted",
    "status": "OK"
   }
   ````
        
3. **Get Cart Information**
   - GET: `http://localhost:8080/carts/{id}`
     
   RESPONSE
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

    - PUT: `http://localhost:8080/carts/{id}`

      ```json
      [
          {
              "id": 1,
              "description": "New Product",
              "amount": 2.0
          }
      ]
      ```

   
## Technical Implementation ⚙️


