public class BowlingGame {

    static String str;

    private static int getCharValue(char c) {
        if (c == 'X')
            return 10;
        else if (c == '-')
            return 0;
        else
            return c - '0';
    }

    public int getBowlingScore(String bowlingCode) {
        str = bowlingCode;
        int[] position = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                index++;
            } else if (str.charAt(i) == 'X') {
                if (index < 10)
                    position[index] = sb.length();
                sb.append('X');
            } else {
                if (index < 10 && position[index] == -1) {
                    position[index] = sb.length();
                }
                sb.append(str.charAt(i));
            }
        }
        str = sb.toString();
        int score = 0;
        for (int i = 0; i < 10; i++) {
            int j = position[i];
            char ch = str.charAt(j);
            if (ch == 'X') {
                score += 10;
                score = StrikeAft(score, j);
            } else {
                int buffer = getCharValue(ch);
                score += buffer;
                if (str.charAt(j + 1) == '/') {
                    score += (10 - buffer);
                    score = SpareAft(score, j);
                } else {
                    score += getCharValue(str.charAt(j + 1));
                }
            }
        }
        return score;
    }

    private static int SpareAft(int score, int index) {
        score += getCharValue(str.charAt(index + 2));
        return score;
    }

    private static int StrikeAft(int score, int index) {
        int buffer;
        buffer = getCharValue(str.charAt(index + 1));
        score += buffer;
        if (str.charAt(index + 2) == '/') {
            score += (10 - buffer);
        } else {
            score += getCharValue(str.charAt(index + 2));
        }
        return score;
    }
}
