package cn.kyrioscraft.web.util.http.arch;

import java.io.IOException;
import java.io.InputStream;

public interface Response {
    int getCode();

    String asString() throws IOException;

    byte[] asBytes() throws IOException;

    InputStream asStream() throws IOException;

    <T> T getNativeResponse();

}