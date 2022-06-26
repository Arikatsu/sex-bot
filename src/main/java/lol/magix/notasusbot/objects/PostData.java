package lol.magix.notasusbot.objects;

import java.util.List;

public final class PostData {
    public Data data;
    
    public static class Data {
        public List<Child> children;

        public String url;
    }

    public static class Child {
        public Data data;
    }
    
    public static class Posts {
        public List<PostData> posts;
    }
}