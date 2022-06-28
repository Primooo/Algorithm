package Programmers;

import java.util.*;

//2021 카카오 채용연계형 인턴십
//문제 : 거리두기 확인하기
//레벨 : 2

public class KAKAO_Check_SafeDist {
    class Point {
        int x;
        int y;
        int step;

        Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    class Solution {
        private static int[] dx = {1, -1, 0, 0};
        private static int[] dy = {0, 0, 1, -1};

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            for(int i=0; i<places.length; i++) {
                boolean flag = false;
                for(int j=0; j<places[i].length; j++) {
                    for(int k=0; k<places[i][j].length(); k++) {
                        if(places[i][j].charAt(k) == 'P') {
                            Queue<Point> q = new LinkedList<Point>();
                            boolean[][] visited = new boolean[5][5];
                            q.add(new Point(j, k, 0));

                            while(!q.isEmpty()) {
                                Point p = q.poll();
                                if(p.step > 0 && places[i][p.x].charAt(p.y) == 'P') {
                                    flag = true;
                                    System.out.println(i + " " + j + " " + p.x + " " + p.y);
                                    break;
                                }

                                for(int l=0; l<4; l++) {
                                    int next_x = p.x + dx[l];
                                    int next_y = p.y + dy[l];
                                    if(next_x < 0 || next_x >= 5 || next_y < 0 || next_y >= 5) continue;
                                    if(places[i][next_x].charAt(next_y) != 'X'
                                            && visited[next_x][next_y] == false && p.step < 2) {
                                        visited[next_x][next_y] = true;
                                        q.add(new Point(next_x, next_y, p.step+1));
                                    }
                                }
                            }
                        }
                        if(flag == true) {
                            break;
                        }
                    }
                    if(flag == true) {
                        break;
                    }
                }
                if(flag) answer[i] = 0;
                else answer[i] = 1;
            }
            return answer;
        }
    }
}
