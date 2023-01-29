package june.String.신규아이디추천;

public class Solution {
    public String solution(String new_id) {
        String id = new_id;
        id = id.toLowerCase();
        id = id.replaceAll("[^-_.a-z0-9]", "");
        id = id.replaceAll("[.]{2,}", ".");
        id = id.replaceAll("^[.]|[.]$", "");
        if (id.isEmpty()) {
            id = "a";
        }
        if (id.length() >= 16) {
            id = id.substring(0, 15);
            id = id.replaceAll("^[.]|[.]$", "");
        }
        while (id.length() <= 2) {
            id = id + id.charAt(id.length() - 1);
        }

        return id;
    }
}
