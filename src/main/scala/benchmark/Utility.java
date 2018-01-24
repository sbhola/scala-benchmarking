package benchmark;

import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.xerial.snappy.Snappy;

/**
 * Created by sbhola on 5/17/2017.
 */
@State(Scope.Benchmark)
public class Utility {

    public static byte[] snappyCompress(String input) throws Exception {
        byte[] data = input.getBytes("UTF-8");
        return Snappy.compress(data);
    }

    public static byte[] gzipCompress(String input) throws IOException {
        byte[] data = input.getBytes("UTF-8");
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gzip = null;
        try {
            bos = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(bos);
            try {
                gzip.write(data);
            } finally {
                gzip.finish();
                gzip.close();
            }
            byte[] compressedMessage = bos.toByteArray();
            return compressedMessage;
        } finally {
            bos.close();
        }
    }

    public static String gzipDecompress(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }

    public static String snappyDecompress(byte[] compressedData) throws Exception {
        byte[] uncompressed = Snappy.uncompress(compressedData);
        return new String(uncompressed, "UTF-8");
    }

    public static byte[] base64Encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    public static byte[] base64Decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }
}

