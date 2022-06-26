package lol.magix.notasusbot.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;

public final class HttpUtils {
    private HttpUtils() {
        // This class is not meant to be instantiated.
    }
    
    private static final OkHttpClient httpClient = new OkHttpClient();

    /**
     * Gets the content of a URL.
     * @param url The URL to get the content of.
     * @return The content of the URL.
     */
    @Nullable
    public static String get(String url) {
        var request = new Request.Builder()
                .url(url).build();
        try (var response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            return null;
        }
    }
}