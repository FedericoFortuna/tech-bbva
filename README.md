<h1 align="center"><u><i>tech-bbva</i></u></h1>

<p align="center">
  API Rest for BBVA Interview
</p>

<div align="center">
  <span>Endpoints</span> •
  <span>Examples</span>
</div>

## Instructions to Run API
- Have/Install Java 8 onwards.
- You have to open a command line on jar directory.
- Execute `mvn clean package`
- You have to write this command to run the API
  - `java -jar bbva-1.1.1-SNAPSHOT.jar`


## Endpoints

- **`/v1/client/register`**: Endpoint to create a new Client.

    - **HTTP Method:** POST
    - **Parameters:** Body

        - `clientId` (Long): Client identification.
        - `name` (String): Client name.
        - `lastname` (String): Client lastname.
        - `street` (String): Client street.
        - `idAddress` (Integer): Address number.
        - `pc` (Integer): Postal code.
        - `telephone` (String): Client telephone.
        - `mobilphone` (String): Client mobil phone.
        - `tclient` (String): Client type.
        - `service` (BankServiceDto): Service bank belonging client.
          - `id` (Long): Id service.

    - **Success Answer:**

        - **HTTP Code:** 201
          - **Content:**

            ```
            Cliente registrado exitosamente
            ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "No existe el servicio bancario especificado"
              ```

- **`/v1/client`**: Endpoint to get all clients.

    - **HTTP Method:** GET
    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              [
                 {
                  "clientId": value,
                  "name": value,
                  "lastname": value,
                  "street": value, 
                  "idAddress": value,
                  "pc": value,
                  "telephone": value,
                  "mobilphone": value,
                  "tClient": value,
                  "service": {
                        "id": value,
                        "name": value
                    }
                 }
              ]
              ```

- **`/v1/client/{clientId}`**: Endpoint to update an specific client mobil phone.

    - **HTTP Method:** PATCH
    - **Parameters:**
        - PathVariable

            - `clientId` (String): Client identification.

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
                 {
                  "clientId": value,
                  "name": value,
                  "lastname": value,
                  "street": value, 
                  "idAddress": value,
                  "pc": value,
                  "telephone": value,
                  "mobilphone": value,
                  "tClient": value,
                  "service": {
                        "id": value,
                        "name": value
                    }
                 }
              ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "No existe el cliente especificado"
              ```

- **`/v1/client/service/{bankId}`**: Endpoint to get clients by bank service id.

    - **HTTP Method:** GET
    - **Parameters:**
        - PathVariable

            - `bankId` (String): Bank service id.

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              [
                 {
                  "clientId": value,
                  "name": value,
                  "lastname": value,
                  "street": value, 
                  "idAddress": value,
                  "pc": value,
                  "telephone": value,
                  "mobilphone": value,
                  "tClient": value,
                  "service": {
                        "id": value,
                        "name": value
                    }
                 }
              ]
              ```
    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "No existe el servicio bancario especificado"
              ```


## Examples

Here you can see some examples that how you can use the API endpoints. The examples below only are for endpoints with a request body.

### Example 1
- **`/v1/client/register`**:
  ```
  {
    "clientId": "4",
    "name": "carlos",
    "lastName": "lopez",
    "street": "beron",
    "idAddress": "2822",
    "pc": "11",
    "telephone": "22222",
    "mobilPhone": "2222",
    "service": {
        "id": "2"
    }
  }
  ```

### Example 2
- **`/v1/client/register`**:
  ```
  {
    "clientId": "504",
    "name": "carlos",
    "lastName": "lopez",
    "street": "beron",
    "idAddress": "2822",
    "pc": "11",
    "telephone": "22222",
    "mobilPhone": "2222"
  }
  ```

### Example 3
- **`/v1/client/504`**:
  ```
  11111
  ```

### Example 4
- **`/v1/client/service/2`**:

  

