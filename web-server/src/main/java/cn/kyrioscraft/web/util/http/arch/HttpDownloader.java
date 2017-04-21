package cn.kyrioscraft.web.util.http.arch;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * http请求下载器,用户文件下载等操作
 */
public interface HttpDownloader<R> {

    HttpDownloader<R> get() throws IOException;

    HttpDownloader<R> post() throws IOException;

    R write(File file) throws IOException;

    R write(OutputStream outputStream) throws IOException;

}