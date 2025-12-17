Master Data Service
Deskripsi
Service untuk manajemen data master (produk, kategori, dll) menggunakan Spring Boot.

Requirements
Java 24

Maven 3.8+

PostgreSQL / MySQL (atau database lain sesuai konfigurasi)

Cara Menjalankan
1. Setup Database
sql
-- PostgreSQL
CREATE DATABASE master_data_db;
-- atau sesuaikan dengan konfigurasi di application.properties
2. Clone & Build
bash
git clone [repository-url]
cd master-data-service
mvn clean install
3. Konfigurasi
Edit src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/master_data_db
spring.datasource.username=postgres
spring.datasource.password=password
4. Jalankan Aplikasi
bash
mvn spring-boot:run
Troubleshooting
Pastikan database sudah berjala
