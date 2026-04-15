import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TotalGoals {

    private static final String BASE_URL =
            "https://jsonmock.hackerrank.com/api/football_matches";
    private static final Pattern TOTAL_PAGES_PATTERN =
            Pattern.compile("\"total_pages\"\\s*:\\s*(\\d+)");

    private TotalGoals() {
    }

    public static int totalGoals(String team, int year) throws IOException, InterruptedException {
        return sumGoals(team, year, "team1", "team1goals") + sumGoals(team, year, "team2", "team2goals");
    }

    private static int sumGoals(String team, int year, String teamParam, String goalsField)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedTeam = URLEncoder.encode(team, StandardCharsets.UTF_8);
        String firstPageUrl = String.format(
                "%s?year=%d&%s=%s&page=1",
                BASE_URL,
                year,
                teamParam,
                encodedTeam
        );

        String firstResponse = sendRequest(client, firstPageUrl);
        int totalPages = extractTotalPages(firstResponse);
        int goals = extractGoals(firstResponse, goalsField);

        for (int page = 2; page <= totalPages; page++) {
            String pageUrl = String.format(
                    "%s?year=%d&%s=%s&page=%d",
                    BASE_URL,
                    year,
                    teamParam,
                    encodedTeam,
                    page
            );
            goals += extractGoals(sendRequest(client, pageUrl), goalsField);
        }

        return goals;
    }

    private static String sendRequest(HttpClient client, String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private static int extractTotalPages(String responseBody) {
        Matcher matcher = TOTAL_PAGES_PATTERN.matcher(responseBody);
        if (!matcher.find()) {
            return 0;
        }
        return Integer.parseInt(matcher.group(1));
    }

    private static int extractGoals(String responseBody, String goalsField) {
        Pattern goalsPattern = Pattern.compile("\"" + goalsField + "\"\\s*:\\s*\"(\\d+)\"");
        Matcher matcher = goalsPattern.matcher(responseBody);
        int total = 0;

        while (matcher.find()) {
            total += Integer.parseInt(matcher.group(1));
        }

        return total;
    }
}
