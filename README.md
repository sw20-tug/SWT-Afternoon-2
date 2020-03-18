# SWT: Afternoon 2
This is the repository for the team *SWT: Afternoon 2*

Technology:
Java 1.8, Typescript, Angular 8, Spring Boot, MySQL



Start and install data base: 

1. Download and install MySQL Workbench
*https://dev.mysql.com/downloads/file/?id=492434
2. Download and install XAMPP Server 
*https://www.apachefriends.org/xampp-files/7.4.3/xampp-windows-x64-7.4.3-0-VC15-installer.exe
username
3. Start Apache, MySQL and Tomcat server
4. Config  # password       = admin 
5. Push button Admin in XAMPP Controler Panel and create new data base
called swt-2020. From menu bar choose Privileges and localhost select
"Edit privileges" set password to "admin"
6. Open file with path C:\xampp\phpMyAdmin\config.inc.php and change password to "admin"
7. Import sql file in phpMyAdmin data interface
=======




How to start the project:
Backend part:
1. Download Java 1.8 and set the path for the JAVA variable ( https://www.oracle.com/java/technologies/javase-jdk8-downloads.html )
2. Download and install gradle 6.2.2 and configure the path ( https://docs.gradle.org/current/userguide/installation.html )
3. Clone the project
4. Start the gradle daemon by typing "gradle bootRun" inside of your terminal
Frontend part:
1. Install NodeJS
2. Navigate inside of SWT-Afternoon 2, to the Hotel folder and run "npm i"
3. Start the frontend by typing "ng serve --open"
