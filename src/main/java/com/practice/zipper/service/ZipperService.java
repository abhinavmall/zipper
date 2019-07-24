package com.practice.zipper.service;

import com.practice.zipper.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class ZipperService {
    @Autowired
    Utilities utilities;

    public void compressFiles(
            String inputDir,
            String outputDir,
            int maxSize) {
        Path inputPath = Paths.get(inputDir);
        Path outputPath = Paths.get(outputDir);

        final Stack<String> stackOfDirs = new Stack<>();
        final Function<Stack<String>, String> createPath = stack -> stack.stream().collect(Collectors.joining("/")) + "/";
        try(final ZipOutputStream zipOut =
                    new ZipOutputStream(new FileOutputStream(outputPath.toFile()))) {
            Files.walkFileTree(inputPath, new FileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                    stackOfDirs.push(dir.toFile().getName());
                    final String path = createPath.apply(stackOfDirs);
                    final ZipEntry zipEntry = new ZipEntry(path);
                    zipOut.putNextEntry(zipEntry);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    final String path = String.format("%s%s", createPath.apply(stackOfDirs), file.toFile().getName());
                    final ZipEntry zipEntry = new ZipEntry(path);
                    zipOut.putNextEntry(zipEntry);
                    Files.copy(file, zipOut);
                    zipOut.closeEntry();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(final Path file, final IOException exc) throws IOException {
                    final StringWriter stringWriter = new StringWriter();
                    try(final PrintWriter printWriter = new PrintWriter(stringWriter)) {
                        exc.printStackTrace(printWriter);
                        System.err.printf("Failed visiting %s because of:\n %s\n",
                                file.toFile().getAbsolutePath(), printWriter.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
                    stackOfDirs.pop();
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decompressFiles(
            String inputDir,
            String outputDir) throws IOException {
        File destDir = new File(outputDir);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(inputDir));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = outputDir + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
