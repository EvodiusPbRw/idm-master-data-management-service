Master Data Service
Deskripsi
Service untuk manajemen data master (produk, kategori, dll) menggunakan Spring Boot.

Requirements
Java 24

Maven 3.8+

PostgreSQL

Cara Menjalankan
1. Setup Database
sql
-- PostgreSQL
CREATE DATABASE idm-master-data-management;
-- atau sesuaikan dengan konfigurasi di application.properties
2. Clone & Build
bash
git clone [[repository-url]](https://github.com/EvodiusPbRw/idm-master-data-management-service)
cd master-data-service
mvn clean install
3. Konfigurasi
Edit src/main/resources/application.properties:

properties:
  spring.datasource.url=jdbc:postgresql://localhost:5432/idm-master-data-management
  spring.datasource.username=postgres
  spring.datasource.password=password

4. Jalankan Aplikasi
bash
mvn spring-boot:run
Troubleshooting
Pastikan database sudah berjala
