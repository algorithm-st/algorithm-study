public int[] solution(int m, int n, int startX, int startY, int[][] balls) {

        int[] answer = new int[balls.length];
        // ball 0 x, ball 1 y
        int count = 0;
        for(int [] ball : balls){
        int tempAnswer = Integer.MAX_VALUE;
        int gapX = -1;
        int gapY = -1;
        // xMin 기준
        gapX = Math.abs(ball[0] - startX);
        gapY = Math.abs(0- ball[1]) + Math.abs(0 - startY);
        if(gapX!=0 || (gapX == 0 && startY<ball[1])) tempAnswer = Math.min(tempAnswer, gapX*gapX + gapY*gapY);
        // xMax
        gapX = Math.abs(ball[0] - startX);
        gapY = Math.abs(n - ball[1]) + Math.abs(n- startY);
        if(gapX != 0 || (gapX == 0 && startY > ball[1])) tempAnswer = Math.min(tempAnswer, gapX*gapX + gapY*gapY);
        // yMin
        gapY = Math.abs(ball[1] - startY);
        gapX = Math.abs(0 - ball[0]) + Math.abs(0 - startX);
        if(gapY != 0 || (gapY == 0 && startX < ball[0])) tempAnswer = Math.min(tempAnswer, gapX*gapX + gapY*gapY);
        // yMax
        gapY = Math.abs(ball[1] - startY);
        gapX = Math.abs(m - ball[0]) + Math.abs(m - startX);
        if(gapY != 0 || (gapY ==0 && startX > ball[0])) tempAnswer = Math.min(tempAnswer, gapX*gapX + gapY*gapY);
        answer[count++] = tempAnswer;
        }
        return answer;
        }