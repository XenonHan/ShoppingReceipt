# Shopping Receipt
This program prints the receipt according to the shopping cart record. The sales tax also included in the receipt based on different locations and product categories.   
<h2> To run the program </h2>
1. Import this maven project to your ide <br>
2. Run Main.java under "src\main\java". <br>
3. Input the transaction file name e.g. transaction_1

<h3> Run on command prompt </h3>
1. cd to the project directory <br>
2. Run below commands: 

```
mvn compile
```
```
mvn exec:java -D"exec.mainClass"="Main"
```
<span>3. Input the transaction file name e.g. transaction_1 </span>

<h3> Preview </h3>

<p float="left">
  <img src="https://user-images.githubusercontent.com/55251580/154285635-06084833-f29b-4730-b323-f6d8ba07972c.png" width="700" /> 
  <img src="https://user-images.githubusercontent.com/55251580/154286588-cddb0933-c7a5-476b-a1f8-efdf1f206f0d.png" width="700" />
  <img src="https://user-images.githubusercontent.com/55251580/154286872-25d69013-fb2a-49ee-a393-2f6d459448f6.png" width="700" />
</p>

<h2> To add a transaction </h2>
1. Create an Execl file(.xslx) <br>
2. Record the transaction info (location, product name, price, quantity) <br>
3. Store the file under directory "/src/main/java/transaction/"  
<h3> Preview </h3>

<p> 
  <img src="https://user-images.githubusercontent.com/55251580/154288079-324bf1e8-aa74-430c-82f7-284463d5a13d.png" width="500" />
</p>

<h2> To modify/add tax rate of a location</h2>
1. Go to directory "/src/main/java/config/"  <br>
2. Open tax.json and modify
<h3> Preview </h3>

<p> 
  <img src="https://user-images.githubusercontent.com/55251580/154289015-6d34de0c-6eb0-40dc-9811-de43b14f27fb.png" width="500" />
</p>

<h2> To modify/add product category</h2>
1. Go to directory "/src/main/java/config/"  <br>
2. Open categories.json and modify
<h3> Preview </h3>

<p> 
  <img src="https://user-images.githubusercontent.com/55251580/154289394-3fe1ceb4-a799-4e63-b1dc-cfaf72feaff7.png" width="500" />
</p>

<h2> Coverage Report </h2>
1. Go to the home directory of this project <br>
2. run 

``` 
mvn test 
```

<span> 3. Open the index.html under ./target/site/jacoco </span>
<h3> Preview </h3>

<p> 
  <img src="https://user-images.githubusercontent.com/55251580/154328661-2b4564b6-0c94-434a-b966-537106dcbe34.png" />
</p>


