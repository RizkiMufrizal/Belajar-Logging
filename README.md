# Belajar-Logging

Aplikasi ini dibuat dalam rangka Belajar Logging

Teknologi yang digunakan :
* Spring Boot
* Spring Data JPA
* Spring MVC
* HSQLDB
* Jackson
* SLF4J
* JUnit
* DBUnit
* Tomcat 8
* Gradle

untuk menjalankannya jalankan perintah berikut :

    gradle clean build bootRun
    
aplikasi stand by pada `http://localhost:8888/BelajarLogging`

untuk melakukan testing aplikasi jalankan perintah berikut :

    gradle clean build --debug

hasil log terdapat di root project dengan nama file `LoggingAplikasi.log`

untuk databasenya sendiri akan di generate dan akan terbentuk di root project dan
berada di dalam folder `databaseHSQLDB`

hasil testing terdapat di dalam folder build/reports/tests
