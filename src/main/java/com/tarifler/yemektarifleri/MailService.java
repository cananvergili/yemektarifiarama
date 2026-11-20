package com.tarifler.yemektarifleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void pdfMailGonder(String alici, String konu, String mesaj, String pdfDosyaAdi) {
        MimeMessage mesajObj = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mesajObj, true);
            helper.setTo(alici);
            helper.setSubject(konu);
            helper.setText(mesaj);

            // PDF dosya yolunu güvenli şekilde oluştur (çalışma dizininde "reports/" klasörüne)
            Path path = Paths.get(System.getProperty("user.dir"), "reports", pdfDosyaAdi);
            File dosya = path.toFile();

            if (!dosya.exists()) {
                System.err.println("PDF dosyası bulunamadı: " + dosya.getAbsolutePath());
                return;
            }

            FileSystemResource file = new FileSystemResource(dosya);
            helper.addAttachment("Haftalik_Favori_Tarif_Onerisi.pdf", file);

            mailSender.send(mesajObj);
            System.out.println("Mail başarıyla gönderildi.");
        } catch (MessagingException e) {
            System.err.println("Mail gönderimi başarısız oldu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
