# Aplikasi Laporan Klaim

![image](https://github.com/user-attachments/assets/d19a3e9d-bb4c-4e20-9d88-48bdeab25cfe)

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- H2DB
- Thymeleaf
- SB2Admin Template
- FeignClient
- Slf4j Logger
- JUnit Service Test

## Aplikasi Laporan Klaim: 
- Mempunyai daftar laporan klaim yang sudah di kelompok kan berdasarkan `LOB` dan `Penyeban Klaim`.
- Mempunyai fitur untuk berbagi laporan data klaim ke Aplikasi Eksternal, integration-report
- Mempunyai log aktifitas informasi pengiriman data
- Telah dilakukan pengujian JUnit test berdasarkan service dari aplikasi
- Data yang sudah di inisialisasi ketika aplikasi pertama kali dijalankan


## Bagaimana cara menjalankan aplikasi
- Git clone
```
$git clone https://github.com/n0tx/claim-report.git
```
- Run Spring Boot
```
$cd claim-report
$pwd
/claim-report
$./mvnw spring-boot:run
```

### Fitur Kirim Data

Untuk dapat mengirimkan data laporan klaim maka Aplikasi Integration Report agar dijalankan juga

```
https://github.com/n0tx/integration-report
```

- Data klaim berhasil dikirim ke aplikasi eksternal (integration-report)

![image](https://github.com/user-attachments/assets/2ecf26cc-0e74-43b1-844a-37c92f76fd87)


- Data klaim berdasarkan `LOB` = `KUR, PEN` berhasil dibuat pada Aplikasi Integration Report

![image](https://github.com/user-attachments/assets/0cc05280-cf77-4dcb-b203-6b92dfc9dc47)


### Log Aktifitas Pengiriman Data

- Log ketika aplikasi (claim-report) mengirim data klaim ke aplikasi eksternal (integration-report) berhasil

![image](https://github.com/user-attachments/assets/b966454c-f396-4862-8990-188d6d2d86d9)

### Data yang sudah di inisialisasi

- Inisialisasi data tabel klaim dan tabel wilker

![image](https://github.com/user-attachments/assets/21df4b21-4ff1-4898-a8e8-22ffb0c52f48)


- Melihat data dari h2db console
```
http://localhost:8080/h2-ui
```

![image](https://github.com/user-attachments/assets/7a4d4f29-1f69-47c4-ac2d-af0f79750615)

![image](https://github.com/user-attachments/assets/dbdb5477-cd04-4405-8d85-870875cb9712)

  
### Pengujian Unit Testing

- Uji coba JUnit test

![image](https://github.com/user-attachments/assets/7a3f83bf-3e13-468f-8d9f-3f20788ec68e)


