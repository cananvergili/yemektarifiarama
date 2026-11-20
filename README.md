# Yemek Tarifleri Uygulaması

## Proje Özeti
TheMealDB API üzerinden yemek tarifleri arama, favorilere ekleme, favorileri JSON dosyasına kaydetme, haftalık PDF raporlama ve e-posta gönderimi yapabilen bir Java uygulamasıdır.

## Kullanılan Teknolojiler
- Java 17
- Spring Boot 3.4.5
- Maven
- JavaFX / Swing
- JUnit
- iText / Apache PDFBox
- JavaMail

## Kurulum ve Çalıştırma
1. Projeyi klonlayın.
2. Maven ile bağımlılıkları yükleyin: mvn clean install.
3. MainGui sınıfını çalıştırarak GUI'yi başlatın.
4. Tarif arayın, favorilere ekleyin, favorileri yönetin.

## API Uç Noktaları
- *GET* /api/tarifler/ara?isim=xxx — Tarif arama.
- *POST* /api/favoriler/ekle — Favoriye tarif ekleme.
- *POST* /api/favoriler/sil — Favoriden tarif silme.
- *GET* /api/favoriler/liste — Favori tarifleri listeleme.
- *POST* /api/favoriler/mailGonder — Favori tariflerden haftalık PDF oluşturup e-posta ile gönderme.

## Testler
- JUnit ile testler src/test/java klasöründe bulunmaktadır.
- Testleri çalıştırmak için: mvn test

