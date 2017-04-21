package cn.kyrioscraft.web.util.http.arch;

import org.apache.http.HttpMessage;

public interface Callback<C extends HttpMessage> {
    void accept(C message);
}
