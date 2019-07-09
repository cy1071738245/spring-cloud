package com.cy.joy.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QRCodeGenerator {

    public static void generate(String content, String filePath) throws WriterException, IOException {
        final int width = 200;
        final int height = 200;
        final String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        Map<EncodeHintType, Object> map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, map);
        Path path = FileSystems.getDefault().getPath(filePath,fileName+".jpg");
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg", path);
    }

}
