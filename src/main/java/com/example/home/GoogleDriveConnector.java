package com.example.home;

import com.example.home.types.CustomGoogleDrive;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gdrive")
@ComponentScan("com.example.home.types.CustomGoogleDrive")
public class GoogleDriveConnector {
    private static final Logger log = LoggerFactory.getLogger(GoogleDriveConnector.class);

    private Drive service = CustomGoogleDrive.getDrive();

    @GetMapping("/connect")
    public void connectToGoogleDrive() throws IOException {
        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()//.setQ("mimeType = 'application/vnd.google-apps.folder'")
                .setQ("name = 'temperatures'")
                //.setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();

        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s\n", file.getName());
            }
        }


    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void saveTemperatureToFile() {
        log.info("Saving temperature to file");

    }
}
