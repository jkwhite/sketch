package org.excelsi.sketch;


import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class Data {
    public static final String resource(final String url) {
        try {
            final URI uri = Data.class.getResource(url).toURI();
            final FileSystem fs = FileSystems.newFileSystem(uri, new HashMap<String,String>(){{ put("create", "true");}});
            return new String(Files.readAllBytes(Paths.get(uri)), "UTF-8");
        }
        catch(Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Data() {}
}
