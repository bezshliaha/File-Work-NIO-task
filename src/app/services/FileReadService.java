package app.services;

import app.utils.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReadService {
    public String read(String fileName) throws IOException {
        Path filePath = Path.of(Constants.BASE_PATH_IN, fileName);
        return Files.readString(filePath);
    }
}