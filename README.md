# Dmoney API Automation with Rest Assured
### This project automates key functionalities of the Dmoney API using Rest Assured. It covers the essential transactions and user actions within the Dmoney system, simulating real-world scenarios such as customer creation, fund transfers, and balance checks.

# Project Overview
### This automation project is designed to interact with the Dmoney API collection, performing the following key actions:
- Admin Login: Authenticate as admin to obtain a token.
- Create Users: Create two customers and one agent.
- Fund Transfer to Agent: Transfer 2000 TK from the system account to the agent.
- Deposit to Customer: Agent deposits 1500 TK to a customer.
- Withdraw by Customer: Customer withdraws 500 TK to the agent.
- Send Money: Customer sends 500 TK to another customer.
- Merchant Payment: Customer pays 100 TK to a merchant.
- Balance Check: Verify the recipient customer's balance.


## Technologies Used
- Java
- Rest Assured
- TestNG
- Gradle
- Allure

## Prerequisites
- JDK LTS Version
- NODEJS LTS Version
- Gradle
- Allure ( If you want to generate an Allure report )

## Preferred IDE
- Intellij

## How to run?
### Execute the following steps :
- ``` git clone <repo_url> ```
- ``` Open the Termnal ```
- Write command ``` gradle clean test ```


## Output
### Gradle Report
![image](https://github.com/user-attachments/assets/f6588c93-42ed-4e63-8807-b5fd85cbb5df)

### Allure Report 
#### Overview Section
![image](https://github.com/user-attachments/assets/59a23abc-08cb-40ee-9e0f-aacc1b230814)

#### Behaviour Section
![image](https://github.com/user-attachments/assets/ad779981-91a8-46f9-adc5-d3ea798b8326)
