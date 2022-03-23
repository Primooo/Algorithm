package Programmers;

import java.util.*;

//2022 KAKAO BLIND RECRUITMENT
//제목 : 주차 요금 계산
//레벨 : 2
class KAKAO_PARKING_FEE {
    public int[] solution(int[] fees, String[] records) {
        ArrayList<String> carList = new ArrayList<String>();
        Map<String, int[]> carFee = new HashMap<String, int[]>();
        Map<String, Boolean> carIn = new HashMap<String, Boolean>();
        
        for(int i=0; i<records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i], " ");
            String time = st.nextToken();
            String carNum = st.nextToken();
            int hour = Integer.parseInt(time.substring(0, 2)) * 60;
            int min = Integer.parseInt(time.substring(3));
            
            if(!carIn.containsKey(carNum)) {
                carIn.put(carNum, true);
                int[] arr = {hour+min, 0};
                carFee.put(carNum, arr);
                carList.add(carNum);
            }
            else {
                if(carIn.get(carNum) == true) {
                    int carInTime = carFee.get(carNum)[0];
                    int accumulateTime = carFee.get(carNum)[1] + (hour+min) - carInTime;
                    int[] arr = {0, accumulateTime};
                    carFee.put(carNum, arr);
                    carIn.put(carNum, false);
                }
                else {
                    int accumulateTime = carFee.get(carNum)[1];
                    int[] arr = {hour+min, accumulateTime};
                    carFee.put(carNum, arr);
                    carIn.put(carNum, true);
                }
            }
        }
        
        Iterator<String> cars = carFee.keySet().iterator();
        while(cars.hasNext()) {
            String car = cars.next();
            if(carIn.get(car)) {
                int endTime = 23*60 + 59;
                int carInTime = carFee.get(car)[0];
                int accumulateTime = carFee.get(car)[1] + endTime - carInTime;
                int[] arr = {0, accumulateTime};
                carFee.put(car, arr);
            }
            int totalTime = carFee.get(car)[1];
            int totalFee = fees[1];
            System.out.println(car + " " + totalTime + " " + totalFee);
            if(totalTime > fees[0]) {
                totalTime -= fees[0];
                if(totalTime % fees[2] == 0) {
                    totalFee += totalTime/fees[2] * fees[3];
                }
                else {
                    totalFee += (totalTime/fees[2]+1) * fees[3];
                }
            }
            int[] resultArr = {0, totalFee};
            carFee.put(car, resultArr);
        }
        
        Collections.sort(carList);
        int[] answer = new int[carList.size()];
        for(int i=0; i<carList.size(); i++) {
            int fee = carFee.get(carList.get(i))[1];
            answer[i] = fee;
        }
        
        return answer;
    }
}
