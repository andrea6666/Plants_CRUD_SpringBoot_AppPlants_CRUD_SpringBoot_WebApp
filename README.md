## Plants CRUD SpringBoot App

### This is a Spring Boot CRUD application connected to MySQL database. It is a version of my desktop [Biljke](https://github.com/andrea6666/Biljke_Desktop_CRUD_DBconnection_App) application. It has all the operation as the desktop version just not the logic for the dates, it doesn't notifies when the next watering date is coming, but it shows the last watering date and it moves the date to the todays date if the button is pressed. All the rest functionalities are the same.

### Just a quick heads up on the Bootstrap user interface. It's intentionally designed to show functionalities rather than for polished aesthetics. The focus here is purely on functionality using Bootstrap as a quick solution.

### Requirements:
#### In order to use application you need to create MySQL database and to input your data in [application.properties](SpringbootCRUDPlants/src/main/resources/application.properties) file. The database table will be created and updated by it self.
#### All dependencies are already in [pom.xml](SpringbootCRUDPlants/pom.xml) file.

## Here you can see the application in use:
### Users can view a list of plants, create new plants, edit existing ones, and delete plants. Plants are associated with attributes such as name, category, watering days, instructions, and an image. The application validates form input and handles image data conversion. Watering actions can be updated, and instructions for each plant can be read. Data is persisted in a MySQL database, and the application uses Spring Data JPA for database interactions.

https://github.com/andrea6666/Plants_CRUD_SpringBoot_WebApp/assets/45822712/fd8d65fb-c915-488c-8b26-3c2dbb08827a



