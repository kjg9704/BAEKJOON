package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        boolean [] visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < M; i++){
            String [] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            list[start].add(end);
            list[end].add(start);
        }
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        visited[1] = true;
        int result = 0;
        while(!que.isEmpty()){
            int now = que.poll();
            for(int next : list[now]){
                if(!visited[next]){
                    que.add(next);
                    visited[next] = true;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
