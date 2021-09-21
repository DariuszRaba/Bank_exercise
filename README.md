# Bank_exercise - restfull app uses external api of Bank NBP to get latest USD currency rate.

there are endpoints:

**http://localhost:8080/user/create/** <br/>
In order to create user you need to provide (name, surname, pesel) in request body as in the picture below.</br>
![useCreationForm](https://user-images.githubusercontent.com/57062542/134228200-e0106aa9-0719-4f80-bada-87bdd3ae1016.png)
We can create bank user Account with main Account in PLN and two subaccounts one in PLN and one in USD. <br/>
User must have at least 18 years old and app checks it with provided pesel number.<br/>

**http://localhost:8080/user/info/{pesel}**<br/>
Ex. http://localhost:8080/user/info/90060804123<br/>
We can get user info by providing pesel of user that we are interested in.<br/>
We get info about user and all related Accounts. <br/>
![info](https://user-images.githubusercontent.com/57062542/127008630-9cab3f60-f2d7-41cd-b1a4-8145cfdf7cec.png)<br/>


**http://localhost:8080/user/convert/{pesel}/{amount}/{from}/{convertTo}**<br/>
Ex. http://localhost:8080/user/convert/90060804123/100//pln/usd<br/>
We can convert cash from PLN to USD and and backwards.<br/>
